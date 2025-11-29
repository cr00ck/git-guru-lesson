package firstPractices;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DemoqaPracticeForm {
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
    void demoga_practice() {

        // Открываем страницу
        open("https://demoqa.com/automation-practice-form");


        // Заполняем поля с ожиданиями
        $("#firstName").shouldBe(visible).setValue("Kirill");
        $("#lastName").shouldBe(visible).setValue("Skotings");
        $("#userEmail").shouldBe(visible).setValue("ggg@mail.ru");
        $("label[for='gender-radio-1']").click();
        $("#userNumber").setValue("8916246109");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1987");
        $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();
       $("#subjectsInput").setValue("Biology").pressEnter();

        $("label[for='hobbies-checkbox-1']").click();
        $("label[for='hobbies-checkbox-2']").click();

        $("input[type='file']").uploadFile(new File("src/test/resources/Medal Star (1).png"));
        $("#currentAddress").setValue("Current Address");

        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#state").shouldHave(text("Haryana"));

        $("#react-select-4-input").setValue("Panipat").pressEnter();;
        $("#city").shouldHave(text("Panipat"));

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body .table-responsive").shouldHave(text("Kirill Skotings"));
        $(".modal-body .table-responsive").shouldHave(text("ggg@mail.ru"));
        $(".modal-body .table-responsive").shouldHave(text("Male"));
        $(".modal-body .table-responsive").shouldHave(text("8916246109"));
        $(".modal-body .table-responsive").shouldHave(text("17 July,1987"));
        $(".modal-body .table-responsive").shouldHave(text("Biology"));
        $(".modal-body .table-responsive").shouldHave(text("Biology"));
        $(".modal-body .table-responsive").shouldHave(text("Sports, Reading"));
        $(".modal-body .table-responsive").shouldHave(text("Medal Star (1).png"));
        $(".modal-body .table-responsive").shouldHave(text("Current Address"));
        $(".modal-body .table-responsive").shouldHave(text("Haryana Panipat"));







    }}