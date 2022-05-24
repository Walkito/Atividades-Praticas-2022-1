package thread;

import java.io.IOException;
import java.net.Socket;

import interfaceGrafica.TelaChat;
import models.Conexao;

public class Runnable2 implements Runnable{
	Conexao puxador = new Conexao();
	
	Socket socket = TelaChat.socket;
	
	public void run() {
		try {
			puxador.enviandoMensagens(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
