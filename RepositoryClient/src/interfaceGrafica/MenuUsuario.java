package interfaceGrafica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import interfaceGrafica.email.CaixaEntrada;
import main.Program;

public class MenuUsuario extends JFrame implements ActionListener{
	Componentes puxadorComponentes = new Componentes();
	TelaLogin puxadorLogin = new TelaLogin("iniciar");
	
	JLabel nomeUsuario = Componentes.criarLabel(new JLabel(puxadorLogin.txtUsuario.getText()), 205, -5, 300, 100);
	JLabel texto = Componentes.criarLabel(new JLabel("Bem-Vindo "), 25, -5, 300, 100);
	Font titulo = new Font("Arial", Font.BOLD,24);
	
	JButton btnChat = puxadorComponentes.criarButton(new JButton("Chat"), 150, 90, 100, 50);
	JButton btnemail = puxadorComponentes.criarButton(new JButton("E-mail"), 150, 180, 100, 50);
	
	public MenuUsuario() {
		editarFrame();
		editarComponentes();
	}
	
	public void editarComponentes() {
		add(nomeUsuario);
		add(texto);
		
		add(btnChat);
		btnChat.addActionListener(this);
		btnemail.addActionListener(this);
		
		add(btnemail);
		
		nomeUsuario.setFont(titulo);
		texto.setFont(titulo);
	}
	
	public void editarFrame(){
		this.setVisible(true);
		this.setTitle("Menu");
		this.setSize(400,300);
		this.setLocation(470, 180);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChat) {
			try {
				Program frame = new Program();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		if (e.getSource() == btnemail) {
			try {
				Program frame = new Program("E-mail");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
}
