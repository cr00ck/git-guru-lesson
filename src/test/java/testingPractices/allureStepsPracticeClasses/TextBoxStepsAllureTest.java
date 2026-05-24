package testingPractices.allureStepsPracticeClasses;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;

import static com.codeborne.selenide.Selenide.*;
import static dataFaker.DataFakerRamdom.*;
import static io.qameta.allure.Allure.step;

public class TextBoxStepsAllureTest extends BaseConfigs {

    String fullName = getFullName();
    String email = getRandomEmail();
    String currentAddress = getRandomAddress();
    String permanentAddress = getRandomAddress();

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @Tag("ALLURE")
    @Feature("Тренировка QAA с формой для ввода")
    @Story("Отработка навыков по автотестам")
    @Owner("vysokikh-mm")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "demoqa.com", url = "https://demoqa.com/text-box")
    @DisplayName("Заполнение полей на форме textBox")
    void textBoxFormAnnotatedTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        
        open("https://demoqa.com/text-box");
        
        $("#userName").setValue(fullName);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();
        
        $("#output").shouldHave(com.codeborne.selenide.Condition.text(fullName));
        $("#output").shouldHave(com.codeborne.selenide.Condition.text(email));
        $("#output").shouldHave(com.codeborne.selenide.Condition.text(currentAddress));
        $("#output").shouldHave(com.codeborne.selenide.Condition.text(permanentAddress));
    }
}
