package testingPractices.collectionPractice;

import java.util.HashMap;
import java.util.Map;

public class FeelingsHashMapPractice {
    public Map<String, Integer> feelings;
    // конструктор для MAP c итерацией по entrySet()

    public FeelingsHashMapPractice() {
        this.feelings = new HashMap<>();
    }
    // добаление нового чувства  и интенсивности
    public void addFeelings(String feeling, int intencity) {
        if (feeling.isEmpty()) {
            System.out.println("надо заполнить чувство и интенсивность");
            return;
        }
        this.feelings.put(feeling.trim(), intencity);
        System.out.println("Чувство '" + feeling + "' добавлено, с интенсивностью '" + intencity+"'");
        allFeelings();
    }
    // вывод всех чувств  и интенсивности
    public void allFeelings (){
        System.out.println("Вот какие сейчас уже есть чувства и их интенсивность: ");
        int i =1;
        for(Map.Entry<String, Integer> entry: feelings.entrySet()){
            String key = entry.getKey();
           int value = entry.getValue();
            System.out.println(i++ +". " +key+" интенсивность - "+value);
        }
    }
    // удалить чувство и интенсивность
    public void deleteFeelingIgnoreCase(String feeling) {
        if (feeling == null || feeling.trim().isEmpty()) {
            System.out.println("Ошибка: пустое название");
            return;
        }

        String feelingToDelete = null;
        int intensity = 0;

        // Ищем чувство (без учета регистра)
        for (Map.Entry<String, Integer> entry : feelings.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(feeling)) {
                feelingToDelete = entry.getKey();  // Оригинальное название
                intensity = entry.getValue();
                break;
            }
        }

        if (feelingToDelete != null) {
            feelings.remove(feelingToDelete);
            System.out.println("✅ Удалили чувство '" + feelingToDelete + "' с интенсивностью (" + intensity +")");
            allFeelings();
        } else {
            System.out.println("❌ Чувство '" + feeling + "' не найдено");
        }
    }
}
// to MAIN
//    FeelingsHashMapPractice feel = new FeelingsHashMapPractice();
//          feel.addFeelings("smile",8);
//                  feel.addFeelings("cry",1);
//                  feel.addFeelings("anvy",3);
//                  feel.addFeelings("jelous",4);
//                  feel.deleteFeelingIgnoreCase("anvY");
//                  feel.deleteFeelingIgnoreCase("anvb");