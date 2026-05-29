package testingPractices.yandexTracker;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * API-клиент для Яндекс Трекера.
 * <p>
 * Использует OAuth-токен и ID организации из переменных окружения:
 * <pre>
 *   YANDEX_TRACKER_TOKEN   — OAuth-токен
 *   YANDEX_TRACKER_ORG_ID  — ID организации (X-Org-Id)
 * </pre>
 * Также поддерживает передачу через JVM-свойства: {@code yt.token}, {@code yt.orgId}.
 */
public class YandexTrackerApi {

    private static final String BASE_URL = "https://api.tracker.yandex.net/v2";

    private static final String TOKEN =
            System.getProperty("yt.token", System.getenv("YANDEX_TRACKER_TOKEN"));
    private static final String ORG_ID =
            System.getProperty("yt.orgId", System.getenv("YANDEX_TRACKER_ORG_ID"));

    static {
        if (TOKEN == null || TOKEN.isBlank()) {
            System.err.println("⚠️ YANDEX_TRACKER_TOKEN не задан! " +
                    "Установи переменную окружения или JVM-свойство yt.token");
        }
        if (ORG_ID == null || ORG_ID.isBlank()) {
            System.err.println("⚠️ YANDEX_TRACKER_ORG_ID не задан! " +
                    "Установи переменную окружения или JVM-свойство yt.orgId");
        }
    }

    private static RequestSpecification spec() {
        return given()
                .filter(new AllureRestAssured())
                .baseUri(BASE_URL)
                .header("Authorization", "OAuth " + TOKEN)
                .header("X-Org-Id", ORG_ID)
                .contentType(ContentType.JSON)
                .log().method()
                .log().uri();
    }

    /**
     * Получить задачи, назначенные на указанного пользователя.
     *
     * @param queue   очередь (например "MFC") или null, если по всем очередям
     * @param uid     UID пользователя (числовой) или null для всех задач
     * @param max     максимальное количество задач
     * @return Response с задачами
     */
    @Step("Получаем задачи из очереди {queue}, назначенные на пользователя {uid}")
    public static Response getIssues(String queue, String uid, int max) {
        StringBuilder filter = new StringBuilder("?pageSize=" + max);

        if (queue != null && !queue.isBlank()) {
            filter.append("&queue=").append(queue);
        }
        if (uid != null && !uid.isBlank()) {
            filter.append("&assignee=").append(uid);
        }

        return spec()
                .when()
                .get("/issues" + filter)
                .then()
                .log().status()
                .extract()
                .response();
    }

    /**
     * Получить информацию о текущем пользователе (вызвавшем API).
     *
     * @return Response с данными пользователя (uid, login, firstName и т.д.)
     */
    @Step("Получаем информацию о текущем пользователе")
    public static Response getCurrentUser() {
        return spec()
                .when()
                .get("/myself")
                .then()
                .log().status()
                .extract()
                .response();
    }

    /**
     * Получить детальную информацию о задаче по её ключу.
     *
     * @param issueKey ключ задачи (например "MFC-1")
     * @return Response с данными задачи
     */
    @Step("Получаем задачу {issueKey}")
    public static Response getIssue(String issueKey) {
        return spec()
                .when()
                .get("/issues/" + issueKey)
                .then()
                .log().status()
                .extract()
                .response();
    }

    /**
     * Получить комментарии к задаче.
     *
     * @param issueKey ключ задачи (например "MFC-1")
     * @return Response со списком комментариев
     */
    @Step("Получаем комментарии к задаче {issueKey}")
    public static Response getComments(String issueKey) {
        return spec()
                .when()
                .get("/issues/" + issueKey + "/comments")
                .then()
                .log().status()
                .extract()
                .response();
    }

    /**
     * Проверить, настроен ли клиент (есть ли токен и Org-ID).
     */
    public static boolean isConfigured() {
        return TOKEN != null && !TOKEN.isBlank()
                && ORG_ID != null && !ORG_ID.isBlank();
    }
}
