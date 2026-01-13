package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Menu extends JFrame implements ActionListener {

    private Container container;
    private JButton bVender;
    private JButton bCadastrar;
    private JButton bConsultar;

    public Menu() {

        setTitle("Tela Inicial");
        setBounds(200, 90, 200, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        bVender = new JButton("Vender");
        bVender.setBounds(50, 50, 100, 50);
        bVender.addActionListener(this);
        container.add(bVender);

        bCadastrar = new JButton("Cadastrar");
        bCadastrar.setBounds(50, 120, 100, 50);
        bCadastrar.addActionListener(this);
        container.add(bCadastrar);

        bConsultar = new JButton("Consultar");
        bConsultar.setBounds(50, 190, 100, 50);
        bConsultar.addActionListener(this);
        container.add(bConsultar);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bCadastrar) {

            MenuCadastro mc = new MenuCadastro(this); // ✅ passa o menu
            mc.setVisible(true);
            setVisible(false);

        } else if (e.getSource() == bConsultar) {

            new Tconsulta();
            setVisible(false);

        } else if (e.getSource() == bVender) {

            new Tvender();
            setVisible(false);
        }
    }
}

