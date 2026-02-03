package control;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.AppMFS;
import banco.Conexao;
import model.Produto;



public class ConexaoController {
	
	private Conexao conectar;
	
	public ConexaoController(Conexao conectar) {
		this.conectar = conectar;
	}
	

 public void inserir(Produto p2) {

     String sql = "INSERT INTO produto(nome, descricao, valor, id_produto) VALUES (?, ?, ?, ?)";

     try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

         ps.setString(1, p2.getNomeP());
         ps.setString(2, p2.getDescricao());
         ps.setDouble(3, p2.getValor()); // índice corrigido
         ps.setInt(4, p2.getId_Produto());

         ps.executeUpdate();

     } catch (SQLException e) {
         e.printStackTrace();
     }
 
 }
}


