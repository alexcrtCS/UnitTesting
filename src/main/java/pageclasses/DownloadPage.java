package pageclasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class DownloadPage {
    private static final String DOWNLOAD_PATH = "C:\\Temp\\";
    private final String WEBPAGE = "http://the-internet.herokuapp.com/download";
    private By sampleDownloadLink = By.cssSelector("div.example a:nth-of-type(3)");
    private By fileDownloadLink = By.cssSelector("div.example a");
    private WebDriver driver;

    public DownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void accessWebpage() {
        driver.get(WEBPAGE);
    }

    public String getDownloadLink() {
        return driver.findElement(sampleDownloadLink).getAttribute("href");
    }

    public void downloadFile() {
        driver.findElement(fileDownloadLink).click();
    }

    public boolean isFileDownloaded() {
        File downloads = new File(DOWNLOAD_PATH);
        String[] files = downloads.list();
        // check if downloads is directory (!= null) and if there are any files in it (length > 0)
        if (files != null && files.length > 0) {
            // have to delete files so tests fail if no new files were downloaded at each execution
            try {
                FileUtils.cleanDirectory(downloads); // directory is cleared
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}
