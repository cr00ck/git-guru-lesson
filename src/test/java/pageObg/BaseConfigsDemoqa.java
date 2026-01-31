package pageObg;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class BaseConfigsDemoqa  extends BaseConfigs{
    @BeforeAll
    public static void setUp() {

        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome"; // по умолчанию установлен
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.baseUrl = "https://demoqa.com";
    }
}
