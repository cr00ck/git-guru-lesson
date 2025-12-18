package tetingPractices;
import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;
import pageObg.TextBoxPage;

import static dataFaker.DataFakerRamdom.*;


public class TextBoxTest  extends BaseConfigs {
    // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее

    // используем кастомные методы для генерации данных
    String fullName = getFullName();
    String email = getRandomEmail();
    String currentAddress = getRandomAddress();
    String permanentAddress = getRandomAddress();


    @Test
    void textBoxForm() {
        TextBoxPage textbopage = new TextBoxPage();
        // Установки для теста
        BaseConfigs.setUp();
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
