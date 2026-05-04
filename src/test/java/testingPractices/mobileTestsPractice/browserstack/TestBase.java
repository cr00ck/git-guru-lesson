package testingPractices.mobileTestsPractice.browserstack;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import testingPractices.mobileTestsPractice.browserstack.drivers.BrowserStackDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        // Настраиваем Selenide на использование нашего кастомного драйвера
        Configuration.browser = BrowserStackDriver.class.getName();
        Configuration.browserSize = null; // для мобильных тестов
        Configuration.timeout = 30000;
        Configuration.holdBrowserOpen = false;

        // Добавляем Allure листенер
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    /**
     * WebDriver в Selenide привязан к потоку. {@code @BeforeAll} выполняется не в том же потоке, что тест,
     * поэтому {@code open()} здесь — в {@code @BeforeEach}.
     */
    @BeforeEach
    void openBrowserStackSession() {
        try {
            open();
            WebDriver driver = WebDriverRunner.getWebDriver();
            System.out.println("✅ WebDriver created. Session ID: "
                    + ((org.openqa.selenium.remote.RemoteWebDriver) driver).getSessionId());
        } catch (Exception e) {
            System.err.println("❌ Failed to create WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }
}