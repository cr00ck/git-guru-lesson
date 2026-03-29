package testingPractices.restAssuredPracticeTests;

import io.qameta.allure.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Feature("API тесты портала Т11")
@Story("Основные сценарии работы с API")
@DisplayName("Тесты API портала Т11")
@Owner("vysokikh-mm")  // кто автор тестов
@Link(name = "T11 Portal", url = "https://t11.ranepa.ru")
public class RanepaApiPractice {

    private static String accessToken;      // ← сохраняем токен
    private static String refreshToken;     // ← сохраняем refresh токен
    private static final String BASE_URL = "https://t11.ranepa.ru";
    private static final String USERNAME = "aandreev-21-02";  // ваш логин
    private static final String PASSWORD = "Zw27o543";        // ваш пароль


    @BeforeAll  // ← ВАЖНО: выполняется 1 раз перед ВСЕМИ тестами
    @Step("Авторизация и получение токена доступа")
    static void login() {
        Response response =
        given()
                .contentType(ContentType.MULTIPART)
                .multiPart("login", USERNAME)
                .multiPart("password", PASSWORD)
                .multiPart("remember_me", "true")
                .log().all()
        .when()
                .post(BASE_URL + "/auth/login")
        .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();

        // Сохраняем токены из ответа
        accessToken = response.path("access_token");
        refreshToken = response.path("refresh_token");

        // Проверяем что токены получены
        assertNotNull(accessToken, "Access token не должен быть null");
        assertNotNull(refreshToken, "Refresh token не должен быть null");

        System.out.println("✅ Успешный логин!");
    }

    /**
     * Вспомогательный метод для создания авторизованного запроса
     */
    private static RequestSpecification authenticatedRequest() {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + accessToken)  // ← добавляем токен
                .header("Version", "1.1")
                .log().all();  // для отладки
    }

    @Story("Авторизация и аутентификация")
    @DisplayName("Проверка авторизации - получение данных пользователя")
    @Severity(SeverityLevel.BLOCKER)  // критичный тест
    @Description("Проверяем что после авторизации возвращаются корректные данные пользователя")
    @Test
    void authTestAuth (){
        authenticatedRequest()
                .get("/auth/test_auth")
                .then()
                .log().all()
                .statusCode(200)
                .body("user_id", is("52"))
                .body("user_login", is("aandreev-21-02"));
    }

    @Story("AI Агрегатор - Статистика форм")
    @DisplayName("Получение статистики по формам AI агрегатора")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверяем получение статистики заполнения форм в AI агрегаторе")
    @Test
    void aiAggregatorFormStats (){
        authenticatedRequest()
                .get("/ou/user/ai_aggregator/form_stats")
                .then()
                .log().all()
                .statusCode(200)
                .body("items", notNullValue())
                .body("items.name", everyItem(notNullValue()))  // проверяем все имена
                .body("items.count", everyItem(notNullValue()));  // проверяем все count
    }
    @Story("AI Агрегатор - Статистика промптов")
    @DisplayName("Получение статистики по промптам")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void aiPromptFormStats (){
        authenticatedRequest()
                .get("/ou/user/ai_prompt/form_stats")
                .then()
                .log().all()
                .statusCode(200)
                .body("items", notNullValue())
                .body("items.name", everyItem(notNullValue()))  // проверяем все имена
                .body("items.count", everyItem(notNullValue()));  // проверяем все count
    }
    @Story("AI Агрегатор - Статистика обратной связи")
    @DisplayName("Получение статистики по обратной связи")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void aiFeedbackFormStats (){
        authenticatedRequest()
                .get("/ou/user/ai_feedback/form_stats")
                .then()
                .log().all()
                .statusCode(200)
                .body("items", notNullValue())
                .body("items.name", everyItem(notNullValue()))  // проверяем все имена
                .body("items.count", everyItem(notNullValue()));  // проверяем все count
    }


}
