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
    private JButton bVendedor;
    private JButton bVoltar;

    private Menu menuPrincipal; // referência correta

    // 🔑 CONSTRUTOR RECEBE O MENU
    public MenuCadastro(Menu menuPrincipal) {

        this.menuPrincipal = menuPrincipal;

        setTitle("Menu de Cadastro");
        setBounds(300, 30, 300, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        bCliente = new JButton("Cliente");
        bCliente.setFont(new Font("Arial", Font.PLAIN, 15));
        bCliente.setBounds(90, 50, 120, 40);
        bCliente.addActionListener(this);
        container.add(bCliente);

        bProduto = new JButton("Produto");
        bProduto.setFont(new Font("Arial", Font.PLAIN, 15));
        bProduto.setBounds(90, 120, 120, 40);
        bProduto.addActionListener(this);
        container.add(bProduto);
        
        bVendedor = new JButton("Vendedor");
        bVendedor.setFont(new Font("Arial", Font.PLAIN, 15));
        bVendedor.setBounds(90, 190, 120, 40);
        bVendedor.addActionListener(this);
        container.add(bVendedor);

        bVoltar = new JButton("Voltar");
        bVoltar.setFont(new Font("Arial", Font.PLAIN, 15));
        bVoltar.setBounds(190, 320, 80, 25);
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

        } else if(e.getSource() == bVendedor) {
        	new CadastroVendedor(this);
        	setVisible(false);
        	
        }
        else if (e.getSource() == bVoltar) {
            menuPrincipal.setVisible(true); // ✅ menu existente
            dispose();                      // fecha só esta tela
        }
    }
}

