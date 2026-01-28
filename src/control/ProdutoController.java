package control;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
public class ProdutoController {
	
	private List<Produto> produtos = new ArrayList<>();
	
	public void cadastrarProduto(String nomeP, String descricao, double valor) {
		
		Produto p = new Produto(nomeP, descricao, valor);
		p.setNomeP(nomeP);
		p.setDescricao(descricao);
		p.setValor(valor);
		produtos.add(p);
	}

}
