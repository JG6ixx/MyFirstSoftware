package control;
import model.Cliente;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

import banco.Conexao;

public class ClienteController {
	
	
	
	private List<Cliente>clientes = new ArrayList<>();
	private ConexaoController conexaoController = new ConexaoController(new Conexao()); 
	//Chama o metodo de Inserir cliente da classe proincipal da Conexao
	public Cliente cadastrarCliente(String nome, String cpf,String telefone, String email, String endereco ) {
		
		Cliente c = new Cliente(nome, cpf, telefone, email, endereco  );
		c.setNome(nome);
		c.setTelefone(telefone);
		c.setEndereco(endereco);
		c.setEmail(email);
		c.setCpf(cpf);
		clientes.add(c);
		conexaoController.inserirCliente(c);
		return c;
		
		
		
	}
	//Chama o metodo que retorna os dados do banco de dados e os armazena em um array 
	public List<Cliente> listarClientes() {
        return conexaoController.listarCliente(); // 
    }

	//Este Metodo eu fiz para impressao do ultimo objeto criado e armazenado no banco de dados, na TextArea da view
	
	  public String ultimoClienteToString() {
	        if (clientes.isEmpty()) {
	            return "Nenhum cliente cadastrado.";
	        }

	        Cliente ultimo = clientes.get(clientes.size() - 1);
	        return ultimo.toString();
	    }
}
