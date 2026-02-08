package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseConfigs {
    @BeforeAll
    public static void setUp() {
        // КРИТИЧЕСКИ ВАЖНО: Указываем путь к системному ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // ОТКЛЮЧАЕМ WebDriverManager полностью
        System.setProperty("wdm.progressBar", "false");
        System.setProperty("wdm.targetPath", "/dev/null");
        System.setProperty("wdm.chromeDriverVersion", "LATEST");
        System.setProperty("wdm.architecture", "64");

        // Отключаем автоматическую загрузку
        System.setProperty("wdm.downloadFolder", "/tmp");
        System.setProperty("wdm.cachePath", "/tmp");

        // Настройки Selenide
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.downloadsFolder = "build/downloads";
        Configuration.reopenBrowserOnFail = false;

        // Устанавливаем ChromeService вручную
        ChromeDriverService service = ChromeDriverService.createDefaultService();
        System.out.println("ChromeDriver service created: " + service.getUrl());

        // Настройки ChromeOptions для Jenkins
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Дополнительные опции для стабильности
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-software-rasterizer");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--disable-features=NetworkService");

        Configuration.browserCapabilities = options;

        // Подключаем Allure listener
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true)
        );

        System.out.println("=== CONFIGURATION ===");
        System.out.println("webdriver.chrome.driver: " + System.getProperty("webdriver.chrome.driver"));
        System.out.println("ChromeDriver exists: " + new File("/usr/local/bin/chromedriver").exists());
        System.out.println("ChromeDriver executable: " + new File("/usr/local/bin/chromedriver").canExecute());
    }

    @AfterAll
    static void tearDown() {
        closeWebDriver();
        SelenideLogger.removeListener("AllureSelenide");
    }
}