package metodos_utilizados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import clinica.Paciente;

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
		
		if (s.length() < 3)
			return lerNome(txt);
		
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
		String s = JOptionPane.showInputDialog(txt);
		
		if (!isNumeric(s)) {
			msg("Valor invalido!");
			return lerInt(txt);
		}
		
		return Integer.parseInt(s);
	}
	
	public static Date lerData(String txt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s = lerString(txt).replace("/", "");
		
		if (s.length() == 8 && isNumeric(s)) {
			String data = "";
			
			// adiciona na data
			for (int i = 0; i < 8; i++) {
				data += s.charAt(i);
				
				if (i == 1 || i == 3)
					data += "/";

			}

			// valida a data
			String[] data_part = data.split("/");
			int dia = Integer.parseInt(data_part[0]);
			int mes = Integer.parseInt(data_part[1]);
			int ano = Integer.parseInt(data_part[2]);
			
			if (dia <= 31 && dia > 0 && mes <= 12 && mes > 0 && ano <= 2024 && ano > 1850 )
				return sdf.parse(data);
			
			else {
				msg("Data inválida !");
				return lerData(txt);
			}
		}
		
		else{
			msg("Data inválida !");
			return lerData(txt);
		}
	}
	
	public static String escreveData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	public static int lerAno(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);	
		return c.get(Calendar.YEAR);
	}
}
