package testingPractices;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigsDemoqa;
import pageObg.TextBoxPage;

import static dataFaker.DataFakerRamdom.*;


public class TextBoxTest  extends BaseConfigsDemoqa {
    // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее

    // используем кастомные методы для генерации данных
    String fullName = getFullName();
    String email = getRandomEmail();
    String currentAddress = getRandomAddress();
    String permanentAddress = getRandomAddress();


    @Test
    void textBoxForm() {
        SelenideLogger.addListener("allure", new AllureSelenide()); // чтобы видеть шаги и скрины в allure
        TextBoxPage textbopage = new TextBoxPage();
        // Установки для теста
        BaseConfigsDemoqa.setUp();
textbopage.openPage()
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
