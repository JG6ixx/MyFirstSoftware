package control;

import model.ItemVenda;
import model.Produto;
import model.Venda;

public class ItemVendaController {
// Esta classe trata excessoes para que o ItemVenda seja adicionado aVenda de forma certa 
    public ItemVenda criarItemVenda(int quantidade, Produto produto, int desconto) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }

        if (desconto < 0 || desconto > 100) {
            throw new IllegalArgumentException("Desconto deve estar entre 0 e 100.");
        }

        ItemVenda item = new ItemVenda(quantidade, produto, desconto);

        return item;
    }

    public void adicionarItemNaVenda(Venda venda, ItemVenda item) {
        venda.adicionarItem(item);
    }
}
