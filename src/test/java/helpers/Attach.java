package helpers;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.codeborne.selenide.Selenide.sessionId;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class Attach {

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName) {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "text/plain")
    public static String attachAsText(String attachName, String message) {
        return message;
    }

    public static void browserConsoleLogs() {
        attachAsText(
                "Browser console logs",
                String.join("\n", Selenide.getWebDriverLogs(BROWSER))
        );
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo() {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getVideoUrl()
                + "' type='video/mp4'></video></body></html>";
    }

    public static URL getVideoUrl() {
        String videoUrl = "https://" + System.getProperty("selenoid", "selenoid.autotests.cloud") + "/video/" + sessionId() + ".mp4";
        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ НОВЫЙ МЕТОД ДЛЯ ВИДЕО ИЗ ЛОКАЛЬНОЙ ПАПКИ
    @Attachment(value = "Video recording", type = "video/mp4")
    public static byte[] addVideoToAllure() {
        String sessionId = getVideoSessionId();
        if (sessionId == null) return null;

        String videoFolderPath = System.getenv("WORKSPACE") + "/selenoid/video/";
        Path videoPath = Paths.get(videoFolderPath + sessionId + ".mp4");

        for (int i = 0; i < 10; i++) {
            if (Files.exists(videoPath)) {
                try {
                    return Files.readAllBytes(videoPath);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static String getVideoSessionId() {
        try {
            return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
        } catch (Exception e) {
            return null;
        }
    }
}