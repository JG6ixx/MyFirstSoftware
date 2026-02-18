package control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.AppMFS;
import banco.Conexao;
import model.Cliente;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import model.Vendedor;



public class ConexaoController {
	
	private Conexao conectar;
	
	public ConexaoController(Conexao conectar) {
		this.conectar = conectar;
	}
	

	public int inserirProduto(Produto p) {

	    int idGerado = 0;

	    String sql = "INSERT INTO produto(nome, descricao, valor) VALUES (?, ?, ?) RETURNING id_produto";

	    try (PreparedStatement pd = conectar.getConexao().prepareStatement(sql)) {

	        pd.setString(1, p.getNomeP());
	        pd.setString(2, p.getDescricao());
	        pd.setDouble(3, p.getValor());

	        ResultSet rs = pd.executeQuery();
	        if (rs.next()) {
	            idGerado = rs.getInt("id_produto");
	            p.setId_Produto(idGerado);
	        }
	        
	        JOptionPane.showMessageDialog(null, "Produto Cadastrado!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idGerado;
	}


 
	public int inserirCliente(Cliente c) {

	    int idGerado = 0;

	    String sql = "INSERT INTO cliente(nome, cpf, telefone, email, endereco  ) "
	               + "VALUES (?, ?, ?, ?, ?) RETURNING id_cliente";

	    try (PreparedStatement cl = conectar.getConexao().prepareStatement(sql)) {

	        cl.setString(1, c.getNome());
	        cl.setString(5, c.getEndereco());
	        cl.setString(3, c.getTelefone());
	        cl.setString(4, c.getEmail());
	        cl.setString(2, c.getCpf());

	        ResultSet rs = cl.executeQuery();
	        if (rs.next()) {
	            idGerado = rs.getInt("id_cliente");
	            c.setId(idGerado); // guarda no objeto
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idGerado;
	}

 
	public int inserirVendedor(Vendedor v) {

	    int idGerado = 0;

	    String sql = "INSERT INTO vendedor(nome, telefone) VALUES (?, ?) RETURNING id_vendedor";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        ps.setString(1, v.getNome());
	        ps.setString(2, v.getTelefone());

	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            idGerado = rs.getInt("id_vendedor");
	            v.setId(idGerado); // <<< ESSENCIAL
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idGerado;
	}

 
 public int inserirVenda(Venda v) {

	    int vendaGerada = 0;

	    String sql = "INSERT INTO venda(id_cliente, id_vendedor, data_venda, forma_pagamento, total_venda) "
	               + "VALUES (?, ?, ?, ?, ?) RETURNING id_venda";

	    try (PreparedStatement vd = conectar.getConexao().prepareStatement(sql)) {

	        vd.setInt(1, v.getCliente().getId());
	        vd.setInt(2, v.getVendedor().getId());
	        vd.setString(3, v.getData());
	        vd.setString(4, v.getFormaPagamento());
	        vd.setDouble(5, v.getTotal());

	        ResultSet rs = vd.executeQuery();
	        if (rs.next()) {
	            vendaGerada = rs.getInt("id_venda");
	            v.setId(vendaGerada); // <<< ESSENCIAL
	        }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return vendaGerada;
	}

 public void inserirItem_Venda(Venda vend) {

	    String sql = "INSERT INTO item_venda(id_venda, id_produto, quantidade, subtotal, desconto) "
	               + "VALUES(?,?,?,?,?)";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        for (ItemVenda item : vend.getItens()) {

	            ps.setInt(1, vend.getId()); // <<< id da venda
	            ps.setInt(2, item.getProduto().getId_Produto()); // <<< id do produto
	            ps.setInt(3, item.getQuantidade());
	            ps.setDouble(4, item.getSubtotal());
	            ps.setInt(5, item.getDesconto());

	            ps.executeUpdate();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
 
 public List<Produto> buscarProdutos() {

	    List<Produto> produtos = new ArrayList<>();

	    String sql = "SELECT * FROM produto";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Produto p = new Produto(
	                rs.getString("nome"),
	                rs.getString("descricao"),
	                rs.getDouble("valor")
	            );
	            p.setId_Produto(rs.getInt("id_produto"));

	            produtos.add(p);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return produtos;
	}
 
 public Cliente buscarClientePorCpf(String cpf) {

	    String sql = "SELECT * FROM cliente WHERE cpf = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        ps.setString(1, cpf);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Cliente c = new Cliente(
	                rs.getString("nome"),
	                rs.getString("telefone"),
	                rs.getString("endereco"),
	                rs.getString("email"),
	                rs.getString("cpf")
	            );
	            c.setId(rs.getInt("id_cliente"));
	            return c;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
 
 public Cliente buscarClientePorId(int id_cliente) {
	 String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        ps.setInt(1, id_cliente);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Cliente c = new Cliente(
	                rs.getString("nome"),
	                rs.getString("telefone"),
	                rs.getString("endereco"),
	                rs.getString("email"),
	                rs.getString("cpf")
	            );
	            c.setId(rs.getInt("id_cliente"));
	            return c;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	return null;
	 
 }
 
 public Vendedor buscarVendedorPorId( int id_vendedor) {
	 String sql = "SELECT * FROM vendedor WHERE id_vendedor = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        ps.setInt(1, id_vendedor);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Vendedor v = new Vendedor(
	                rs.getString("nome"),
	                rs.getString("telefone")
	                
	            );
	            v.setId(rs.getInt("id_vendedor"));
	            return v;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	 return null;
 }
 
 public Venda buscarVendaPorId(int idVenda) {

	    String sqlVenda = "SELECT * FROM venda WHERE id_venda = ?";
	    Venda venda = null;

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sqlVenda)) {

	        ps.setInt(1, idVenda);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Cliente c = buscarClientePorId(rs.getInt("id_cliente"));
	            Vendedor v = buscarVendedorPorId(rs.getInt("id_vendedor"));

	            venda = new Venda(
	                rs.getString("forma_pagamento"),
	                v,
	                c,
	                rs.getString("data_venda")
	            );

	            venda.setId(idVenda);
	            venda.setTotal(rs.getDouble("total_venda"));

	            buscarItensDaVenda(venda);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return venda;
	}
 
 
 public Produto buscarProdutoPorId(int idProduto) {
	    String sql = "SELECT * FROM produto WHERE id_produto = ?";
	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {
	        ps.setInt(1, idProduto);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            Produto p = new Produto(
	                rs.getString("nome"),
	                rs.getString("descricao"),
	                rs.getDouble("valor")
	            );
	            p.setId_Produto(idProduto);
	            return p;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

 
 public void buscarItensDaVenda(Venda venda) {

	    String sql = "SELECT * FROM item_venda WHERE id_venda = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        ps.setInt(1, venda.getId());
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Produto p = buscarProdutoPorId(rs.getInt("id_produto"));


	            ItemVenda item = new ItemVenda(
	                rs.getInt("quantidade"),
	                p,
	                rs.getInt("desconto")
	            );

	            venda.adicionarItem(item);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}






}


