package pageObg;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class BaseConfigsDemoqa  extends BaseConfigs{
    @BeforeAll
    public static void setUp() {

        // СИСТЕМНЫЙ ChromeDriver
        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        //WebDriverManager.chromedriver().setup();

        // Если надо запустить удаленно через selenoid то пишем тут путь; wd- webDriver; user1:123 - креды на вход
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        // Чтобы видеть шаги и скрины в allure
        //SelenideLogger.addListener("allure", new AllureSelenide());

// ========== 1. ПОЛУЧЕНИЕ ПАРАМЕТРОВ ИЗ КОМАНДНОЙ СТРОКИ ==========
        // Эти параметры передаются через -D в консоли, например: ./gradlew test -Dbrowser=firefox

        /**
         * System.getProperty("ключ", "значение по умолчанию") - читает параметр из командной строки
         * Если параметр не передан, используется значение по умолчанию (chrome)
         * Пример: ./gradlew test -Dselenide.browser=firefox
         */
        String browser = System.getProperty("selenide.browser", "chrome");

        /**
         * Режим headless - браузер работает в фоне без графического интерфейса
         * true - для Jenkins/сервера (не видно окна браузера)
         * false - для локальной отладки (видно окно браузера)
         * Пример: ./gradlew test -Dselenide.headless=true
         */
        String headless = System.getProperty("selenide.headless", "false");

        /**
         * URL удаленного сервера Selenoid для запуска тестов в контейнерах
         * Формат: https://логин:пароль@адрес:порт/wd/hub
         * Позволяет запускать тесты на разных браузерах параллельно
         */
        String remote = System.getProperty("selenide.remote", "https://user1:1234@selenoid.autotests.cloud/wd/hub");

        // ========== 2. НАСТРОЙКА УДАЛЕННОГО ЗАПУСКА ==========

        /**
         * Configuration.remote - указывает Selenide использовать удаленный WebDriver
         * Если remote не пустой, тесты будут запускаться на Selenoid сервере
         * Это позволяет не устанавливать браузеры локально
         */
        if (remote != null && !remote.isEmpty()) {
            Configuration.remote = remote;
            System.out.println("Using remote WebDriver: " + remote);
        }

        // ========== 3. ОСНОВНЫЕ НАСТРОЙКИ БРАУЗЕРА ==========

        /**
         * Configuration.browser - какой браузер использовать
         * Доступные значения: chrome, firefox, opera, edge, safari
         * По умолчанию: chrome
         */
        Configuration.browser = browser;

        /**
         * Configuration.browserSize - размер окна браузера в пикселях
         * Формат: "ширина x высота"
         * 1920x1080 - стандартный Full HD размер
         * Важно для проверки адаптивности верстки
         */
        Configuration.browserSize = System.getProperty("selenide.browserSize", "1920x1080");

        /**
         * Configuration.headless - режим без графического интерфейса
         * true: браузер не видно, тесты идут быстрее (для CI/CD)
         * false: видно окно браузера (для отладки)
         */
        Configuration.headless = Boolean.parseBoolean(headless);

        /**
         * Configuration.timeout - максимальное время ожидания элементов (в миллисекундах)
         * Если элемент не появился за это время - тест падает
         * 15000 = 15 секунд
         * Увеличивайте если тесты падают из-за медленной загрузки
         */
        Configuration.timeout = Long.parseLong(System.getProperty("selenide.timeout", "15000"));

        // ========== 4. ОПТИМИЗАЦИЯ И СТАБИЛЬНОСТЬ ==========

        /**
         * Configuration.reopenBrowserOnFail - переоткрывать браузер при падении теста
         * false: не переоткрывать, чтобы видеть состояние в котором упал тест
         * true: переоткрывать (может скрыть проблему)
         */
        Configuration.reopenBrowserOnFail = false;

        /**
         * Configuration.holdBrowserOpen - оставлять браузер открытым после теста
         * false: закрывать браузер после каждого теста
         * true: оставлять открытым (удобно для отладки, но жрет память)
         */
        Configuration.holdBrowserOpen = false;

        /**
         * Configuration.pageLoadStrategy - стратегия загрузки страницы
         * "eager": ждать только DOM, не ждать загрузки картинок/стилей (быстрее)
         * "normal": ждать полной загрузки страницы (медленнее, но стабильнее)
         * "none": не ждать загрузки (самый быстрый, но ненадежный)
         */
        Configuration.pageLoadStrategy = "eager";

        /**
         * Configuration.screenshots - делать скриншоты при падении тестов
         * false: отключено (у нас есть Allure для скриншотов)
         * true: делать автоматические скриншоты
         */
        Configuration.screenshots = false;

        /**
         * Configuration.savePageSource - сохранять HTML страницы при падении
         * false: отключено (Allure делает это лучше)
         * true: сохранять .html файлы
         */
        Configuration.savePageSource = false;

        /**
         * Configuration.baseUrl - базовый URL для всех тестов
         * Позволяет в тестах писать open("/text-box") вместо open("https://demoqa.com/text-box")
         * Удобно при смене окружения (dev/stage/prod)
         */
        Configuration.baseUrl = "https://demoqa.com";

        // ========== 5. ЛОГИРОВАНИЕ НАСТРОЕК ==========
        // Выводим в консоль итоговую конфигурацию для отладки

        System.out.println("\n=== Test Configuration ===");
        System.out.println("Browser:           " + Configuration.browser);
        System.out.println("Headless:          " + Configuration.headless);
        System.out.println("Remote:            " + Configuration.remote);
        System.out.println("Base URL:          " + Configuration.baseUrl);
        System.out.println("Browser size:      " + Configuration.browserSize);
        System.out.println("Timeout:           " + Configuration.timeout + "ms");
        System.out.println("Page load strategy: " + Configuration.pageLoadStrategy);
        System.out.println("==========================\n");
    }
}

