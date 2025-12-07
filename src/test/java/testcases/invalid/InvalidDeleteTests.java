package testcases.invalid;
import base.BaseTest;
import endpoints.AuthEndpoint;
import endpoints.BookingEndpoints;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import payloads.AuthPayload;

public class InvalidDeleteTests extends BaseTest {

    @Test
    public void deleteWithoutToken() {
        Response res = BookingEndpoints.deleteBooking(1, "");
        res.then().statusCode(403);
    }

    @Test
    public void deleteWithWrongToken() {
        Response res = BookingEndpoints.deleteBooking(1, "wrong12345");
        res.then().statusCode(403);
    }

    @Test
    public void deleteUnknownBooking() {
        AuthPayload auth = new AuthPayload("admin", "password123");
        String token = AuthEndpoint.createToken(auth).jsonPath().getString("token");

        Response res = BookingEndpoints.deleteBooking(9123789, token);
        res.then().statusCode(405);
    }
}