package serialization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import pojo.*;
import util.Desempacotamento;
import util.Empacotamento;

public class PessoaInputStream extends InputStream{
	private static InputStream in;
	
	public PessoaInputStream(InputStream in) {
		super();
		this.in = in;
	}
	
	
	public static void main(String args[]) throws Exception{
		
	ArrayList<Object> pessoas = new ArrayList<Object>();
	
	
	String nome;
	int idade;
	Scanner ler = new Scanner(System.in);
	//metodo que realizar os metodos Fileinputstream para ler e atualizar, 
	pessoas = Desempacotamento.lerArquivoBinario("dado.dat");

	// 2) entrada de dados
	System.out.printf("Pessoas cadastradas: %d.\n", (pessoas.size()));

	while (true) {
		System.out.printf("Informe o nome da pessoa, FIM para encerrar:\n");
		nome = ler.nextLine();
		if (nome.equalsIgnoreCase("FIM"))
			break;

		System.out.printf("Informe sua idade: ");
		idade = ler.nextInt();
		Person p = new Person(nome, idade);
		pessoas.add(p); // adiciona um novo objeto a lista

		ler.nextLine(); // esvazia o buffer do teclado
		System.out.printf("\n");
	}
	ler.close();

	//metodo que realizar os metodos fileoutputstream
	Empacotamento.gravarArquivoBinario(pessoas, "dado.dat");

	int n = pessoas.size(); //numero de pessoas
	
	
	//Enviando via SocketUDP
	DatagramSocket socketCliente = new DatagramSocket();

	InetAddress IPAddress = InetAddress.getByName("localhost");
	
	for (Object a : pessoas) {
		byte[] nBytes = new byte[1024];
		byte[] iBytes = new byte[1024];
		
		String nomeudp = ((Person) a).getName();
		int idadeudp = ((Person) a).getAge();
		nBytes = nomeudp.getBytes();
		iBytes = Integer.toString(idadeudp).getBytes();
		
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
			String nometcp = ((Person)a).getName();
			int idadetcp = ((Person)a).getAge();
			out.writeUTF(nometcp);
			out.writeInt(idadetcp);
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

		//recebendo via fileinputstream
		System.out.println("Recuperando valores: ");
		
		int i = 1;
		for (Object item : pessoas) {
			System.out.printf("Pessoa nro....: %d.\n", i++);
			// ((Pessoa)item) - implementa o mecanismo de downcast, ou seja,
			// o objeto "item" declarado a partir da classe
			// base "Object" � referenciado como um objeto "Pessoa"
			System.out.println("Nome: "+ ((Person) item).getName());
			System.out.println("Idade: "+ ((Person) item).getAge());
		}
		
				
		
	}
	
	
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}



	
