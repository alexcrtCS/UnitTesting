import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    private final String WEBPAGE = "https://demo.seleniumeasy.com/dynamic-data-loading-demo.html";
    private final By GET_BTN = By.id("save");
    private final By LOADING_DIV = By.id("loading");
    private final By USER_IMG = By.cssSelector("div#loading img[src$='.jpg']");
    private WebDriver driver;

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
    }

    @BeforeEach
    void initChromeDriver() {
        driver = new ChromeDriver();
        driver.get(WEBPAGE);
        driver.manage().window().maximize();
    }

    // Simple test for checking that user image is displayed
    @Test
    void UserImageTest() {
        driver.findElement(GET_BTN).click();
        // Explicit wait until image is displayed
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(USER_IMG));
        assertTrue(driver.findElement(USER_IMG).isDisplayed());
    }

    // Test checking that user profile elements are displayed
    @Test
    void UserProfileTest() {
        driver.findElement(GET_BTN).click();
        // Explicit waiter with custom condition that waits until 'loading' text disappears
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until((ExpectedCondition<Boolean>) driver -> {
                    assert driver != null;
                    return !driver.findElement(LOADING_DIV).getText().contains("loading");
                });
        // Checking profile image, first name text, last name text are present
        assertAll(
                () -> assertTrue(driver.findElement(USER_IMG).isDisplayed()),
                () -> assertTrue(driver.findElement(LOADING_DIV).getText().contains("First Name")),
                () -> assertTrue(driver.findElement(LOADING_DIV).getText().contains("Last Name"))
        );
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
