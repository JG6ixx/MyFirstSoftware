package model;

import java.math.BigInteger;

public class Cliente {
	
	private String nome;
	private String telefone;
	private static int contador = 0;
	private int id;
	private String endereco;
	private String cpf;
	private String email;

	
	
	public Cliente(String nome, String telefone, String endereco, String email, String cpf) {
		
		
		contador++;
		this.endereco = endereco;
		this.nome = nome;
		this.endereco = telefone;
		this.cpf = cpf;
		this.email = email;
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
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", telefone=" + telefone + ", id=" + id + ", endereco=" + endereco + ", cpf="
				+ cpf + ", email=" + email + "]";
	}
	

}
