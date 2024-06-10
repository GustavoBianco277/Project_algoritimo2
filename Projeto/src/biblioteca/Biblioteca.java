package biblioteca;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import metodos_utilizados.Metodos;

public class Biblioteca {

	public static void main(String[] args) {
		ArrayList<Livro> livros = new ArrayList<Livro>();
		
		int op = 0;
		
		do {
			op = menu();
			switch (op) {
			case 1: {
				cadastrar(livros);
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
		int op = Metodos.lerOpcao("1 - Cadastrar Livro\n"
				+ "2 - Locaçizar livro por ISBN\n"
				+ "3 - Verificar livros emprestados\n"
				+ "4 - Dados dos livros publicados em 2020\n"
				+ "5 - Listar livros por gênero\n"
				+ "6 - Sair");
		return op;
	}
	
	private static void cadastrar(ArrayList<Livro> livros) {
		Livro l = new Livro();
		l.titulo = Metodos.lerString("Titulo");
		l.autor = Metodos.lerString("Autor");
		//l.ano_publicacao = lerInt("Ano de publicação");
		l.genero = Metodos.lerString("Gênero");
		l.isbn = lerISBN();
		
		livros.add(l);
	}
	
	private static String lerISBN() {
		String s = Metodos.lerString("Numero ISBN").replace("-", "").replace(" ", "");
		if (s.length() == 13) {
			
			if (Metodos.isNumeric(s))
				return s;
			
			else {
				Metodos.msg("Numero invalido!");
				return lerISBN();
			}
		}
		
		else {
			Metodos.msg("Numero invalido!");
			return lerISBN();
		}
	}
}
