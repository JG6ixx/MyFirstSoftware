package model;

public class Vendedor {
	
	private String nome;
	private String telefone;
	private static int cont = 0;
	private int id;
	
	public Vendedor(String nome, String telefone) {
		cont++;
		this.id = cont;
		this.nome = nome;
		this.telefone = telefone;
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

	@Override
	public String toString() {
		return "Vendedor [nome=" + nome + ", telefone=" + telefone + ", id=" + id + "]";
	} 
	
	
}
