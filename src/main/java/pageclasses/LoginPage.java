package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By USER_FIELD = By.id("passp-field-login");
    private final By PASS_FIELD = By.id("passp-field-passwd");
    private final By SIGN_IN_BTN = By.xpath("//*[@id='passp:sign-in']");
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public MailPage login(String username, String password) throws InterruptedException {
        // perform login
        fillUserField(username);
        clickSignInButton();
        fillPasswordField(password);
        Thread.sleep(6000); // to prevent Selenium usage detection
        clickSignInButton();
        // redirection to mail page
        return new MailPage(driver);
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
