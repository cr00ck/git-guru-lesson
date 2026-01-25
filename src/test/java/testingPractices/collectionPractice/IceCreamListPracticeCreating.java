package testingPractices.collectionPractice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IceCreamListPracticeCreating {
    // чтобы удалить повторяющиеся элементы, может LIST переложить в в Set, потом обратно в List
    public List<String> icecreamList;
    //List.of() - создание неизменяемого списка java, поэтому надо работать с add и другими методами
    //List<String> immutable = List.of("a", "b", "c");
    // immutable.add("d"); // Будет исключение!

    //создаем конструктор для листа(как название как класс)
    public IceCreamListPracticeCreating(String[] icecreamArray) {
        icecreamList = new ArrayList<>();
        for (int i = 0; i < icecreamArray.length; i++) {
            icecreamList.add(icecreamArray[i]);
        }

    }

    // 1. Вывод всех вкусов
    public void icecreamAllFlavors() {
        System.out.println("Всего у нас '" + icecreamList.size()+ "' вкусов");  // количество элементов
        if (icecreamList.isEmpty()) {
            System.out.println("Список пуст");
            return; // return тут значит выйти из цикла
        }
        icecreamList.sort(Comparator.naturalOrder());
        System.out.println("Все вкусы мороженного: ");
        for (int i = 0; i < icecreamList.size(); i++) {
            System.out.println(i + 1 + ". " + icecreamList.get(i));
            //System.out.println();
        }
    }

    // Метод: Кастомный toString только для списка вкусов
    public String flavorsToString() {
        return "Список вкусов: " + icecreamList.toString();
    }

    // 2. Метод для проверки наличия (с учетом регистра)
        public boolean findIcecreamName(String name) {
            for (String flavor : icecreamList) {
                if (flavor.equalsIgnoreCase(name)) {
                    System.out.println("✅ Мороженное '" + flavor + "' есть в наличии");

                    return true;
                }
            }
            System.out.println("❌ К сожалению, вкуса '" + name + "' нет в наличии");
            return false;
        }

    // 3. Добавление нового элемента
    public void addNewIcecream(String name) {
        for (String flavor : icecreamList) {
            // Проверяем, нет ли уже такого вкуса (без учета регистра)
            if (flavor.equalsIgnoreCase(name)) {
                System.out.println("⚠️ Вкус '" + name + "' уже есть в списке как '" + flavor + "'\n");
                return;
            }
        }
        // Добавляем в том регистре, в котором передали
        icecreamList.add(name.toLowerCase());
        System.out.println("✅Новый вкус мороженного " + name + " добавлен в коллекцию\n");
        icecreamAllFlavors();
    }


    // 3. Удаление элемента
    public boolean deleteIcecream(String name) {
       String flavorToDelete = null;
        for (String flavor : icecreamList){
            // кладем в переменную, т.к. нужен игнор кейс, а remove() требует точного совпадения
            if(flavor.equalsIgnoreCase(name)){
                flavorToDelete = flavor;
                break; // выходим из цикла тк зачем дальше искть если уже нашли
            }
        } if (flavorToDelete != null) {
                icecreamList.remove(flavorToDelete); //
                System.out.println("✅ Вкус мороженного '" + name + "' удален из коллекции\n");
                icecreamAllFlavors();
                return true;
        }
            else {
                System.out.println("❌ Вкус мороженного '" + name + "' не наден! И не удален из коллекции\n");
                icecreamAllFlavors();
            return false;

        }
    }

}
// Вставить в MAIN
//    IceCreamListPracticeCreating ice = new IceCreamListPracticeCreating(new String[]{
//            "fruit-ice",
//            "magnat",
//            "bounty",
//            "twix",
//            "snickers",
//            "Oreo",
//            "Mars"
//    });
//         ice.icecreamAllFlavors();





