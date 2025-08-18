package tests;

import base.BaseTest;
import clients.UsuarioClient;
import dtos.CarrinhoDTO;
import clients.CarrinhoClient;
import factories.CarrinhoFactory;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utils.TokenUtils;
import clients.ProdutoClient;

import static org.hamcrest.Matchers.*;

@Epic("Carrinhos")
@Feature("Criação e Listagem")
public class CarrinhoTest extends BaseTest {

    @Test(groups = "cadastro",description = "Deve criar carrinho com produto")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Adicionar produto ao carrinho")
    public void deveCriarCarrinhoComProduto() {

        Response buscarUsuarioPorId = UsuarioClient
                                      .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        String token = TokenUtils
                       .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        Response produtos = ProdutoClient.listarProdutos();
        String idProduto = produtos.jsonPath().getString("produtos[0]._id");

        CarrinhoDTO carrinho = CarrinhoFactory.carrinhoComProduto(idProduto);
        Response response = CarrinhoClient.criarCarrinho(carrinho, token);

        response.then()
                .statusCode(201)
                .body("message", containsString("Cadastro realizado com sucesso"));
    }

    @Test(groups = "cadastro", description = "Deve listar carrinhos existentes")
    @Severity(SeverityLevel.MINOR)
    @Story("Listar carrinhos")
    public void deveListarCarrinhos() {
        Response response = CarrinhoClient.listarCarrinhos();

        response.then()
                .statusCode(200)
                .body("quantidade", greaterThanOrEqualTo(0));
    }
}
