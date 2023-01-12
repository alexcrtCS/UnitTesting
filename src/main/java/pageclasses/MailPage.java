package pageclasses;

import org.openqa.selenium.By;

public class MailPage extends BasePage {
    private final By USER_ACCOUNT = By.cssSelector("a span.user-account__name");
    private final By LOGOUT_BTN = By.cssSelector("a[aria-label='Log out']");

    public MailPage() {
        super();
    }

    public LandingPage logout() {
        // perform logout
        driver.findElement(USER_ACCOUNT).click();
        driver.findElement(LOGOUT_BTN).click();
        // redirection to login page
        return new LandingPage();
    }

    public String getUserAccountLabel() {
        return driver.findElement(USER_ACCOUNT).getText();
    }
}
