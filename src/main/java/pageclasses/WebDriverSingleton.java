package pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String USERNAME = "alexcrtCS";
    private static final String USER_KEY = "2fd5dab0-128a-45af-bd39-04a6d2de11b0";
    public static final String SAUCE_URL = "https://" + USERNAME + ":" + USER_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
    private static final String ENVIRONMENT = "chrome";

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(setupDriver());
        }
        return driver.get();
    }

    private static WebDriver setupDriver() {
        WebDriver webDriver = getRemoteInstance(ENVIRONMENT);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        return webDriver;
    }

    private static RemoteWebDriver getRemoteInstance(String environment) {
        URL url;
        try {
            url = new URL(SAUCE_URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> sauceOptions = new HashMap<>();

        switch (environment) {
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPlatformName("Windows 10");
                edgeOptions.setBrowserVersion("latest");
                sauceOptions.put("build", "Win 10, Edge version: latest");
                sauceOptions.put("name", "Login & Logout Test");
                edgeOptions.setCapability("sauce:options", sauceOptions);
                return new RemoteWebDriver(url, edgeOptions);
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPlatformName("Windows 8.1");
                firefoxOptions.setBrowserVersion("latest");
                sauceOptions.put("build", "Win 8.1, Firefox version: latest");
                sauceOptions.put("name", "Login & Logout Test");
                firefoxOptions.setCapability("sauce:options", sauceOptions);
                return new RemoteWebDriver(url, firefoxOptions);
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPlatformName("macOS 12");
                chromeOptions.setBrowserVersion("latest");
                sauceOptions.put("build", "MacOS, Chrome version: latest");
                sauceOptions.put("name", "Login & Logout Test");
                chromeOptions.setCapability("sauce:options", sauceOptions);
                return new RemoteWebDriver(url, chromeOptions);
        }
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
