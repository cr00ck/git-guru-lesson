package testingPractices.dogsPractice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для VolfRandomizer и VolfAction.
 * Проверяют, что рандомизация вынесена в отдельный интерфейс
 * и может быть заменена для тестирования.
 */
class VolfRandomizerTest {

    @Test
    void testRandomVolfRandomizerGeneratesValidColors() {
        VolfRandomizer randomizer = new RandomVolfRandomizer();
        String color = randomizer.randomColor();
        assertTrue(color.equals("White") || color.equals("Grey") || color.equals("Black") || color.equals("Brown"));
    }

    @Test
    void testRandomVolfRandomizerGeneratesValidTails() {
        VolfRandomizer randomizer = new RandomVolfRandomizer();
        String tail = randomizer.randomTail();
        assertTrue(tail.equals("Long") || tail.equals("Short") || tail.equals("Curly") || tail.equals("Straight"));
    }

    @Test
    void testRandomVolfRandomizerAlwaysReturnsGreyBreed() {
        VolfRandomizer randomizer = new RandomVolfRandomizer();
        assertEquals("Grey", randomizer.randomBreed());
    }

    @Test
    void testCreateVolfWithRandomVolfRandomizer() {
        VolfRandomizer randomizer = new RandomVolfRandomizer();
        VolfAction action = new VolfAction(randomizer);
        Volf volf = action.createVolf();

        assertNotNull(volf);
        assertEquals("Grey", volf.getBreed());
        assertNotNull(volf.getColor());
        assertNotNull(volf.getTail());
    }

    @Test
    void testCreateVolfWithStubRandomizer() {
        // Создаем стаб-рандомизатор с фиксированными значениями
        VolfRandomizer stubRandomizer = new VolfRandomizer() {
            @Override
            public String randomColor() {
                return "White";
            }

            @Override
            public String randomTail() {
                return "Long";
            }

            @Override
            public String randomBreed() {
                return "Grey";
            }
        };

        VolfAction action = new VolfAction(stubRandomizer);
        Volf volf = action.createVolf();

        assertEquals("White", volf.getColor());
        assertEquals("Long", volf.getTail());
        assertEquals("Grey", volf.getBreed());
    }
}
