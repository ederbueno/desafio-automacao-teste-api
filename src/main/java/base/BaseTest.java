package base;


import clients.UsuarioClient;
import dtos.UsuarioDTO;
import factories.UsuarioFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.hamcrest.Matchers.equalTo;


public class BaseTest {


    protected Response usuarioCriado;

    @BeforeMethod(onlyForGroups = "busca")
    public void setup() {
        RestAssured.baseURI = "https://serverest.dev";
    }


    @BeforeMethod(onlyForGroups = "cadastro")
    public void criarUsuario() {
        RestAssured.baseURI = "https://serverest.dev";
        UsuarioDTO usuario = UsuarioFactory.usuarioValido();
        Response response = UsuarioClient.criarUsuario(usuario);

        usuarioCriado = response;

    }

}


