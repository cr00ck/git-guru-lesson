package testingPractices.dogsPractice;

/**
 * Класс, который создаёт волка, выводит его характеристики
 * и выполняет его случайное действие.
 */
public class VolfAction {
    private final VolfRandomizer randomizer;

    public VolfAction() {
        this(new RandomVolfRandomizer());
    }

    public VolfAction(VolfRandomizer randomizer) {
        this.randomizer = randomizer;
    }

    /**
     * Создаёт волка и возвращает его.
     */
    public Volf createVolf() {
        Volf volf = new Volf();
        // Устанавливаем случайные характеристики через рандомизер
        volf.setColor(randomizer.randomColor());
        volf.setTail(randomizer.randomTail());
        volf.setBreed(randomizer.randomBreed());
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
        VolfAction action = new VolfAction();

        // Создаём волка
        Volf volf = action.createVolf();

        // Выводим информацию о волке
        printVolfInfo(volf);

        // Выполняем его действие
        volfDoAction(volf);
    }
}
