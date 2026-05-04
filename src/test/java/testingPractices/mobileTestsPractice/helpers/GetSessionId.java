package testingPractices.mobileTestsPractice.helpers;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import io.restassured.response.Response;
// ссылка для получения видео из BrowserStack куда надо вставить sessionId
//https://api.browserstack.com/automate/sessions/<session-id>.json

public class GetSessionId {

    @Test
    public String getSessionId() throws IOException {
        Response response = given().log().all()
                .when()
                .get("https://app-automate.browserstack.com/qig/get-started")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        String sessionId = response.path("sessionId");
        System.out.println("Session ID: " + sessionId);
        return sessionId;
    }
}

