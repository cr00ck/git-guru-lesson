package testingPractices.mobileTestsPractice;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import testingPractices.mobileTestsPractice.helpers.GetSessionId;
import testingPractices.mobileTestsPractice.browserstack.drivers.BrowserStackDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.browser = BrowserStackDriver.class.getName(); // устанавливаем созданный драйвер
        Configuration.browserSize = null; // 
    }

    @BeforeEach
    void beforeEach() {
        open();
    }
        @AfterEach
        void afterEach () {

            String sessionId = Selenide.sessionId().toString();
            System.out.println("Session ID: " + sessionId);
            Attach.pageSource();
            Attach.screenshotAs(sessionId);
            Attach.addVideo();
            Attach.browserConsoleLogs();
            closeWebDriver();
        }
}

    



