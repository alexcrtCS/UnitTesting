import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class EmployeeTest {
    private final String WEBPAGE = "https://demo.seleniumeasy.com/table-sort-search-demo.html";
    private final By ENTRY_SELECT = By.name("example_length");
    private final By NEXT_BTN = By.id("example_next");
    private final By TABLE_ROWS = By.xpath("//tbody/tr");
    private final By NAME_CELLS = By.xpath("//td[1]");
    private final By POSITION_CELLS = By.xpath("//td[2]");
    private final By OFFICE_CELLS = By.xpath("//td[3]");
    private final By AGE_CELLS = By.xpath("//td[4]");
    private final By SALARY_CELLS = By.xpath("//td[6]");

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

    // Test that takes min age and max salary as parameters
    @ParameterizedTest
    @CsvSource({"30, 400000"})
    void employeeTableTest(int minAge, int maxSalary) {
        List<Employee> employeeList = new ArrayList<>();
        Select select = new Select(driver.findElement(ENTRY_SELECT));
        select.selectByIndex(0);

        boolean enabled = true;
        while (enabled) {
            WebElement nextBtn = driver.findElement(NEXT_BTN);
            List<WebElement> rowList = driver.findElements(TABLE_ROWS);
            rowList.forEach(row -> {
                int actualAge = Integer.parseInt(row.findElement(AGE_CELLS).getText());
                // Have to take salary from data-order since can't convert text value $x,xxx/y to integer
                int actualSalary = Integer.parseInt(row.findElement(SALARY_CELLS).getAttribute("data-order"));
                // Checking age & salary against our test parameters
                if (actualAge > minAge && actualSalary <= maxSalary) {
                    String name = row.findElement(NAME_CELLS).getText();
                    String position = row.findElement(POSITION_CELLS).getText();
                    String office = row.findElement(OFFICE_CELLS).getText();
                    // Will take all relevant details and create new employee then add it to our list
                    Employee employee = new Employee(name, position, office);
                    employeeList.add(employee);
                }
            });
            if (!nextBtn.getAttribute("class").endsWith("disabled")) {
                // Clicking next to get the following 10 rows of our table
                nextBtn.click();
            } else {
                enabled = false;
            }
        }
        // Testing that after all the above, the list is not empty
        Assertions.assertFalse(employeeList.isEmpty());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
