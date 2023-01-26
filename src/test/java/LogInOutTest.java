import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pageclasses.LandingPage;
import pageclasses.LoginPage;
import pageclasses.MailPage;
import util.BaseTest;

@Epic("Yandex Mail - Account")
@Story("Account Login & Logout")
public class LogInOutTest extends BaseTest {
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
    @Feature("Ability to Login")
    @AllureId("T1")
    @Description("Check if user can successfully login")
    public void loginTest() {
        Assertions.assertEquals(USERNAME, mailPage.getUserAccountLabel());
    }

    @Test
    @Feature("Ability to Logout")
    @AllureId("T2")
    @Description("Check if user can successfully logout")
    public void logoutTest() {
        landingPage = mailPage.logout();
        Assertions.assertTrue(landingPage.isSignInDisplayed());
    }
}
