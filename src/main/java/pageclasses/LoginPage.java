package pageclasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "passp-field-login")
    private WebElement userField;

    @FindBy(id = "passp-field-passwd")
    private WebElement passField;

    @FindBy(xpath = "//*[@id='passp:sign-in']")
    private WebElement signInBtn;

    public LoginPage() {
        super();
    }

    public MailPage login(String username, String password) throws InterruptedException {
        // perform login
        fillUserField(username);
        clickSignInButton();
        fillPasswordField(password);
        Thread.sleep(6000); // to prevent Selenium usage detection
        clickSignInButton();
        return new MailPage();
    }

    private void fillUserField(String username) {
        userField.sendKeys(username);
    }

    private void fillPasswordField(String password) {
        passField.sendKeys(password);
    }

    private void clickSignInButton() {
        signInBtn.click();
    }

}
