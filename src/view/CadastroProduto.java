package view;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CadastroProduto extends JFrame implements ActionListener{
	
	private JLabel nomeP;
	private JTextField tNomeP;
	private JTextArea tDescricao;
	private JLabel descricao;
	private JLabel valor;
	private JTextField tValor;
	private Container container;
	private JTextArea Mensagem;

	public CadastroProduto() {
		
		setTitle("Cadastro de Produto");
		setBounds(300, 30, 500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		container = getContentPane();
		container.setLayout(null);
		
		
		nomeP = new JLabel("Nome do Produto:");
		nomeP.setFont(new Font("Arial", Font.PLAIN, 15));
		nomeP.setSize(200, 100);
		nomeP.setLocation(100, 100);
		container.add(nomeP);
		
		setVisible(true);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
