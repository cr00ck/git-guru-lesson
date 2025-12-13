package tetingPractices;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;


import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;


public class GithubHoverTest {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy = "eager";

        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;

    }

    @Test
    void testWithHover() {
        // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее


        //На главной странице GitHub выберите: Меню -> Solutions -> Enterprize (с помощью команды hover для Solutions).
        open("https://github.com");
        $$("ul li").findBy(text("Enterprise")).hover();
        $("a[href='/enterprise']").shouldBe(Condition.visible).click();

        //Убедитесь, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.").
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform for the agent-ready enterprise"));
        webdriver().shouldHave(url("https://github.com/enterprise"));
        // sleep(5000);
    }
}




