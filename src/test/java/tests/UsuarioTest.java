package tests;


import base.BaseTest;
import dtos.UsuarioDTO;
import clients.UsuarioClient;
import factories.UsuarioFactory;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Epic("Usuários")
@Feature("Cadastro")
public class UsuarioTest extends BaseTest {

    @Test(groups = "cadastro", description = "Deve criar usuário com sucesso")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Criar novo usuário válido")
    public void deveCriarUsuarioComSucesso() {

        usuarioCriado.then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));

    }

    @Test(groups = "cadastro", description = "Deve falar ao criar um usuário Invalido")
    @Severity(SeverityLevel.NORMAL)
    @Story("Falhar ao criar um usuário Invalido")
    public void deveFalharAoCriarUsuarioInvalido() {
        UsuarioDTO usuario = UsuarioFactory.usuarioInvalido();
        Response response = UsuarioClient.criarUsuario(usuario);

        response.then()
                .statusCode(400)
                .body("email", equalTo("email deve ser um email válido"));
    }


    @Test(groups = "busca", description = "Deve listar usuários cadastrados")
    @Severity(SeverityLevel.MINOR)
    @Story("Listar usuários cadastrados")
    public void deveListarUsuáriosCadastrados() {
        Response response = UsuarioClient.listarUsuariosCadastrados();

        response.then()
                .statusCode(200)
                .body("quantidade",  not(equalTo(0)));
    }


}

