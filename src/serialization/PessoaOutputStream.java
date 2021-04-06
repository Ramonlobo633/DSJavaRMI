package serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import pojo.*;
import util.Desempacotamento;


public class PessoaOutputStream extends OutputStream{
	private static ArrayList<Object> persons;
	private OutputStream out;
	
	public PessoaOutputStream(ArrayList<Object> pessoas, OutputStream out) {
		super();
		this.persons = persons;
		this.out = out;
	}

	public static void main(String[] args) throws Exception {
		ArrayList<Object> pessoas = new ArrayList();
		//metodo que realizar os metodos fileinputstream
		pessoas = Desempacotamento.lerArquivoBinario("dado.dat");

		int i = 1;
		for (Object item : pessoas) {
			System.out.printf("Pessoa nro....: %d.\n", i++);
			// ((Pessoa)item) - implementa o mecanismo de downcast, ou seja,
			// o objeto "item" declarado a partir da classe
			// base "Object" � referenciado como um objeto "Pessoa"
			System.out.println("Nome: "+ ((Person) item).getName());
			System.out.println("Idade: \n"+ ((Person) item).getAge());
		}

		int n = pessoas.size(); //numero de pessoas
		
		
		//Enviando via SocketUDP
		DatagramSocket socketCliente = new DatagramSocket();

		InetAddress IPAddress = InetAddress.getByName("localhost");
		
		for (Object a : pessoas) {
			byte[] nBytes = new byte[1024];
			byte[] iBytes = new byte[1024];
			
			String nome = ((Person) a).getName();
			int idade = ((Person) a).getAge();
			nBytes = nome.getBytes();
			iBytes = Integer.toString(idade).getBytes();
			
			DatagramPacket pacoteByte = new DatagramPacket(nBytes,
					nBytes.length, IPAddress, 6789);
			socketCliente.send(pacoteByte);
			
			DatagramPacket pacoteIdade = new DatagramPacket(iBytes,
					iBytes.length, IPAddress, 6789);
			socketCliente.send(pacoteIdade);
			
			

		}
		socketCliente.close();
		
		//Enviando via SocketTCP		
		Socket s = null;
		try {
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			//numero de pessoas
			out.writeInt(n);
			
			for (Object a : pessoas) {
				String nome = ((Person)a).getName();
				int idade = ((Person)a).getAge();
				out.writeUTF(nome);
				out.writeInt(idade);
			}
		} catch (UnknownHostException e) {
			System.out.println("TCP Socket:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("TCP EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("TCP readline:" + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
		
	}

	@Override
	public void write(int arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
