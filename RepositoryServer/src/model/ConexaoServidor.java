package model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Scanner;
import main.Program;

public class ConexaoServidor {
	
	public Socket aguardarConexao(ServerSocket server) throws IOException {
		System.out.println("Aguardando conexão...");
		return server.accept();
	}

	public void recebimentoMensagens(Socket socket) throws IOException {
			while(true) {
				BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintStream saida = new PrintStream(socket.getOutputStream());
				
				Program.CLIENTES.add(saida);
				
				while(true) {
				String msg = entrada.readLine();
				
				enviarMensagemCliente(saida, msg);		
				
				}
		}
	}
	
	private void enviarMensagemCliente(PrintStream saida, String msg) throws IOException {
		Enumeration e = Program.CLIENTES.elements();
		while (e.hasMoreElements()) {
			
			PrintStream chat = (PrintStream) e.nextElement();
			
			if (chat != saida) {
				chat.println(msg);
			}
		}
	}
	
}
