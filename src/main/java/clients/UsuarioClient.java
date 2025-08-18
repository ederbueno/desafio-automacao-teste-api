package clients;


import dtos.UsuarioDTO;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UsuarioClient {
    public static Response criarUsuario(UsuarioDTO usuario) {
        return given()
                .contentType("application/json")
                .body(usuario)
                .log().all()
                .post("/usuarios");
    }

    public static Response listarUsuarios(String id) {
        return given()
                .log().all()
                .get("/usuarios/" + id);
    }

    public static Response listarUsuariosCadastrados() {
        return given()
                .log().all()
                .get("/usuarios/");
    }


    public static Response deletarUsuario(String id) {
        return given()
                .log().all()
                .delete("/usuarios/" + id);

    }

    public static Response editarUsuario(String id, UsuarioDTO usuarioAlterado) {
        return given()
                .contentType("application/json")
                .body(usuarioAlterado)
                .log().all()
                .put("/usuarios/" + id);
    }
}



