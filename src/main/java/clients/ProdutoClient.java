package clients;

import dtos.ProdutoDTO;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ProdutoClient {
    public static Response criarProduto(ProdutoDTO produto, String token) {
        return given()
                .header("Authorization", token)
                .contentType("application/json")
                .body(produto)
                .log().all()
                .post("/produtos");
    }

    public static Response listarProdutos() {
        return given()
                .log().all()
                .get("/produtos");
    }

    public static Response buscarProdutoPorId(String id) {
        return given()
                .log().all()
                .get("/produtos/" + id);
    }
}
