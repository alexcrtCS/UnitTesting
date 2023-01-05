import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertTest {
    private static final String WEBPAGE = "https://demo.seleniumeasy.com/javascript-alert-box-demo.html";
    private final By ALERT_BTN = By.cssSelector("button[class='btn btn-default']");
    private final By CONFIRM_BTN = By.cssSelector("button[class='btn btn-default btn-lg']");
    private final By ALERT_RESULT = By.id("confirm-demo");
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

    @Test
    void jSAlertTest() {
        String expectedMessage = "I am an alert box!";
        driver.findElement(ALERT_BTN).click();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        alert.accept();
        Assertions.assertEquals(expectedMessage, alertMessage);
    }

    @Test
    void jSConfirmAcceptTest() {
        String expectedMessage = "You pressed OK!";
        driver.findElement(CONFIRM_BTN).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        String alertResult = driver.findElement(ALERT_RESULT).getText();
        Assertions.assertEquals(expectedMessage, alertResult);
    }

    @Test
    void jSConfirmDismissTest() {
        String expectedMessage = "You pressed Cancel!";
        driver.findElement(CONFIRM_BTN).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String alertResult = driver.findElement(ALERT_RESULT).getText();
        Assertions.assertEquals(expectedMessage, alertResult);
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
