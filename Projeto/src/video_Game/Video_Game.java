package video_Game;

import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import metodos_utilizados.Metodos;

public class Video_Game {

	enum Modo_busca{
		Plataforma,
		Nota,
		Plataform_Ano
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
				buscaJogos(jogos, Modo_busca.Plataforma);
				break;
			case 4:
				buscaJogos(jogos, Modo_busca.Nota);
				break;
			case 5:
				buscaJogos(jogos, Modo_busca.Plataform_Ano);
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
		j.ano_lançamento = Metodos.lerAno("Ano de lançamento", 1990);
		j.plataforma = Metodos.lerString("Plataforma");
		j.nota = lerNota("Nota [1 a 5]");
		jogos.add(j);
	}
	
	private static void listarTodos(ArrayList<Jogo> jogos) {
		String output = "Jogos cadastrados \n\n";
		
		for (Jogo j : jogos) {
			output += mostraJogo(j);
		}
		Metodos.msg(output);
	}
	
	private static void buscaJogos(ArrayList<Jogo> jogos, Modo_busca modo_de_busca) {
		String output = "Jogos da plataforma \n\n";
		
		if (modo_de_busca.equals(Modo_busca.Plataforma)) {
			String plataforma = Metodos.lerString("Plataforma");
			
			for (Jogo j : jogos) {
				if (j.plataforma.equalsIgnoreCase(plataforma)) {
					output += mostraJogo(j);
				}
			}
		}
		else if (modo_de_busca.equals(Modo_busca.Plataform_Ano)) {
			int ano = Metodos.lerInt("Ano");
			String plataforma = Metodos.lerString("Plataforma");
			
			for (Jogo j : jogos) {
				if (j.plataforma.equalsIgnoreCase(plataforma)) {
					if (j.ano_lançamento == ano) {
						output += mostraJogo(j);
					}
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
		Metodos.msg(output);
	}
	
	private static String mostraJogo(Jogo j) {
		return String.format("Titulo: %s\n"
				+ "Ano de lançamento: %s\n"
				+ "Plataforma: %s\n"
				+ "Nota: %s\n", j.titulo, j.ano_lançamento, j.plataforma, j.nota);
	}
	
	private static int lerNota(String txt) {
		int n = Metodos.lerInt(txt);
		
		if (n >= 1 && n <= 5)
			return n;
		
		else {
			Metodos.msg("Valor invalido!");
			return lerNota(txt);
		}	
	}
}
