package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseConfigs {
    @BeforeAll
    public static void setUp() {
        // Получаем версию Chrome из системы
        String chromeVersion = getChromeVersion();
        System.out.println("Detected Chrome version: " + chromeVersion);

        // Настраиваем WebDriverManager
        if (chromeVersion != null && !chromeVersion.isEmpty()) {
            try {
                // Пробуем использовать точную версию
                WebDriverManager.chromedriver()
                        .driverVersion(chromeVersion)
                        .setup();
                System.out.println("Using ChromeDriver version: " + chromeVersion);
            } catch (Exception e) {
                System.out.println("Exact version not available, using latest compatible...");
                WebDriverManager.chromedriver().setup();
            }
        } else {
            // Если не удалось определить версию, используем latest
            WebDriverManager.chromedriver().setup();
        }

        // Настройки Selenide
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.headless = true;
        Configuration.timeout = 10000;

        // Важные настройки для Jenkins/Linux
        Configuration.remoteReadTimeout = 30000;
        Configuration.remoteConnectionTimeout = 30000;
        Configuration.downloadsFolder = "build/downloads";

        // Настройки ChromeOptions для headless режима
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");  // Новый headless режим
        options.addArguments("--no-sandbox");    // Обязательно для Docker/Jenkins
        options.addArguments("--disable-dev-shm-usage");  // Для ограниченной памяти
        options.addArguments("--disable-gpu");   // Отключаем GPU
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Отключаем автоматизационные флаги
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        Configuration.browserCapabilities = options;

        // Подключаем Allure listener для Selenide
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true)
        );

        System.out.println("Browser configuration completed");
    }

    @AfterAll
    static void tearDown() {
        // Закрываем браузер
        closeWebDriver();

        // Убираем listener
        SelenideLogger.removeListener("AllureSelenide");
    }

    private static String getChromeVersion() {
        try {
            // Пробуем получить версию Chrome из командной строки
            Process process = Runtime.getRuntime().exec("google-chrome --version");
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                // Ищем паттерн версии (например: "Google Chrome 144.0.7559.132")
                java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
                        "\\b\\d+\\.\\d+\\.\\d+\\.\\d+\\b"
                );
                java.util.regex.Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    return matcher.group();
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error getting Chrome version: " + e.getMessage());
        }
        return null;
    }
}