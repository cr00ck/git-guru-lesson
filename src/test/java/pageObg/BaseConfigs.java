package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class BaseConfigs {

    static {
        // Определяем URL удалённого Selenium-сервера
        String remoteUrl = System.getProperty("selenide.remote", "http://localhost:4444/wd/hub");
        String customBrowser = System.getProperty("selenide.browser");

        // Проверяем доступность Selenium Grid
        try {
            Configuration.remote = remoteUrl;
            Configuration.browser = customBrowser != null ? customBrowser : "chrome";
            System.out.println("✅ Selenium Grid доступен — тесты запущены в REMOTE режиме");
            System.out.println("   Remote URL: " + remoteUrl);
        } catch (Exception e) {
            System.out.println("⚠️ Selenium Grid НЕ доступен — тесты в LOCAL режиме");
            Configuration.remote = null;
            Configuration.browser = customBrowser != null ? customBrowser : "chrome";
        }

        // ========== ОСНОВНЫЕ НАСТРОЙКИ ==========
        Configuration.browserSize = "1920x1080";

        // ✅ ГОТОВО — работает с -Dselenide.headless=false
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "false"));

        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;

        // Allure listener
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true)
        );

        System.out.println("=== Test Configuration ===");
        System.out.println("Mode: " + (Configuration.remote != null ? "REMOTE" : "LOCAL"));
        System.out.println("Remote URL: " + Configuration.remote);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Headless: " + Configuration.headless);
        System.out.println("=================================");
    }

    public static void setUp() {
        System.out.println("setUp() called - configuration already initialized");
    }
}