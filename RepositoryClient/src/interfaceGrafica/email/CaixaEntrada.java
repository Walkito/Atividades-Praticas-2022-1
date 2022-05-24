package interfaceGrafica.email;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;

import interfaceGrafica.Componentes;
import thread.Runnable1;
import thread.Runnable5;

public class CaixaEntrada extends JFrame implements ActionListener{
	static Componentes conexaoComponentes = new Componentes();
	
	public static Socket socket;
	public static Boolean conectado = false;
	
	public static DefaultListModel listaT = new DefaultListModel();
	public static DefaultListModel listaM = new DefaultListModel();
	public static DefaultListModel listaMensagems = new DefaultListModel();
	public static DefaultListModel listaTitulos = new DefaultListModel();
	
	JList caixaT = conexaoComponentes.criarLista(new JList(listaT), 40, 120, 400, 430);
	JList caixaM = conexaoComponentes.criarLista(new JList(listaM), 440, 120, 400, 430);
	JPanel topo = conexaoComponentes.criarJPanel(new JPanel(), 40, 80, 800, 40);
	
	JButton btnNewMensagem = conexaoComponentes.criarButton(new JButton("Nova Mensagem"), 40, 20, 150,50);
	JButton btnExcluirMensagem = conexaoComponentes.criarButton(new JButton("Excluir"), 230, 20, 150,50);
	JButton btnVoltar = conexaoComponentes.criarButton(new JButton("Voltar"), 630, 20, 150,50);
	JButton btnLer = conexaoComponentes.criarButton(new JButton("Ler e-Mail"), 430, 20, 150,50);
	
	JLabel lblTitulo = conexaoComponentes.criarLabel(new JLabel("Assunto"), 165, -5, 200, 50);
	JLabel lblMensagem= conexaoComponentes.criarLabel(new JLabel("Mensagem"), 495, -5, 200, 50);
	
	JLabel testeTitulo = conexaoComponentes.criarLabel(new JLabel("Uma Mensagem para Você"), 30, -50, 200, 200);
	JLabel testeMensagem = conexaoComponentes.criarLabel(new JLabel("Olá, meu nome é Walker tenho 22 anos e eu quero te contar um segredo. Sabe, já faz um tempo desde que eu morri"), 190, -50, 800, 200);
	
	public CaixaEntrada(Socket socket) {
		this.socket = socket;
		
		editarFrame();
		editarComponentes();
		
		Thread worker7 = new Thread(new Runnable5());
		worker7.start();
		
		
	}
		
	public CaixaEntrada(String string) {
		// TODO Auto-generated constructor stub
	}

	public void editarComponentes() {
		this.add(caixaT);
		caixaT.setLayout(null);
		caixaT.setBackground(Color.gray);
		
		this.add(caixaM);
		caixaM.setLayout(null);
		caixaM.setBackground(Color.gray);
		
		this.add(topo);
		topo.setLayout(null);
		topo.setBackground(Color.DARK_GRAY);
		
		this.add(btnNewMensagem);
		btnNewMensagem.addActionListener(this);
		this.add(btnExcluirMensagem);
		btnExcluirMensagem.addActionListener(this);
		this.add(btnVoltar);
		btnVoltar.addActionListener(this);
		this.add(btnLer);
		btnLer.addActionListener(this);
		
		topo.add(lblTitulo);
		topo.add(lblMensagem);
		
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setForeground(Color.WHITE);
		
		lblMensagem.setFont(new Font("Arial", Font.BOLD, 18));
		lblMensagem.setForeground(Color.WHITE);
	}
	
	public void editarFrame(){
		this.setVisible(true);
		this.setTitle("E-mail");
		this.setSize(900,600);
		this.setLocation(260, 70);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		
		
	}
	
	public void aparecerEmail(String mensagem) {
		listaMensagems.addElement(mensagem);
		
		int indexFinal = listaTitulos.size() - 1;
		
		listaT.addElement("         " + listaTitulos.getElementAt(indexFinal).toString());
		listaM.addElement("         " + listaMensagems.getElementAt(indexFinal).toString());
		caixaT.repaint();
		
	}
	
	public void aparecerTitulo(String titulo) {
		listaTitulos.addElement(titulo);		
	}
		
	public void verificarIndex() {
		caixaT.addListSelectionListener(e -> {
			int index = caixaT.getSelectedIndex();
			int index2 = caixaM.getSelectedIndex();
			
			if(index != index2) {
				caixaM.setSelectedIndex(index);
			}
		
		});
		
		caixaM.addListSelectionListener(e -> {
			int index = caixaT.getSelectedIndex();
			int index2 = caixaM.getSelectedIndex();
			
			if(index != index2) {
				caixaT.setSelectedIndex(index2);
			}
		
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewMensagem) {
			NovaMensagem f = new NovaMensagem();
			f.setVisible(true);
			verificarIndex();
		}
		if (e.getSource() == btnLer) {
			LerMensagem frame = new LerMensagem();
			
			int index = caixaT.getSelectedIndex();
			
			LerMensagem.caixaTxt.setText(listaMensagems.getElementAt(index).toString());
			LerMensagem.titulo.setText(listaTitulos.getElementAt(index).toString());
		}
		
		if(e.getSource() == btnExcluirMensagem) {
			int indexT = caixaT.getSelectedIndex();
			int indexM = caixaM.getSelectedIndex();
			
			listaT.removeElementAt(indexT);
			listaM.removeElementAt(indexM);
			listaTitulos.removeElementAt(indexT);
			listaMensagems.removeElementAt(indexM);
		
		}
		
		if(e.getSource() == btnVoltar) {
			conectado = false;
			this.dispose();
		}
		
	}

}
