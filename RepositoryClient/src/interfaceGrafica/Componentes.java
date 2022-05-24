package interfaceGrafica;

import java.util.ArrayList;

import javax.swing.*;

public class Componentes {
	ArrayList<JLabel> listaLabelsRecebidas =  new ArrayList<JLabel>();
	ArrayList<JLabel> listaLabelsEnviadas =  new ArrayList<JLabel>();	
	ArrayList<JLabel> nomeOutroUsuario = new ArrayList<JLabel>();
	ArrayList<JLabel> nomeUsuarioLocal = new ArrayList<JLabel>();
	
	public static JLabel criarLabel(JLabel label, int x, int y, int largura, int altura) {
		label.setBounds(x, y, largura, altura);
		label.setVisible(true);
		
	return label;
	}
	
	
	public JButton criarButton(JButton button, int x, int y, int largura, int altura) {
		button.setBounds(x, y, largura, altura);
		button.setVisible(true);
		
	return button;
	
	}
	
	public static JTextField criarTextField(JTextField txt, int x, int y, int largura, int altura) {
			txt.setBounds(x, y, largura, altura);
			txt.setVisible(true);
			
		return txt;
	}
	
	public JPasswordField criarPasswordField(JPasswordField txt, int x, int y, int largura, int altura) {
		txt.setBounds(x, y, largura, altura);
		txt.setVisible(true);
		
	return txt;
	}
	
	public JPanel criarJPanel(JPanel panel, int x, int y, int largura, int altura) {
		panel.setBounds(x, y, largura, altura);
		panel.setVisible(true);
		
		return panel;
	}
	
	public JList criarLista(JList lista, int x, int y, int largura, int altura) {
		lista.setBounds(x, y, largura, altura);
		lista.setVisible(true);
		
		return lista;
	}

}