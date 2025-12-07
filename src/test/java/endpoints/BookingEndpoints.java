package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.BookingPayload;

import static io.restassured.RestAssured.given;

public class BookingEndpoints {

    public static Response createBooking(BookingPayload body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/booking");
    }

    public static Response getBookingById(int id) {
        return given()
                .when()
                .get("/booking/" + id);
    }

    public static Response getAllBookings() {
        return given()
                .when()
                .get("/booking");
    }

    public static Response updateBooking(int bookingId, BookingPayload payload, String token) {
        return given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + token)   // <-- هنا الفرق
                .body(payload)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .extract().response();
    }


    public static Response partialUpdateBooking(int id, Object partialBody, String token) {
        return given()
                .contentType("application/json")
                .accept("application/json")
                .header("Cookie", "token=" + token)   // <-- هنا الفرق
                .body(partialBody)
                .when()
                .patch("/booking/" + id)
                .then()
                .extract().response();
    }

    public static Response deleteBooking(int id, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .when()
                .delete("/booking/" + id);
    }


}
