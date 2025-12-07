package testcases.invalid;
import base.BaseTest;
import endpoints.AuthEndpoint;
import endpoints.BookingEndpoints;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import payloads.AuthPayload;
import payloads.BookingDates;
import payloads.BookingPayload;

public class InvalidUpdateTests extends BaseTest {

    @Test
    public void updateWithoutToken() {
        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");
        BookingPayload body = new BookingPayload("Radwa", "Amgad", 150, true, dates, "Breakfast");
        Response res = BookingEndpoints.updateBooking(1, body, "");
        res.then().statusCode(403);
    }

    @Test
    public void updateWithWrongToken() {
        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");
        BookingPayload body = new BookingPayload("Radwa", "Amgad", 150, true, dates, "Breakfast");
        Response res = BookingEndpoints.updateBooking(1, body, "invalidtoken123");
        res.then().statusCode(403);
    }

    @Test
    public void updateUnknownBooking() {
        AuthPayload auth = new AuthPayload("admin", "password123");
        String token = AuthEndpoint.createToken(auth).jsonPath().getString("token");
        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");
        BookingPayload body = new BookingPayload("Radwa", "Amgad", 150, true, dates, "Breakfast");
        Response res = BookingEndpoints.updateBooking(999999, body, token);
        res.then().statusCode(405); // API behavior
    }
}