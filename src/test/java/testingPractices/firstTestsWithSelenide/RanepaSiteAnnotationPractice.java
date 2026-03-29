package testingPractices.firstTestsWithSelenide;

import DataEnum.RanepaChanels;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RanepaSiteAnnotationPractice {

    @BeforeAll
    static void setup() {
        // СИСТЕМНЫЙ ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Минимальные настройки
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
        Configuration.timeout = 15000;

        // Отключаем все, что может мешать
        Configuration.browser = "chrome";
        Configuration.reopenBrowserOnFail = false;
        Configuration.holdBrowserOpen = false;
    }

    @ParameterizedTest(name = "Проверка канала: {0}")
    @EnumSource(RanepaChanels.class)
    @DisplayName("Проверка всех социальных каналов РАНХиГС, используя ENUM")

    void allChannelsIconsVisibleThroughEnum(RanepaChanels channel) {
        System.out.println("\n=== Начинаем тест канала: " + channel.displayName + " ===");

        // 1. Открываем страницу
        open("https://my.ranepa.ru/profile_new/");
        $("body").should(appear);

        // Проверяем что блок соцсетей виден
        $(".rnp__social_list").shouldBe(visible);

        // Проверяем что конкретная иконка видна
        String selector = String.format("[data-social-details='%s']", channel.dataAttribute);
        $(selector)
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(attribute("data-social-details", channel.dataAttribute));

        System.out.println("✅ Иконка " + channel.displayName + " отображается");
    }
    @ParameterizedTest(name = "Логин : {0}  Пароль: {1}")
    @CsvFileSource(resources = "/testData/textBoxForm.csv")
    @DisplayName("Негативный тест - логинимся на сайт")

    void loginThroughCsvFileSource(String login, String password) {

        // 1. Открываем страницу
        open("https://my.ranepa.ru/profile_new/");
        $("body").should(appear);

        // вводим логин и пароль и жмем ente
        $("[name='USER_LOGIN']").shouldBe(visible).setValue(login);
        $("[name='USER_PASSWORD']").shouldBe(visible).setValue(password);
        $("[name='Login']").shouldBe(visible).click();
        sleep(4000);
        $(".block-error__text").shouldHave(text("Не удалось найти данные."));
    }
    @ParameterizedTest(name = "Логин : {0}  Пароль: {1}")
    @CsvSource(value = {
            "Антон Пупкин, anton@mail.ru" +
                    "Валерий Бышмаков, valera@mail.ru" +
                    "Роман Вальтер, roman@mail.ru"
    })
    @DisplayName("Негативный тест - логинимся на сайт с помощью CsvSource")

    void loginThroghCsv(String login, String password) {

        // 1. Открываем страницу
        open("https://my.ranepa.ru/profile_new/");
        $("body").should(appear);

        // вводим логин и пароль и жмем ente
        $("[name='USER_LOGIN']").shouldBe(visible).setValue(login);
        $("[name='USER_PASSWORD']").shouldBe(visible).setValue(password);
        $("[name='Login']").shouldBe(visible).click();
        sleep(4000);
        $(".block-error__text").shouldHave(text("Не удалось найти данные."));



    }
}