package model;

public class Vendedor {
	
	private String nome;
	private String telefone;
	private int id;
	private String senha;
	private String usuario;
	
	public Vendedor(String nome, String telefone) {
		
		this.nome = nome;
		this.telefone = telefone;
	//	this.usuario = usuario;
		//this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	} 
	
	public String toString() {
		return "Vendedor cadastrado:\n" +
		           "Nome: " + nome + "\n" +
		           "Telefone: " + telefone + "\n";
	}
	
	
}
