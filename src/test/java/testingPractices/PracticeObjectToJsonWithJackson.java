package testingPractices;

import JsonModelClasses.DataForJsonCompilingThroughJackson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class PracticeObjectToJsonWithJackson {
    //private ClassLoader cl = PracticeObjectToJsonWithJackson.class.getClassLoader();

    @Test
    void fromObjectToJsonWithJackson() throws Exception {
        ObjectMapper om = new ObjectMapper();

        // Create and populate the object
        DataForJsonCompilingThroughJackson person = new DataForJsonCompilingThroughJackson();
        person.setAge(25);
        person.setGender("male");
        person.setLastname("Green");
        person.setHairColor("black");
        person.setHobbies(Arrays.asList("Ps", "coding", "reading", "cooking"));
        person.setName("Mike");

        String json = om.writeValueAsString(person);
        System.out.println(json);

        String prettyJson = om.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(prettyJson);

    }

    @Test
    void fromJsonToObjectWithJackson() throws Exception {
       try(InputStream is = getClass().getResourceAsStream("/json/VkLayout.json")){
           ObjectMapper om = new ObjectMapper();
            JsonNode jn = om.readTree(is);
            String prettyJson = jn.toPrettyString();
           System.out.println(prettyJson);

           assertThat(jn.has("title")).isTrue();//для assertJ подключаем testImplementation 'org.assertj:assertj-core:3.25.3 и testImplementation 'net.javacrumbs.json-unit:json-unit-assertj:3.2.2
// Проверка всех корневых полей
           assertThat(jn.fieldNames())
                   .toIterable()//В старых версиях AssertJ (до 3.14.0) для Iterator не было метода containsExactlyInAnyOrder(),toIterable() конвертирует Iterator в Iterable, у которого больше методов. В новых версиях AssertJ добавили больше методов для IteratorAssert
                   .containsExactlyInAnyOrder(
                           "id",
                           "data_type",
                           "layout",
                           "badge",
                           "title"
                   );
// === 2. ПРОВЕРКА ПРОСТЫХ ПОЛЕЙ ===
           assertThat(jn.get("id").asText())
                   .describedAs("Поле id")
                   .isNotNull()
                   .isNotEmpty()
                   .hasSize(64) // или проверьте длину
                   .matches("^[A-Z0-9]+$"); // только заглавные буквы и цифры

           assertThat(jn.get("data_type").asText())
                   .describedAs("Поле data_type")
                   .isEqualTo("none");
//           asText() критически важен при работе с JsonNode. Вот почему:
//           Что такое JsonNode?
//                   JsonNode - это обертка Jackson для любого JSON значения. Он может быть:
//           Объектом ({...})
//           Массивом ([...])
//           Строкой ("text")
//           Числом (123, 45.67)
//           Булевым (true, false)
//           Null (null)
           //JsonNode node = jn.get("title");
// ✅ ПРАВИЛЬНО - получаем значение как String
           //String textValue = node.asText();
// "Приглашения" - чистая строка без кавычек
// ❌ toString() - возвращает JSON представление
           //String jsonString = node.toString();
// "\"Приглашения\"" - с экранированными кавычками
// ✅ asText() с default значением
           //String safeValue = node.asText("default");
// вернет "default" если node null или missing
           assertThat(jn.get("title").asText())
                   .describedAs("Поле title")
                   .isEqualTo("Приглашения");
// === 3. ПРОВЕРКА ВЛОЖЕННОГО ОБЪЕКТА layout ===
           assertThat(jn.has("layout")).isTrue();
           JsonNode layout = jn.get("layout");

           assertThat(layout)
                   .describedAs("Объект layout")
                   .hasSize(2); // 2 поля: name и title

           assertThat(layout.get("name").asText())
                   .describedAs("layout.name")
                   .isEqualTo("header");

           assertThat(layout.get("title").asText())
                   .describedAs("layout.title")
                   .isEqualTo("Приглашения");
// Проверка типов данных
           JsonNode badge = jn.get("badge");
           assertThat(jn.get("id").isTextual()).isTrue();
           assertThat(jn.get("data_type").isTextual()).isTrue();
           assertThat(badge.get("text").isTextual()).isTrue();
           assertThat(badge.get("text").isInt()).isTrue();
           assertThat(badge.get("text").asInt())
                   .describedAs("badge.text")
                   .isEqualTo(191);

       }



    }
//    @Test
//    void jsonManipulationMethods() throws Exception {
//        ObjectMapper om = new ObjectMapper();
//
//        // === 1. СОЗДАНИЕ JSON С НУЛЯ ===
//        System.out.println("=== Создание JSON ===");
//
//        ObjectNode rootNode = om.createObjectNode();
//
//        // put() - добавление простых значений
//        rootNode.put("name", "John");
//        rootNode.put("age", 30);
//        rootNode.put("active", true);
//        rootNode.putNull("middleName");
//
//        // putObject() - создание вложенного объекта
//        ObjectNode address = rootNode.putObject("address");
//        address.put("city", "New York");
//        address.put("zip", "10001");
//
//        // putArray() - создание массива
//        ArrayNode hobbies = rootNode.putArray("hobbies");
//        hobbies.add("reading");
//        hobbies.add("gaming");
//        hobbies.add("hiking");
//
//        System.out.println("Созданный JSON:");
//        System.out.println(rootNode.toPrettyString());
//
//        // === 2. МОДИФИКАЦИЯ СУЩЕСТВУЮЩЕГО JSON ===
//        System.out.println("\n=== Модификация JSON ===");
//
//        // Добавление поля
//        rootNode.put("newField", "newValue");
//
//        // Удаление поля
//        rootNode.remove("middleName");
//
//        // Замена значения
//        rootNode.put("age", 31);
//
//        // Добавление в массив
//        hobbies.add("swimming");
//
//        System.out.println("После модификации:");
//        System.out.println(rootNode.toPrettyString());
//
//        // === 3. КОПИРОВАНИЕ И СРАВНЕНИЕ ===
//        System.out.println("\n=== Копирование ===");
//
//        // deepCopy() - глубокая копия
//        ObjectNode copy = rootNode.deepCopy();
//        copy.put("name", "Copy");
//
//        System.out.println("Оригинал name: " + rootNode.get("name"));
//        System.out.println("Копия name: " + copy.get("name"));
//
//        // equals() - сравнение
//        System.out.println("equals: " + rootNode.equals(copy));
//
//        // === 4. ПОИСК И ФИЛЬТРАЦИЯ ===
//        System.out.println("\n=== Поиск ===");
//
//        // findValue() - поиск по значению (рекурсивно)
//        JsonNode found = rootNode.findValue("reading");
//        System.out.println("findValue('reading'): " + found);
//
//        // findValues() - все найденные значения
//        List<JsonNode> values = rootNode.findValues("hobbies");
//        System.out.println("findValues('hobbies'): " + values.size() + " результатов");
//
//        // findParent() - найти родителя
//        if (found != null) {
//            JsonNode parent = rootNode.findParent("hobbies");
//            System.out.println("findParent('hobbies'): " + parent);
//        }
//    }
}

