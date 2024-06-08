package clinica;

import javax.swing.JOptionPane;

public class Clinica {

	public static void main(String[] args) {
		

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
	
	private static void msg(String msm) {
		JOptionPane.showMessageDialog(null, msm);
	}
	
	private static String lerString(String txt) {
		String s = JOptionPane.showInputDialog(txt);
		if (s.trim().length() <= 2)
			return lerString(txt);
		else
			return s;
	}

}
