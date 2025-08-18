package dtos;



import lombok.Data;
import java.util.List;

@Data
public class CarrinhoDTO {
    private List<ItemCarrinho> produtos;
    @Data
    public static class ItemCarrinho {
        private String idProduto;
        private int quantidade;
    }
}
