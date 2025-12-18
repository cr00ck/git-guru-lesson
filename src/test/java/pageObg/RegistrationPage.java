package pageObg;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    public static SelenideElement firstNameLocator = $("#firstName"),
            lastNameLocator = $("#lastName"),
            emailLocator = $("#userEmail"),
            genderLocator = $("label[for='gender-radio-1']"),
            numberLocator = $("#userNumber"),
            subjectInput = $("#subjectsInput"),
    hobbiesOneCheckbox =  $("label[for='hobbies-checkbox-1']"),
    hobbiesTwoCheckbox =  $("label[for='hobbies-checkbox-2']"),
    hobbiesThreeCheckbox =  $("label[for='hobbies-checkbox-3']"),
    fileInput =  $("input[type='file']"),
    addressInput =  $("#currentAddress"),
    stateInput =  $("#react-select-3-input"),
    cityInput =  $("#react-select-4-input"),
    submitClick =  $("#submit"),
    tableLocator =  $(".modal-content"),
            tableTitleLocator =  $("#example-modal-sizes-title-lg"),
            tableContentLocator =  $(".table-responsive");
    public String filePatH =  "src/test/resources/Medal Star (1).png";

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        // Ждем, когда страница загрузится
        $(".practice-form-wrapper").shouldBe(visible);
        return this;
    }

    public RegistrationPage setFirstName(String name) {
        firstNameLocator.shouldBe(visible).setValue(name);
        return this;
    }

    ;

    public RegistrationPage setLastName(String lastName) {
        lastNameLocator.shouldBe(visible).setValue(lastName);
        return this;
    }

    ;

    public RegistrationPage setUserEmail(String email) {
        emailLocator.shouldBe(visible).setValue(email);
        return this;
    }

    ;

    public RegistrationPage setGender(String gender) {
        genderLocator.shouldBe(visible).click();
        return this;
    }

    ;

    public RegistrationPage setNumber(String number) {
        numberLocator.shouldBe(visible).setValue(number);
        return this;
    }

    public RegistrationPage setDateOfBirth(String month, String year) {
        $("#dateOfBirthInput").click();
        new CalendarComponent().setCalendar(month,year);

        return this;
    }
    public RegistrationPage setSubject(String subject_Biology_) {
        subjectInput.setValue(subject_Biology_).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String hobby) {
        // Сбрасываем чекбоксы
//        $(byText("Male")); //хороший вариант но если будут другие локали то другой язык
        // Устанавливаем выбранные
            if (hobby.equals("Sports")) {
                hobbiesOneCheckbox.click();
            } else if (hobby.equals("Reading")) {
                hobbiesTwoCheckbox.click();
            } else if (hobby.equals("Music")) {
                hobbiesThreeCheckbox.click();
            }


        return this;
    }


    public RegistrationPage setFile() {
        fileInput.uploadFile(new File(filePatH));
//        $("input[type='file']").uploadFromClasspath ("/Medal Star (1).png"); // по дефолту знает что в ресурсах лежит , только для type=file


        return this;
    }
    public RegistrationPage setAddress(String currentAddress) {
        addressInput.setValue(currentAddress);

        return this;
    }
    public RegistrationPage setState(String state_Haryana) {
        stateInput.setValue(state_Haryana).pressEnter(); // so-so
        $("#state").shouldHave(text(state_Haryana));
//              $("#state").$(byText("Haryana")).click(); // good


        return this;
    }
    public RegistrationPage setCity(String city_Panipat) {
        cityInput.setValue(city_Panipat).pressEnter();; //so-so
        $("#city").shouldHave(text(city_Panipat));
//      $("#city").$(byText("Panipat")).click(); // good


        return this;
    }
    public RegistrationPage clickSubmit() {
        submitClick.click();
        return this;
    }
    public RegistrationPage assertModalTable(String key, String value) {
        tableTitleLocator.shouldHave(text("Thanks for submitting the form"));
        tableContentLocator.$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }
}
