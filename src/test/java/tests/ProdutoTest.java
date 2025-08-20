package tests;

import base.BaseTest;
import clients.UsuarioClient;
import dtos.ProdutoDTO;
import clients.ProdutoClient;
import factories.ProdutoFactory;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import utils.TokenUtils;

import static org.hamcrest.Matchers.*;

@Epic("Produtos")
@Feature("Cadastro e Listagem")
public class ProdutoTest extends BaseTest {

    @Test(groups = "cadastro", description = "Deve criar produto com sucesso")
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

    @Test(groups = "cadastro", description = "Deve listar produtos disponíveis")
    @Severity(SeverityLevel.MINOR)
    @Story("Listar todos os produtos")
    public void deveListarProdutos() {
        Response response = ProdutoClient.listarProdutos();
        response.then()
                .statusCode(200)
                .body("quantidade", greaterThan(0));
    }

    @Test(groups = "cadastro", description = "Deve buscar produto por ID com sucesso")
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar produto por ID")
    public void deveBuscarProdutoPorIdComSucesso() {


        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));
        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        ProdutoDTO novoProduto = ProdutoFactory.produtoValido();
        Response produtoCriado = ProdutoClient.criarProduto(novoProduto, token);
        String id = produtoCriado.jsonPath().getString("_id");

        Response response = ProdutoClient.buscarProdutoPorId(id);

                 response.then()
                   .log().all()
                   .statusCode(200)
                   .body("_id", equalTo(id))
                   .body("nome", equalTo(novoProduto.getNome()));
    }

    @Test(groups = "cadastro", description = "Deve editar produto  com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Editar produto")
    public void deveEditarProdutoComSucesso() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));
        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        ProdutoDTO novoProduto = ProdutoFactory.produtoValido();
        Response produtoCriado = ProdutoClient.criarProduto(novoProduto, token);
        String idProduto = produtoCriado.jsonPath().getString("_id");

        ProdutoDTO produtoAtualizado = ProdutoFactory.produtoAtualizado();

        ProdutoClient.editarProduto(idProduto, produtoAtualizado, token)
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));

        ProdutoClient.buscarProdutoPorId(idProduto)
                .then()
                .statusCode(200)
                .body("nome", containsString("Produto Atualizado"))
                .body("preco", equalTo(199))
                .body("descricao", equalTo("Descrição editada"))
                .body("quantidade", equalTo(25));
    }



    @Test(groups = "cadastro", description = "Deve excluir produtos com sucesso")
    @Severity(SeverityLevel.NORMAL)
    @Story("Buscar produto por ID")
    public void deveExcluirProdutoComSucesso() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        ProdutoDTO produto = ProdutoFactory.produtoValido();
        Response produtoCriado = ProdutoClient.criarProduto(produto, token);
        String idProduto = produtoCriado.jsonPath().getString("_id");

        ProdutoClient.deletarProduto(idProduto, token)
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

    @Test(groups = "cadastro", description = "não deve excluir produtos sem autenticação")
    @Severity(SeverityLevel.MINOR)
    @Story("Não deve excluir produto sem autenticação")
    public void naoDeveExcluirProdutoSemAutenticacao() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        String token = TokenUtils
                .gerarToken(buscarUsuarioPorId.jsonPath().getString("email"),
                        buscarUsuarioPorId.jsonPath().getString("password"));

        ProdutoDTO produto = ProdutoFactory.produtoValido();
        Response produtoCriado = ProdutoClient.criarProduto(produto, token);
        String idProduto = produtoCriado.jsonPath().getString("_id");

        ProdutoClient.deletarProduto(idProduto, "") // sem token
                .then()
                .statusCode(401)
                .body("message", containsString("Token de acesso ausente, inválido, expirado ou usuário do token não existe mais"));
    }

    @AfterMethod
    public void tearDown(){
        if(usuarioCriado != null){
            Response response = UsuarioClient
                    .deletarUsuario(usuarioCriado.jsonPath().getString("_id"));
            response.then()
                    .log().all()
                    .statusCode(200)
                    .body("message", equalTo("Registro excluído com sucesso"));
        }
    }


}
