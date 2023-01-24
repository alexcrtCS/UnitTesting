import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageclasses.LandingPage;
import pageclasses.LoginPage;
import pageclasses.MailPage;
import util.SetupTest;

public class LogInOutTest extends SetupTest {
    private final String USERNAME = "seleniumtrainingcs";
    private final String PASSWORD = "Selenium23&*!#&";
    private LandingPage landingPage;
    private MailPage mailPage;

    @BeforeEach
    public void setup() throws InterruptedException {
        landingPage = new LandingPage();
        landingPage.openLandingPage();
        LoginPage loginPage = landingPage.clickSignIn();
        mailPage = loginPage.login(USERNAME, PASSWORD);
    }

    @Test
    @Epic("Yandex Mail - Account")
    @Story("Account Login")
    @Feature("Ability to Login")
    @AllureId("T1")
    @Description("Check if user can successfully login")
    public void loginTest() {
        Assertions.assertEquals(PASSWORD, mailPage.getUserAccountLabel());
    }

    @Test
    @Epic("Yandex Mail - Account")
    @Story("Account Logout")
    @Feature("Ability to Logout")
    @AllureId("T2")
    @Description("Check if user can successfully logout")
    public void logoutTest() {
        landingPage = mailPage.logout();
        Assertions.assertFalse(landingPage.isSignInDisplayed());
    }
}
