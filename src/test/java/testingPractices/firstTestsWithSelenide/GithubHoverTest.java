package testingPractices.firstTestsWithSelenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;


public class GithubHoverTest {
    @BeforeAll
    static void setUp() {
        // Минимальные настройки
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 60000;
        Configuration.pageLoadStrategy = "eager";

        // Отключаем все, что может мешать
        Configuration.browser = "chrome";
        Configuration.reopenBrowserOnFail = false;
        Configuration.holdBrowserOpen = false;


    }

    @Disabled("GitHub часто меняет UI и блокирует headless-браузеры")
    @Test
    @Tag("SEARCH")
    void testWithHover() {
        // ctrl+shift+L жмем после каждого готового теста, чтобы оптимизировать и удалить все лишнее


        // На главной странице GitHub выберите: Menu -> Solutions -> Enterprise.
        open("https://github.com");
        $$("button, a, summary").findBy(text("Solutions")).hover();
        $$("a").findBy(text("Enterprise")).shouldBe(Condition.visible).click();

        // Проверяем факт перехода на Enterprise-страницу (контент у GitHub часто A/B и меняется).
        webdriver().shouldHave(url("https://github.com/enterprise"));
        // sleep(5000);
    }
}




