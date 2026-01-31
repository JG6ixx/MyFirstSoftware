package model;

import java.util.ArrayList;
import java.util.List;

public class Venda {
	
	private static int cont = 0;
	private String formaPagamento;
	private Vendedor vendedor;
	private String data;
	private int id;
	private double total;
	private Cliente cliente;
	private List<ItemVenda> itens =  new ArrayList<>();
	
	public Venda (String formaPagamento, Vendedor vendedor, Cliente cliente, String data) {
		cont++;
		this.id = cont;
		this.data = data;
		this.formaPagamento = formaPagamento;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.itens = new ArrayList<>();
		
	}
	
	public void adicionarItem(ItemVenda item) {
		this.itens.add(item);
		calcularTotal();
	}
	
	public void calcularTotal() {
		total = 0;
		for(ItemVenda item : itens) {
			total += item.getSubtotal();
		}
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	@Override
	public String toString() {
		return "Venda [formaPagamento=" + formaPagamento + ", vendedor=" + vendedor + ", data=" + data + ", id=" + id
				+ ", total=" + total + ", cliente=" + cliente + ", itens=" + itens + "]";
	}

}
