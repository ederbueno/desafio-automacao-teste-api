package tests;

import base.BaseTest;
import clients.UsuarioClient;
import dtos.CarrinhoDTO;
import clients.CarrinhoClient;
import dtos.ProdutoDTO;
import factories.CarrinhoFactory;
import factories.ProdutoFactory;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utils.TokenUtils;
import clients.ProdutoClient;

import static org.hamcrest.Matchers.*;

@Epic("Carrinhos")
@Feature("Criação e Listagem")
public class CarrinhoTest extends BaseTest {

    String tokenAfter;

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
                .body("message", equalTo("Cadastro realizado com sucesso"));

        tokenAfter = token;
    }

    @Test(groups = "cadastro",description = "Deve concluir compra com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Deve concluir compra com sucesso")
    public void deveConcluirCompraComSucesso() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        Response produtos = ProdutoClient.listarProdutos();
        String idProduto = produtos.jsonPath().getString("produtos[0]._id");

        CarrinhoDTO carrinho = CarrinhoFactory.carrinhoComProduto(idProduto);
        CarrinhoClient.criarCarrinho(carrinho, token);

        CarrinhoClient.concluirCompra(token)
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

    }

    @Test(groups = "cadastro",description = "Deve buscar carrinho por id")
    @Severity(SeverityLevel.NORMAL)
    @Story("Deve concluir compra com sucesso")
    public void deveBuscarCarrinhoPorId() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        ProdutoDTO produto = ProdutoFactory.produtoValido();
        Response produtoCriado = ProdutoClient.criarProduto(produto, token);
        String idProduto = produtoCriado.jsonPath().getString("_id");


        CarrinhoDTO carrinho = CarrinhoFactory.carrinhoComProduto(idProduto);
        Response carrinhoCriado = CarrinhoClient.criarCarrinho(carrinho, token);
        String idCarrinho = carrinhoCriado.jsonPath().getString("_id");


        CarrinhoClient.buscarCarrinhoPorId(idCarrinho)
                .then()
                .statusCode(200)
                .body("_id", equalTo(idCarrinho))
                .body("produtos", not(empty()));

        tokenAfter = token;
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


    @Test(groups = "cadastro", description = "Deve Cancelar Compras")
    @Severity(SeverityLevel.NORMAL)
    @Story("Cancelar Compras")
    public void deveCancelarCompraComSucesso() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        ProdutoDTO produto = ProdutoFactory.produtoValido();
        Response produtoCriado = ProdutoClient.criarProduto(produto, token);
        String idProduto = produtoCriado.jsonPath().getString("_id");

        CarrinhoDTO carrinho = CarrinhoFactory.carrinhoComProduto(idProduto);
        CarrinhoClient.criarCarrinho(carrinho, token);

        CarrinhoClient.cancelarCompra(token)
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso. Estoque dos produtos reabastecido"));

    }

    @AfterMethod
    public void tearDown(){
        if(tokenAfter != null) {
            CarrinhoClient.cancelarCompra(tokenAfter)
                    .then()
                    .statusCode(200)
                    .body("message", equalTo("Registro excluído com sucesso. Estoque dos produtos reabastecido"));
        }
        if(usuarioCriado != null){
            Response response = UsuarioClient
                    .deletarUsuario(usuarioCriado.jsonPath().getString("_id"));
            response.then()
                    .log().all()
                    .statusCode(200)
                    .body("message", equalTo("Registro excluído com sucesso"));
        }
        tokenAfter = null;
    }
}
