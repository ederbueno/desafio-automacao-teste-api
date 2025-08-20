package clients;

import dtos.ProdutoDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ProdutoClient {
    public static Response criarProduto(ProdutoDTO produto, String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
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

    public static Response deletarProduto(String produtoId, String token) {
        return given()
                .header("Authorization", token)
                .delete("/produtos/" + produtoId);
    }

    public static Response editarProduto(String produtoId, ProdutoDTO produtoAtualizado, String token) {
        return given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(produtoAtualizado)
                .when()
                .put("/produtos/" + produtoId);
    }




}
