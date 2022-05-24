package thread;

import java.io.IOException;
import java.net.Socket;

import interfaceGrafica.TelaChat;
import interfaceGrafica.email.CaixaEntrada;
import models.Conexao;

public class Runnable5 implements Runnable{
	Conexao puxador = new Conexao();	
	Socket socket = CaixaEntrada.socket;
	
	public void run() {
		try {
			puxador.recebimentoMensagens(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
