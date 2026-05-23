package testingPractices.dogsPractice;

import java.util.Random;

/**
 * Реализация VolfRandomizer, которая генерирует случайные характеристики волка.
 */
public class RandomVolfRandomizer implements VolfRandomizer {
    private static final Random random = new Random();
    private static final String[] COLORS = {"White", "Grey", "Black", "Brown"};
    private static final String[] TAILS = {"Long", "Short", "Curly", "Straight"};

    @Override
    public String randomColor() {
        return COLORS[random.nextInt(COLORS.length)];
    }

    @Override
    public String randomTail() {
        return TAILS[random.nextInt(TAILS.length)];
    }

    @Override
    public String randomBreed() {
        return "Grey"; // волки всегда серой породы
    }
}
