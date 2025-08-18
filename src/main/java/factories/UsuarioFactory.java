package factories;

import dtos.UsuarioDTO;
import java.util.UUID;

public class UsuarioFactory {

    public static UsuarioDTO usuarioValido() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome("Usuário Teste");
        usuario.setEmail("teste_" + UUID.randomUUID() + "@email.com");
        usuario.setPassword("123456");
        usuario.setAdministrador("true");
        return usuario;
    }

    public static UsuarioDTO usuarioInvalido() {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome("");
        usuario.setEmail("email_invalido");
        usuario.setPassword("");
        usuario.setAdministrador("false");
        return usuario;
    }
}
