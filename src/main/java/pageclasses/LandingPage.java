package pageclasses;

import org.openqa.selenium.By;

public class LandingPage extends BasePage {
    private static final String WEBPAGE = "https://mail.yandex.com/";
    private final By MAIL_LOGIN = By.cssSelector("[type='button']");

    public LandingPage() {
        super();
    }

    public void openLandingPage() {
        driver.get(WEBPAGE);
    }

    public boolean isSignInDisplayed() {
        return driver.findElement(MAIL_LOGIN).isDisplayed();
    }

    public LoginPage clickSignIn() {
        // click button
        driver.findElement(MAIL_LOGIN).click();
        // redirection to login page
        return new LoginPage();
    }
}
