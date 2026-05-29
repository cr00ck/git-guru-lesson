package testingPractices;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;

import static com.codeborne.selenide.Selenide.*;

public class QuickCheckTest extends BaseConfigs {


    @Test
    void quickCheck() {
        System.out.println("Starting test...");
        open("https://www.google.com");
        System.out.println("Title: " + title());
        sleep(3000);
    }
}
