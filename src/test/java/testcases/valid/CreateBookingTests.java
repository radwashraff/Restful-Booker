package testcases.valid;

import base.BaseTest;
import endpoints.BookingEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.BookingDates;
import payloads.BookingPayload;

public class CreateBookingTests extends BaseTest {

    @Test
    public void testCreateBooking() {

        BookingDates dates = new BookingDates("2025-01-01", "2025-01-10");

        BookingPayload booking = new BookingPayload(
                "Radwa",
                "Amgad",
                150,
                true,
                dates,
                "Breakfast"
        );
        Response res = BookingEndpoints.createBooking(booking);
        res.then().statusCode(200);
        String firstname = res.jsonPath().getString("booking.firstname");
        Assert.assertEquals(firstname, "Radwa", "Firstname not matching!");
    }
}
