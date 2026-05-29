package testingPractices.yandexTracker;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Яндекс Трекер API")
class YandexTrackerApiTest {

    private static String currentUserUid;
    private static String firstIssueKey;

    @BeforeAll
    static void setUp() {
        assertTrue(YandexTrackerApi.isConfigured(),
                "Настрой трекер: export YANDEX_TRACKER_TOKEN=... и YANDEX_TRACKER_ORG_ID=...");

        // Получаем UID текущего пользователя для дальнейших запросов
        Response myself = YandexTrackerApi.getCurrentUser();
        assertEquals(200, myself.statusCode());
        currentUserUid = myself.jsonPath().getString("uid");
        assertNotNull(currentUserUid, "Не удалось получить UID пользователя");
        System.out.println("👤 Текущий пользователь: " + myself.jsonPath().getString("login")
                + " (" + myself.jsonPath().getString("firstName") + " "
                + myself.jsonPath().getString("lastName") + "), UID=" + currentUserUid);

        // Получаем первую задачу из MFC для динамических тестов
        Response issues = YandexTrackerApi.getIssues("MFC", currentUserUid, 1);
        if (issues.statusCode() == 200) {
            List<String> keys = issues.jsonPath().getList("key");
            if (keys != null && !keys.isEmpty()) {
                firstIssueKey = keys.get(0);
                System.out.println("📋 Первая задача: " + firstIssueKey);
            }
        }
    }

    @Test
    @DisplayName("Проверка авторизации — запрос /myself")
    void checkAuth() {
        Response response = YandexTrackerApi.getCurrentUser();

        assertEquals(200, response.statusCode(), "Не удалось получить информацию о пользователе");
        assertNotNull(response.jsonPath().getString("uid"), "UID не должен быть пустым");
        System.out.println("✅ Авторизация работает! Логин: " + response.path("login"));
    }

    @Test
    @DisplayName("Получение задач из очереди MFC, назначенных на меня")
    void getMyIssues() {
        assertNotNull(currentUserUid, "UID не определён");

        Response response = YandexTrackerApi.getIssues("MFC", currentUserUid, 5);

        assertEquals(200, response.statusCode(), "Не удалось получить список задач");
        List<String> keys = response.jsonPath().getList("key");
        System.out.println("✅ Найдено задач, назначенных на меня в MFC: " + (keys != null ? keys.size() : 0));
        if (keys != null) {
            for (String key : keys) {
                String summary = response.jsonPath().getString("find { it.key == '" + key + "' }.summary");
                System.out.println("   - " + key + ": " + (summary != null ? summary : ""));
            }
        }
    }

    @Test
    @DisplayName("Получение комментариев к первой задаче из MFC")
    void getIssueComments() {
        assertNotNull(firstIssueKey, "Нет доступных задач для проверки комментариев");

        Response response = YandexTrackerApi.getComments(firstIssueKey);

        assertEquals(200, response.statusCode(), "Не удалось получить комментарии к " + firstIssueKey);
        List<String> texts = response.jsonPath().getList("text");
        System.out.println("✅ Комментариев к " + firstIssueKey + ": " + (texts != null ? texts.size() : 0));
        if (texts != null && !texts.isEmpty()) {
            System.out.println("   Первый комментарий: " + texts.get(0).substring(0, Math.min(100, texts.get(0).length())) + "...");
        }
    }
}
