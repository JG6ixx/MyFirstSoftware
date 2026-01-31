package control;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {
	
	private List<Cliente>clientes = new ArrayList<>();
	
	public void cadastrarCliente(String nome, String telefone, String endereco, 
			String email, long cpf ) {
		
		Cliente c = new Cliente(endereco, telefone, nome, email, cpf);
		c.setNome(nome);
		c.setTelefone(telefone);
		c.setEndereco(endereco);
		c.setEmail(email);
		c.setCpf(cpf);
		clientes.add(c);
		
		
		
	}
	
	

}
