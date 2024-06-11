package metodos_utilizados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
		
		if (s.isEmpty() || s.length() < 3)
			return lerString(txt);
		
		else
			return s;
	}
	
	public static int lerOpcao(String txt) {
		String s = JOptionPane.showInputDialog(txt);
		
		if (!Character.isDigit(s.charAt(0))) {
			msg("Valor invalido!");
			return lerOpcao(txt);
		}
		
		return Integer.parseInt(s);
	}
	
	public static Date lerData(String txt) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s = lerString(txt).replace("/", "");
		
		if (s.length() == 8 && isNumeric(s)) {
			String data = "";
			
			// adiciona / na data
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
}
