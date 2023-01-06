package pageclasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriverSingleton {
    private static volatile WebDriverSingleton webDriverSingleton; // volatile so it's always up-to-date
    private final WebDriver driver;

    private WebDriverSingleton() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // waiter before throwing error at page loading for 10s as it loads slowly
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    // synchronized method so that multiple threads don't try to access it simultaneously
    public static synchronized WebDriverSingleton getInstance() {
        if (webDriverSingleton == null) {
            webDriverSingleton = new WebDriverSingleton();
        }
        return webDriverSingleton;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
