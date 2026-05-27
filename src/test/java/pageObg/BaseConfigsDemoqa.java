package pageObg;

import com.codeborne.selenide.Configuration;

/**
 * Конфигурация для тестов demoqa.com.
 * Отличается от BaseConfigs только таймаутом (15s вместо 10s).
 */
public class BaseConfigsDemoqa extends BaseConfigs {

    static {
        Configuration.timeout = 15000;
    }

    // Пустой метод для обратной совместимости
    public static void setUp() {
        System.out.println("BaseConfigsDemoqa.setUp() called - configuration already initialized");
    }
}
