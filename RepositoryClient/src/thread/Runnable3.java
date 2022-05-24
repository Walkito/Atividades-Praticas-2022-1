package thread;

import java.io.IOException;
import java.net.Socket;

import interfaceGrafica.TelaChat;
import main.Program;
import models.Conexao;

public class Runnable3 implements Runnable{
	Socket socket = Program.socket;
	
	
	public void run() {
		
		try {
			new Conexao().consultarConexao(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
