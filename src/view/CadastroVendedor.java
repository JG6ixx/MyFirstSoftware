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
	
	private MenuCadastro menuCadastro;
    private VendedorController vendedorController = new VendedorController();

    private JTextField tNome;
    private JTextField tTelefone;
    private JTextArea mensagem;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CadastroVendedor(MenuCadastro menuCadastro) {
        super("Cadastro de Vendedor");
        this.menuCadastro = menuCadastro;

        // Inicializando componentes
        tNome = new JTextField(2);
        tTelefone = new JTextField(2);
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

        panelForm.add(new JLabel("Mensagem:"));
        panelForm.add(new JScrollPane(mensagem));

        // Painel dos botões
        JPanel panelBotoes = new JPanel(new FlowLayout());
        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);

        // Layout principal
        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.BEFORE_FIRST_LINE);
        add(panelBotoes, BorderLayout.SOUTH);

        // Configurações da janela
        setBounds(300, 30, 350, 350);
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
                menuCadastro.setVisible(true);
                dispose();
    	}
    	
        }catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro na transferencia de dados!");

        }
    }





}
