import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageclasses.LandingPage;
import pageclasses.LoginPage;
import pageclasses.MailPage;
import pageclasses.WebDriverSingleton;

public class LogInOutTest {
    private final String USERNAME = "seleniumtrainingcs";
    private final String PASSWORD = "Selenium23&*!#";
    private LandingPage landingPage;
    private MailPage mailPage;

    @BeforeEach
    public void setup() throws InterruptedException {
        // Common steps for all tests to avoid code duplication
        landingPage = new LandingPage();
        landingPage.openLandingPage();
        LoginPage loginPage = landingPage.clickSignIn();
        mailPage = loginPage.login(USERNAME, PASSWORD);
    }

    // Both tests in same class due to similar steps and feature under test
    @Test
    public void loginTest() {
        Assertions.assertEquals(USERNAME, mailPage.getUserAccountLabel());
    }

    @Test
    public void logoutTest() {
        landingPage = mailPage.logout();
        Assertions.assertTrue(landingPage.isSignInDisplayed());
    }

    @AfterEach
    public void closeBrowser() {
        WebDriverSingleton.tearDown();
    }
}
