package model;



public class Cliente {
	
	private String nome;
	private String telefone;
	private int id;
	private String endereco;
	private String cpf;
	private String email;

	
	
	public Cliente(String nome, String telefone, String endereco, String email, String cpf) {
		
		
		
		this.endereco = endereco;
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.email = email;
		
		
		
		
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
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return 		 nome + "\n" + " "+
		           "Telefone: " + telefone + "\n" +
		           "Endereco: " + endereco + "\n" +
		           "Email:  " + email + "\n" +
		           "CPF:  " + cpf + "\n";
	
	
	}
	
	

}
