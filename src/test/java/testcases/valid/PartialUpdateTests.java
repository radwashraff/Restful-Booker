package testcases.valid;

import base.BaseTest;
import endpoints.AuthEndpoint;
import endpoints.BookingEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.AuthPayload;
import payloads.BookingDates;
import payloads.BookingPayload;

import java.util.HashMap;
import java.util.Map;

public class PartialUpdateTests extends BaseTest {

    @Test
    public void testPartialUpdate() {
        AuthPayload auth = new AuthPayload("admin", "password123");
        String token = AuthEndpoint.createToken(auth).jsonPath().getString("token");

        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");
        BookingPayload createBody = new BookingPayload("Radwa", "Amgad", 150, true, dates, "Breakfast");
        int bookingId = BookingEndpoints.createBooking(createBody).jsonPath().getInt("bookingid");
        Map<String, String> partial = new HashMap<>();
        partial.put("firstname", "Omar");

        Response res = BookingEndpoints.partialUpdateBooking(bookingId, partial, token);
        res.then().statusCode(200);
        Assert.assertEquals(res.jsonPath().getString("firstname"), "Omar");
    }
}
