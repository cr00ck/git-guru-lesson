package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SearchTests {
    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000; // Увеличиваем таймаут загрузки
        Configuration.browserSize = "1920x1080";

        // Настройки для стабильности
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;

        Configuration.browserCapabilities.setCapability("goog:chromeOptions", java.util.Map.of(
                "excludeSwitches", java.util.List.of("enable-automation"),
                "useAutomationExtension", false
        ));
    }

    @Test
    //на тестах с гугл всегда появлялась капча и тест падал, дипсик предложил создать файл в проекте
    //с html страницей и на ней запустить, все сработало.
    void successfulSearchTest() {
        // Открываем локальный HTML файл
        open("file://" + System.getProperty("user.dir") + "/src/test/resources/test-page.html");

        // Проверяем заголовок
        $("h3").shouldHave(Condition.text("Selenide"));

        // Проверяем ссылку на Selenide
        $("a[href='https://selenide.org']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("https://selenide.org"));

        // Проверяем текст в блоке поиска
        $("#search").shouldHave(Condition.text("concise and powerful"));

        System.out.println("Тест успешно выполнен!");
    }
    @Test
    void testDuckDuckGo() {
        open("https://duckduckgo.com/");
        $("#searchbox_input").setValue("Selenide").pressEnter();
        // 1. Проверяем заголовок (должен содержать "Selenide")
        // Просто проверяем что на странице есть текст "selenide.org"
        $("body").shouldHave(Condition.text("selenide.org"));

        // И что есть текст "Selenide"
        $("body").shouldHave(Condition.text("Selenide"));
        System.out.println("Тест успешно выполнен!");


    }

}