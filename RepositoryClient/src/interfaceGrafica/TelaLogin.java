package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dao.ConnectionAPS;
import models.Usuario;

public class TelaLogin extends JFrame implements ActionListener{
	static Componentes puxadorComponentes = new Componentes();
	
	public static JTextField txtIP = puxadorComponentes.criarTextField(new JTextField(), 90, 20, 200, 20);
	static JTextField txtUsuario = puxadorComponentes.criarTextField(new JTextField(), 90, 60, 200, 20);
	JPasswordField txtSenha = puxadorComponentes.criarPasswordField(new JPasswordField(), 90, 100, 200, 20);
	JButton btnLogin = puxadorComponentes.criarButton(new JButton("Login"), 140, 150, 100, 50);
	JLabel ip = puxadorComponentes.criarLabel(new JLabel("IP"), 45, 18, 100, 20);
	JLabel usuario = puxadorComponentes.criarLabel(new JLabel("Usuario"), 30, 58, 100, 20);
	JLabel senha = puxadorComponentes.criarLabel(new JLabel("Senha"), 35, 98, 100, 20);
	
	public TelaLogin(){
		editarFrame();
		adicionarComponentes();
	}
	
	public TelaLogin(String inicio) {
		
	}
	
	public void editarFrame(){
		this.setVisible(true);
		this.setTitle("Login");
		this.setSize(400,250);
		this.setLocation(500, 270);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		
	}
	
	public void adicionarComponentes() {
		add(txtUsuario);
		add(txtSenha);
		add(txtIP);
		
		add(btnLogin);
		btnLogin.addActionListener(this);
		
		add(ip);
		add(usuario);
		add(senha);
	}
	
	public static void main(String[] args) {
		new TelaLogin();
		
		
	}
	
	public void iniciarDB() {
		String usuario2 = txtUsuario.getText();
		String senha = txtSenha.getText();
		
		try {
			ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
			ConnectionAPS conexaoDb = new ConnectionAPS();
			Connection conn = conexaoDb.getConnetion();
			String query = "SELECT * FROM TELA_LOGIN";
			String usuarioDigitado = usuario2;
			String senhaDigitada = senha;
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario user = new Usuario();
				user.setId(rs.getInt("IDUSUARIO"));
				user.setUsuario(rs.getString("LOGINUSUARIO"));
				user.setSenha(rs.getString("SENHAUSUARIO"));
				user.setAdmin(rs.getString("ADMINUSUARIO"));
				listaUsuarios.add(user);
			}

			for (Usuario usuario : listaUsuarios) {
				if (usuario.getUsuario().equalsIgnoreCase(usuarioDigitado) && usuario.getSenha().equals(senhaDigitada)) {
						MenuUsuario f = new MenuUsuario();
						f.setVisible(true);
						this.dispose();
						break;
					}
				}
				
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			iniciarDB();
		}
		
	}


}
