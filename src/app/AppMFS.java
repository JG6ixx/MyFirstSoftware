package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import banco.Conexao;
import control.ConexaoController;
import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import model.Vendedor;
import view.CadastroProduto;
import view.Menu;
public class AppMFS {

	
	
	
	public static void main(String[] args) {
		
		/*Produto p1 = new Produto("Copo 300ml", "Copo Copozan pct 100und", 12.00);
		
		Produto pp = new Produto("Copo 300ml", "Copo Copozan pct 100und", 15.00);
		
		Cliente c = new Cliente("Joao Silva","62994575470", "Avenida 10, Residencial Prime Sul", "joaog.oliveeira2@gmail.com", "14063554620");
		Vendedor vds = new Vendedor("Joao Gabriel", "8554585458");
		
		
		 
		
		Venda v2 =  new Venda("debito",vds, c,"16/02/27");
		ItemVenda it = new ItemVenda(3,pp,1);
		ItemVenda i2 = new ItemVenda(4,p1,3);
		
		
		v2.adicionarItem(it);
		v2.adicionarItem(i2);
		
		Conexao conectar = new Conexao();
		
		ConexaoController cx = new ConexaoController(conectar);
		
		cx.inserirProduto(p1);
		cx.inserirProduto(pp);
		cx.inserirCliente(c);
		cx.inserirVendedor(vds);
		cx.inserirVenda(v2);
		cx.inserirItem_Venda(v2);
		
		conectar.fecharConexao();*/
		
		
		
		//CadastroProduto cp = new CadastroProduto();
		
		Menu m = new Menu();
		
	}
       
}