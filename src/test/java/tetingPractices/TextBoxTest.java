package tetingPractices;

import org.junit.jupiter.api.Test;
import pageObg.BaseConfigs;
import pageObg.TextBoxPage;

public class TextBoxTest  extends BaseConfigs {
    // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее

    @Test
    void textBoxForm() {
        TextBoxPage textbopage = new TextBoxPage();
        // Установки для теста
        BaseConfigs.setUp();
textbopage.openPage()
        .setFullName("Oleg")
        .setEmail("fff@mail.ru")
        .setCurrentAddress("Pushkinskaia street, 10, 2, 1")
        .setPermanentAddress("____------_____")
        .clickSubmit()
        // Проверки в таблице по пунктам
        .assertInsideTable("Oleg")
        .assertInsideTable("fff@mail.ru")
        .assertInsideTable("Pushkinskaia street, 10, 2, 1")
        .assertInsideTable("____------_____");

    }
}
