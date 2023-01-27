package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.Objects;

import static helpers.Constants.*;

public class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(setupDriver(ENVIRONMENT, BROWSER));
        }
        return driver.get();
    }

    private static WebDriver setupDriver(String environment, String browser) {
        Capabilities capabilities = BrowserCapabilities.getBrowserCapabilities(browser);
        if (environment.equals("local")) {
            return getLocalInstance(browser);
        }
        return getRemoteInstance(REMOTE, capabilities);
    }

    @SneakyThrows
    private static RemoteWebDriver getRemoteInstance(String remote, Capabilities capabilities) {
        switch (remote) {
            case "saucelabs":
                return new RemoteWebDriver(new URL(SAUCE_URL), capabilities);
            case "grid":
                return new RemoteWebDriver(new URL(GRID_URL), capabilities);
        }
        return null;
    }

    public static WebDriver getLocalInstance(String browser) {
        Capabilities capabilities = BrowserCapabilities.getBrowserCapabilities(browser);
        switch (browser) {
            case "chrome":
                WebDriverManager.getInstance(ChromeDriver.class).setup();
                return new ChromeDriver((ChromeOptions) Objects.requireNonNull(capabilities));
            case "firefox":
                WebDriverManager.getInstance(FirefoxDriver.class).setup();
                return new FirefoxDriver((FirefoxOptions) Objects.requireNonNull(capabilities));
        }
        return null;
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
