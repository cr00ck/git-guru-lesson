package testingPractices.firstTestsWithSelenide;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class GithubHoverTest extends BaseConfigs {

    @Test
    void testWithHover() {
        open("https://github.com");
        sleep(4000);
        $$("button, a, summary").findBy(text("Solutions")).hover();
        $$("a").findBy(text("Enterprise")).shouldBe(Condition.visible).click();
        webdriver().shouldHave(url("https://github.com/enterprise"));
    }
}