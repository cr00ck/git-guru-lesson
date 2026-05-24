package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class BaseConfigs {
    static {
        // Настройки для удалённого Selenium-сервера
        Configuration.remote = System.getProperty("selenide.remote", "http://localhost:4444/wd/hub");
        Configuration.browser = System.getProperty("selenide.browser", "chrome");
        Configuration.browserSize = "1920x1080";
        Configuration.headless = Boolean.parseBoolean(System.getProperty("selenide.headless", "true")); //true - без графического интерфейса
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";// "eager" - Selenide не ждёт загрузки всех стилей и картинок,а продолжает выполнение, как только загружен основной HTML и DOM.
        Configuration.screenshots = false;// Установлено в 'false', потому что мы делегируем создание скриншотов и видео самому Allure и Selenoid.
        Configuration.savePageSource = false;// Установлено в 'false' Allure справляется с этой задачей лучше.

        // Allure listener
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
                        .includeSelenideSteps(true)
        );

        System.out.println("=== Test Configuration ===");
        System.out.println("Remote URL: " + Configuration.remote);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Headless: " + Configuration.headless);
        System.out.println("=========================");
    }
    // Некоторые старые тесты в проекте могут вызывать BaseConfigs.setUp().
    // Этот метод ничего не делает, но предотвращает ошибки компиляции.
    // Все нужные настройки уже выполнены в статическом блоке выше.
    // Пустой метод для обратной совместимости
    public static void setUp() {
        // Настройки уже выполнены в статическом блоке
        System.out.println("setUp() called - configuration already initialized");
    }
}
