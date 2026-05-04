package testingPractices.mobileTestsPractice.browserstack.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * App Automate (mobile): use {@code bstack:options} — Selenium 4.x rejects legacy top-level keys
 * ({@code app}, {@code browserstack.user}, {@code device}, …) as non-W3C.
 * Hub must be App Automate: {@code https://hub-cloud.browserstack.com/wd/hub} (not {@code hub.browserstack.com}).
 */
public class BrowserStackDriver implements WebDriverProvider {

    private static final String DEFAULT_HUB = "https://hub-cloud.browserstack.com/wd/hub";

    // ========== ХАРДКОД ПЕРЕМЕННЫХ ==========
    // ЗАМЕНИТЕ НА РЕАЛЬНЫЕ ЗНАЧЕНИЯ ИЗ BROWSERSTACK
    private static final String HARDCODED_USERNAME = "bsuser_n0F7wd";
    private static final String HARDCODED_ACCESS_KEY = "r1HMtt1PpRwTRx7b2mnk";
    private static final String HARDCODED_APP = "bs://8aaf4eaf3a28246552065105dcf6a2d74367d524";
    // =========================================

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        // Сначала пробуем взять из переменных окружения / JVM свойств
        String userName = getenvOrProperty("BROWSERSTACK_USERNAME", "browserstack.user");
        String accessKey = getenvOrProperty("BROWSERSTACK_ACCESS_KEY", "browserstack.key");
        String app = getenvOrProperty("BROWSERSTACK_APP", "browserstack.app");

        // Если переменные окружения не заданы — используем хардкод
        if (isBlank(userName)) {
            userName = HARDCODED_USERNAME;
            System.out.println("⚠️ Using hardcoded BROWSERSTACK_USERNAME: " + userName);
        }
        if (isBlank(accessKey)) {
            accessKey = HARDCODED_ACCESS_KEY;
            System.out.println("⚠️ Using hardcoded BROWSERSTACK_ACCESS_KEY");
        }
        if (isBlank(app)) {
            app = HARDCODED_APP;
            System.out.println("⚠️ Using hardcoded BROWSERSTACK_APP: " + app);
        }

        // Финальная проверка
        if (isBlank(userName) || isBlank(accessKey) || isBlank(app)) {
            throw new IllegalStateException(
                    "❌ Не удалось получить credentials! Установите переменные окружения:\n" +
                    "   BROWSERSTACK_USERNAME, BROWSERSTACK_ACCESS_KEY, BROWSERSTACK_APP (bs://...)\n" +
                    "   или заполните HARDCODED_* в классе BrowserStackDriver.");
        }

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", userName);
        bstackOptions.put("accessKey", accessKey);
        bstackOptions.put("app", app);
        bstackOptions.put("deviceName", "Samsung Galaxy S22 Ultra");
        bstackOptions.put("platformVersion", "12.0");
        bstackOptions.put("platformName", "android");
        bstackOptions.put("projectName", "First Java Project");
        bstackOptions.put("buildName", "browserstack-build-1");
        bstackOptions.put("sessionName", "first_test");

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("bstack:options", bstackOptions);

        String hub = System.getProperty("browserstack.hub", DEFAULT_HUB);
        try {
            URL hubUrl = URI.create(hub).toURL();
            System.out.println("✅ Connecting to BrowserStack hub: " + hubUrl);
            return new RemoteWebDriver(hubUrl, caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getenvOrProperty(String envName, String propertyName) {
        String v = System.getenv(envName);
        if (!isBlank(v)) {
            return v;
        }
        return System.getProperty(propertyName);
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }
}