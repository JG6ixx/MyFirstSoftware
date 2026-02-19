package model;

public class ItemVenda {
	
	private int quantidade;
	private double subtotal;
	private int desconto;
	private Produto produto;
	
	
	public ItemVenda() {}
	
	
	
	public ItemVenda(int quantidade, Produto produto, int desconto) {
	
		this.produto = produto;
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.subtotal = subtotal;
		calcularSubtotal();
		
	
	}
	
	
	public void calcularSubtotal() {
		subtotal = produto.getValor()*quantidade;
		this.subtotal = subtotal - ((subtotal*desconto)/100);
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
		calcularSubtotal();
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Produto getProduto() {
		return produto;
	}
	
	public int getDesconto() {
		return desconto;
	}



	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}
	


	
	public void setProduto(Produto produto) {
		this.produto = produto;
		calcularSubtotal();
	}



	@Override
	public String toString() {
		return "ItemVenda [ quantidade=" + quantidade + ", subtotal=" + subtotal + ", desconto=" + desconto
				+ ", produto=" + produto + "]";
	}



	



	


	







}
