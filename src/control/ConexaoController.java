package control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.AppMFS;
import banco.Conexao;
import model.Cliente;
import model.Produto;
import model.Venda;
import model.Vendedor;



public class ConexaoController {
	
	private Conexao conectar;
	
	public ConexaoController(Conexao conectar) {
		this.conectar = conectar;
	}
	

 public void inserirProduto(Produto p) {

     String sql = "INSERT INTO produto(nome, descricao, valor, id_produto) VALUES (?, ?, ?, ?)";

     try (PreparedStatement pd = conectar.getConexao().prepareStatement(sql)) {

         pd.setString(1, p.getNomeP());
         pd.setString(2, p.getDescricao());
         pd.setDouble(3, p.getValor()); // índice corrigido
         pd.setInt(4, p.getId_Produto());

         pd.executeUpdate();

     } catch (SQLException e) {
         e.printStackTrace();
     }
 
 }
 
 public void inserirCliente(Cliente c ) {
	 
	 String sql = "INSERT INTO cliente(nome, endereco, telefone, email, cpf, id_cliente) VALUES (?, ?, ?, ?, ?, ?)";
	 try(PreparedStatement cl = conectar.getConexao().prepareStatement(sql)){
		 
		 cl.setString(1, c.getNome());
		 cl.setString(2, c.getEndereco());
		 cl.setString(3, c.getTelefone());
		 cl.setString(4, c.getEmail());
		 cl.setString(5, c.getCpf());
		 cl.setInt(6, c.getId());
		 
		 cl.executeUpdate();
		 
	 } catch (SQLException e ){
		 e.printStackTrace();
	 }
	 
 }
 
 public void inserirVendedor(Vendedor c) {

	 String sql = "INSERT INTO vendedor(nome, telefone, id_vendedor) VALUES (?, ?, ?)";
	 try(PreparedStatement vdd = conectar.getConexao().prepareStatement(sql)){
		 
		 vdd.setString(1, c.getNome());
		 vdd.setString(2, c.getTelefone());
		 vdd.setInt(3, c.getId());
		
		 vdd.executeUpdate();
		 
	 } catch (SQLException e ){
		 e.printStackTrace();
	 }
	 
 }
 
 public void inserirVenda(Venda v) {
	 String sql =  "INSERT INTO venda(nome, telefone, id_vendedor) VALUES (?, ?, ?)";
	 
 }
}


