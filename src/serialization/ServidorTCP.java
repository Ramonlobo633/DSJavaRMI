package serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorTCP {
	public static void main(String args[]) {
		try {
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen socket:" + e.getMessage());
		}
	}
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try { // an echo server
			int np = in.readInt();
			String nome = in.readUTF();
			int cpf = in.readInt();
			int idade = in.readInt();
			
			System.out.println("numero de cadastros: " + np);
			
			System.out.println("dados recebidos: nome " + nome+" idade "+idade+" cpf "+cpf);
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("readline:" + e.getMessage());
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				/* close failed */}
		}

	}
}