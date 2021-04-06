package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import pojo.Person;

public class Exportacao {
	public static void main(String[] args) throws IOException {
		// desserializa��o: recuperando os objetos gravados no arquivo bin�rio "dados.dat"
		ArrayList<Object> pessoa = Desempacotamento.lerArquivoBinario("dado.dat");

		FileWriter arq = new FileWriter("export.txt");
		PrintWriter gravarArq = new PrintWriter(arq);

		int i = 1;
		int n = pessoa.size();

		for (Object item : pessoa) {
			System.out.printf("Exportando %do. registro de %d: %s\n", i++, n, ((Person) item).getName());
			gravarArq.printf("Nome|%s; Idade",
					((Person) item).getName(), ((Person) item).getAge());
		}

		gravarArq.close();

		System.out.printf("\nExportacao realizada com sucesso.\n");
	}
}
