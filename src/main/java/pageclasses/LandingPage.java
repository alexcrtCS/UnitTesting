package pageclasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends BasePage {
    private static final String WEBPAGE = "https://mail.yandex.com/";
    @FindBy(css = "[type='button']")
    private WebElement mailLogin;

    public LandingPage() {
        super();
    }

    public void openLandingPage() {
        driver.get(WEBPAGE);
    }

    public boolean isSignInDisplayed() {
        return mailLogin.isDisplayed();
    }

    public LoginPage clickSignIn() {
        // click button
        mailLogin.click();
        // redirection to login page
        return new LoginPage();
    }
}
