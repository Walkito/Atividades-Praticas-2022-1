package interfaceGrafica.email;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;

import interfaceGrafica.Componentes;
import thread.Runnable2;
import thread.Runnable4;

public class NovaMensagem extends JFrame implements ActionListener{
	static Componentes conexaoComponentes = new Componentes();
	CaixaEntrada puxador1 = new CaixaEntrada("Iniciar");
	
	public static Socket socket = CaixaEntrada.socket;
	
	public static JTextArea caixaTxt = new JTextArea();
	
	public static JTextField txtDestinaratio = conexaoComponentes.criarTextField(new JTextField(), 85, 18, 200, 25);
	public static JTextField txtTitulo = conexaoComponentes.criarTextField(new JTextField(), 350, 18, 200, 25);
	
	JLabel destinatario = conexaoComponentes.criarLabel(new JLabel("Destinatário"), 10, -20, 200, 100);
	JLabel titulo = conexaoComponentes.criarLabel(new JLabel("Título"), 300, -20, 200, 100);
	
	JButton btnEnviar = conexaoComponentes.criarButton(new JButton("Enviar"), 190, 405, 200, 50);
	
	public NovaMensagem() {
		editarFrame();
		editarComponentes();
	}
	
	public NovaMensagem(String nome) {
		
	}
	
	public void editarComponentes() {
		this.add(caixaTxt);
		caixaTxt.setBounds(10, 50, 565, 350);
		caixaTxt.setVisible(true);
		caixaTxt.setLineWrap(true);
		
		this.add(txtDestinaratio);
		this.add(destinatario);
		this.add(titulo);
		this.add(txtTitulo);
		this.add(btnEnviar);
		
		btnEnviar.addActionListener(this);
	}
	
	public void apagarEnvio() {
		txtDestinaratio.setText("");
		txtTitulo.setText("");
		caixaTxt.setText("");
	}
	
	public void editarFrame(){
		this.setVisible(true);
		this.setTitle("Nova Mensagem");
		this.setSize(600,500);
		this.setLocation(460, 90);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEnviar) {
			Thread worker4 = new Thread(new Runnable4());
			worker4.start();
		}
		
	}

}
