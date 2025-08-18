package dtos;

import lombok.Data;

@Data
public class ProdutoDTO {
    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;
}
