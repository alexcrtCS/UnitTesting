package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageclasses.WebDriverSingleton;

@ExtendWith(FailedTestWatcher.class)
public class BaseTest {
    protected WebDriver driver;
    protected static String browserInformation;
    protected static byte[] screenshot;

    @BeforeEach
    public void setupDriver() {
        driver = WebDriverSingleton.getDriver();
    }

    @AfterEach
    public void closeBrowser() {
        browserInformation = getBrowserInformation();
        screenshot = getScreenshot();
        WebDriverSingleton.tearDown();
    }

    private String getBrowserInformation() {
        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = capabilities.getBrowserName();
        String browserVersion = capabilities.getBrowserVersion();
        return "Browser Name: " + browserName + ", Browser Version: " + browserVersion;
    }

    private byte[] getScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}

