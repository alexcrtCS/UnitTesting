package pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage() {
        this.driver = WebDriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
