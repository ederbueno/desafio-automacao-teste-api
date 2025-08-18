package tests;

import base.BaseTest;
import clients.UsuarioClient;
import dtos.ProdutoDTO;
import clients.ProdutoClient;
import factories.ProdutoFactory;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utils.TokenUtils;

import static org.hamcrest.Matchers.*;

@Epic("Produtos")
@Feature("Cadastro e Listagem")
public class ProdutoTest extends BaseTest {

    @Test(description = "Deve criar produto com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Cadastrar novo produto")
    public void deveCriarProdutoComSucesso() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));
        ProdutoDTO produto = ProdutoFactory.produtoValido();

        Response response = ProdutoClient.criarProduto(produto, token);

        response.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));
    }

    @Test(description = "Deve listar produtos disponíveis")
    @Severity(SeverityLevel.MINOR)
    @Story("Listar todos os produtos")
    public void deveListarProdutos() {
        Response response = ProdutoClient.listarProdutos();
        response.then()
                .statusCode(200)
                .body("quantidade", greaterThan(0));
    }
}
