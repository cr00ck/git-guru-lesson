package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class BaseConfigsDemoqa  extends BaseConfigs{
    @BeforeAll
    public static void setUp() {

        // СИСТЕМНЫЙ ChromeDriver
        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        //WebDriverManager.chromedriver().setup();

        // Если надо запустить удаленно через selenoid то пишем тут путь; wd- webDriver; user1:123 - креды на вход
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        // Чтобы видеть шаги и скрины в allure
        //SelenideLogger.addListener("allure", new AllureSelenide());


        // Минимальные настройки
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false; // открывать визуализвцию браузера или нет
        Configuration.timeout = 15000;

        // Отключаем все, что может мешать
        Configuration.reopenBrowserOnFail = false;
        Configuration.holdBrowserOpen = false;
        Configuration.browser = "chrome"; // по умолчанию установлен
        Configuration.pageLoadStrategy = "eager";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.baseUrl = "https://demoqa.com";
    }
}
