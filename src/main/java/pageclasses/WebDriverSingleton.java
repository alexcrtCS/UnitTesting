package pageclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverSingleton {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            driver.set(setupDriver());
        }
        return driver.get();
    }

    private static WebDriver setupDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // waiter before throwing error at page loading for 10s as it loads slowly
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        return webDriver;
    }

    public static void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
