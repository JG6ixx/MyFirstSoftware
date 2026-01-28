package model;

public class Cliente {
	
	private String nome;
	private String telefone;
	private static long contador = 0;
	private long id;
	private String endereco;
	private long cpf;
	private String sexo;
	private String email;

	
	
	public Cliente(String nome, String telefone, String endereco,String sexo, String email, long cpf) {
		
		
		contador++;
		this.endereco = endereco;
		this.nome = nome;
		this.endereco = telefone;
		this.cpf = cpf;
		this.email = email;
		this.sexo = sexo;
		this.id = contador;
		
		
		
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
	public long getId() {
		return id;
	}
	
	
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public long getCpf() {
		return cpf;
	}
	public void setCpf(long cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", id=" + id + ", endereco=" + endereco + ", cpf="
				+ cpf + ", sexo=" + sexo + ", email=" + email + "]";
	}
	

}
