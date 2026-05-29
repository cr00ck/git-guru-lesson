package testingPractices.restAssuredPracticeTests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;

@Disabled("Api не активно, тесты просто для учебы сделаны")

public class BookingTesting {

    //    Тест-кейс: Создание токена аутентификации
//    Шаги:
//    Отправить POST-запрос на создание токена аутентификации к эндпоинту /auth.
//    {
//        "username": "admin",
//            "password": "password123"
//    }
//
//    Проверить, что ответ на запрос имеет статус код 200 (OK).
//    Проверить, что в ответе есть непустой параметр "token".
//    Проверить, что параметр "token" соответствует ожидаемому значению "abc123"
//    Замерить время выполнения запроса и удостовериться, что оно находится в разумных пределах.
//    Ожидаемый результат:
//    Токен аутентификации успешно создан, и его значение соответствует ожидаемому. Время выполнения запроса находится в пределах разумных значений.

        private static final String BASE_URL = "https://booking-api-dev.herokuapp.com";
        private static final String USERNAME = "admin";
        private static final String PASSWORD = "password123";

        @Test
        public void authToken() {
            RestAssured.baseURI = BASE_URL;

            long startTime = System.currentTimeMillis();

            String token = given()
                    .contentType(ContentType.JSON)
                    .body("{\"username\": \"" + USERNAME + "\", \"password\": \"" + PASSWORD + "\"}")
                    .when()
                .post("/auth")
                    .then()
                    .statusCode(200)
                    .body("token", not(isEmptyOrNullString()))
                    .body("$", hasKey("token"))
                    .extract()
                    .path("token");

            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println("Token received: " + token);
            System.out.println("Request execution time: " + executionTime + " milliseconds");
        }
//    Тест-кейс: Получение всех идентификаторов бронирования
//    Шаги:
//    Отправить GET-запрос по адресу https://booking-api-dev.herokuapp.com/booking.
//    Проверить, что код состояния ответа равен 200 (HTTP/1.1 200 OK).
//    Проверить, что тип контента в ответе является JSON (Content-Type: application/json).
//    Проверить, что JSON-ответ не пустой.
//            Проверить, что в поле "bookingid" присутствуют значения 1, 2, 3, 4.
//    Проверить, что все значения в поле "bookingid" являются целыми числами.
//            Проверить, что все значения в поле "bookingid" не являются пустыми (не null).
//    Ожидаемый результат:
//    Все проверки пройдены успешно, тест считается успешным.
@Test
public void getAllBookingIds() {
    RestAssured.baseURI = "https://booking-api-dev.herokuapp.com";

    given()
            .when()
            .get("/booking")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(not(empty()))
            .body("bookingid", hasItems(1, 2, 3, 4))
            .body("bookingid", everyItem(isA(Integer.class)))
            .body("bookingid", everyItem(not(nullValue())));
}
//    Тест-кейс: Получение данных бронирования по ID
//    Шаги:
//    Выполнить GET-запрос к "/booking/{id}" с указанным ID бронирования.
//    Установить заголовок "Accept" со значением "application/json".
//
//    Ожидаемый результат:
//    Проверить, что код состояния ответа равен 200 (OK).
//    Проверить, что поле "firstname" не пусто.
//    Проверить, что поле "lastname" не пусто.
//    Проверить, что значение поля "totalprice" больше 0.
//    Проверить, что значение поля "depositpaid" является логическим значением.
//            Проверить, что значение поля "bookingdates.checkin" не пусто.
//    Проверить, что значение поля "bookingdates.checkout" не пусто.
//    Проверить, что значение поля "additionalneeds" не пусто.
//    Проверить, что значение поля "firstname" равно "Sally".
//    Проверить, что значение поля "lastname" равно "Brown".
//    Проверить, что значение поля "totalprice" равно 111.
//    Проверить, что значение поля "depositpaid" равно true.
//    Проверить, что значение поля "bookingdates.checkin" равно "2013-02-23".
//    Проверить, что значение поля "bookingdates.checkout" равно "2014-10-23".
@BeforeAll
public static void setup() {
    RestAssured.baseURI = "https://booking-api-dev.herokuapp.com";
}

