import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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
    void JSAlertTest() {
        String expectedMessage = "I am an alert box!";
        driver.findElement(ALERT_BTN).click();
        try {
            Alert alert = driver.switchTo().alert();
            String alertMessage = alert.getText();
            alert.accept();
            Assertions.assertEquals(expectedMessage, alertMessage);
        } catch (NoAlertPresentException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void JSConfirmAcceptTest() {
        String expectedMessage = "You pressed OK!";
        driver.findElement(CONFIRM_BTN).click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            String alertResult = driver.findElement(ALERT_RESULT).getText();
            Assertions.assertEquals(expectedMessage, alertResult);
        } catch (NoAlertPresentException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void JSConfirmDismissTest() {
        String expectedMessage = "You pressed Cancel!";
        driver.findElement(CONFIRM_BTN).click();
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            String alertResult = driver.findElement(ALERT_RESULT).getText();
            Assertions.assertEquals(expectedMessage, alertResult);
        } catch (NoAlertPresentException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }
}
