package interfaceGrafica.email;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;

import interfaceGrafica.Componentes;
import thread.Runnable2;
import thread.Runnable4;

public class LerMensagem extends JFrame implements ActionListener{
	static Componentes conexaoComponentes = new Componentes();
	CaixaEntrada puxador1 = new CaixaEntrada("Iniciar");
	
	public static Socket socket = CaixaEntrada.socket;
	
	public static JTextArea caixaTxt = new JTextArea();
	
	public static JLabel titulo = conexaoComponentes.criarLabel(new JLabel("Título"), 260, -20, 400, 100);
	
	JButton btnVoltar = conexaoComponentes.criarButton(new JButton("Voltar"), 190, 405, 200, 50);
	
	public LerMensagem() {
		editarFrame();
		editarComponentes();
	}
	
	public void editarComponentes() {
		this.add(caixaTxt);
		caixaTxt.setBounds(10, 50, 565, 350);
		caixaTxt.setVisible(true);
		caixaTxt.setEditable(false);
		caixaTxt.setLineWrap(true);
		
		this.add(titulo);
		this.add(btnVoltar);
		
		btnVoltar.addActionListener(this);
	}
	
	public void editarFrame(){
		this.setVisible(true);
		this.setTitle("Ler E-mail");
		this.setSize(600,500);
		this.setLocation(460, 90);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVoltar) {
			this.dispose();
		}
		
	}

}
