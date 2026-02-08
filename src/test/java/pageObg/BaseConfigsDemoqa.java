package pageObg;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;

public class BaseConfigsDemoqa  extends BaseConfigs{
    @BeforeAll
    public static void setUp() {

        // СИСТЕМНЫЙ ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Минимальные настройки
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
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
