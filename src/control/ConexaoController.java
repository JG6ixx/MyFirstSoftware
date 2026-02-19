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
    //Principal Classe que coordena as acoes de insercao em banco de dados


public class ConexaoController {
	
	private Conexao conectar;
	
	public ConexaoController(Conexao conectar) {
		this.conectar = conectar;
	}
	

	public int inserirProduto(Produto p) {

	    int idGerado = 0;

	    String sql = "INSERT INTO produto(nome, descricao, valor) VALUES (?, ?, ?) RETURNING id_produto";

	    try (PreparedStatement pd = conectar.getConexao().prepareStatement(sql)) {// Criando conexao com o banco

	        pd.setString(1, p.getNomeP());
	        pd.setString(2, p.getDescricao());
	        pd.setDouble(3, p.getValor());

	        ResultSet rs = pd.executeQuery();
	        if (rs.next()) {
	            idGerado = rs.getInt("id_produto");
	            p.setId_Produto(idGerado);//  Guarda o id gerado no objeto
	        }
	        
	        JOptionPane.showMessageDialog(null, "Produto Cadastrado!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return idGerado;// Retorna o id pois o banco gera os ids de foema aleatoria e unica
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
	            c.setId(idGerado); // guarda o id retornado no objeto
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
	            v.setId(vendaGerada);//estev id é essencial pois sera usado no metodo de insercao de itens       
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

	            ps.setInt(1, vend.getId()); //  id da venda
	            ps.setInt(2, item.getProduto().getId_Produto()); //  id do produto
	            ps.setInt(3, item.getQuantidade());
	            ps.setDouble(4, item.getSubtotal());
	            ps.setInt(5, item.getDesconto());

	            ps.executeUpdate();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
 
 
 //Lista os objetos das tabelas e os armazenam em ArrayLists--- estes serao usados em ConsulyaController
 public List<Produto> listarProdutos() {

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
 
 public List<Cliente> listarCliente() {
	 	List<Cliente> clientes = new ArrayList<>();

	    String sql = "SELECT * FROM cliente ";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Cliente c = new Cliente(
	                rs.getString("nome"),
	                rs.getString("telefone"),
	                rs.getString("endereco"),
	                rs.getString("email"),
	                rs.getString("cpf")
	            );
	            c.setId(rs.getInt("id_cliente"));
	            clientes.add(c);
	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return clientes;
	}
 
 
 
 public List<Vendedor> listarVendedor( ) {
	 List<Vendedor> vendedores = new ArrayList<>();
	 String sql = "SELECT * FROM vendedor ";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {
	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) {
	            Vendedor v = new Vendedor(
	                rs.getString("nome"),
	                rs.getString("telefone")
	                
	            );
	            v.setId(rs.getInt("id_vendedor"));
	            vendedores.add(v);
	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	 return vendedores;
 }
 public List<Venda> listarVendas() {

	    List<Venda> vendas = new ArrayList<>();

	    String sql = """
	        SELECT v.id_venda, v.data_venda, v.forma_pagamento, v.total_venda,
	               c.id_cliente, c.nome AS nome_cliente,
	               ve.id_vendedor, ve.nome AS nome_vendedor
	        FROM venda v
	        JOIN cliente c ON v.id_cliente = c.id_cliente
	        JOIN vendedor ve ON v.id_vendedor = ve.id_vendedor
	    """;

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {

	            Cliente c = new Cliente();
	            c.setId(rs.getInt("id_cliente"));
	            c.setNome(rs.getString("nome_cliente"));

	            Vendedor ve = new Vendedor();
	            ve.setId(rs.getInt("id_vendedor"));
	            ve.setNome(rs.getString("nome_vendedor"));

	            Venda venda = new Venda();
	            venda.setId(rs.getInt("id_venda"));
	            venda.setCliente(c);
	            venda.setVendedor(ve);
	            venda.setData(rs.getString("data_venda"));
	            venda.setFormaPagamento(rs.getString("forma_pagamento"));
	            venda.setTotal(rs.getDouble("total_venda"));

	            vendas.add(venda);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return vendas;
	}
 public List<ItemVenda> buscarItensVenda(int idVenda) {

	    List<ItemVenda> itens = new ArrayList<>();

	    String sql = """
	        SELECT iv.id_item_venda,
	               iv.quantidade,
	               iv.subtotal,
	               iv.desconto,
	               p.id_produto,
	               p.nome,
	               p.descricao,
	               p.valor
	        FROM item_venda iv
	        JOIN produto p ON iv.id_produto = p.id_produto
	        WHERE iv.id_venda = ?
	    """;

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {

	        ps.setInt(1, idVenda);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            Produto p = new Produto(
	                rs.getString("nome"),
	                rs.getString("descricao"),
	                rs.getDouble("valor")
	            );
	            p.setId_Produto(rs.getInt("id_produto"));

	            ItemVenda item = new ItemVenda();
	            item.setProduto(p);
	            item.setQuantidade(rs.getInt("quantidade"));
	            item.setSubtotal(rs.getDouble("subtotal"));
	            item.setDesconto(rs.getInt("desconto"));

	            itens.add(item);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return itens;
	}

 

	public Cliente buscarClientePorId(int id) {

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
	
	public void excluirCliente(int id) {

	    String sql = "DELETE FROM cliente WHERE id_cliente = ?";

	    try (PreparedStatement ps = conectar.getConexao().prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void excluirProduto(int idProduto) {

	    String sqlItem = "DELETE FROM item_venda WHERE id_produto = ?";
	    String sqlProduto = "DELETE FROM produto WHERE id_produto = ?";

	    try (PreparedStatement psItem = conectar.getConexao().prepareStatement(sqlItem);
	         PreparedStatement psProduto = conectar.getConexao().prepareStatement(sqlProduto)) {

	        psItem.setInt(1, idProduto);
	        psItem.executeUpdate();

	        psProduto.setInt(1, idProduto);
	        psProduto.executeUpdate();

	        JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Produto está vinculado a uma venda!");
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


