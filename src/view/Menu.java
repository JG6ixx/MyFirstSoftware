package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		bVender.setFont(new Font("Arial", Font.PLAIN, 15));
		bVender.setSize(100, 50);
		bVender.setLocation(50, 50);
		bVender.addActionListener(this);
		container.add(bVender);
		
		bCadastrar =  new JButton("Cadastrar");
		bCadastrar.setFont(new Font("Arial", Font.PLAIN, 15 ));
		bCadastrar.setSize(100, 50);
		bCadastrar.setLocation(50,120);
		bCadastrar.addActionListener(this);
		container.add(bCadastrar);
		
		bConsultar = new JButton("Consultar");
		bConsultar.setFont(new Font("Arial", Font.PLAIN, 15));
		bConsultar.setSize(100,50);
		bConsultar.setLocation(50,190);
		bConsultar.addActionListener(this);
		container.add(bConsultar);
		
		setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bConsultar) {
			
			Tconsulta c = new Tconsulta();
			
		}else if(e.getSource() == bCadastrar) {
			
			Tcadastrar ca = new Tcadastrar();
		
		}else if(e.getSource() == bVender) {
		
			Tvender tv = new Tvender();
		}
	}

}
