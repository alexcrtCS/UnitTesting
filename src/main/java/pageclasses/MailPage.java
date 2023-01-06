package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MailPage {
    private final By USER_ACCOUNT = By.cssSelector("a span.user-account__name");
    private final By LOGOUT_BTN = By.cssSelector("a[aria-label='Log out']");
    private final WebDriver driver;

    public MailPage(WebDriver driver) {
        this.driver = driver;
    }

    public LandingPage logout() {
        // perform logout
        driver.findElement(USER_ACCOUNT).click();
        driver.findElement(LOGOUT_BTN).click();
        // redirection to login page
        return new LandingPage(driver);
    }

    public String getUserAccountLabel() {
        return driver.findElement(USER_ACCOUNT).getText();
    }
}
