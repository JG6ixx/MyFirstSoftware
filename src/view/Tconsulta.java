package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import banco.Conexao;

import java.awt.*;
import java.util.List;

import control.ConexaoController;
import control.ConsultaController;
import model.*;

public class Tconsulta extends JFrame {

    private JComboBox<String> cbTipoConsulta;
    private JTable tabela;
    private DefaultTableModel modelo;
    private Menu menu = new Menu();
    

    private ConexaoController controller = new ConexaoController(new Conexao());

    public Tconsulta() {

        setTitle("Consulta Geral");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cbTipoConsulta = new JComboBox<>(new String[]{
            "Clientes", "Produtos", "Vendedores", "Vendas"
        });

      JButton  btnConsultar = criarBotao("Consultar");
      JButton   btnExcluir = criarBotao("Excluir Selecionado");
      JButton btnCancelar = criarBotao("Cancelar");

        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Consultar:"));
        painelTopo.add(cbTipoConsulta);
        painelTopo.add(btnConsultar);
        painelTopo.add(btnExcluir);
        painelTopo.add(btnCancelar);

        add(painelTopo, BorderLayout.NORTH);

        modelo = new DefaultTableModel();
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        btnConsultar.addActionListener(e -> consultar());
        btnExcluir.addActionListener(e -> excluirSelecionado());
        btnCancelar.addActionListener(e -> {
            menu.setVisible(true);
            dispose();
        });
    }
//REeferente as Consultas
    private void consultar() {

        String tipo = cbTipoConsulta.getSelectedItem().toString();
        modelo.setRowCount(0);
        modelo.setColumnCount(0);

        switch (tipo) {
         // Retorna todos os itens existente no banco de dados em uma tabela com os atributos

            case "Clientes":
                modelo.setColumnIdentifiers(new String[]{"ID", "Nome", "Telefone", "Endereço", "Email", "CPF"});
                List<Cliente> clientes = controller.listarCliente();

                for (Cliente c : clientes) {
                    modelo.addRow(new Object[]{
                        c.getId(), c.getNome(), c.getTelefone(),
                        c.getEndereco(), c.getEmail(), c.getCpf()
                    });
                }
                break;

            case "Produtos":
                modelo.setColumnIdentifiers(new String[]{"ID", "Nome", "Descrição", "Valor"});
                List<Produto> produtos = controller.listarProdutos();

                for (Produto p : produtos) {
                    modelo.addRow(new Object[]{
                        p.getId_Produto(), p.getNomeP(),
                        p.getDescricao(), p.getValor()
                    });
                }
                break;

            case "Vendedores":
                modelo.setColumnIdentifiers(new String[]{"ID", "Nome", "Telefone"});
                List<Vendedor> vendedores = controller.listarVendedor();

                for (Vendedor v : vendedores) {
                    modelo.addRow(new Object[]{
                        v.getId(), v.getNome(), v.getTelefone()
                    });
                }
                break;

            case "Vendas":
                modelo.setColumnIdentifiers(new String[]{
                    "ID Venda", "Cliente", "Vendedor", "Data",
                    "Forma Pagamento", "Produto", "Qtd", "Subtotal", "Desconto"
                });

                List<Venda> vendas = controller.listarVendas(); //Este metodo lista as Venda salvas 
                for (Venda v : vendas) {

                    List<ItemVenda> itens = controller.buscarItensVenda(v.getId());

                    for (ItemVenda item : itens) {
                        modelo.addRow(new Object[]{
                            v.getId(),
                            v.getCliente().getNome(),
                            v.getVendedor().getNome(),
                            v.getData(),
                            v.getFormaPagamento(),
                            item.getProduto().getNomeP(),
                            item.getQuantidade(),
                            item.getSubtotal(),
                            item.getDesconto()
                        });
                    }
                }
                break;
        }
    }

    
    // Esta parte implementa as a parte da view que exclui
    private void excluirSelecionado() {

        int linha = tabela.getSelectedRow();// Linha selecionada

        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha!");
            return;
        }
        //Pega o tipo de consulta selecionado (Cliente, Vendedor, Produto)
        String tipo = cbTipoConsulta.getSelectedItem().toString();
        int id = Integer.parseInt(modelo.getValueAt(linha, 0).toString());

        int op = JOptionPane.showConfirmDialog(
            this,
            "Deseja excluir este registro?",     //Mensagem de confirmacao
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (op != JOptionPane.YES_OPTION) return;

        switch (tipo) {

            case "Clientes":
                controller.excluirCliente(id);
                break;

            case "Produtos":
                controller.excluirProduto(id);
                break;

            case "Vendedores":
                controller.excluirVendedor(id);
                break;
        }

        consultar(); // atualiza tabela
    }
    
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

