package clients;

import dtos.LoginDTO;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginClient {
    public static Response realizarLogin(LoginDTO login) {
        return given()
                .contentType("application/json")
                .body(login)
                .post("/login");
    }
}

