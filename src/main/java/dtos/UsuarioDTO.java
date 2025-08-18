package dtos;


import lombok.Data;

@Data
public class UsuarioDTO {
    private String nome;
    private String email;
    private String password;
    private String administrador;
}
