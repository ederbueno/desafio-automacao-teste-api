package clients;

import dtos.CarrinhoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CarrinhoClient {
    public static Response criarCarrinho(CarrinhoDTO carrinho, String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(carrinho)
                .post("/carrinhos");
    }

    public static Response listarCarrinhos() {
        return given().get("/carrinhos");
    }

    public static Response buscarCarrinhoPorId(String carrinhoId) {
        return given()
                .contentType(ContentType.JSON)
                .get("/carrinhos/" + carrinhoId);
    }

    public static Response concluirCompra(String token) {
        return given()
                .header("Authorization", token)
                .delete("/carrinhos/concluir-compra");
    }

    public static Response cancelarCompra(String token) {
        return given()
                .header("Authorization", token)
                .delete("/carrinhos/cancelar-compra");
    }
}
