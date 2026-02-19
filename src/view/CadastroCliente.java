package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import control.ClienteController;

public class CadastroCliente extends JFrame implements ActionListener {

    private Menu menu;
    private ClienteController clienteController = new ClienteController();

    private JTextField tNome;
    private JTextField tTelefone;       //Definicao dos componetes que vao ter na tela
    private JTextField tEndereco;
    private JTextField tCpf;
    private JTextField tEmail;
    private JTextArea mensagem;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CadastroCliente(Menu menu) {
        super("Cadastro de Cliente");
        this.menu = menu;
        

        // Inicializando os componentes
        tNome = new JTextField(30);
        tTelefone = new JTextField(30);
        tEndereco = new JTextField(30);
        tCpf = new JTextField(32);
        tEmail = new JTextField(30);
        mensagem = new JTextArea(8, 25);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        // Adicionando açoes aos  botoes 
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Painel das componetes de entrada de dados
        
        JPanel painel = new JPanel(new FlowLayout());
       
        painel.setLayout(new FlowLayout());

        painel.add(new JLabel("Nome:"));
        painel.add( tNome);

        painel.add(new JLabel("Telefone:"));
        painel.add(tTelefone);

        painel.add(new JLabel("Endereço:"));
        painel.add(tEndereco);
        
        painel.add(new JLabel("CPF:"));
        painel.add(tCpf);

        painel.add(new JLabel("Email:"));
        painel.add(tEmail);

        painel.add(new JLabel("Mensagem:"));
        painel.add(mensagem);

        // Painel dos botoes
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);

        // Este painel junta os dois outros paineis
        setLayout(new BorderLayout());
        add(painel, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        
        setBounds(300, 30, 400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //Da "vida " as acoes dos botoes 
    public void actionPerformed(ActionEvent e) {
    	try {
    		if (e.getSource() == btnSalvar) {
                clienteController.cadastrarCliente(tNome.getText(), tCpf.getText(), tTelefone.getText(),  tEmail.getText() ,tEndereco.getText() );
                		
                //Chama o toString da Classe Cliente atraves da Classe ClienteController para exibir no TextArea o resultado da insersao
               JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
               mensagem.append("Cliente cadastrado:\n");
               mensagem.append(clienteController.ultimoClienteToString());
            }

        	// Retorna o menu de cadastro e sai da tela de cadastro de Cliente.

            if (e.getSource() == btnCancelar) {
                menu.setVisible(true);
                dispose();
    	}
    	
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na transferencia de dados!");

        }
    }
}

