package carros;

import java.util.ArrayList;

import metodos_utilizados.Metodos;
import video_Game.Jogo;

public class Carros {

	public static void main(String[] args) {
		ArrayList<Carro> jogos = new ArrayList<Carro>();

		int op = 0;
				
		do {
			op = menu();
			switch (op) {
			case 1: {
				
				break;
			}
			case 2: {
				
				break;
			}
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				Metodos.msg("Programa finalizado !");
				break;
			default:
				Metodos.msg("Valor invalido!");
			}
			
		}while(op != 6);

	}
	
	private static int menu() {
		int op = Metodos.lerInt("1 - Cadastrar Carro\n"
				+ "2 - Listar carro pela placa\n"
				+ "3 - Verificar qual carro o usuario pode dirigir\n"
				+ "4 - Todos os carros fabricados em 2024\n"
				+ "5 - Todos os carros de uma cor\n"
				+ "6 - Sair");
		
		return op;
	}
	
	private static void cadastrar (ArrayList<Carro> carros) {
		Carro c = new Carro();
		c.marca = Metodos.lerNome("Marca");
		c.modelo = Metodos.lerNome("Modelo");
		c.ano = Metodos.lerInt("Ano");
		c.cor = Metodos.lerNome("Cor");
		c.placa = lerPlaca("Placa");
	}
	
	private static String lerPlaca (String txt) {
		String s = Metodos.lerString(txt);
		
		return s;
		
	}
}
