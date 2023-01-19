import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpHead;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageclasses.DownloadPage;
import webdrivers.FirefoxDriverSingleton;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FirefoxDownloadTest {
    private WebDriver driver;
    private DownloadPage downloadPage;

    @BeforeEach
    public void setup() {
        driver = FirefoxDriverSingleton.getDriver();
        downloadPage = new DownloadPage(driver);
        downloadPage.accessWebpage();
    }

    @Test
    public void httpDownloadTest() throws IOException {
        String link = downloadPage.getDownloadLink();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpHead request = new HttpHead(link);
        HttpResponse response = httpClient.execute(request);
        String contentType = response.getFirstHeader("Content-Type").getValue();
        int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());

        assertAll(
                () -> assertEquals("application/octet-stream", contentType),
                () -> assertNotEquals(0, contentLength)
        );
    }

    @Test
    public void downloadTest() {
        downloadPage.downloadFile();
        assertTrue(downloadPage.isFileDownloaded());
    }

    @AfterEach
    public void closeBrowser() {
        FirefoxDriverSingleton.tearDown();
    }
}
