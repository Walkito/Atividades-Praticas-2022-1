package models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import interfaceGrafica.TelaChat;
import interfaceGrafica.email.CaixaEntrada;
import interfaceGrafica.email.NovaMensagem;

public class Conexao {
	public void consultarConexao(Socket socket) throws IOException {
		System.out.println("Comunicação feita!");
	}
	
	public void recebimentoMensagens(Socket socket) throws IOException {
		while(true) {			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("Entrei aki");
			receberMensagemServidor(entrada);
		}
	}
	
	public void enviandoMensagens(Socket socket) throws IOException {
			PrintStream saida = new PrintStream(socket.getOutputStream());
			
			enviarMensagemServidor(saida);
			
	}
	
	private void enviarMensagemServidor(PrintStream saida) throws IOException {
		TelaChat puxador = new TelaChat("Iniciar");
		CaixaEntrada puxador2 = new CaixaEntrada("Iniciar");
		NovaMensagem puxador3 = new NovaMensagem("Iniciar");
		
		if (puxador.conectado == true) {
			saida.println(TelaChat.txtFEnvio.getText());	
			puxador.aparecerMensagemEnviada(TelaChat.txtFEnvio.getText());
			puxador.apagarCampoEnvio();
			saida.flush();
		} 
		
		if (puxador2.conectado == true) {
			saida.println(NovaMensagem.txtTitulo.getText() + "~" + NovaMensagem.caixaTxt.getText());
			puxador3.apagarEnvio();
			saida.flush();			
		} 
	}

	private void receberMensagemServidor(BufferedReader entrada) throws IOException {
		TelaChat puxador = new TelaChat("Iniciar");
		CaixaEntrada puxador2 = new CaixaEntrada("Iniciar");
		
		String msg = entrada.readLine();
		
				
		if(puxador.conectado == true) {
			puxador.aparecerMensagemRecebida(msg);
		}
		
		if(puxador2.conectado == true) {
			String[] mensagemDividida = msg.split("~");
			
			puxador2.aparecerTitulo(mensagemDividida[0]);
			puxador2.aparecerEmail(mensagemDividida[1]);
		}
	}
}
