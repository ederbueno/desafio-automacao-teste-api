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

    public static ProdutoDTO produtoAtualizado() {
        ProdutoDTO produto = new ProdutoDTO();
        produto.setNome("Produto Atualizado " + UUID.randomUUID());
        produto.setPreco(199);
        produto.setDescricao("Descrição editada");
        produto.setQuantidade(25);
        return produto;
    }

}
