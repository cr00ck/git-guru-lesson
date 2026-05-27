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
 *
 * Credentials берутся из переменных окружения:
 *   BROWSERSTACK_USERNAME, BROWSERSTACK_ACCESS_KEY, BROWSERSTACK_APP
 * или JVM properties:
 *   -Dbrowserstack.user=... -Dbrowserstack.key=... -Dbrowserstack.app=...
 */
public class BrowserStackDriver implements WebDriverProvider {

    private static final String DEFAULT_HUB = "https://hub-cloud.browserstack.com/wd/hub";

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        String userName = getenvOrProperty("BROWSERSTACK_USERNAME", "browserstack.user");
        String accessKey = getenvOrProperty("BROWSERSTACK_ACCESS_KEY", "browserstack.key");
        String app = getenvOrProperty("BROWSERSTACK_APP", "browserstack.app");

        if (isBlank(userName) || isBlank(accessKey) || isBlank(app)) {
            throw new IllegalStateException(
                    "❌ Не удалось получить credentials! Установите переменные окружения:\\n" +
                    "   BROWSERSTACK_USERNAME, BROWSERSTACK_ACCESS_KEY, BROWSERSTACK_APP (bs://...)\\n" +
                    "   Например: export BROWSERSTACK_USERNAME=your_user\\n" +
                    "             export BROWSERSTACK_ACCESS_KEY=your_key\\n" +
                    "             export BROWSERSTACK_APP=bs://...");
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
