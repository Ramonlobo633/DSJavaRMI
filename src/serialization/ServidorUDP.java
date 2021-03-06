package serialization;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
	public static void main(String args[]) throws Exception {

		DatagramSocket socketServidor = new DatagramSocket(6789);

		byte[] dadosRecebidos = new byte[1024];
		byte[] dadosEnviados = new byte[1024];

		while (true) {
			System.out.println("Servidor em Execucao...");
			
			DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
			socketServidor.receive(pacoteRecebido);
			
			String frase = new String(pacoteRecebido.getData());
			InetAddress IPAddress = pacoteRecebido.getAddress();
			int porta = pacoteRecebido.getPort();
			
			System.out.println("Recebeu conexao do cliente com IP: "+ IPAddress.getHostAddress() +" e porta :"+ porta);
			System.out.println("Frase recebida: "+ frase);
			String fraseEmMaiusculas = frase.toUpperCase();
			
			dadosEnviados = fraseEmMaiusculas.getBytes();
			DatagramPacket pacoteEnviado = new DatagramPacket(dadosEnviados, dadosEnviados.length, IPAddress, porta);

			socketServidor.send(pacoteEnviado);
		}
}
}
