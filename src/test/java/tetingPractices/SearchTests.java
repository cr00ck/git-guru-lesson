package tetingPractices;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;

@DisplayName("Первые учебные тесты")
public class SearchTests {
    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        //Configuration.timeout = 10000;
        //Configuration.pageLoadTimeout = 30000; // Увеличиваем таймаут загрузки
        Configuration.browserSize = "1920x1080";

        // Настройки для стабильности
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;

//        Configuration.browserCapabilities.setCapability("goog:chromeOptions", java.util.Map.of(
//                "excludeSwitches", java.util.List.of("enable-automation"),
//                "useAutomationExtension", false
       // ));
    }
    @Disabled("тут надо писать тикет бага в ТМС, тест будет виден как неактивный")
    @Test
    @Tags({
            @Tag("HTML"),
            @Tag("PRACTICE")
    })
    @DisplayName("Тест со созданной страницей HTML из файла")

    //на тестах с гугл всегда появлялась капча и тест падал, дипсик предложил создать файл в проекте
    //с html страницей и на ней запустить, все сработало.
    void successfulSearchTest() {
        // Открываем локальный HTML файл
        open("file://" + System.getProperty("user.dir") + "src/test/resources/htmlPages/test-page.html");

        // Проверяем заголовок
        $("h3").shouldHave(Condition.text("Selenide"));

        // Проверяем ссылку на Selenide
        $("a[href='https://selenide.org']")
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("https://selenide.org"));

        // Проверяем текст в блоке поиска
        $("#search").shouldHave(Condition.text("concise and powerful"));

        System.out.println("Тест успешно выполнен!");
    }
    @Test
    void testDuckDuckGo() {
        open("https://duckduckgo.com/");
        $("#searchbox_input").setValue("Selenide").pressEnter();
        // 1. Проверяем заголовок (должен содержать "Selenide")
        // Просто проверяем что на странице есть текст "selenide.org"
        $("body").shouldHave(Condition.text("selenide.org"));

        // И что есть текст "Selenide"
        $("body").shouldHave(Condition.text("Selenide"));
        System.out.println("Тест успешно выполнен!");


    }
    // два сокращения , для айдишника ставим # $(#"locator") , для класса ставим . $(."locator")
      //если в дом-дереве нужно найти вложенный элемент (класс в классе) , который ниже, можно записать через пробел так : $(#"locator #locator2")
    //NAME можно записывать так: $("[name=email]").setValue; или $(byName("email")).setValue("1"); одинакого
    //CLASS можно так: $(".inputtext.login_form_inform_box").setValue("1"); одинакого или $(".login_form_inform_box").setValue("1"); одинакого
     //чтобы подняться по дереву вверх пишем .parent() - пример $("input"[type=submit]".parent()
     //когда ищем по тексту и если ищем весь текст то : $(byText("Hello world")) а если только часть текста, то : $(withText("llo wor"))
     //
     //
     //
     //
     //
    public void formatPractice (){
        String day = "25";
        String month = "July";
        String year = "1987";
        System.out.println(String.format("The day %s isnt contains in month %s and year %s !!", day, month, year));
    }
    public void math () {


        short c = 0;
        byte d = 127;
        c = (short) (d + 1);
        System.out.println(c);

        //операции с комбинированными типами данных
        int bInt = 9;
        double bDouble = 0.1;
        System.out.println(bInt + bDouble);

        System.out.println(bInt / bDouble);

        System.out.println(bInt % bDouble);
    }

}
