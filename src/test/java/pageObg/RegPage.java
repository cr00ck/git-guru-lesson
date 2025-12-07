package pageObg;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegPage {

    public SelenideElement subjectInput = $("#subjectsInput");
    public SelenideElement hobbiesOneCheckbox =  $("label[for='hobbies-checkbox-1']");
    public SelenideElement hobbiesTwoCheckbox =  $("label[for='hobbies-checkbox-2']");
    public SelenideElement fileInput =  $("input[type='file']");
    public SelenideElement addressInput =  $("#currentAddress");
    public SelenideElement stateInput =  $("#react-select-3-input");
    public SelenideElement cityInput =  $("#react-select-4-input");
    public SelenideElement submitClick =  $("#submit");
    public String filePatH =  "src/test/resources/Medal Star (1).png";



    public RegPage setCalendar17Day( String month_July_, String year) {

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month_July_);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--017:not(.react-datepicker__day--outside-month)").click();
        return this;
    }
    public RegPage setSubject( String subject_Biology_) {
        subjectInput.setValue(subject_Biology_).pressEnter();

        return this;
    }
    public RegPage setHobbies(String... hobbies) {
        // Сбрасываем чекбоксы
        $("label[for='hobbies-checkbox-1']").parent().$("input").setSelected(false);
        $("label[for='hobbies-checkbox-2']").parent().$("input").setSelected(false);
        $("label[for='hobbies-checkbox-3']").parent().$("input").setSelected(false);

        // Устанавливаем выбранные
        for (String hobby : hobbies) {
            if (hobby.equals("Sports")) {
                hobbiesOneCheckbox.click();
            } else if (hobby.equals("Reading")) {
                hobbiesTwoCheckbox.click();
            } else if (hobby.equals("Music")) {
                $("label[for='hobbies-checkbox-3']").click();
            }
        }

        return this;
    }


    public RegPage setFile() {
        fileInput.uploadFile(new File(filePatH));

        return this;
    }
    public RegPage setAddress(String currentAddress) {
        addressInput.setValue(currentAddress);

        return this;
    }
    public RegPage setState(String state_Haryana) {
        stateInput.setValue(state_Haryana).pressEnter(); // so-so
        $("#state").shouldHave(text(state_Haryana));

        return this;
    }
    public RegPage setCity(String city_Panipat) {
            cityInput.setValue(city_Panipat).pressEnter();; //so-so
            $("#city").shouldHave(text(city_Panipat));

        return this;
    }
    public RegPage clickSubmit() {
        submitClick.click();

        return this;
    }
    public RegPage verifyFormData() {
        $(".modal-content").shouldBe(visible);
        $(".modal-content").should(appear);

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".modal-body .table-responsive").shouldHave(text("Kirill Skotings"));
        $(".modal-body .table-responsive").shouldHave(text("ggg@mail.ru"));
        $(".modal-body .table-responsive").shouldHave(text("Male"));
        $(".modal-body .table-responsive").shouldHave(text("8916246109"));
        $(".modal-body .table-responsive").shouldHave(text("17 July,1987"));
//            $(".modal-body .table-responsive").shouldHave(text("Biology"));
        $(".modal-body .table-responsive").shouldHave(text("Biology"));
        $(".modal-body .table-responsive").shouldHave(text("Sports, Reading"));
        $(".modal-body .table-responsive").shouldHave(text("Medal Star (1).png"));
        $(".modal-body .table-responsive").shouldHave(text("Current Address"));
        $(".modal-body .table-responsive").shouldHave(text("Haryana Panipat"));

        return this;
//        // Проверяем все поля с сохраненными данными
//        $(".modal-body .table-responsive").shouldHave(text(firstName + " " + lastName));
//        $(".modal-body .table-responsive").shouldHave(text(email));
//        $(".modal-body .table-responsive").shouldHave(text(gender));
//        $(".modal-body .table-responsive").shouldHave(text(mobile));
//
//        // Форматируем дату
//        String formattedDate = day + " " + month + "," + year;
//        $(".modal-body .table-responsive").shouldHave(text(formattedDate));
//
//        $(".modal-body .table-responsive").shouldHave(text(subject));
//        $(".modal-body .table-responsive").shouldHave(text(hobbies));
//        $(".modal-body .table-responsive").shouldHave(text(fileName));
//        $(".modal-body .table-responsive").shouldHave(text(address));
//        $(".modal-body .table-responsive").shouldHave(text(state + " " + city));
//
//        return this;
    }
}

