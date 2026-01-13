package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MenuCadastro extends JFrame implements ActionListener {

    private Container container;
    private JButton bCliente;
    private JButton bProduto;
    private JButton bVoltar;

    private Menu menuPrincipal; // referência correta

    // 🔑 CONSTRUTOR RECEBE O MENU
    public MenuCadastro(Menu menuPrincipal) {

        this.menuPrincipal = menuPrincipal;

        setTitle("Menu de Cadastro");
        setBounds(300, 30, 300, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        bCliente = new JButton("Cliente");
        bCliente.setFont(new Font("Arial", Font.PLAIN, 15));
        bCliente.setBounds(90, 50, 100, 50);
        bCliente.addActionListener(this);
        container.add(bCliente);

        bProduto = new JButton("Produto");
        bProduto.setFont(new Font("Arial", Font.PLAIN, 15));
        bProduto.setBounds(90, 120, 100, 50);
        bProduto.addActionListener(this);
        container.add(bProduto);

        bVoltar = new JButton("Voltar");
        bVoltar.setFont(new Font("Arial", Font.PLAIN, 15));
        bVoltar.setBounds(190, 220, 80, 25);
        bVoltar.addActionListener(this);
        container.add(bVoltar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bCliente) {
            new CadastroCliente(this);
            setVisible(false);

        } else if (e.getSource() == bProduto) {
            new CadastroProduto(this);
            setVisible(false);

        } else if (e.getSource() == bVoltar) {
            menuPrincipal.setVisible(true); // ✅ menu existente
            dispose();                      // fecha só esta tela
        }
    }
}

