import org.openqa.selenium.By;

public class ByVariables {
    By loginButton = By.id("passp:sign-in");
    By loginInputField = By.name("login");
    By loginHeader = By.className("passp-title");
    By authenticationForm = By.tagName("form");
    By learnMoreLink = By.linkText("Learn more");
    By forgotCredentialsLink = By.partialLinkText("forgot");
    By facebookLoginButton = By.xpath("//button[@aria-label='Facebook']");
    By acceptCookiesButton = By.cssSelector("button[data-id='button-all']");
}
