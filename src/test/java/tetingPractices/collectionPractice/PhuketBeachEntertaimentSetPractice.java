package tetingPractices.collectionPractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class PhuketBeachEntertaimentSetPractice {
    public Set<String> phuketBeachEntertaimentSet;

    //Set (множество) - коллекция, которая не хранит дубликаты и не гарантирует порядок элементов (кроме LinkedHashSet и TreeSet).
//    TreeSet НЕ может содержать null, hashSet может только один null,
    //второй будет дублем и не запишется
//    Set<String> treeSet = new TreeSet<>();
//treeSet.add(null);  // NullPointerException!
    public PhuketBeachEntertaimentSetPractice(String[] entertaimentValues) {
        this.phuketBeachEntertaimentSet = new HashSet<>();
        for (int i = 0; i < entertaimentValues.length; i++) {
            this.phuketBeachEntertaimentSet.add(entertaimentValues[i]);
        }
    }

    // Вывод всех мероприятий
    public void allEntertaiments() {
        System.out.println("Вот все активности : ");
        for (String all : phuketBeachEntertaimentSet) {
            System.out.println("=> " + all);
        }
        ;
    }

    // Добавление нового развлечения
    public void addlPhuketBeachEntertaiments(String newName) {
        boolean isContained = false;
        for (String kind : phuketBeachEntertaimentSet) {
            if (kind.equalsIgnoreCase(newName)) {
                isContained = true;
                break;
            }
        }
        if (isContained) {
            System.out.println("Мероприятие '" + newName + "' уже есть в списке");
        } else {
            phuketBeachEntertaimentSet.add(newName.toLowerCase());
            System.out.println("Новое развлечение '" + newName + "' добавлено в список развлечений");
        }
        allEntertaiments();

    }
    // Поиск развлечения по имени
    public void searchPhuketBeachEntertaiments (String serchName){
        for(String all : phuketBeachEntertaimentSet) {
            if (all.contentEquals(serchName)) {
                System.out.println("Мероприятие '" + serchName + "' есть в списке мероприятий");
                return;
            }
        }
            System.out.println("Мероприятие '"+serchName+ "' нет в списке мероприятий");
    }

    // Удаление мероприятия
    public void deletePhuketBeachEntertaiments(String value) {
        // Ищем элемент (без учета регистра)
        String toDelete = null;
        for (String name : phuketBeachEntertaimentSet) {
            if (name.equalsIgnoreCase(value)) {  // ← Проверяем РАВЕНСТВО!
                toDelete = name;  // Запоминаем оригинальное название
                break;
            }
        }

        if (toDelete != null) {
            phuketBeachEntertaimentSet.remove(toDelete);  // Удаляем по оригинальному имени
            System.out.println("✅ Мероприятие '" + toDelete + "' успешно удалено!");
        } else {
            System.out.println("❌ Мероприятие '" + value + "' не найдено!");
        }
        allEntertaiments();
        System.out.println("");
    }

}
// to MAIN
//    PhuketBeachEntertaimentSetPractice phuket = new PhuketBeachEntertaimentSetPractice(new String[]{
//            "swimming","sunbathing", "water ski","relaxing","readind"
//    });
//          phuket.allEntertaiments();
//                  phuket.addlPhuketBeachEntertaiments("sex");
//                  phuket.addlPhuketBeachEntertaiments("sex");
//                  phuket.deletePhuketBeachEntertaiments("sex");
//                  phuket.deletePhuketBeachEntertaiments("sunbathing");
//                  phuket.searchPhuketBeachEntertaiments("swimming");
//                  phuket.searchPhuketBeachEntertaiments("swimmIIng");
//                  phuket.searchPhuketBeachEntertaiments("swimmng ");
//



