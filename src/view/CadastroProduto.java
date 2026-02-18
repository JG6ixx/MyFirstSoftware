package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.ProdutoController;

public class CadastroProduto extends JFrame implements ActionListener {

	private ProdutoController produtoController =  new ProdutoController();
	private MenuCadastro menuCadastro;
	private JTextField nomeP;
    private JTextArea descricao;
    private JTextField valor;
    private JTextArea mensagem;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CadastroProduto(MenuCadastro menuCadastro) {
    	this.menuCadastro = menuCadastro;
        super("Cadastro de Produto");

        // Inicializando componentes
        nomeP = new JTextField(2);
        descricao = new JTextArea(2, 20);
        valor = new JTextField(2);
        mensagem = new JTextArea(5, 20);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Painel principal (vertical)
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));

        panelForm.add(new JLabel("Produto:"));
        panelForm.add(new JScrollPane(nomeP));

        panelForm.add(new JLabel("Descrição:"));
        panelForm.add(new JScrollPane(descricao));

        panelForm.add(new JLabel("Valor:"));
        panelForm.add(new JScrollPane(valor));

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
        setBounds(300, 30, 400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == btnSalvar) {
				produtoController.cadastrarProduto(nomeP.getText(), descricao.getText(), Double.parseDouble(valor.getText()));
				JOptionPane.showMessageDialog(this, "Produto cadastrado!!");

			    mensagem.setText(""); // limpa antes
			    mensagem.append("Produto cadastrado:\n");
			    mensagem.append(produtoController.ultimoProdutoToString());
			    
				
			}
			
			if(e.getSource() == btnCancelar) {
				menuCadastro.setVisible(true); // ✅ menu existente
	            dispose();  
				
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro na view!");
			
		}
		
		
		
		
	}
}

