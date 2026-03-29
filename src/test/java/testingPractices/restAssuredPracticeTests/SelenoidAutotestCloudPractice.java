package testingPractices.restAssuredPracticeTests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SelenoidAutotestCloudPractice {
// @Test
//   public void getStatus() {
//     given()
//             .log().uri() // выводит урл вызываемый Request URI:	https://selenoid.autotests.cloud/status
//             .get("https://selenoid.autotests.cloud/status")
//             .then()
//             .body("total", is(5))     ;
// }
//
// @Test
//     public void verifyFirefoxVersion124IsAvailable() {
//         given()
//                 .when()
//                 .get("https://selenoid.autotests.cloud/status")
//                 .then()
//                 .statusCode(200)
//                 .body("browsers.firefox", hasKey("124.0"))
//                 .body("browsers.firefox.size()", is(2))  // проверка что всего 2 версии firefox
//                 .body("browsers.firefox.keySet()", containsInAnyOrder("124.0", "125.0"))
//                 .log().all();  // выведет ответ в консоль для наглядности
//     }
//    @Test
//    public void fullExample() {
//        given()
//                // ПОДГОТОВКА ЗАПРОСА
//                .header("Content-Type", "application/json")  // добавляем заголовок
//                .param("key", "value")                        // добавляем параметр
//                .cookie("sessionId", "12345")                 // добавляем куку
//                .body("{ \"name\": \"John\" }")                // добавляем тело запроса
//                .log().all()                                   // логируем всё что отправили
//
//                .when()
//                // ДЕЙСТВИЕ
//                .post("https://api.example.com/users")        // отправляем POST запрос
//
//                .then()
//                // ПРОВЕРКИ ОТВЕТА
//                .statusCode(201)                               // проверяем статус
//                .header("Content-Type", "application/json")    // проверяем заголовок
//                .body("id", notNullValue())                    // проверяем поле в ответе
//                .body("name", equalTo("John"))                  // проверяем имя
//                .time(lessThan(2000L))                          // проверяем время ответа
//                .log().body();                                  // логируем ответ
//    }
    //1. Отправка куки в запросе:

//    given()
//    .cookie("sessionId", "12345")              // простая кука
//    .cookie("theme", "dark")                    // еще одна кука
//    .cookie("language", "ru", "en")              // кука с несколькими значениями
//.when()
//    .get("/profile")

// 2.Получаем куку из ответа
//Response response = given()
//        .when()
//        .post("/login")
//        .then()
//        .extract()        // говорим "сохрани мне ответ"
//    .response();      // забираем весь ответ
//
//    String sessionId = response.getCookie("sessionId");  // извлекаем куку
//
//    // Теперь можно использовать response для чего угодно:
//String name = response.path("name");        // получить поле из JSON
//String cookie = response.getCookie("sid");  // получить куку
//String header = response.getHeader("Content-Type");  // получить заголовок
//int statusCode = response.statusCode();     // получить статус
//long time = response.time();                // получить время ответа

//    @Test
//            //1. Сохранение данных для следующих запросов:
//    public void createAndGetUser() {
//        // Создаем пользователя и сохраняем его ID
//        Response createResponse = given()
//                .body("{ \"name\": \"John\", \"email\": \"john@test.com\" }")
//                .when()
//                .post("/api/users")
//                .then()
//                .statusCode(201)
//                .extract().response();
//
//        String userId = createResponse.path("id");  // извлекаем созданный ID
//
//        // Используем ID в следующем запросе
//        given()
//                .pathParam("id", userId)
//                .when()
//                .get("/api/users/{id}")
//                .then()
//                .statusCode(200)
//                .body("name", is("John"));
//    }

   // Полезные методы Response:

//    Response response = given().get("/api/data");
//
//// Получение данных
//response.path("user.name")              // JSON путь
//        response.jsonPath().getString("name")   // через JsonPath
//response.xmlPath().getString("//name")  // через XmlPath
//response.getBody().asString()            // тело как строка
//response.getBody().prettyPrint()         // красиво отформатированное тело
//
//// Метаданные
//response.getStatusCode()                  // статус код
//        response.getContentType()                 // тип контента
//        response.getHeaders()                      // все заголовки
//        response.getCookies()                      // все куки
//        response.getTime()                          // время выполнения
//
//// Проверки
//        response.then().statusCode(200)            // можно и так проверять
 }


