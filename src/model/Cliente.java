package model;

public class Cliente {
	
	private String nome;
	private String telefone;
	private static long contador = 0;
	private long id;
	private String endereco;
	
	
	public Cliente(String nome, String telefone, String endereco) {
		
		
		contador++;
		this.endereco = endereco;
		this.nome = nome;
		this.endereco = telefone;
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
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", id=" + id + ", endereco=" + endereco + "]";
	}
	

}
