package testingPractices.mobileTestsPractice.browserstack;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WikipediaTest {

    private AndroidDriver driver;

    // Берём из переменных окружения или JVM properties
    private static final String USERNAME = System.getProperty("browserstack.user", System.getenv("BROWSERSTACK_USERNAME"));
    private static final String ACCESS_KEY = System.getProperty("browserstack.key", System.getenv("BROWSERSTACK_ACCESS_KEY"));
    private static final String APP_URL = System.getProperty("browserstack.app", System.getenv("BROWSERSTACK_APP"));

    @BeforeEach
    public void setUp() throws Exception {
        // Используем DesiredCapabilities (без appium: префикса)
        DesiredCapabilities caps = new DesiredCapabilities();

        // Основные настройки (без префикса appium:)
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "12.0");
        caps.setCapability("appium:deviceName", "Samsung Galaxy S22 Ultra");
        caps.setCapability("appium:app", APP_URL);

        // BrowserStack настройки в отдельном контейнере
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", USERNAME);
        bstackOptions.put("accessKey", ACCESS_KEY);
        bstackOptions.put("projectName", "First Java Project");
        bstackOptions.put("buildName", "browserstack-build-1");
        bstackOptions.put("sessionName", "Wikipedia Test");

        caps.setCapability("bstack:options", bstackOptions);

        System.out.println("Connecting to BrowserStack...");
        driver = new AndroidDriver(
                new URL("https://hub-cloud.browserstack.com/wd/hub"),
                caps
        );
        System.out.println("Session started: " + driver.getSessionId());
    }

    @Test
    public void wikipediaSearchTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Клик по иконке поиска
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Search Wikipedia"))).click();

        // Ввод текста в поле поиска
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("org.wikipedia.alpha:id/search_src_text"))).sendKeys("Appium");

        // Проверка результатов
        boolean resultsFound = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                AppiumBy.className("android.widget.TextView"))).size() > 0;

        assertTrue(resultsFound, "No search results found");
        System.out.println("Search completed successfully");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Session closed");
        }
    }
}