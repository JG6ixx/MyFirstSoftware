
package control;

import java.util.ArrayList;
import java.util.List;

import banco.Conexao;
import model.Produto;

public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();
	private ConexaoController conexaoController = new ConexaoController(new Conexao());


    public Produto cadastrarProduto(String nomeP, String descricao, double valor) {
        Produto p = new Produto(nomeP, descricao, valor);
        produtos.add(p);
        conexaoController.inserirProduto(p);
        
        return p;
    }

    public List<Produto> listarProdutos() {
        return conexaoController.listarProdutos(); // ✅ banco de dados
    }

    
    public String ultimoProdutoToString() {
        if (produtos.isEmpty()) {
            return "Nenhum produto cadastrado.";
        }

        Produto ultimo = produtos.get(produtos.size() - 1);
        return ultimo.toString();
    }


	
}

