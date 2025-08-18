package factories;

import dtos.CarrinhoDTO;
import java.util.Collections;

public class CarrinhoFactory {
    public static CarrinhoDTO carrinhoComProduto(String idProduto) {
        CarrinhoDTO.ItemCarrinho item = new CarrinhoDTO.ItemCarrinho();
        item.setIdProduto(idProduto);
        item.setQuantidade(1);

        CarrinhoDTO carrinho = new CarrinhoDTO();
        carrinho.setProdutos(Collections.singletonList(item));
        return carrinho;
    }
}
