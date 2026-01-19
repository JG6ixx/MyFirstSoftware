package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CadastroCliente extends JFrame implements ActionListener {
	
	
	private Container container;
	private JButton bEnviar;
	private JButton bResetar;
	private JButton bVoltar;
	private JLabel nome;
	private JTextField tNome;
	private JLabel telefone; 
	private JTextField tTelefone;
	private JLabel endereco;
	private JTextField tEndereco;
	private JLabel id;
	private ArrayList cliente;
	private JTextArea mensagem;
	 


	public CadastroCliente(MenuCadastro menuCadastro) {
		
		setTitle("Cadastro de Cliente");
		setBounds(300, 30, 500, 500);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

} 
