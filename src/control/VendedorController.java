 package control;

import java.util.ArrayList;
import java.util.List;

import banco.Conexao;
import model.Cliente;
import model.Vendedor;

public class VendedorController {

    private List<Vendedor> vendedores = new ArrayList<>();
	private ConexaoController conexaoController = new ConexaoController(new Conexao()); 
//Metodo que faz o mesmo que o ClienteController
	
	//Serve para como ligascao entre o Controller da Conexao e view

    public Vendedor cadastrarVendedor(String nome, String telefone) {

        Vendedor v = new Vendedor(nome, telefone);
        v.setNome(nome);
        v.setTelefone(telefone);
        conexaoController.inserirVendedor(v);
        vendedores.add(v);

        return v;
    }

    public List<Vendedor> listarVendedor() {
        return conexaoController.listarVendedor();
    }
    
    
    public String ultimoVendedorToString() {
        if (vendedores.isEmpty()) {
            return "Nenhum cliente cadastrado.";
        }

        Vendedor ultimo = vendedores.get(vendedores.size() - 1);
        return ultimo.toString();
    }
}

