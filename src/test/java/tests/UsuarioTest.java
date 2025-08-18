package tests;


import base.BaseTest;
import dataprovider.DataProviderServeRest;
import dtos.UsuarioDTO;
import clients.UsuarioClient;
import factories.UsuarioFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Usuários")
@Feature("Cadastro")
public class UsuarioTest extends BaseTest {

    @Test(groups = "cadastro", description = "Deve criar usuário com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Criar novo usuário válido")
    public void deveCriarUsuarioComSucesso() {

        usuarioCriado.then()
                .log().all()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));

    }

    @Test(groups = "cadastro", description = "Deve tentar criar usuário existente")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Criar novo usuário com email cadastrado")
    public void deveCriarUsuarioComSucessoComErro() {

       usuarioCriado.then()
                .log().all()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));

       Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));
       UsuarioDTO usuario = UsuarioFactory
                .usuarioExistente(buscarUsuarioPorId.jsonPath().getString("email"));
       Response response = UsuarioClient
                .criarUsuario(usuario);
       response.then()
                .log().all()
                .statusCode(400)
                .body("message", equalTo("Este email já está sendo usado"));

    }

    @Test(groups = "cadastro", description = "Deve falar ao criar um usuário Invalido")
    @Severity(SeverityLevel.NORMAL)
    @Story("Falhar ao criar um usuário Invalido")
    public void deveFalharAoCriarUsuarioInvalido() {
        UsuarioDTO usuario = UsuarioFactory.usuarioInvalido();
        Response response = UsuarioClient.criarUsuario(usuario);

        response.then()
                .log().all()
                .statusCode(400)
                .body("email", equalTo("email deve ser um email válido"));
    }


    @Test(groups = "busca", description = "Deve listar usuários cadastrados")
    @Severity(SeverityLevel.MINOR)
    @Story("Listar usuários cadastrados")
    public void deveListarUsuáriosCadastrados() {
        Response response = UsuarioClient.listarUsuariosCadastrados();
        response.then()
                .log().all()
                .statusCode(200)
                .body("quantidade",  not(equalTo(0)));
    }

    @Test(
            groups = "busca",
            description = "Deve tentar buscar usuário cadastrado com id inexistente",
            dataProvider = "idsInvalidos",
            dataProviderClass = DataProviderServeRest.class
    )
    @Severity(SeverityLevel.MINOR)
    @Story("Listar usuários cadastrados")
    public void deveTentarListarUsuariosCadastradosIdInexistente(String idInvalido) {
        Response response = UsuarioClient.listarUsuarios(idInvalido);
        response.then()
                .log().all()
                .statusCode(400);

        String message = response.path("message");
        String idMessage = response.path("id");

        assertThat(
                Arrays.asList(message, idMessage),
                hasItem(anyOf(
                        equalTo("Usuário não encontrado"),
                        equalTo("id deve ter exatamente 16 caracteres alfanuméricos")
                )));
    }

    @Test(groups = "busca", description = "Deve deletar usuário com sucesso")
    @Severity(SeverityLevel.NORMAL)
    @Story("Deletar usuário válido")
    public void deveDeletarUsuarioComSucesso() {

        UsuarioDTO usuario = UsuarioFactory.usuarioValido();
        Response usuarioCriado = UsuarioClient.criarUsuario(usuario);

        usuarioCriado.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));

        Response response = UsuarioClient
                .deletarUsuario(usuarioCriado.jsonPath().getString("_id"));
        response.then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

    @Test(groups = "cadastro", description = "Deve editar usuário com sucesso")
    @Severity(SeverityLevel.NORMAL)
    @Story("Editar usuário válido")
    public void deveEditarUsuarioComSucesso() {
        usuarioCriado.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));

        String id = usuarioCriado.jsonPath().getString("_id");
        UsuarioDTO usuario = UsuarioFactory.usuarioAlterado();

        Response response = UsuarioClient.editarUsuario(id, usuario);
        response.then()
                .log().all()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

}

