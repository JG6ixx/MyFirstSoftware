package control;


import java.sql.*;
import banco.Conexao;
import model.Cliente;
import model.Produto;
import model.Venda;
import model.Vendedor;
//                                         Esta classe gerencia as consultas e exclusoes 
public class ConsultaController {
		
	private Conexao conectar = new Conexao();
	
	public ConsultaController(Conexao conectar) {
		this.conectar = conectar;
	}

	public Cliente buscarClientePorId(int id) {
// SQL que retorna cliente pelo Id
		Cliente cliente = null;
		String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

		try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

			ps.setInt(1, id);
		    ResultSet rs = ps.executeQuery();

		    if (rs.next()) {
		    	cliente = new Cliente(
		        rs.getString("nome"),
		        rs.getString("telefone"),
		        rs.getString("endereco"),
		        rs.getString("email"),
		        rs.getString("cpf")
		            );
		        cliente.setId(rs.getInt("id_cliente"));
		        }

		 } catch (SQLException e) {
		        e.printStackTrace();
		 }

	 return cliente;
}
	// Esta SqL busca Vendedor pelo nome ou id
	public Vendedor buscarVendedor(String valor) {

	    Vendedor vendedor = null;
	    String sql = "SELECT * FROM vendedor WHERE id_vendedor = ? OR nome = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        ps.setString(1, valor);
	        ps.setString(2, valor);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            vendedor = new Vendedor(
	                rs.getString("nome"),
	                rs.getString("telefone")
	            );
	            vendedor.setId(rs.getInt("id_vendedor"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return vendedor;
	}
//Busca Produto pelo id ou nome
	public Produto buscarProdutoPorIdOuNome(String valor) {

	    Produto produto = null;
	    String sql = "SELECT * FROM produto WHERE id_produto = ? OR nome = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        // Se for número, tenta como ID
	        if (valor.matches("\\d+")) {
	            ps.setInt(1, Integer.parseInt(valor));
	        } else {
	            ps.setNull(1, java.sql.Types.INTEGER);
	        }

	        // Sempre tenta como nome também
	        ps.setString(2, valor);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            produto = new Produto(
	                rs.getString("nome"),
	                rs.getString("descricao"),
	                rs.getDouble("valor")
	            );
	            produto.setId_Produto(rs.getInt("id_produto"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return produto;
	}
	// USa o JOIN patra buscar venda e une com as tabelas de itemVenda, Cliente e Vendedor no banco de dados para o retormo correto
	public Venda buscarVenda(String valor) {

	    Venda venda = null;

	    String sql = """
	        SELECT v.id_venda, v.data_venda, v.forma_pagamento, v.total_venda,
	               c.id_cliente, c.nome AS nome_cliente,
	               ve.id_vendedor, ve.nome AS nome_vendedor
	        FROM venda v
	        JOIN cliente c ON v.id_cliente = c.id_cliente
	        JOIN vendedor ve ON v.id_vendedor = ve.id_vendedor
	        WHERE v.id_venda = ? OR c.nome LIKE ? OR ve.nome LIKE ?
	    """;

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        // Se for número, tenta como ID da venda
	        if (valor.matches("\\d+")) {
	            ps.setInt(1, Integer.parseInt(valor));
	        } else {
	            ps.setNull(1, java.sql.Types.INTEGER);
	        }

	        ps.setString(2, "%" + valor + "%");
	        ps.setString(3, "%" + valor + "%");

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            Cliente c = new Cliente();
	            c.setId(rs.getInt("id_cliente"));
	            c.setNome(rs.getString("nome_cliente"));

	            Vendedor ve = new Vendedor();
	            ve.setId(rs.getInt("id_vendedor"));
	            ve.setNome(rs.getString("nome_vendedor"));

	            venda = new Venda();
	            venda.setId(rs.getInt("id_venda"));
	            venda.setCliente(c);
	            venda.setVendedor(ve);
	            venda.setData(rs.getString("data_venda"));
	            venda.setFormaPagamento(rs.getString("forma_pagamento"));
	            venda.setTotal(rs.getDouble("total_venda"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return venda;
	}
	// SQL que exclui  instancias do banco de dados
	//Estes metodos sao chamados Em Tconsulta 
	public void excluirCliente(int id) {

	    String sql = "DELETE FROM cliente WHERE id_cliente = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void excluirProduto(int id) {

	    String sql = "DELETE FROM produto WHERE id_produto = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void excluirVendedor(int id) {

	    String sql = "DELETE FROM vendedor WHERE id_vendedor = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}





}

