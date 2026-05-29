package testingPractices.firstTestsWithSelenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class GithubDragAndDropTest {
    @DisplayName("Проверка на перемещение квадратов")
    @BeforeAll
    static void setUp() {

        // Минимальные настройки
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 60000;
        Configuration.pageLoadStrategy = "eager";

        // Отключаем все, что может мешать
        Configuration.browser = "chrome";
        Configuration.reopenBrowserOnFail = false;
        Configuration.holdBrowserOpen = false;

        // НЕ открываем страницу здесь — тесты @Disabled и каждый открывает свою страницу самостоятельно
    }

    @Disabled("the-internet.herokuapp.com нестабилен / медленно загружается")
    @Test
        //(опциональное) Запрограммируйте Drag&Drop с помощью Selenide.actions()
    void dragAndDropTest() {
        // ctrl+shift+L жмем после каждого готового теста, чтобы оптимизировать и удалить все лишнее


//- Откройте https://the-internet.herokuapp.com/drag_and_drop
        open("https://the-internet.herokuapp.com/drag_and_drop");

//- Перенесите прямоугольник А на место В
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
//- Проверьте, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
        //sleep(5000);
    }
    @Disabled("the-internet.herokuapp.com нестабилен / медленно загружается")
    @Test
        void dragAndDropTestSecondMethod () {
        //- В Selenide есть команда $(element).dragAndDrop($(to-element)),
        // проверьте работает ли тест, если использовать её вместо actions()

        $("#column-a").dragAndDrop(to($("#column-b")));
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
       // sleep(5000);
    }
    }

