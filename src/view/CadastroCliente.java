package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import control.ClienteController;

public class CadastroCliente extends JFrame implements ActionListener {

    private MenuCadastro menuCadastro;
    private ClienteController clienteController = new ClienteController();

    private JTextField tNome;
    private JTextField tTelefone;
    private JTextField tEndereco;
    private JTextField tCpf;
    private JTextField tEmail;
    private JTextArea mensagem;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CadastroCliente(MenuCadastro menuCadastro) {
        super("Cadastro de Cliente");
        this.menuCadastro = menuCadastro;

        // Inicializando componentes
        tNome = new JTextField(2);
        tTelefone = new JTextField(2);
        tEndereco = new JTextField(2);
        tCpf = new JTextField(2);
        tEmail = new JTextField(2);
        mensagem = new JTextArea(5, 20);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        // Registrar listeners
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Painel principal (vertical)
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));

        panelForm.add(new JLabel("Nome:"));
        panelForm.add( tNome);

        panelForm.add(new JLabel("Telefone:"));
        panelForm.add(tTelefone);

        panelForm.add(new JLabel("Endereço:"));
        panelForm.add(tEndereco);
        
        panelForm.add(new JLabel("CPF:"));
        panelForm.add(tCpf);

        panelForm.add(new JLabel("Email:"));
        panelForm.add(tEmail);

        panelForm.add(new JLabel("Mensagem:"));
        panelForm.add(new JScrollPane(mensagem));

        // Painel dos botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);

        // Layout principal
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        // Configurações da janela
        setBounds(300, 30, 400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	try {
    		if (e.getSource() == btnSalvar) {
                clienteController.cadastrarCliente(tNome.getText(), tCpf.getText(), tTelefone.getText(),  tEmail.getText() ,tEndereco.getText() );
                		

               JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
               mensagem.append("Cliente cadastrado:\n");
               mensagem.append(clienteController.ultimoClienteToString());
            }

        	

            if (e.getSource() == btnCancelar) {
                menuCadastro.setVisible(true);
                dispose();
    	}
    	
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na transferencia de dados!");

        }
    }
}

