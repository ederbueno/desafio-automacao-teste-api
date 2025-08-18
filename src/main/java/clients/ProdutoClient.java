package clients;

import dtos.ProdutoDTO;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProdutoClient {
    public static Response criarProduto(ProdutoDTO produto, String token) {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(produto)
                .post("/produtos");
    }

    public static Response listarProdutos() {
        return given().get("/produtos");
    }
}
