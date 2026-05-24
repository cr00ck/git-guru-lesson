package testingPractices;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class QuickCheckTest {

    static {
        // Настройки для удалённого Selenium-сервера
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.headless = false;  // Временно выключите, чтобы увидеть браузер
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        
        System.out.println("=== Selenide Configuration ===");
        System.out.println("Remote: " + Configuration.remote);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Headless: " + Configuration.headless);
        System.out.println("==============================");
    }

    @Test
    void quickCheck() {
        System.out.println("Starting test...");
        open("https://www.google.com");
        System.out.println("Title: " + title());
        sleep(3000);
    }
}
