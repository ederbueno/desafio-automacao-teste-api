package factories;

import dtos.ProdutoDTO;
import java.util.UUID;

public class ProdutoFactory {

    public static ProdutoDTO produtoValido() {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setNome("Produto " + UUID.randomUUID());
        produto.setPreco(100);
        produto.setDescricao("Produto de teste");
        produto.setQuantidade(10);
        return produto;
    }
}
