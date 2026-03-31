package testingPractices.mobileTestsPractice.browserstack.classesFromBSsite;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import testingPractices.mobileTestsPractice.browserstack.drivers.BrowserStackDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.browser = BrowserStackDriver.class.getName(); // устанавливаем созданный драйвер
    }

    @AfterEach
    void afterAll() {

            closeWebDriver();
        }

   }

