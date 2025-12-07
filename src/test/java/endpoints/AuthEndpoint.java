package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AuthPayload;

import static io.restassured.RestAssured.given;

public class AuthEndpoint {

    public static Response createToken(AuthPayload body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth");
    }
}
