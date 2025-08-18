package clients;

import dtos.CarrinhoDTO;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CarrinhoClient {
    public static Response criarCarrinho(CarrinhoDTO carrinho, String token) {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(carrinho)
                .post("/carrinhos");
    }

    public static Response listarCarrinhos() {
        return given().get("/carrinhos");
    }
}
