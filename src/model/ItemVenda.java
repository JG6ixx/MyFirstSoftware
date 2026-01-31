package model;

public class ItemVenda {
	private static int cont = 0;
	private int idItemVenda;
	private int quantidade;
	private double subtotal;
	private Produto produto;
	
	
	public ItemVenda() {}
	
	
	
	public ItemVenda(int quantidade, Produto produto) {
		cont++;
		this.idItemVenda = cont;
		this.produto = produto;
		this.quantidade = quantidade;
		calcularSubtotal();
		
	
	}
	
	
	public void calcularSubtotal() {
		this.subtotal = produto.getValor()*quantidade;
	}
	
	
	public int getIdItemVenda() {
		return idItemVenda;
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
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
		calcularSubtotal();
	}



	@Override
	public String toString() {
		return "ItemVenda [idItemVenda=" + idItemVenda + ", quantidade=" + quantidade + ", subtotal=" + subtotal
				+ ", produto=" + produto + "]";
	}
}
