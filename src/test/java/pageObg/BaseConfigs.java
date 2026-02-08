package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseConfigs {
    @BeforeAll
    public static void setUp() {
        // ВАЖНО: Указываем путь к СИСТЕМНОМУ ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Отключаем автоматическую загрузку WebDriverManager
        System.setProperty("wdm.progressBar", "false");
        System.setProperty("wdm.driverCache", "/root/.cache/selenium");
        System.setProperty("wdm.chromeDriverVersion", "144.0.7559.132");

        // Настройки Selenide
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.downloadsFolder = "build/downloads";

        // Критически важные настройки для Jenkins
        Configuration.remoteReadTimeout = 30000;
        Configuration.remoteConnectionTimeout = 30000;
        Configuration.reopenBrowserOnFail = false;

        // Настройки ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Отключаем автоматизационные флаги
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Устанавливаем capabilities
        Configuration.browserCapabilities = options;

        // Подключаем Allure listener
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true)
        );

        // Проверяем конфигурацию
        System.out.println("=== CONFIGURATION ===");
        System.out.println("ChromeDriver path: " + System.getProperty("webdriver.chrome.driver"));
        System.out.println("Headless: " + Configuration.headless);
        System.out.println("Browser: " + Configuration.browser);
    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
        SelenideLogger.removeListener("AllureSelenide");
    }
}