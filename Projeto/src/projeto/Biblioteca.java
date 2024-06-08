package projeto;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
				msg("Programa finalizado !");
				break;
			default:
				msg("Valor invalido!");
			}
			
		}while(op != 6);
		
	}

	private static int menu() {
		int op = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Cadastrar Livro\n"
				+ "2 - Locaçizar livro por ISBN\n"
				+ "3 - Verificar livros emprestados\n"
				+ "4 - Dados dos livros publicados em 2020\n"
				+ "5 - Listar livros por gênero\n"
				+ "6 - Sair"));
		
		return op;
	}
	
	public static void cadastrar(ArrayList<Livro> livros) {
		Livro l = new Livro();
		l.titulo = lerString("Titulo");
		l.autor = lerString("Autor");
		l.ano_publicacao = lerInt("Ano de publicação");
		l.genero = lerString("Gênero");
		l.isbn = lerISBN();
		
		livros.add(l);
	}
	
	private static String lerString(String txt) {
		String s = JOptionPane.showInputDialog(txt);
		if (s.trim().length() <= 2)
			return lerString(txt);
		else
			return s;
	}
	
	private static int lerInt(String txt) {
		int n = Integer.parseInt(JOptionPane.showInputDialog(txt));
		
		if (n > 0)
			return n;
		else
			return lerInt(txt);
	}
	
	private static String lerISBN() {
		String s = lerString("Numero ISBN").replace("-", "");
		if (s.length() == 13) {
			boolean isNumeric = true;
			
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				
				if (Character.isAlphabetic(c)) {
					isNumeric = false;
					break;
				}
				
			}
			if (isNumeric)
				return s;
			else {
				msg("Numero invalido!");
				return lerISBN();
			}
		}
		
		else {
			msg("Numero invalido!");
			return lerISBN();
		}
	}
	
	private static void msg(String msm) {
		JOptionPane.showMessageDialog(null, msm);
	}
}
