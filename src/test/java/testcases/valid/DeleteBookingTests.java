package testcases.valid;

import base.BaseTest;
import endpoints.AuthEndpoint;
import endpoints.BookingEndpoints;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AuthPayload;
import payloads.BookingDates;
import payloads.BookingPayload;

public class DeleteBookingTests extends BaseTest {

    @Test
    public void testDeleteBooking() {
        AuthPayload auth = new AuthPayload("admin", "password123");
        String token = AuthEndpoint.createToken(auth).jsonPath().getString("token");

        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");
        BookingPayload createBody = new BookingPayload("Radwa", "Amgad", 150, true, dates, "Breakfast");
        int bookingId = BookingEndpoints.createBooking(createBody).jsonPath().getInt("bookingid");

        Response delRes = BookingEndpoints.deleteBooking(bookingId, token);
        // API returns 201 on successful delete in this service
        delRes.then().statusCode(201);
        // verify delete (expect 404)
        BookingEndpoints.getBookingById(bookingId).then().statusCode(404);
    }
}
