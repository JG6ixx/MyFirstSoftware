package view;

import java.awt.Container;
import java.awt.Font;
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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        container = getContentPane();
        container.setLayout(null);
		
        nome =  new JLabel("Nome");
        nome.setFont(new Font("Arial",Font.PLAIN, 15));
        nome.setSize(100, 20);
        nome.setLocation(100, 100);
        container.add(nome);
        
        tNome =  new JTextField ("");
        tNome.setFont(new Font("Arial", Font.PLAIN, 15));
        tNome.setSize(230, 25);
        tNome.setLocation(150, 100);
        container.add(tNome);   
        
        telefone= new JLabel("Telefone");
        telefone.setFont(new Font("Arial", Font.PLAIN, 15));
        telefone.setSize(100, 20);
        telefone.setLocation(100, 150);
        container.add(telefone);
        
        tTelefone = new JTextField("");
        tTelefone.setFont(new Font("Afrial", Font.PLAIN, 15));
        tTelefone.setSize(230,25);
        tTelefone.setLocation(170, 150);
        container.add(tTelefone);
        
        
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
