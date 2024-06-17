package metodos_utilizados;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

public class Metodos {
	
	public static void msg(String msm) {
		JOptionPane.showMessageDialog(null, msm);
	}
	
	public static boolean isNumeric(String s) {
		boolean isNumeric = true;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (!Character.isDigit(c)) {
				isNumeric = false;
				break;
			}
		}
		return isNumeric;
	}
	
	public static String lerString(String txt) {
		String s = JOptionPane.showInputDialog(txt).trim();
		
		if (s.isEmpty())
			return lerString(txt);
		
		else
			return s;
	}
	
	public static String lerNome(String txt) {
		String s = lerString(txt);
		
		if (s == null || s.length() < 3) {
			msg("O nome deve ter pelo menos 3 caracteres, e não pode ser vazio");
			return lerNome(txt);
		}
		
		else
			return s;
	}
	
	public static String lerGenero(String txt) {
		String s = lerString(txt);
		
		if (s.length() == 1 && s.equalsIgnoreCase("M") || s.length() == 1 && s.equalsIgnoreCase("F")) 
			return s.toUpperCase();
		
		else {
			msg("Gênero invalido !");
			return lerGenero(txt);
		}
			
	}
	
	public static int lerInt(String txt) {
		String s = lerString(txt);
		
		if (!isNumeric(s)) {
			msg("Valor invalido!");
			return lerInt(txt);
		}
		
		return Integer.parseInt(s);
	}
	
	public static int lerAno(String txt, int min) {
		String s = lerString(txt);
		
		if (!isNumeric(s) || s.length() != 4 ) {
			msg("Ano invalido!");
			return lerAno(txt, min);
		}
		int ano = Integer.parseInt(s);
		
		if (ano < min || ano > 2024) {
			msg("Ano invalido!");
			return lerAno(txt, min);
		}
		
		return ano;
	}
	
	public static Date converteData(String data) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(data);
	}
	
	public static Date lerData(String txt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s = lerString(txt).replace("/", "");
		Calendar dataHoje = Calendar.getInstance();
		
		if (s.length() != 8 || !isNumeric(s)) {
			msg("Data inválida !");
			return lerData(txt);
		}
		else {
			String data = "";
			// adiciona / na data
			for (int i = 0; i < 8; i++) {
				data += s.charAt(i);
				
				if (i == 1 || i == 3)
					data += "/";
			}

			// Válida a data
			String[] data_part = data.split("/");
			int dia = Integer.parseInt(data_part[0]);
			int mes = Integer.parseInt(data_part[1]);
			int ano = Integer.parseInt(data_part[2]);
			
			// Verifica se a data é válida
			if (dia > 31 || dia <= 0 || mes > 12 || mes <= 0 || ano > dataHoje.get(Calendar.YEAR) || ano <= 0) {
				msg("Data inválida !");
				return lerData(txt);
			}
			
			else if (sdf.parse(data).compareTo(dataHoje.getTime()) >= 0) {
				msg("Essa data ainda não chegou !");
				return lerData(txt);
			}
			
			else {
				return sdf.parse(data);
			}
		}
	}
	
	public static String escreveData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	public static int mostraAno(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);	
		return c.get(Calendar.YEAR);
	}
	
	public static int calculaIdade(Date data) {
		Calendar hoje = Calendar.getInstance();
		Calendar dataSet = Calendar.getInstance();
		dataSet.setTime(data);
		int idade = hoje.get(Calendar.YEAR) - dataSet.get(Calendar.YEAR);
		
		if (hoje.before(dataSet))
			idade--;
		
		return idade;
	}
	
	public static ArrayList<String> lerMemoria(int op) throws IOException {
		Path path;
		
		if (op == 1) path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\biblioteca\\bd_biblioteca.txt");
		else if (op == 2) path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\clinica\\bd_clinica.txt");
		else if (op == 3) path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\video_Game\\bd_video_games.txt");
		else path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\carros\\bd_carros.txt");
		
		return (ArrayList<String>) Files.readAllLines(path);
	}
	
	public static void salvaMemoria(ArrayList<String> linhas, int op) throws IOException {
		Path path;
		
		if (op == 1) path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\biblioteca\\bd_biblioteca.txt");
		else if (op == 2) path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\clinica\\bd_clinica.txt");
		else if (op == 3) path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\video_Game\\bd_video_games.txt");
		else path = Paths.get("C:\\Users\\PC-Bianco\\git\\repository\\Projeto\\src\\carros\\bd_carros.txt");
		
		Files.write(path, linhas);
	}
}
