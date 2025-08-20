package tests;

import base.BaseTest;
import clients.UsuarioClient;
import dtos.LoginDTO;
import clients.LoginClient;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import io.qameta.allure.*;

import static org.hamcrest.Matchers.*;

@Epic("Autenticação")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(groups = "cadastro", description = "Deve realizar login com sucesso")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Login com credenciais válidas")
    public void deveRealizarLoginComSucesso() {

        Response buscarUsuarioPorId = UsuarioClient
                .listarUsuarios(usuarioCriado.jsonPath().getString("_id"));

        LoginDTO login = new LoginDTO();
        login.setEmail(buscarUsuarioPorId.jsonPath().getString("email"));
        login.setPassword(buscarUsuarioPorId.jsonPath().getString("password"));

        Response response = LoginClient.realizarLogin(login);

        response.then()
                .statusCode(200)
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue());
    }

    @Test(groups = "cadastro", description = "Deve falhar ao logar com senha incorreta")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login com credenciais inválidas")
    public void deveFalharLoginComSenhaIncorreta() {
        LoginDTO login = new LoginDTO();
        login.setEmail("testeUsaruarioInexistente@qa.com");
        login.setPassword("senhaErrada");

        Response response = LoginClient.realizarLogin(login);

        response.then()
                .statusCode(401)
                .body("message", equalTo("Email e/ou senha inválidos"));
    }

    @AfterMethod
    public void tearDown(){
            Response response = UsuarioClient
                    .deletarUsuario(usuarioCriado.jsonPath().getString("_id"));
            response.then()
                    .log().all()
                    .statusCode(200)
                    .body("message", equalTo("Registro excluído com sucesso"));

    }
}
