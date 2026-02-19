package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import banco.Conexao;
import java.awt.*;
import java.util.List;
import control.*;
import model.*;

public class Tvender extends JFrame {

    private JComboBox<Produto> cbProdutos;
    private JComboBox<Cliente> cbClientes;
    private JComboBox<Vendedor> cbVendedores;

    private JTextField txtQtd, txtDescontoItem, txtDescontoVenda;
    private JLabel lblTotal;

    private JRadioButton rbPix, rbCartao, rbDinheiro;

    private JTable tabela;
    private DefaultTableModel modelo;

    private VendaController vendaController = new VendaController();
    private ItemVendaController itemController = new ItemVendaController();
    private ProdutoController produtoController = new ProdutoController();
    private ClienteController clienteController = new ClienteController();
    private VendedorController vendedorController = new VendedorController();
    private ConexaoController conexaoController = new ConexaoController(new Conexao());

    private Venda vendaAtual;

    public Tvender() {

        setTitle("Tela de Venda");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        vendaAtual = new Venda();

        JPanel painelTopo = new JPanel(new GridLayout(4, 4, 5, 5));

        // CLIENTE
        painelTopo.add(new JLabel("Cliente:"));
        cbClientes = new JComboBox<>();
        carregarClientes();
        painelTopo.add(cbClientes);

        // VENDEDOR
        painelTopo.add(new JLabel("Vendedor:"));
        cbVendedores = new JComboBox<>();
        carregarVendedores();
        painelTopo.add(cbVendedores);

        // PAGAMENTO
        painelTopo.add(new JLabel("Pagamento:"));
        rbPix = new JRadioButton("PIX");
        rbCartao = new JRadioButton("Cartão");
        rbDinheiro = new JRadioButton("Dinheiro");

        ButtonGroup grupoPagamento = new ButtonGroup();
        grupoPagamento.add(rbPix);
        grupoPagamento.add(rbCartao);
        grupoPagamento.add(rbDinheiro);
        rbPix.setSelected(true);

        painelTopo.add(rbPix);
        painelTopo.add(rbCartao);
        painelTopo.add(rbDinheiro);

        // PRODUTO
        painelTopo.add(new JLabel("Produto:"));
        cbProdutos = new JComboBox<>();
        carregarProdutos();
        painelTopo.add(cbProdutos);

        painelTopo.add(new JLabel("Qtd:"));
        txtQtd = new JTextField();
        painelTopo.add(txtQtd);

        painelTopo.add(new JLabel("Desconto Item (%):"));
        txtDescontoItem = new JTextField("0");
        painelTopo.add(txtDescontoItem);

        JButton btnAdicionar = new JButton("Adicionar Item");
        painelTopo.add(btnAdicionar);

        add(painelTopo, BorderLayout.NORTH);

        // TABELA
        modelo = new DefaultTableModel(new Object[]{"Produto","Qtd","Preço","Subtotal"},0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // PAINEL INFERIOR
        JPanel painelBaixo = new JPanel(new GridLayout(3,2));

        painelBaixo.add(new JLabel("Desconto Venda (%):"));
        txtDescontoVenda = new JTextField("0");
        painelBaixo.add(txtDescontoVenda);

        painelBaixo.add(new JLabel("Total:"));
        lblTotal = new JLabel("R$ 0.00");
        painelBaixo.add(lblTotal);

        JButton btnFinalizar = new JButton("Finalizar Venda");
        painelBaixo.add(btnFinalizar);

        add(painelBaixo, BorderLayout.SOUTH);

        // EVENTOS
        btnAdicionar.addActionListener(e -> adicionarItem());
        btnFinalizar.addActionListener(e -> finalizarVenda());
    }

    private void carregarProdutos() {
        List<Produto> produtos = produtoController.listarProdutos();
        for (Produto p : produtos) cbProdutos.addItem(p);
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteController.listarClientes();
        for (Cliente c : clientes) cbClientes.addItem(c);
    }

    private void carregarVendedores() {
        List<Vendedor> vendedores = vendedorController.listarVendedores();
        for (Vendedor v : vendedores) cbVendedores.addItem(v);
    }

    private void adicionarItem() {

        Produto produto = (Produto) cbProdutos.getSelectedItem();
        int qtd = Integer.parseInt(txtQtd.getText());
        int descontoItem = Integer.parseInt(txtDescontoItem.getText());

        ItemVenda item = itemController.criarItemVenda(qtd, produto, descontoItem);
        vendaAtual.adicionarItem(item);

        modelo.addRow(new Object[]{
            produto.getNomeP(),
            qtd,
            produto.getValor(),
            item.getSubtotal()
        });

        atualizarTotal();
    }

    private void atualizarTotal() {
        double total = vendaAtual.getTotal();

        int descontoVenda = Integer.parseInt(txtDescontoVenda.getText());
        if (descontoVenda > 0) {
            total = total - (total * descontoVenda / 100);
        }

        lblTotal.setText("R$ " + String.format("%.2f", total));
    }

    private void finalizarVenda() {

        Cliente cliente = (Cliente) cbClientes.getSelectedItem();
        Vendedor vendedor = (Vendedor) cbVendedores.getSelectedItem();

        String formaPagamento = rbPix.isSelected() ? "PIX" :
                                rbCartao.isSelected() ? "Cartão" : "Dinheiro";

        int descontoVenda = Integer.parseInt(txtDescontoVenda.getText());

        vendaAtual.setCliente(cliente);
        vendaAtual.setVendedor(vendedor);
        vendaAtual.setFormaPagamento(formaPagamento);
       
        vendaAtual.setTotal(Double.parseDouble(lblTotal.getText().replace("R$", "").trim()));

        int idVenda = conexaoController.inserirVenda(vendaAtual);
        vendaAtual.setId(idVenda);

        conexaoController.inserirItem_Venda(vendaAtual);

        JOptionPane.showMessageDialog(this, "Venda salva com sucesso!");
        dispose();
    }
}
 

