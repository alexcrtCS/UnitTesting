package pageclasses;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By USER_FIELD = By.id("passp-field-login");
    private final By PASS_FIELD = By.id("passp-field-passwd");
    private final By SIGN_IN_BTN = By.xpath("//*[@id='passp:sign-in']");

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
        // redirection to mail page
        return new MailPage();
    }

    private void fillUserField(String username) {
        driver.findElement(USER_FIELD).sendKeys(username);
    }

    private void fillPasswordField(String password) {
        driver.findElement(PASS_FIELD).sendKeys(password);
    }

    private void clickSignInButton() {
        driver.findElement(SIGN_IN_BTN).click();
    }

}
