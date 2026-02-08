package testingPractices;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class GithubTests {

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
