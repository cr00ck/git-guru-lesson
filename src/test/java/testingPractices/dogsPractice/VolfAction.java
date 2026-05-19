package testingPractices.dogsPractice;

import java.util.Random;

/**
 * Класс, который создаёт волка, выводит его характеристики
 * и выполняет его случайное действие.
 */
public class VolfAction {
    private static final Random random = new Random();

    /**
     * Создаёт волка и возвращает его.
     */
    public static Volf createVolf() {
        Volf volf = new Volf();
        // Устанавливаем случайные характеристики
        String[] colors = {"White", "Grey", "Black", "Brown"};
        String[] tails = {"Long", "Short", "Curly", "Straight"};
        volf.setColor(colors[random.nextInt(colors.length)]);
        volf.setTail(tails[random.nextInt(tails.length)]);
        volf.setBreed("Grey"); // волки всегда серой породы
        return volf;
    }

    /**
     * Выводит характеристики волка на экран.
     */
    public static void printVolfInfo(Volf volf) {
        System.out.println("=== Информация о волке ===");
        System.out.println("Порода: " + volf.getBreed());
        System.out.println("Цвет:   " + volf.getColor());
        System.out.println("Хвост:  " + volf.getTail());
        System.out.println("==========================");
    }

    /**
     * Выполняет случайное действие волка.
     */
    public static void volfDoAction(Volf volf) {
        System.out.println("\nВолк выполняет действие:");
        volf.randomAct();
    }

    /**
     * Главный метод: создаёт волка, выводит инфо и выполняет действие.
     */
    public static void main(String[] args) {
        // Создаём волка
        Volf volf = createVolf();

        // Выводим информацию о волке
        printVolfInfo(volf);

        // Выполняем его действие
        volfDoAction(volf);
    }
}
