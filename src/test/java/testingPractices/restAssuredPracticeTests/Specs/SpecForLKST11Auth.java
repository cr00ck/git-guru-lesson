package testingPractices.restAssuredPracticeTests.Specs;

import io.qameta.allure.Step;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpecForLKST11Auth {

    public static final String BASE_URL = "https://t11.ranepa.ru";
    private static final String USERNAME = System.getProperty("ranepa.login", System.getenv("RANEPA_LOGIN"));
    private static final String PASSWORD = System.getProperty("ranepa.password", System.getenv("RANEPA_PASSWORD"));

    private static String accessToken;
    private static boolean isLoggedIn = false;

    @Step("Авторизация и получение токена доступа")
    public static void loginGetToken() {
        if (isLoggedIn) {
            System.out.println("✅ Уже авторизованы");
            return;
        }

        Response response = given()
                .filter(new AllureRestAssured()) // чтобы в отчете были curl
                .contentType(ContentType.MULTIPART)
                .multiPart("login", USERNAME)
                .multiPart("password", PASSWORD)
                .multiPart("remember_me", "true")
                .when()
                .post(BASE_URL + "/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .response();

        accessToken = response.path("access_token");
        assertNotNull(accessToken, "Access token не должен быть null");

        isLoggedIn = true;
        System.out.println("✅ Успешный логин!");
    }

    public static RequestSpecification getAuthenticatedSpec() { //RequestSpecification- это интерфейс из библиотеки REST Assured, который представляет собой спецификацию (настройки) HTTP запроса.
        if (!isLoggedIn) {
            loginGetToken();
        }

        // ПРОСТОЙ ВАРИАНТ: используем given() напрямую
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + accessToken)
                .header("Version", "1")
                .filter(new AllureRestAssured())  // ← один раз здесь!
                .log().all();
    }
}