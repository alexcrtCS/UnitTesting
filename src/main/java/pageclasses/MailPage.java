package pageclasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.IOException;

public class MailPage extends BasePage {
    private static final String SCREENSHOT_PATH = "src/test/resources/mail.png";

    @FindBy(css = "a span.user-account__name")
    private WebElement userAccount;

    @FindBy(css = "a[aria-label='Log out']")
    private WebElement logoutBtn;

    public MailPage() {
        super();
    }

    public LandingPage logout() {
        userAccount.click();
        logoutBtn.click();
        return new LandingPage();
    }

    public void takeScreenshot() throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(SCREENSHOT_PATH));
    }

    public String getUserAccountLabel() {
        return userAccount.getText();
    }
}
