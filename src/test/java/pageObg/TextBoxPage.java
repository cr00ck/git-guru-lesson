package pageObg;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private SelenideElement fullNameLocator = $("#userName"),
            emailLocator = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit");

    public TextBoxPage openPage(){
        open("/text-box");
        return this;
    }

    public TextBoxPage setFullName(String name) {
        fullNameLocator.shouldBe(visible).setValue(name);
        return this;
    }

    public TextBoxPage setEmail(String name) {
        emailLocator.shouldBe(visible).setValue(name);
        return this;
    }

    public TextBoxPage setCurrentAddress(String name) {
        currentAddressInput.shouldBe(visible).setValue(name);
        return this;
    }

    public TextBoxPage setPermanentAddress(String name) {
        permanentAddressInput.shouldBe(visible).setValue(name);
        return this;
    }

    public TextBoxPage clickSubmit() {
        submitButton.click();
        return this;
    }

    public TextBoxPage assertInsideTable (String value){
        $("#output").shouldBe(visible).shouldHave(text(value));
        return this;
    }

}
