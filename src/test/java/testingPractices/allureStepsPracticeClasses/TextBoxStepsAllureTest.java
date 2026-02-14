package testingPractices.allureStepsPracticeClasses;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigsDemoqa;
import pageObg.TextBoxPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static dataFaker.DataFakerRamdom.*;
import static io.qameta.allure.Allure.step;


public class TextBoxStepsAllureTest extends BaseConfigsDemoqa {
    // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее

    // используем кастомные методы для генерации данных
    String fullName = getFullName();
    String email = getRandomEmail();
    String currentAddress = getRandomAddress();
    String permanentAddress = getRandomAddress();


    @Test
    @Tag("ALLURE")
    @Feature("Тренировка QAA с формой для ввода")
    @Story("Отработка навыков по автотестам")
    @Owner("vysokikh-mm")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "demoqa.com", url = "https://demoqa.com/text-box")
    @DisplayName("Заполнение полей на форме textBox")

//    @AfterEach
//    void addAttachments () {
//        Attach.screenshotAs("Last screenShot"); // делаем скрин после теста в алюре
//
//    }


    void textBoxFormLambdaTest() { //  такой подход используется если степы не переиспользуются
        SelenideLogger.addListener("allure", new AllureSelenide()); // чтобы видеть шаги и скрины в allure

        TextBoxAllureStepsPage textbopage = new TextBoxAllureStepsPage();
        // Установки для теста
       // BaseConfigsDemoqa.setUp();
        // Шаги и в allure будет по сценарию
        step("Отрываем главную страницу", () -> {
                open("https://demoqa.com");
        });
        step("Переходим к разделу Text Box", () -> {
            textbopage.openPageNew();
        });
        step("Вводим полное имя", () -> {
            textbopage.setFullName(fullName);
        });
        step("Вводим емайл", () -> {
            textbopage.setEmail(email);
        });
        step("Вводим постоянный адрес", () -> {
            textbopage.setCurrentAddress(currentAddress);
        });
        step("Вводим временный адрес", () -> {
            textbopage.setPermanentAddress(permanentAddress);
        });
        step("Клик на сабмит", () -> {
            textbopage.clickSubmit();
        });;
        // Проверки в таблице по пунктам
        step("Проверка полное имя", () -> {
            textbopage.assertInsideTable(fullName);
        });
        step("Проверка емайл", () -> {
            textbopage.assertInsideTable(email);
        });
        step("Проверка постоянный адрес", () -> {
            textbopage.assertInsideTable(currentAddress);
        });
        step("Проверка временный адрес", () -> {
            textbopage.assertInsideTable(permanentAddress);;
        });

        // посути лямбда бы была вот :
//         step("Отрываем главную страницу", new Allure.ThrowableRunnableVoid() { но так как один только метод в классе, может поставить лямбду
//             @Override
//             public void run() throws Throwable {
//             textbopage.openPage();
//             });


    }
    @Test
    @Tag("ALLURE")
    void textBoxFormAnnotatedTest() { //  такой подход используется если степы !! переиспользуются !!
        // и уже в самой странице TextBoxAllureStepsPage с методами пишем стэпы
        SelenideLogger.addListener("allure", new AllureSelenide());
        // аннотации в алюре
        Allure.feature("Тренировка QAA с формой для ввода");
        Allure.story("Отработка навыков по автотестам");
        Allure.label("owner", "vysokikh-mm");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("demoqa.com", "https://demoqa.com/text-box");
        // объявляем класс
        TextBoxAllureStepsPage textbopage = new TextBoxAllureStepsPage();
        // Установки для теста
        //BaseConfigsDemoqa.setUp();
        open("https://demoqa.com");
        textbopage.openPageNew()
                .setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit()
                // Проверки в таблице по пунктам
                .assertInsideTable(fullName)
                .assertInsideTable(email)
                .assertInsideTable(currentAddress)
                .assertInsideTable(permanentAddress);

    }

}
