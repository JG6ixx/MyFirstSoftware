package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import banco.Conexao;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import control.*;
import model.*;

public class Tvender extends JFrame {
	//Definicao de componentes e Referencias as classes usadaS
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

    private Menu menu;

    public Tvender(Menu menu) {

        this.menu = menu;
//Definicao da tela 
        setTitle("Tela de Venda");
        setSize(1400, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        vendaAtual = new Venda();

        
        JPanel painelTopo = new JPanel(new GridLayout(4, 4, 5, 5));
//Aqui estoua adicionando os componente ao Layout da tela 
        painelTopo.add(new JLabel("Cliente:"));
        cbClientes = new JComboBox<>();
        carregarClientes();
        painelTopo.add(cbClientes);

        painelTopo.add(new JLabel("Vendedor:"));
        cbVendedores = new JComboBox<>();
        carregarVendedores();
        painelTopo.add(cbVendedores);

        painelTopo.add(new JLabel("Pagamento:"));
        rbPix = new JRadioButton("PIX");
        rbCartao = new JRadioButton("Cartão");
        rbDinheiro = new JRadioButton("Dinheiro");
//As forma de pagamento ficaram em forma de botao e aqui esta definindo  grfupo de botoes 
        ButtonGroup grupoPagamento = new ButtonGroup();
        grupoPagamento.add(rbPix);
        grupoPagamento.add(rbCartao);
        grupoPagamento.add(rbDinheiro);
        rbPix.setSelected(true);

        painelTopo.add(rbPix);
        painelTopo.add(rbCartao);
        painelTopo.add(rbDinheiro);

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

        JButton btnAdicionar = criarBotao("Adicionar Item");
        painelTopo.add(btnAdicionar);

        add(painelTopo, BorderLayout.NORTH);

        //Tabela que vai mostrar os itens que serao adicionados na venda 
        modelo = new DefaultTableModel(new Object[]{"Produto","Qtd","Preço","Subtotal"},0);
        tabela = new JTable(modelo);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        //O painel de baixo que contem mais alguns componentes 
        JPanel painelBaixo = new JPanel(new GridLayout(4,2,10,10));

        painelBaixo.add(new JLabel("Desconto Venda (%):"));
        txtDescontoVenda = new JTextField("0");
        painelBaixo.add(txtDescontoVenda);

        painelBaixo.add(new JLabel("Total:"));
        lblTotal = new JLabel("R$ 0.00");
        painelBaixo.add(lblTotal);

        JButton btnRemoverItem = criarBotao("Remover Item");
        JButton btnFinalizar = criarBotao("Finalizar Venda");
        JButton btnCancelar = criarBotao("Cancelar Venda");

        painelBaixo.add(btnRemoverItem);
        painelBaixo.add(btnFinalizar);
        painelBaixo.add(btnCancelar);

        add(painelBaixo, BorderLayout.SOUTH);

        //Adicionando acoes aos botoes 
        
        btnAdicionar.addActionListener(e -> adicionarItem());
        btnRemoverItem.addActionListener(e -> removerItemSelecionado());

        btnFinalizar.addActionListener(e -> {
            finalizarVenda();
            menu.setVisible(true);
            dispose();
        });

        btnCancelar.addActionListener(e -> {
            int opcao = JOptionPane.showConfirmDialog(
                this,
                "Deseja cancelar a venda?",
                "Cancelar",
                JOptionPane.YES_NO_OPTION
            );

            if (opcao == JOptionPane.YES_OPTION) {
                menu.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

//Metodos para mostrar os uma lista com todos os Produtos, Clientes e Vendedores cadastrados no banco de dados
    
    private void carregarProdutos() {
        List<Produto> produtos = produtoController.listarProdutos();
        for (Produto p : produtos) cbProdutos.addItem(p);
    }

    private void carregarClientes() {
        List<Cliente> clientes = clienteController.listarClientes();
        for (Cliente c : clientes) cbClientes.addItem(c);
    }

    private void carregarVendedores() {
        List<Vendedor> vendedores = vendedorController.listarVendedor();
        for (Vendedor v : vendedores) cbVendedores.addItem(v);
    }

    //Adicionando Itens a Venda e atualizando o Valor Total
    private void adicionarItem() {
        try {
            Produto produto = (Produto) cbProdutos.getSelectedItem();
            int qtd = Integer.parseInt(txtQtd.getText());
            int descontoItem = Integer.parseInt(txtDescontoItem.getText());

            //Chamada do metodo que adiciona os itens
            ItemVenda item = itemController.criarItemVenda(qtd, produto, descontoItem);
            vendaAtual.adicionarItem(item);
            //Organizando as informacoes na tabela adicionando uma linha 
            modelo.addRow(new Object[]{
                produto.getNomeP(),
                qtd,
                produto.getValor(),
                item.getSubtotal()
            });

            atualizarTotal();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar item. Verifique os dados.");
        }
    }
//Remove o item selecionado
    private void removerItemSelecionado() {
        int linha = tabela.getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover.");
            return;
        }

        modelo.removeRow(linha);
        vendaAtual.removerItem(linha);
        atualizarTotal();
    }
//Atualiza o total caso tenha algum desconto inserido
    private void atualizarTotal() {
        double total = vendaAtual.getTotal();

        int descontoVenda = Integer.parseInt(txtDescontoVenda.getText());
        if (descontoVenda > 0) {
            total -= total * descontoVenda / 100;
        }

        lblTotal.setText("R$ " + String.format("%.2f", total));
    }
// Finaliza e manda o controller salvar a venda no banco de Dados
    private void finalizarVenda() {

        Cliente cliente = (Cliente) cbClientes.getSelectedItem();
        Vendedor vendedor = (Vendedor) cbVendedores.getSelectedItem();

        String formaPagamento = rbPix.isSelected() ? "PIX" :
                                rbCartao.isSelected() ? "Cartão" : "Dinheiro";

        vendaAtual.setCliente(cliente);
        vendaAtual.setVendedor(vendedor);
        vendaAtual.setFormaPagamento(formaPagamento);
        vendaAtual.setData(LocalDate.now().toString());

        String totalTexto = lblTotal.getText()
                .replace("R$", "")
                .replace(",", ".")
                .trim();

        vendaAtual.setTotal(Double.parseDouble(totalTexto));

        int idVenda = conexaoController.inserirVenda(vendaAtual);// Solicitando que o controllher salve no banco

        if (idVenda == 0) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar venda!");
            return;
        }

        vendaAtual.setId(idVenda);
        conexaoController.inserirItem_Venda(vendaAtual);

        JOptionPane.showMessageDialog(this, "Venda salva com sucesso!");
    }
// Metodo para criar botoes mais 'bonitinhos'
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(52, 152, 219));
        botao.setForeground(Color.WHITE);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return botao;
    }
        		
    
}
