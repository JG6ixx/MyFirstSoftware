package control;

import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.ItemVenda;
import model.Venda;
import model.Vendedor;

public class VendaController {

    private List<Venda> vendas = new ArrayList<>();

    public Venda cadastrarVenda(String formaPagamento, Vendedor vendedor, Cliente cliente, String data) {

        Venda vd = new Venda(formaPagamento, vendedor, cliente, data);

        vendas.add(vd); // GUARDA a venda

        return vd;
    }

    public void adicionarItemNaVenda(Venda venda, ItemVenda item) {
        venda.adicionarItem(item);
    }

    public List<Venda> listarVendas() {
        return vendas;
    }
}

