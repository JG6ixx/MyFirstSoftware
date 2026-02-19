package model;

public class Produto {

	private String nomeP;
	private String descricao;
	private double valor;
	private int id_produto;
	
	
	
	public Produto(String nomeP, String descricao, double valor) {
		this.nomeP =  nomeP;
		this.descricao = descricao;
		this.valor = valor; 
		
	}
	

	public String getNomeP() {
		return nomeP;
	}


	public void setNomeP(String nomeP) {
		this.nomeP = nomeP;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}


	public int getId_Produto() {
		return id_produto;
	}


	public void setId_Produto(int id_produto) {
		this.id_produto = id_produto;
	}


	@Override
	public String toString() {
	    return nomeP + "\n" +
	            " " + descricao + "\n" +
	           "R$ " + valor + "\n";
	}

	
	
	
}
