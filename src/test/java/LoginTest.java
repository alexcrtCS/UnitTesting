import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class LoginTest {
    private final String WEBPAGE = "https://mail.yandex.com/";
    private final String USERNAME = "seleniumtrainingcs";
    private final String PASSWORD = "SeleniumTest22&*!#";
    private WebDriver driver;

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
    }

    @BeforeEach
    void initChromeDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // try finding element for max 5s
    }

    @Test
    void loginToYandexMailTest() throws InterruptedException {
        driver.get(WEBPAGE);
        driver.findElement(By.cssSelector("[type='button']")).click();
        driver.findElement(By.id("passp-field-login")).sendKeys(USERNAME);
        driver.findElement(By.xpath("//*[@id='passp:sign-in']")).click();
        driver.findElement(By.id("passp-field-passwd")).sendKeys(PASSWORD);
        // Must pause before submitting details to prevent detection of Selenium usage
        Thread.sleep(5000); // at least 5s are required to pass, else Selenium is flagged as suspicious activity
        driver.findElement(By.id("passp:sign-in")).click();
        // Checking that Compose email button is displayed on page
        Assertions.assertTrue(driver.findElement(By.cssSelector("[aria-describedby='tooltip-0-1']")).isDisplayed());
    }

    @AfterEach
    void closeBrowser() {
        driver.close();
    }
}
