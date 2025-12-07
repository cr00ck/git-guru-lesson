package firstPractices;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pageObg.RegPage;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoqaPracticeFormPO {



    public class DemoqaPracticeForm {
        // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее
        RegPage regPage = new RegPage() ;
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


//        $("#dateOfBirthInput").click();
//        $(".react-datepicker__month-select").selectOption("July");
//        $(".react-datepicker__year-select").selectOption("1987");
//        $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();// не содержит класс .react-datepicker__day--outside-month
//          String ChoosenDate =
            regPage.setCalendar17Day("July","1999")
                    .setSubject("Biology")
                    .setHobbies()
                    .setHobbies()
                    .setFile()
                    .setAddress("currentAddress")
                    .setState("Haryana")
                    .setCity("Panipat")
                    .clickSubmit()
                    ;
//            $("#subjectsInput").setValue("Biology").pressEnter();

//            $("label[for='hobbies-checkbox-1']").click();
//        $(byText("Male")); //хороший вариант но если будут другие локали то другой язык
//            $("label[for='hobbies-checkbox-2']").click();

//            $("input[type='file']").uploadFile(new File("src/test/resources/Medal Star (1).png"));
//        $("input[type='file']").uploadFromClasspath ("/Medal Star (1).png"); // по дефолту знает что в ресурсах лежит , только для type=file
//            $("#currentAddress").setValue("Current Address");

//        $("#state").$(byText("Haryana")).click(); // good
//            $("#react-select-3-input").setValue("Haryana").pressEnter(); // so-so
//            $("#state").shouldHave(text("Haryana"));

//        $("#city").$(byText("Panipat")).click(); // good
//            $("#react-select-4-input").setValue("Panipat").pressEnter();; //so-so
//            $("#city").shouldHave(text("Panipat"));

//            $("#submit").click();
            // Ждем появления модального окна
//            $(".modal-content").shouldBe(visible);
//            $(".modal-content").should(appear);
//
//            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
//            $(".modal-body .table-responsive").shouldHave(text("Kirill Skotings"));
//            $(".modal-body .table-responsive").shouldHave(text("ggg@mail.ru"));
//            $(".modal-body .table-responsive").shouldHave(text("Male"));
//            $(".modal-body .table-responsive").shouldHave(text("8916246109"));
//            $(".modal-body .table-responsive").shouldHave(text("17 July,1987"));
////            $(".modal-body .table-responsive").shouldHave(text("Biology"));
//            $(".modal-body .table-responsive").shouldHave(text("Biology"));
//            $(".modal-body .table-responsive").shouldHave(text("Sports, Reading"));
//            $(".modal-body .table-responsive").shouldHave(text("Medal Star (1).png"));
//            $(".modal-body .table-responsive").shouldHave(text("Current Address"));
//            $(".modal-body .table-responsive").shouldHave(text("Haryana Panipat"));

        }}
}
