package pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {
    private static final String WEBPAGE = "https://mail.yandex.com/";
    private final By MAIL_LOGIN = By.cssSelector("[type='button']");
    private final WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
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
        return new LoginPage(driver);
    }
}
