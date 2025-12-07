package testcases.invalid;
import base.BaseTest;
import endpoints.BookingEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.BookingPayload;

public class InvalidCreateBookingTests extends BaseTest {

    @Test
    public void missingFirstname() {
        BookingPayload body = new BookingPayload(null, "Amgad", 150, true, null, "Breakfast");

        Response res = BookingEndpoints.createBooking(body);
        res.then().statusCode(500); // Restful booker returns 500 for invalid body
    }

    @Test
    public void missingBookingDates() {
        BookingPayload body = new BookingPayload("Radwa", "Amgad", 150, true, null, "Breakfast");
        Response res = BookingEndpoints.createBooking(body);
        res.then().statusCode(500);
    }

    @Test
    public void wrongDatatype() {
        // totalprice should be int → we send string using raw JSON
        String wrongJson = "{\n" +
                "\"firstname\": \"Radwa\",\n" +
                "\"lastname\": \"Amgad\",\n" +
                "\"totalprice\": \"abc\",\n" +
                "\"depositpaid\": true,\n" +
                "\"bookingdates\": {\"checkin\":\"2025-01-01\",\"checkout\":\"2025-01-10\"}\n" +
                "}";

        Response res =
                io.restassured.RestAssured.given()
                        .contentType("application/json")
                        .body(wrongJson)
                        .post("/booking");

        res.then().statusCode(500);
    }
}