import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ParameterizedLoginTest {
    private final String WEBPAGE = "https://mail.yandex.com/";
    private final By MAIL_LOGIN = By.cssSelector("[type='button']");
    private final By USER_FIELD = By.id("passp-field-login");
    private final By PASS_FIELD = By.id("passp-field-passwd");
    private final By SIGN_IN_BTN = By.xpath("//*[@id='passp:sign-in']");
    private final By USER_ACCOUNT = By.cssSelector("a span.user-account__name");
    private WebDriver driver;

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
    }

    @BeforeEach
    void initChromeDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // try finding element for max 5s
    }

    @ParameterizedTest
    @CsvSource({"seleniumtrainingcs, Selenium23&*!#", "seleniumtrainingcs2, SeleniumTest22&*!#"})
    void loginWithAccountsTest(String userId, String password) throws InterruptedException {
        driver.get(WEBPAGE);
        driver.findElement(MAIL_LOGIN).click();
        driver.findElement(USER_FIELD).sendKeys(userId);
        driver.findElement(SIGN_IN_BTN).click();
        driver.findElement(PASS_FIELD).sendKeys(password);
        // Must pause before submitting details to prevent detection of Selenium usage
        Thread.sleep(6000); // explicit waiter, else Selenium is flagged as suspicious activity
        driver.findElement(SIGN_IN_BTN).click();
        // Added Explicit Wait with custom polling frequency (max 10s at 2s intervals)
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(USER_ACCOUNT));
        // Checking that user account name is displayed on page
        Assertions.assertEquals(userId, driver.findElement(USER_ACCOUNT).getText());
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
