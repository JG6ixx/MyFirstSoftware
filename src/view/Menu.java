package view;

import java.awt.*;
import javax.swing.*;

public class Menu extends JFrame {

    private CardLayout cardLayout;
    private JPanel painelPrincipal;

    public Menu() {

        setTitle("Sistema de Vendas");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();
        painelPrincipal = new JPanel(cardLayout);

        painelPrincipal.add(criarPainelMenu(), "menu");
        painelPrincipal.add(criarPainelCadastro(), "cadastro");

        add(painelPrincipal);

        cardLayout.show(painelPrincipal, "menu");

        setVisible(true);
    }

    //Cria o Menu principal
    private JPanel criarPainelMenu() {
        JPanel painel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("MENU PRINCIPAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel botoes = new JPanel(new GridLayout(3, 1, 15, 15));
        botoes.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 150));

        JButton bVender = criarBotao("Vender");
        JButton bCadastrar = criarBotao("Cadastrar");
        JButton bConsultar = criarBotao("Consultar");

        bCadastrar.addActionListener(e -> cardLayout.show(painelPrincipal, "cadastro"));

        bVender.addActionListener(e -> {
            Tvender telaVender = new Tvender(this);
            telaVender.setVisible(true);
            this.setVisible(false); // opcional: esconder menu
        });

        bConsultar.addActionListener(e -> {
            Tconsulta telaConsulta = new Tconsulta();
            telaConsulta.setVisible(true);
            this.setVisible(false); // opcional
        });

       
       
        botoes.add(bVender);
        botoes.add(bCadastrar);
        botoes.add(bConsultar);
        
       
        painel.add(titulo, BorderLayout.NORTH);
        painel.add(botoes, BorderLayout.CENTER);

        return painel;
    }

    // Menu de Cadastro
    private JPanel criarPainelCadastro() {
        JPanel painel = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("MENU DE CADASTRO", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel botoes = new JPanel(new GridLayout(3, 1, 15, 15));
        botoes.setBorder(BorderFactory.createEmptyBorder(30, 150, 30, 150));

        JButton bCliente = criarBotao("Cliente");
        JButton bProduto = criarBotao("Produto");
        JButton bVendedor = criarBotao("Vendedor");

        JButton bVoltar = criarBotao("Voltar");
        
        //Acoes dos botoes de cadastro

        bCliente.addActionListener(e -> new CadastroCliente(this));
        bProduto.addActionListener(e -> new CadastroProduto(this));
        bVendedor.addActionListener(e -> new CadastroVendedor(this));

        bVoltar.addActionListener(e -> cardLayout.show(painelPrincipal, "menu"));

        botoes.add(bCliente);
        botoes.add(bProduto);
        botoes.add(bVendedor);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(botoes, BorderLayout.CENTER);
        painel.add(bVoltar, BorderLayout.SOUTH);

        return painel;
    }

    // Botoes mois bonitos
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


