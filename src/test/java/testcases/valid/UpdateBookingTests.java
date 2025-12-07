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

public class UpdateBookingTests extends BaseTest {

    @Test
    public void testUpdateBooking() {

        // 1. create token
        AuthPayload auth = new AuthPayload("admin", "password123");
        Response tRes = AuthEndpoint.createToken(auth);
        tRes.then().statusCode(200);
        String token = tRes.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token creation failed!");

        // 2. create booking
        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");
        BookingPayload createBody = new BookingPayload("Radwa", "Amgad", 150, true, dates, "Breakfast");
        Response createRes = BookingEndpoints.createBooking(createBody);
        Assert.assertTrue(
                createRes.statusCode() == 200 || createRes.statusCode() == 201,
                "Create booking failed! Status: " + createRes.statusCode()
        );
        int bookingId = createRes.jsonPath().getInt("bookingid");
        Assert.assertTrue(bookingId > 0, "Booking ID not generated!");
        // 3. update booking
        BookingDates newDates = new BookingDates("2025-02-01", "2025-02-15");
        BookingPayload updateBody = new BookingPayload("Radwa", "Ashraf", 200, true, newDates, "Lunch");

        Response updateRes = BookingEndpoints.updateBooking(bookingId, updateBody, token);
        Assert.assertEquals(updateRes.jsonPath().getString("firstname"), updateBody.getFirstname());
        Assert.assertEquals(updateRes.jsonPath().getString("lastname"), updateBody.getLastname());
    }
}
