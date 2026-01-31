package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseConfigs {
    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome"; // по умолчанию установлен
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;

        // Подключаем Allure listener для Selenide
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)          // Делать скриншоты
                        .savePageSource(true)       // Сохранять HTML страницы
                        .includeSelenideSteps(true) // Логировать все шаги Selenide
        );
    }
    @AfterAll
    static void tearDown() {
        // Закрываем браузер
        closeWebDriver();

        // Убираем listener (опционально)
        SelenideLogger.removeListener("AllureSelenide");
    }
}
