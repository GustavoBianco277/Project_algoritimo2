package carros;

import java.text.ParseException;
import java.util.ArrayList;

import clinica.Diagnostico;
import clinica.Paciente;
import metodos_utilizados.Metodos;

public class Carros {

	enum Modo_Busca{
		Placa,
		Nome,
		Ano,
		Cor
	}
	public static void main(String[] args) throws ParseException {
		ArrayList<Carro> carros = new ArrayList<Carro>();

		int op = 0;
				
		do {
			op = menu();
			switch (op) {
			case 1: {
				cadastrar(carros);
				break;
			}
			case 2: {
				localizarCarros(carros, Modo_Busca.Placa);
				break;
			}
			case 3:
				localizarCarros(carros, Modo_Busca.Nome);
				break;
			case 4:
				localizarCarros(carros, Modo_Busca.Ano);
				break;
			case 5:
				localizarCarros(carros, Modo_Busca.Cor);
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
	
	private static void cadastrar (ArrayList<Carro> carros) throws ParseException {
		Carro c = new Carro();
		c.marca = Metodos.lerNome("Marca");
		c.modelo = Metodos.lerNome("Modelo");
		c.ano = Metodos.lerAno("Ano de fabricação");
		c.cor = Metodos.lerNome("Cor");
		c.placa = lerPlaca("Placa");
		c.condutores = salvaCondutores();
		carros.add(c);
	}
	
	private static void localizarCarros(ArrayList<Carro> carros, Modo_Busca md_busca) {
		String output = "";
		switch (md_busca) {
		case Placa:
			output = "Nenhum condutor encontrado !";
			String nr_placa = lerPlaca("Placa");
			
			for (Carro c : carros) {
				if (c.placa.equals(nr_placa)) {
					for(Condutor condutor : c.condutores) {
						output += String.format("Condutor: %s\n"
								+ "Data de nascimento: %s\n", condutor.nome, Metodos.escreveData(condutor.data_nascimento));
					}
					break;
				}
			}
			
			if (output.length() > 30)
				output = output.replace("Nenhum condutor encontrado !", "Condutores\n\n");
			
			break;
			
		case Nome:
			output = "Nenhum carro disponivel!";
			String nome = Metodos.lerNome("Nome");
			
			for (Carro c : carros) {
				for(Condutor condutor : c.condutores) {
					if (condutor.nome.equals(nome)) {
						output += escreveCarro(c) + "\n";
					}
				}
			}	
			
			if (output.length() > 30)
				output = output.replace("Nenhum carro disponivel!", "Carros\n\n");
			
			break;
			
		case Ano:
			output = "Nenhum carro encontrado !";
			
			for (Carro c : carros) {
				if (c.ano == 2024) {
					output += escreveCarro(c) + "\n";
				}
			}
			
			if (output.length() > 30)
				output = output.replace("Nenhum carro encontrado !", "Carros\n\n");
			
			break;
			
		case Cor:
			output = "Nenhum carro encontrado !";
			String cor = Metodos.lerNome("Cor");
			
			for (Carro c : carros) {
				if (c.cor.equals(cor)) {
					output += escreveCarro(c) + "\n";
				}
			}
			
			if (output.length() > 30)
				output = output.replace("Nenhum carro encontrado !", "Carros\n\n");
			
			break;

		default:
			break;
		}
		Metodos.msg(output);
	} 
	
	private static String escreveCarro(Carro c) {
		return String.format("Marca: %s \n"
				+ "Modelo: %s \n"
				+ "Ano de fabricação: %s \n"
				+ "Cor: %s \n"
				+ "Placa: %s \n", c.marca,c.modelo, c.ano, c.cor, c.placa);
	}
	
	private static ArrayList<Condutor> salvaCondutores() throws ParseException {
		ArrayList<Condutor> condutores = new ArrayList<Condutor>();
		int op = 0;
		int quantidade = 0;
		
		do {
			op = Metodos.lerInt("1 - Adicionar Condutor\n"
					+ "2 - Sair");
			
			if (op == 1) {
				condutores.add(leCondutor());
				quantidade ++;
			}
				
			if (op == 2) {
				if (quantidade == 0) {
					Metodos.msg("Cadastre ao menos 1 condutor");
					op = 0;
				}
				else
					Metodos.msg("Cadastrado !");
			}
			
		} while(op != 2);
		
		return condutores;
	}
	
	private static Condutor leCondutor() throws ParseException {
		Condutor c = new Condutor();
		c.nome = Metodos.lerNome("Nome");
		c.data_nascimento = Metodos.lerData("Data de nascimento [dd/mm/yyyy]");
		return c;
	}
	
	private static String lerPlaca (String txt) {
		String s = Metodos.lerString(txt).replace("-", "").replace(" ", "");
		
		// Verifica se tem 7 digitos
		if (s.length() != 7) {
			Metodos.msg("Placa inválida, deve ter ao menos 7 digitos");
			return lerPlaca(txt);
		}
		// Verifica se os 3 primeiros digitos são letras e se os 2 ultimos são numeros
		else if(!veDigito(s, 0, 2) || !veDigito(s, 1, 2) || !veDigito(s, 2, 2) || !veDigito(s, 3, 1) || !veDigito(s, 5, 1) || !veDigito(s, 6, 1)) {
			Metodos.msg("Placa inválida !");
			return lerPlaca(txt);
		}
		
		return s;
	}
	
	private static boolean veDigito(String s, int id, int op) {
		if (op == 1) {
			return Character.isDigit(s.charAt(id));
		}
		else {
			return Character.isAlphabetic(s.charAt(id));
		}
	}
}
