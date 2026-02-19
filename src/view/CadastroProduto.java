package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.ProdutoController;

public class CadastroProduto extends JFrame implements ActionListener {

	private ProdutoController produtoController =  new ProdutoController();
	private Menu menu;
	private JTextField nomeP;
    private JTextField descricao;
    private JTextField valor;
    private JTextArea mensagem;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public CadastroProduto(Menu menu) {
    	
        super("Cadastro de Produto");
        this.menu = menu;
        // Inicializando componentes
        nomeP = new JTextField(30);
        descricao = new JTextField(30);
        valor = new JTextField(30);
        mensagem = new JTextArea(8, 20);

        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);

        // Painel das JTextFiel e JTextArea - componentes de entrada(nomep, descricao, valor, mensagem)
        JPanel painel = new JPanel(new FlowLayout());
        painel.setLayout(new FlowLayout());

        painel.add(new JLabel("Produto:"));
        painel.add(nomeP);

        painel.add(new JLabel("Descrição:"));
        painel.add(descricao);

        painel.add(new JLabel("Valor:"));
        painel.add(valor);

        painel.add(new JLabel("Mensagem:"));
        painel.add(mensagem);

        // Painel dos botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        //Painel que une os outros paineis 
        setLayout(new BorderLayout());
        add(painel, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

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

			    mensagem.setText(""); 
			    mensagem.append("Produto cadastrado:\n");
			    mensagem.append(produtoController.ultimoProdutoToString());
			    
				
			}
			
			if(e.getSource() == btnCancelar) {
				menu.setVisible(true); // Volta Para o menu de cadastro
	            dispose();  
				
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro na view!");
			
		}
		
		
		
		
	}
}

