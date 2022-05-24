package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import interfaceGrafica.TelaChat;
import interfaceGrafica.TelaLogin;
import interfaceGrafica.email.CaixaEntrada;
import models.Cliente;
import models.Conexao;
import thread.Runnable3;

public class Program {
	public static Socket socket;
	TelaLogin puxador = new TelaLogin("Iniciar");
	TelaChat puxador2 = new TelaChat("Iniciar");
	CaixaEntrada puxador3 = new CaixaEntrada("Iniciar");
	
	public Program(Socket socket) {
		this.socket = socket;
	}
	
	public Program() throws IOException {
		Socket socket = new Socket(puxador.txtIP.getText(), 55555);
				
		Thread worker3 = new Thread(new Runnable3());
		new TelaChat(socket);
		puxador2.conectado = true;
	}
	
	public Program(String email) throws IOException {
		Socket socket = new Socket(puxador.txtIP.getText(), 55555);
				
		Thread worker3 = new Thread(new Runnable3());
		new CaixaEntrada(socket);
		puxador3.conectado = true;
	}

}
