package utils;


import clients.LoginClient;
import dtos.LoginDTO;
import io.restassured.response.Response;

public class TokenUtils {
    public static String gerarToken(String email, String senha) {
        LoginDTO login = new LoginDTO();
        login.setEmail(email);
        login.setPassword(senha);

        Response response = LoginClient.realizarLogin(login);
        return response.jsonPath().getString("authorization");
    }
}
