package pageclasses;

import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = WebDriverSingleton.getDriver();
    }
}
