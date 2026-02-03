package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import banco.Conexao;
import control.ConexaoController;
import model.Cliente;
import model.Produto;
import view.CadastroProduto;
import view.Menu;
public class AppMFS {

	
	
	
	public static void main(String[] args) {
		
		/*Produto p = new Produto("Copo 300ml", "Copo Copozan pct 100und", 12.00);
		
		Produto p2 = new Produto("Copo 300ml", "Copo Copozan pct 100und", 15.00);
		
		Conexao conectar = new Conexao();
		
		ConexaoController cx = new ConexaoController(conectar);
		
		cx.inserir(p2);
		
		conectar.fecharConexao();*/
		
		CadastroProduto cp = new CadastroProduto();
		
		
		// TODO Auto-generated method stub
		
	}
       
}