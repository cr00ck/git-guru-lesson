package testingPractices.allureStepsPracticeClasses;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxAllureStepsPage {

    private SelenideElement fullNameLocator = $("#userName"),
            emailLocator = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit");


    @Step("Отрываем главную страницу")
    public TextBoxAllureStepsPage openPage(){
        open("/text-box");
        return this;
    }
    @Step("Отрываем главную страницу после того как убрали прямую ссылку openPage")
    public TextBoxAllureStepsPage openPageNew(){
        $("[href='/forms']").click();
        $("[src='/assets/Toolsqa-DZdwt2ul.jpg']").shouldBe(visible);
        $(".header-text").shouldHave(text("Elements")).click();
        $(".text").shouldHave(text("Text Box")).click();
        $(".text-center").shouldHave(text("Text Box")).shouldBe(visible);
        return this;
    }
    @Step("Вводим полное имя {name}")
    public TextBoxAllureStepsPage setFullName(String name) {
        fullNameLocator.shouldBe(visible).setValue(name);
        return this;
    }
    @Step("Вводим емайл {name}")
    public TextBoxAllureStepsPage setEmail(String name) {
        emailLocator.shouldBe(visible).setValue(name);
        return this;
    }
    @Step("Вводим постоянный адрес {name}")
    public TextBoxAllureStepsPage setCurrentAddress(String name) {
        currentAddressInput.shouldBe(visible).setValue(name);
        return this;
    }
    @Step("Вводим временный адрес {name}")
    public TextBoxAllureStepsPage setPermanentAddress(String name) {
        permanentAddressInput.shouldBe(visible).setValue(name);
        return this;
    }
    @Step("Клик на сабмит")
    public TextBoxAllureStepsPage clickSubmit() {
        submitButton.click();
        return this;
    }
    @Step("Проверки в таблице по пунктам {value}")
    public TextBoxAllureStepsPage assertInsideTable (String value){
        $("#output").shouldBe(visible).shouldHave(text(value));
        return this;
    }

}
