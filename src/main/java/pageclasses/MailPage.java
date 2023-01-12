package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MailPage extends BasePage {
    @FindBy(css = "a span.user-account__name")
    private WebElement userAccount;

    @FindBy(css = "a[aria-label='Log out']")
    private WebElement logoutBtn;

    public MailPage() {
        super();
    }

    public LandingPage logout() {
        // perform logout
        userAccount.click();
        logoutBtn.click();
        // redirection to login page
        return new LandingPage();
    }

    public String getUserAccountLabel() {
        return userAccount.getText();
    }
}
