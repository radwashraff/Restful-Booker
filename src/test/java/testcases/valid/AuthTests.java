package testcases.valid;

import base.BaseTest;
import endpoints.AuthEndpoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.AuthPayload;

public class AuthTests extends BaseTest {

    @Test
    public void testCreateToken() {
        AuthPayload auth = new AuthPayload("admin", "password123");
        Response res = AuthEndpoint.createToken(auth);

        res.then().statusCode(200);
        String token = res.jsonPath().getString("token");
        Assert.assertNotNull(token, "token should not be null");
        System.out.println("Token = " + token);
    }
}
