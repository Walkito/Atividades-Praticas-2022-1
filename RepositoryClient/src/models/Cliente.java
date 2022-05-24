package models;
import java.io.IOException;
import java.net.Socket;

public class Cliente {

	private String nome;
	private int porta;

	public Cliente(String nome, int porta) {
		this.nome = nome;
		this.porta = porta;
	}

	public Socket criarSocket() throws IOException {
		return new Socket(nome, porta);
	}

	public String getNome() {
		return nome;
	}

	public int getPorta() {
		return porta;
	}
}
