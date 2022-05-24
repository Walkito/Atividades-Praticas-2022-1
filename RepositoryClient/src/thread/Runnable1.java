package thread;

import java.io.IOException;
import java.net.Socket;

import interfaceGrafica.TelaChat;
import models.Conexao;

public class Runnable1 implements Runnable{
	Conexao puxador = new Conexao();
	TelaChat puxador2 = new TelaChat("Iniciar");
	
	Socket socket = TelaChat.socket;
	
	public void run() {
		try {
			puxador.recebimentoMensagens(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
