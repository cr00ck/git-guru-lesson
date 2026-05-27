package testingPractices.allureStepsPracticeClasses;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;

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
        TextBoxAllureStepsPage textBoxPage = new TextBoxAllureStepsPage();

        step("Открываем страницу Text Box", () -> {
            Selenide.open("https://demoqa.com/text-box");
        });

        step("Вводим полное имя", () -> {
            textBoxPage.setFullName(fullName);
        });

        step("Вводим Email", () -> {
            textBoxPage.setEmail(email);
        });

        step("Вводим постоянный адрес", () -> {
            textBoxPage.setCurrentAddress(currentAddress);
        });

        step("Вводим временный адрес", () -> {
            textBoxPage.setPermanentAddress(permanentAddress);
        });

        step("Клик на Submit", () -> {
            textBoxPage.clickSubmit();
        });

        step("Проверяем полное имя", () -> {
            textBoxPage.assertInsideTable(fullName);
        });

        step("Проверяем Email", () -> {
            textBoxPage.assertInsideTable(email);
        });

        step("Проверяем постоянный адрес", () -> {
            textBoxPage.assertInsideTable(currentAddress);
        });

        step("Проверяем временный адрес", () -> {
            textBoxPage.assertInsideTable(permanentAddress);
        });
    }
}
