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
        setBounds(200, 90, 400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        bVender = new JButton("Vender");
        bVender.setBounds(150, 50, 120, 40);
        bVender.addActionListener(this);
        container.add(bVender);

        bCadastrar = new JButton("Cadastrar");
        bCadastrar.setBounds(150, 120, 120, 40);
        bCadastrar.addActionListener(this);
        container.add(bCadastrar);

        bConsultar = new JButton("Consultar");
        bConsultar.setBounds(150, 190, 120, 40);
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

             new Tvender().setVisible(true);
             setVisible(false);
           
        }
    }
}

