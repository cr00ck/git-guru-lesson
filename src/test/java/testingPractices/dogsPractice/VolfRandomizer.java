package testingPractices.dogsPractice;

/**
 * Интерфейс для рандомизации характеристик волка.
 * Позволяет легко заменять стратегию генерации (например, для тестирования).
 */
public interface VolfRandomizer {

    /**
     * Генерирует случайный цвет волка.
     */
    String randomColor();

    /**
     * Генерирует случайный тип хвоста волка.
     */
    String randomTail();

    /**
     * Генерирует случайную породу волка.
     */
    String randomBreed();
}
