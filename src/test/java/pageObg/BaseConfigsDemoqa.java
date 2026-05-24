package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class BaseConfigsDemoqa {
    static {
        // Базовые настройки Selenide для удалённого сервера
        Configuration.remote = System.getProperty("selenide.remote", "http://localhost:4444/wd/hub");
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.browserSize = "1920x1080";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "true"));
        Configuration.timeout = 15000;
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
        
        System.out.println("=== Test Configuration (BaseConfigsDemoqa) ===");
        System.out.println("Remote URL: " + Configuration.remote);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Headless: " + Configuration.headless);
        System.out.println("==============================================");
    }
    
    // Пустой метод для обратной совместимости
    public static void setUp() {
        // Настройки уже выполнены в статическом блоке
        System.out.println("setUp() called - configuration already initialized");
    }
}
