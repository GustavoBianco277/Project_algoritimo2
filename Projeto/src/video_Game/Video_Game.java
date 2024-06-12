package video_Game;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import metodos_utilizados.Metodos;

public class Video_Game {

	enum Modo_busca{
		Plataforma,
		Nota
	}
	public static void main(String[] args) throws ParseException {
		ArrayList<Jogo> jogos = new ArrayList<Jogo>();

		int op = 0;
				
		do {
			op = menu();
			switch (op) {
			case 1: {
				cadastrar(jogos);
				break;
			}
			case 2: {
				listarTodos(jogos);
				break;
			}
			case 3:
				buscaJogos(jogos, Modo_busca.Plataforma, false);
				break;
			case 4:
				buscaJogos(jogos, Modo_busca.Nota, false);
				break;
			case 5:
				buscaJogos(jogos, Modo_busca.Plataforma, true);
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
		int op = Metodos.lerInt("1 - Cadastrar Jogo\n"
				+ "2 - Listar todos\n"
				+ "3 - Buscar por plataforma\n"
				+ "4 - Buscar por nota\n"
				+ "5 - Jogo com maior nota\n"
				+ "6 - Sair");
		
		return op;
	}
	
	private static void cadastrar(ArrayList<Jogo> jogos) throws ParseException {
		Jogo j = new Jogo();
		j.titulo = Metodos.lerNome("Titulo");
		j.data_lançamento = Metodos.lerData("Data de lançamento [dd/mm/yyyy]");
		j.plataforma = Metodos.lerString("Plataforma");
		j.nota = lerNota("Nota [1 a 5");
	}
	
	private static void listarTodos(ArrayList<Jogo> jogos) {
		String output = "Jogos cadastrados \n\n";
		
		for (Jogo j : jogos) {
			output += mostraJogo(j);
		}
	}
	
	private static void buscaJogos(ArrayList<Jogo> jogos, Modo_busca modo_de_busca, boolean Ano) {
		String output = "Jogos da plataforma \n\n";
		int ano = 0;
		
		if (modo_de_busca.equals(Modo_busca.Plataforma)) {
			String plataforma = Metodos.lerNome("Plataforma");
			ano = Ano ? Metodos.lerInt("Ano") : 0;
			
			for (Jogo j : jogos) {
				if (j.plataforma.equalsIgnoreCase(plataforma)) {
					if (Ano && Metodos.lerAno(j.data_lançamento) == ano) {
						output += mostraJogo(j);
					}
					else if (!Ano)
						output += mostraJogo(j);
				}
			}
		}
		else if (modo_de_busca.equals(Modo_busca.Nota)) {
			int nota = lerNota("Nota");
			
			for (Jogo j : jogos) {
				if (j.nota == nota) {
					output += mostraJogo(j);
				}
			}
		}
	}
	
	private static String mostraJogo(Jogo j) {
		return String.format("Titulo: %s\n"
				+ "Data de lançamento: %s\n"
				+ "Plataforma: %s\n"
				+ "Nota: %s\n", j.titulo, Metodos.escreveData(j.data_lançamento), j.plataforma, j.nota);
	}
	
	private static int lerNota(String txt) {
		String s = JOptionPane.showInputDialog(txt);
		int n = 0;
		
		if (!Character.isDigit(s.charAt(0))) {
			Metodos.msg("Valor invalido!");
			return lerNota(txt);
		}
		n = Integer.parseInt(s);
		
		if (n >= 1 && n <= 5)
			return n;
		
		else {
			Metodos.msg("Valor invalido!");
			return lerNota(txt);
		}
			
	}

}
