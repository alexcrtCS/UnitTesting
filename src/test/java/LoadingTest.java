import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class LoadingTest {
    private final String WEBPAGE = "https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html";
    private final By DOWNLOAD_BTN = By.id("cricle-btn");
    private final By LOAD_PERCENT = By.className("percenttext");
    private FluentWait<WebDriver> wait;
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
        // Creating Fluent Wait to handle timeout, polling frequency and exception
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);
    }

    // Test with built-in condition for waiter
    @Test
    void downloadLoadingTest() {
        driver.findElement(DOWNLOAD_BTN).click();
        // want to wait until download reaches 50%
        wait.until(ExpectedConditions.textToBe(LOAD_PERCENT, "50%"));
        driver.navigate().refresh();
        // testing that page was refreshed by checking load % is back at 0
        Assertions.assertEquals("0%", driver.findElement(LOAD_PERCENT).getText());
    }

    // Same test but with custom condition for waiter
    @Test
    void downloadLoadingCustomTest() {
        driver.findElement(DOWNLOAD_BTN).click();
        // if there was no similar built-in condition for waiter this way would build that condition
        wait.until((ExpectedCondition<Boolean>) driver -> {
            assert driver != null;
            return driver.findElement(LOAD_PERCENT).getText().equals("50%");
        });
        driver.navigate().refresh();
        Assertions.assertEquals("0%", driver.findElement(LOAD_PERCENT).getText());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
