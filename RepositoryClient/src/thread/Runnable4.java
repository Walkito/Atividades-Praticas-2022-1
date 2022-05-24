package thread;

import java.io.IOException;
import java.net.Socket;

import interfaceGrafica.email.NovaMensagem;
import models.Conexao;

public class Runnable4 implements Runnable {
	Conexao puxador = new Conexao();
	Socket socket = NovaMensagem.socket;
	
	public void run() {
		try {
			puxador.enviandoMensagens(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
