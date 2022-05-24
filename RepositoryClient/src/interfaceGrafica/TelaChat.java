package interfaceGrafica;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

import main.Program;
import models.Cliente;
import models.Conexao;
import thread.Runnable1;
import thread.Runnable2;

public class TelaChat extends JFrame implements ActionListener, KeyListener{
	static Componentes puxadorComponentes = new Componentes();
	Conexao puxador = new Conexao();
	
	public static JTextField txtFEnvio = new JTextField();
	
	JButton btnEnviar = puxadorComponentes.criarButton(new JButton("Enviar"), 750, 509, 100, 50 );
	JButton btnVoltar = puxadorComponentes.criarButton(new JButton("Voltar"), 750, 255, 100, 200 );
	
	String [] cores = {"Azul Claro", "Azul Escuro", "Amarelo", "Vermelho", "Verde", "Branco"};
	
	JList lista = new JList(cores);
	
	static JPanel panelMensagem = new JPanel();
	
	public static Boolean conectado = false;
	
	public static Socket socket;
	String mensagem;
	
	int y2 = -40;
	final int x2 = 65;
	final int largura2 = 1000;
	final int altura2 = 100;
	
	public TelaChat(Socket socket) throws IOException {
		editarFrame();
		editarComponentes();
		listaDeCores();
		this.addKeyListener(this);
		
		TelaChat.socket = socket;
		Thread worker = new Thread(new Runnable1());
		worker.start();
	}
	
	public TelaChat(String iniciar) {

	}
	
	public void editarFrame() {
		this.setVisible(true);
		this.setLocation(270, 100);
		this.setSize(900, 600);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Chat Cliente"); 
		this.setResizable(false);
		this.setLayout(null);
	}
	
	public void editarComponentes() {
		panelMensagem.setBounds(10, 10, 700, 500);
		panelMensagem.setVisible(true);
		panelMensagem.setLayout(null);
		this.add(panelMensagem);
		panelMensagem.setBackground(Color.CYAN);
				
		txtFEnvio.setBounds(10, 510, 701, 50);
		txtFEnvio.setVisible(true);
		txtFEnvio.setBackground(Color.white);
		this.add(txtFEnvio);
		
		btnEnviar.addActionListener(this);
		this.add(btnEnviar);
		
		lista.setBounds(755, 10, 90, 200);
		this.add(lista);
		lista.setVisible(true);
		
		this.add(btnVoltar);
		btnVoltar.addActionListener(this);
	}
	
	public void listaDeCores() {
		lista.addListSelectionListener(e -> {
			int index = lista.getSelectedIndex();
						
			switch (index) {
				case 0:
					panelMensagem.setBackground(Color.CYAN);
					break;
				case 1:
					panelMensagem.setBackground(Color.BLUE);
					break;
				case 2:
					panelMensagem.setBackground(Color.YELLOW);
					break;
				case 3:
					panelMensagem.setBackground(Color.RED);
					break;
				case 4:
					panelMensagem.setBackground(Color.GREEN);
					break;
				case 5:
					panelMensagem.setBackground(Color.WHITE);
					break;
			}
			
		});
	}
	
	public void aparecerMensagemRecebida(String mensagem) {	
		puxadorComponentes.listaLabelsRecebidas.add(Componentes.criarLabel(new JLabel(mensagem), x2, y2, largura2, altura2));
		puxadorComponentes.nomeOutroUsuario.add(Componentes.criarLabel(new JLabel("Colega: "), 15, y2, 200, 100));
		
		int lastIndex = puxadorComponentes.listaLabelsRecebidas.size() - 1;
		int lastIndex2 = puxadorComponentes.listaLabelsEnviadas.size() - 1;
		
		if(puxadorComponentes.listaLabelsRecebidas.get(0).getY() == y2) {
			for(int i =0; i < puxadorComponentes.listaLabelsRecebidas.size(); i++) {
				if (puxadorComponentes.listaLabelsRecebidas.get(lastIndex).getY() == puxadorComponentes.listaLabelsRecebidas.get(i).getY() &&
					puxadorComponentes.listaLabelsRecebidas.get(lastIndex) != puxadorComponentes.listaLabelsRecebidas.get(i)) {
					y2+=20;
					puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
				} else {
					puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setVisible(true);
					panelMensagem.add(puxadorComponentes.listaLabelsRecebidas.get(lastIndex));
				}
				
				for (int j = 0; j < puxadorComponentes.listaLabelsEnviadas.size(); j++) {
					if (puxadorComponentes.listaLabelsRecebidas.get(lastIndex).getY() == puxadorComponentes.listaLabelsEnviadas.get(j).getY()) {
						y2+=20;
						puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
					} else {
						puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setVisible(true);
						panelMensagem.add(puxadorComponentes.listaLabelsRecebidas.get(lastIndex));
					}
				}
				
				puxadorComponentes.nomeOutroUsuario.get(i).setBounds(15, puxadorComponentes.listaLabelsRecebidas.get(i).getY(), 200, 100);
				puxadorComponentes.nomeOutroUsuario.get(i).setVisible(true);
				panelMensagem.add(puxadorComponentes.nomeOutroUsuario.get(i));
			}
		} else {
			for(int i =0; i < puxadorComponentes.listaLabelsRecebidas.size(); i++) {
				for (int j = 0; j < puxadorComponentes.listaLabelsEnviadas.size(); j++) {
					if (puxadorComponentes.listaLabelsRecebidas.get(lastIndex).getY() == puxadorComponentes.listaLabelsEnviadas.get(j).getY()) {
						y2+=20;
						puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
					} else {
						puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setVisible(true);
						panelMensagem.add(puxadorComponentes.listaLabelsRecebidas.get(lastIndex));
					}
				}
				
				if (puxadorComponentes.listaLabelsRecebidas.get(lastIndex).getY() == puxadorComponentes.listaLabelsRecebidas.get(i).getY() &&
					puxadorComponentes.listaLabelsRecebidas.get(lastIndex) != puxadorComponentes.listaLabelsRecebidas.get(i)) {
					y2+=20;
					puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
				} else {
					puxadorComponentes.listaLabelsRecebidas.get(lastIndex).setVisible(true);
					panelMensagem.add(puxadorComponentes.listaLabelsRecebidas.get(lastIndex));
				}
				
				puxadorComponentes.nomeOutroUsuario.get(i).setBounds(15, puxadorComponentes.listaLabelsRecebidas.get(i).getY(), 200, 100);
				puxadorComponentes.nomeOutroUsuario.get(i).setVisible(true);
				panelMensagem.add(puxadorComponentes.nomeOutroUsuario.get(i));
			}
		}
		
		panelMensagem.repaint();
	}
	
