package testingPractices.firstTestsWithSelenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class GithubTests {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 60000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
    }

    @Test
    @Tag("SEARCH")
    void HomeworkFindJUnit5 (){

        //- Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");

        // - Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        // - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $$("ul li a").findBy(text("Soft assertions")).should(visible);

        // - Откройте страницу SoftAssertions,
        $$("a").findBy(text("Soft assertions")).click();

        // проверьте что внутри есть пример кода для JUnit5
        $(".markdown-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");

                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }"""));

    }
}
