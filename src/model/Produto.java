package model;

public class Produto {

	private String nomeP;
	private String descricao;
	private double valor;
	private long codigoP;
	private static long contP = 0;
	
	
	public Produto(String nomeP, String descricao, double valor) {
		contP++;
		this.nomeP =  nomeP;
		this.descricao = descricao;
		this.valor = valor; 
		this.codigoP = contP;
		
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


	public long getCodigoP() {
		return codigoP;
	}


	public void setCodigoP(long codigoP) {
		this.codigoP = codigoP;
	}


	@Override
	public String toString() {
		return "Produto [nomeP=" + nomeP + ", descricao=" + descricao + ", valor=" + valor + ", codigoP=" + codigoP
				+ "]";
	}
	
	
	
}
