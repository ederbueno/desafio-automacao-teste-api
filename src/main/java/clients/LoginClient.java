package clients;

import dtos.LoginDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient {
    public static Response realizarLogin(LoginDTO login) {
        return given()
                .contentType(ContentType.JSON)
                .body(login)
                .post("/login");
    }
}

