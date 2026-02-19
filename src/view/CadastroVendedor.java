package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.VendedorController;



public class CadastroVendedor  extends JFrame implements ActionListener{
	
	
    private VendedorController vendedorController = new VendedorController();

    private JTextField tNome;
    private JTextField tTelefone;
    private JTextArea mensagem;

    private JButton btnSalvar;
    private JButton btnCancelar;
	private Menu menu;

    public CadastroVendedor(Menu menu) {
        super("Cadastro de Vendedor");
        this.menu = menu;

        // Inicializando componentes
        tNome = new JTextField(30);
        tTelefone = new JTextField(30);
        mensagem = new JTextArea(8, 20);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        // Registrar listeners
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Painel principal (vertical)
        JPanel painel = new JPanel(new FlowLayout());
        painel.setLayout(new FlowLayout());

        painel.add(new JLabel("Nome:"));
        painel.add( tNome);

        painel.add(new JLabel("Telefone:"));
        painel.add(tTelefone);

        painel.add(new JLabel("Mensagem:"));
        painel.add(new JScrollPane(mensagem));

        // Painel dos botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        // Layout principal
        setLayout(new BorderLayout());
        add(painel, BorderLayout.BEFORE_FIRST_LINE);
        add(painelBotoes, BorderLayout.SOUTH);

        // Configurações da janela
        setBounds(300, 30, 400,400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	try {
    		if (e.getSource() == btnSalvar) {
                vendedorController.cadastrarVendedor(tNome.getText(), tTelefone.getText() );
                		

               JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
               mensagem.append("Vendedor cadastrado:\n");
               mensagem.append(vendedorController.ultimoVendedorToString());
            }

        	

            if (e.getSource() == btnCancelar) {
                menu.setVisible(true);
                dispose();
    	}
    	
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na transferencia de dados!");

        }
    }





}
