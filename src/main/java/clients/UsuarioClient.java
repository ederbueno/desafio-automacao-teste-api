package clients;


import dtos.UsuarioDTO;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UsuarioClient {
    public static Response criarUsuario(UsuarioDTO usuario) {
        return given()
                .contentType("application/json")
                .body(usuario)
                .post("/usuarios");
    }

    public static Response listarUsuarios(String id) {
        return given()
                .get("/usuarios/" + id);
    }

    public static Response listarUsuariosCadastrados() {
        return given()
                .get("/usuarios/");
    }


    public static Response deletarUsuario(String id) {
        return given()
                .delete("/usuarios/" + id);

    }
}



