import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DemoqaPracticeForm {
    @BeforeAll
    static void options() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 30000;

        // Важные настройки для стабильности
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        Configuration.savePageSource = false;

        // Настройки для Chrome
        Configuration.browserCapabilities.setCapability("goog:chromeOptions", java.util.Map.of(
                "args", java.util.List.of(
                        "--disable-blink-features=AutomationControlled",
                        "--disable-dev-shm-usage",
                        "--no-sandbox",
                        "--disable-gpu",
                        "--remote-allow-origins=*"
                )
        ));
    }

    @Test
    void Demoga_practice() {
        // Открываем страницу
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        // Ждем загрузки страницы - проверяем видимость заголовка
        $(".practice-form-wrapper").shouldBe(visible);

        // Добавляем небольшую паузу для полной стабилизации
        sleep(2000);

        // Заполняем поля с ожиданиями
        $("#firstName").shouldBe(visible).setValue("Kirill");
        $("#lastName").shouldBe(visible).setValue("Skotings");
        $("#userEmail").shouldBe(visible).setValue("ggg@mail.ru");


//       $("label[for='gender-radio-1']").click();
//
//        $("#userNumber").setValue("8916246109");
//
//        $("#dateOfBirthInput").click();
//        $(".react-datepicker__month-select").selectOption("July");
//        $(".react-datepicker__year-select").selectOption("1987");
//        $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();
//
//       $("#subjectsInput").setValue("Biology").pressEnter();
//
//        $("label[for='hobbies-checkbox-1']").click();
//        $("label[for='hobbies-checkbox-2']").click();

        //$("input[type='file']").uploadFile(new File("src/test/resources/Medal Star (1).png"));
//        $("[id=#currentAddress]").setValue("Current Address");
//        $("[id=#state]").setValue("Haryana");
//        $("[id=#city]").setValue("Panipat");
//        $("[id=#submit]").click();
        //
        //


    }}