    @Test
    public void testGetBookingById() {
        String bookingId = "1";

        String response =
                given()
                        .header("Accept", "application/json")
                        .when()
                        .get("/booking/{id}", bookingId)
                        .then()
                        .statusCode(200)
                        .body("firstname", not(emptyOrNullString()))
                        .body("lastname", not(emptyOrNullString()))
                        .body("totalprice", greaterThan(0))
                        .body("depositpaid", isA(Boolean.class))
                        .body("bookingdates.checkin", not(emptyOrNullString()))
                        .body("bookingdates.checkout", not(emptyOrNullString()))
                        .body("firstname", equalTo("Jim"))
                        .body("lastname", equalTo("Ericsson"))
                        .body("totalprice", equalTo(543))
                        .body("depositpaid", equalTo(true))
                        .body("bookingdates.checkin", equalTo("2020-04-13"))
                        .body("bookingdates.checkout", equalTo("2022-01-07"))
                        .extract().asString();
        System.out.println("Response: " + response);
    }
//    Test Case: Create a New Booking
//    Test Steps:
//    Open the booking API endpoint in the testing environment.
//    Prepare a JSON request payload with the following details:
//    {
//        "firstname" : "Jim",
//            "lastname" : "Brown",
//            "totalprice" : 111,
//            "depositpaid" : true,
//            "bookingdates" : {
//        "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//    },
//        "additionalneeds" : "Breakfast"
//    }'
//
//    Verify that the HTTP response status code is 200 (OK).
//    Verify that the response body contains the following details:
//    Booking ID is not null.
//    First name is "Jim".
//    Last name is "Brown".
//    Total price is 111.
//    Deposit paid is true.
//    Check-in date is "2018-01-01".
//    Check-out date is "2019-01-01".
//    Additional needs are "Breakfast".
//    Expected Result:
//    The new booking is successfully created, and the response matches the expected details.
@Test
public void createBookingTest() {
    // Set base URI and port
    RestAssured.baseURI = "https://booking-api-dev.herokuapp.com";
    RestAssured.port = 443; // Assuming the API uses HTTPS

    // Request body JSON
    String requestBody = "{\n" +
            "    \"firstname\" : \"Jim\",\n" +
            "    \"lastname\" : \"Brown\",\n" +
            "    \"totalprice\" : 111,\n" +
            "    \"depositpaid\" : true,\n" +
            "    \"bookingdates\" : {\n" +
            "        \"checkin\" : \"2018-01-01\",\n" +
            "        \"checkout\" : \"2019-01-01\"\n" +
            "    },\n" +
            "    \"additionalneeds\" : \"Breakfast\"\n" +
            "}";

    // Send POST request
    String response=
            given()
                    .header("Content-Type", ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post("/booking")
                    .then()
                    .statusCode(200)
                    .body("bookingid", notNullValue())
                    .body("booking.firstname", equalTo("Jim"))
                    .body("booking.lastname", equalTo("Brown"))
                    .body("booking.totalprice", equalTo(111))
                    .body("booking.depositpaid", equalTo(true))
                    .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                    .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                    .body("booking.additionalneeds", equalTo("Breakfast"))
                    .extract().asString();
    System.out.println("Response: " + response);
}
//    Test Case: Update Booking
//    Preconditions:
//    A booking with ID 1 exists in the system.
//
//    Test Steps:
//    Send a PUT request to the endpoint /booking/1 with the following details:
//    Request Method: PUT
//    Headers:
//    Content-Type: application/json
//    Accept: application/json
//    Cookie: token=abc123
//
//    {
//        "firstname": "James",
//            "lastname": "Brown",
//            "totalprice": 111,
//            "depositpaid": true,
//            "bookingdates": {
//        "checkin": "2018-01-01",
//                "checkout": "2019-01-01"
//    },
//        "additionalneeds": "Breakfast"
//    }
//
//    Expected Status Code: 200
//    Expected Content Type: application/json
//    Validate the response body:
//            "firstname" equals "James"
//            "lastname" equals "Brown"
//            "totalprice" equals 111
//            "depositpaid" equals true
//            "bookingdates.checkin" equals "2018-01-01"
//            "bookingdates.checkout" equals "2019-01-01"
//            "additionalneeds" equals "Breakfast"
//    Expected Result: The booking information is successfully updated, and the response matches the expected details.

    @Test
    public void updateBooking() {
        // Set the booking ID you want to update
        int bookingId = 1;

        // Define the request body
        String requestBody = "{\n" +
                " \"firstname\" : \"James\",\n" +
                " \"lastname\" : \"Brown\",\n" +
                " \"totalprice\" : 111,\n" +
                " \"depositpaid\" : true,\n" +
                " \"bookingdates\" : {\n" +
                " \"checkin\" : \"2018-01-01\",\n" +
                " \"checkout\" : \"2019-01-01\"\n" +
                " },\n" +
                " \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        // Perform the PUT request using the obtained token
        Response response = RestAssured.given()
                .headers("Content-Type", ContentType.JSON.toString(), "Accept", ContentType.JSON.toString(), "Cookie", "token=" ) //+ authToken)
                .body(requestBody)
                .put("/booking/" + bookingId);

        // Validate the response
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .time(lessThan(5000L)) // Response time less than 5 seconds
                .body("firstname", equalTo("James"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));
    }

    @AfterAll
    public static void teardown() {
        // Reset the base URI after the test
        RestAssured.baseURI = null;
    }

    private String obtainAuthToken(String username, String password) {
        // Step 2: Send a POST request to obtain authentication token
        String requestBody = "{\n" +
                " \"username\" : \"" + username + "\",\n" +
                " \"password\" : \"" + password + "\"\n" +
                "}";

        Response response = RestAssured.given()
                .headers("Content-Type", ContentType.JSON.toString(), "Accept", ContentType.JSON.toString())
                .body(requestBody)
                .post("/auth");

        // Step 3: Extract the token from the response
        return response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .path("token");
    }


}
