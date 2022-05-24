package main;

import java.awt.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import model.ConexaoServidor;

public class Program extends Thread{
	public static Vector CLIENTES;
	private Socket socket;
	
	public Program(Socket socket) {
		this.socket = socket;
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		CLIENTES = new Vector();
		
		ServerSocket server = new ServerSocket(55555);
		
		while(true) {
			Socket conexao = server.accept();
			
			Thread t = new Program(conexao);
			t.start();
		}
}
	
	public void run(){
		
			ConexaoServidor puxador = new ConexaoServidor();
			System.out.println("Nova Conexão: " + socket.getInetAddress().getHostAddress());
			
			try {
				puxador.recebimentoMensagens(this.socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	
	}
