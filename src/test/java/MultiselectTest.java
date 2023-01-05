import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MultiselectTest {
    private static final String WEBPAGE = "https://demo.seleniumeasy.com/basic-select-dropdown-demo.html";
    private final By LIST = By.id("multi-select");
    private WebDriver driver;

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
    }

    @BeforeEach
    void initChromeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    void multiselectTest() {
        driver.get(WEBPAGE);
        Select list = new Select(driver.findElement(LIST));
        int size = list.getOptions().size();
        // Only if list supports multi-selection will go inside
        if (list.isMultiple()) {
            // Saving list of values of 3 random indices from multiselect list
            List<String> expectedSelectedOptions = new Random().ints(3, 0, size).boxed()
                    .map(i -> list.getOptions().get(i).getText()).collect(Collectors.toList());
            // Selecting options from multiselect list based on each value of our saved list
            expectedSelectedOptions.forEach(list::selectByValue);
            // Saving list of values of the currently selected options from multiselect list
            List<String> actualSelectedOptions = list.getAllSelectedOptions().stream()
                    .map(WebElement::getText).collect(Collectors.toList());
            // Checking if same values that were saved were selected
            Assertions.assertTrue(expectedSelectedOptions.containsAll(actualSelectedOptions));
        } else {
            // If this is executed, test is failed as list does not support multi-selection
            Assertions.fail("List does not support multi selection");
        }
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
