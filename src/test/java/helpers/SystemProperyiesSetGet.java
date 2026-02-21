package helpers;

import org.junit.jupiter.api.Test;

public class SystemProperyiesSetGet {

    @Test
    void testGetBrowserProperty() {
        // Читаем значение из командной строки (-Dbrowser=opera)
        String browser = System.getProperty("browser", "chrome");
        System.out.println("Browser from command line: " + browser);
    }

    @Test
    void testGetSelenideBrowserProperty() {
        // Читаем значение для Selenide
        String selenideBrowser = System.getProperty("selenide.browser", "chrome");
        System.out.println("Selenide browser: " + selenideBrowser);
    }

    @Test
    void testGetScreenSizeProperty() {
        // Читаем значение для Selenide
        String screenSize = System.getProperty("screen.size", "1920x1080");
        System.out.println("Selenide screen size: " + screenSize);
    }
    @Test
    void testSetHeadless() {
        // Читаем значение для Selenide
        String screenSize = System.getProperty("screen.size", "1920x1080");
        System.out.println("Selenide screen size: " + screenSize);
    }

    // Этот метод ТОЛЬКО для демонстрации установки значения
//    @Test
//    void demonstrateSetProperty() {
//        // Устанавливаем значение программно (НЕ НУЖНО для вашего случая)
//        System.setProperty("browser", "firefox");
//        System.out.println("Now browser = " + System.getProperty("browser"));
//    }
}
