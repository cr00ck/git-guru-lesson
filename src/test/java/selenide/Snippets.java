package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {

    // ctrl+shift+L жмем после кажного готового теста, чтобы оптимизировать и удалить все лишнее

//    // this is not a full list, just the most common
//
//    void browser_command_examples() {
//        open("https://google.com");
//        open("/customer/orders");     // -Dselenide.baseUrl=http://123.23.23.1
//        open("/", AuthenticationType.BASIC,
//                new BasicAuthCredentials("", "user", "password"));
//
//        Selenide.back();//- стрелка назад в браузе
//        Selenide.refresh();//- обновить в браузе
//
//        Selenide.clearBrowserCookies();//- почистить куки в браузе
//        Selenide.clearBrowserLocalStorage();
//        executeJavaScript("sessionStorage.clear();"); // no Selenide command for this yet
//
//        Selenide.confirm(); // OK in alert dialogs
//        Selenide.dismiss(); // Cancel in alert dialogs
//
//        Selenide.closeWindow(); // close active tab
//        Selenide.closeWebDriver(); // close browser completely
//
//        Selenide.switchTo().frame("new");
//        Selenide.switchTo().defaultContent(); // return from frame back to the main DOM
//
//        Selenide.switchTo().window("The Internet");
//
//        var cookie = new Cookie("foo", "bar");
//        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
//
//
//    }
//
//    void selectors_examples() {
//        $("div").click();
//        element("div").click();
//
//        $("div", 2).click(); // the third div
//
//        $x("//h1/div").click();
//        $(byXpath("//h1/div")).click();
//
//        $(byText("full text")).click();
//        $(withText("ull tex")).click(); //частичный текст
//
//        $(byTagAndText("div", "full text"));
//        $(withTagAndText("div", "ull text"));
//
//        $("").parent();//родитель можно наверх искать по дереву, верхний основной класс
//        $("").sibling(1); //вниз по дереву брат\сестра, начиная с 0(первый)
//        $("").preceding(1);//вверх по дереву брат\сестра, начиная с 0(первый)
//        $("").closest("div");//предок кот. имеет тэг div или искать по классу
//        $("").ancestor("div"); // the same as closest
//        $("div:last-child");//берем последнего div
//
//        $("div").$("h1").find(byText("abc")).click();
//          $("#state").$(byText("Haryana")).click(); // good

//        // very optional
//        $(byAttribute("abc", "x")).click();
//        $("[abc=x]").click();
//
//        $(byId("mytext")).click();
//        $("#mytext").click();// если начинается с цифры $("#3mytext"), лучше использовать верхний вар
//
//        $(byClassName("red")).click();
//        $(".red").click();
//    }
//
//    void actions_examples() {
//        $("").click();
//        $("").doubleClick();
//        $("").contextClick();// тоже что и правый клик
//
//        $("").hover();// подвести мышь но не жмакать
//
//        $("").setValue("text");
//        $("").append("text"); // добавить текст к уже имеющемуся в конце
//        $("").clear();// может гдето не работать
//        $("").setValue(""); // clear
//
//        $("div").sendKeys("c"); // hotkey c on element , просто нажать какуюто кнопку на странице в элемент
//        actions().sendKeys("c").perform(); //hotkey c on whole application, просто нажать какуюто кнопку без цели
//        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F - посылать комбинации клавиш
//        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // если action не сработал
//
//        $("").pressEnter();
//        $("").pressEscape();
//        $("").pressTab();
//
//
//        // complex actions with keybord and mouse, example
//        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();
//
//        // old html actions don't work with many modern frameworks
//        $("").selectOption("dropdown_option"); // с некотырыми есть блок option и он по нему ищем, если нет блока, то надо кликать и потом еще внутри кликать
//        $("").selectRadio("radio_options");
//
//    }
//
//    void assertions_examples() {
//        $("").shouldBe(visible);
//        $("").shouldNotBe(visible);
//        $("").shouldHave(text("abc"));
//        $("").shouldNotHave(text("abc"));
//        $("").should(appear);
//        $("").shouldNot(appear);
//
//
//        //longer timeouts
//        $("").shouldBe(visible, Duration.ofSeconds(30)); // можно увеличить или УМЕНЬШИТЬ
//
//    }
//
//    void conditions_examples() {
//        $("").shouldBe(visible);
//        $("").shouldBe(hidden);
//
//        $("").shouldHave(text("abc"));
//        $("").shouldHave(exactText("abc")); // только этот текст
//        $("").shouldHave(textCaseSensitive("abc"));
//        $("").shouldHave(exactTextCaseSensitive("abc"));
//        $("").should(matchText("[0-9]abc$"));
//        //  ----------------------------------------------------------------------------------------------------------------
//
//        // REGEX
//        // Точка - любой символ
//        boolean match1 = "cat".matches("c.t"); // true (cat, cot, cut и т.д.)
//
//        // Квадратные скобки - набор символов
//        boolean match2 = "cat".matches("[cbr]at"); // true (cat, bat, rat)
//
//        // Диапазоны
//        boolean match3 = "a1".matches("[a-z][0-9]"); // true
//        boolean match4 = "A1".matches("[A-Za-z][0-9]"); // true
//
//        // Отрицание
//        boolean match5 = "cat".matches("[^bcr]at"); // true (не bat, cat, rat)
//        public static boolean isValidEmail (String email){
//            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
//            return email.matches(regex);
//        }
//        System.out.println(isValidEmail("test@example.com")); // true
//        System.out.println(isValidEmail("invalid.email")); // false
////----------------------------------------------------------------------------------------------------------------
//        $("").shouldHave(cssClass("red")); // именно называется так, а не содержит
//        $("").shouldHave(cssValue("font-size", "12"));
//
//        $("").shouldHave(value("25"));
//        $("").shouldHave(exactValue("25"));
//        $("").shouldBe(empty);
    //        $(".modal-content").should(appear);

//
//        $("").shouldHave(attribute("disabled"));
//        $("").shouldHave(attribute("name", "example"));
//        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));
//
//        $("").shouldBe(checked); // for checkboxes
//
//        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
//        $("").should(exist); // проверяет наличие, но не проверяет видимость !
//
//        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
//        $("").shouldBe(disabled);
//        $("").shouldBe(enabled);
//    }
//
//    void collections_examples() {
//
//        $$("div"); // does nothing!
//
//        $$x("//div"); // by XPath
//
//        // selections
//        $$("div").filterBy(text("123")).shouldHave(size(1));// которые содержат
//        $$("div").excludeWith(text("123")).shouldHave(size(1)); // которые НЕ содержат
//
//        $$("div").first().click();
//        elements("div").first().click();
//        // $("div").click();
//        $$("button").first().click();
//        $$("a").first().click();
//        $$("span").first().click();
//        $$("input").first().click();
//        $$(".btn").first().click();
//        $$(".submit").first().click();
//        $$("div").last().click();
//        $$("div").get(1).click(); // the second! (start with 0)
//        $("div", 1).click(); // same as previous
//        $$("div").findBy(text("123")).click(); //  finds first
//
//        // assertions
//        $$("").shouldHave(size(0));
//        $$("").shouldBe(CollectionCondition.empty); // the same
//
//        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); // количество элементов проверяет, но если будет Alfa-centavr то не упадет
//        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));// и количество элементов и точное написаниепроверяет,  если будет Alfa-centavr то упадет
//
//        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa")); // может быть в любом порядке
//        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));
//
//        $$("").shouldHave(itemWithText("Gamma")); // only one text
//
//        $$("").shouldHave(sizeGreaterThan(0));
//        $$("").shouldHave(sizeGreaterThanOrEqual(1));
//        $$("").shouldHave(sizeLessThan(3));
//        $$("").shouldHave(sizeLessThanOrEqual(2));
//
//
//    }
//
//    void file_operation_examples() throws FileNotFoundException {
//
//        File file1 = $("a.fileLink").download(); // only for <a href=".."> links
//        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid
//
//        File file = new File("src/test/resources/readme.txt");
//        $("#file-upload").uploadFile(file);
//        $("#file-upload").uploadFromClasspath("readme.txt");
//        // don't forget to submit!
//        $("uploadButton").click();
//    }
//
//    void javascript_examples() {
//        executeJavaScript("alert('selenide')");
//        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
//        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
//        -------------------------------------------------------------------------------------------------------------
//        //когда надо заморозить страницу, если поп-ап изчезает, когда убираем мышь
//        // либо в девтулз Sourses-> pause
//        //если это не сработало, то пишем в девтулз в Console-> setTimeout (function () {debugger}, 4000);
//
//        // если есть близнец-локатор, он может быть невидимый, тогда пишем в девтулз в Console-> $$(".Popover") и видим сколько таких локаторов
//        // и ИДЕ пишем $$(".Popover").findBy(visible); находим первый который видимый

          // когда мешает баннер или надо скрыть элемент со страницы
          // в консоле    Selenide.executeJavaScript$("#Popover").remove;
//
//    }
}