	public void aparecerMensagemEnviada(String mensagem) {
		puxadorComponentes.listaLabelsEnviadas.add(Componentes.criarLabel(new JLabel(mensagem), x2, y2, largura2, altura2));
		puxadorComponentes.nomeUsuarioLocal.add(Componentes.criarLabel(new JLabel("Eu: "), 15, y2, 200, 100));
		
		int lastIndex = puxadorComponentes.listaLabelsEnviadas.size() - 1;
		int lastIndex2 = puxadorComponentes.listaLabelsRecebidas.size() - 1;
		
		if(puxadorComponentes.listaLabelsEnviadas.get(0).getY() == y2) {
			for(int i =0; i < puxadorComponentes.listaLabelsEnviadas.size(); i++) {
				if (puxadorComponentes.listaLabelsEnviadas.get(lastIndex).getY() == puxadorComponentes.listaLabelsEnviadas.get(i).getY() &&
					puxadorComponentes.listaLabelsEnviadas.get(lastIndex) != puxadorComponentes.listaLabelsEnviadas.get(i)) {
					y2+=20;
					puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
				} else {
					puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setVisible(true);
					panelMensagem.add(puxadorComponentes.listaLabelsEnviadas.get(lastIndex));
				}
				
				for (int j = 0; j < puxadorComponentes.listaLabelsRecebidas.size(); j++) {
					if (puxadorComponentes.listaLabelsEnviadas.get(lastIndex).getY() == puxadorComponentes.listaLabelsRecebidas.get(j).getY()) {
						y2+=20;
						puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
					} else {
						puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setVisible(true);
						panelMensagem.add(puxadorComponentes.listaLabelsEnviadas.get(lastIndex));
					}
				}
				
				puxadorComponentes.nomeUsuarioLocal.get(i).setBounds(15, puxadorComponentes.listaLabelsEnviadas.get(i).getY(), 200, 100);
				puxadorComponentes.nomeUsuarioLocal.get(i).setVisible(true);
				panelMensagem.add(puxadorComponentes.nomeUsuarioLocal.get(i));
			}
		} else {
			for(int i =0; i < puxadorComponentes.listaLabelsEnviadas.size(); i++) {
				for (int j = 0; j < puxadorComponentes.listaLabelsRecebidas.size(); j++) {
					if (puxadorComponentes.listaLabelsEnviadas.get(lastIndex).getY() == puxadorComponentes.listaLabelsRecebidas.get(j).getY()) {
						y2+=20;
						puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
					} else {
						puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setVisible(true);
						panelMensagem.add(puxadorComponentes.listaLabelsEnviadas.get(lastIndex));
					}
				}
				
				if (puxadorComponentes.listaLabelsEnviadas.get(lastIndex).getY() == puxadorComponentes.listaLabelsEnviadas.get(i).getY() &&
					puxadorComponentes.listaLabelsEnviadas.get(lastIndex) != puxadorComponentes.listaLabelsEnviadas.get(i)) {
					y2+=20;
					puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setBounds(x2, y2, largura2, altura2);
				} else {
					puxadorComponentes.listaLabelsEnviadas.get(lastIndex).setVisible(true);
					panelMensagem.add(puxadorComponentes.listaLabelsEnviadas.get(lastIndex));
				}
				
				puxadorComponentes.nomeUsuarioLocal.get(i).setBounds(15, puxadorComponentes.listaLabelsEnviadas.get(i).getY(), 200, 100);
				puxadorComponentes.nomeUsuarioLocal.get(i).setVisible(true);
				panelMensagem.add(puxadorComponentes.nomeUsuarioLocal.get(i));
			}
		}
		
		panelMensagem.repaint();
	}
	
	public void apagarCampoEnvio() {
		txtFEnvio.setText("");
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnEnviar) {
			
			Thread worker2 = new Thread(new Runnable2());
			worker2.start();
		}
		
		if(e.getSource() == btnVoltar) {
			conectado = false;
			this.dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
