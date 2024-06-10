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
		
		if (s.isEmpty())
			return lerString(txt);
		
		else
			return s;
	}
	
	public static int lerOpcao(String txt) {
		String s = lerString(txt);
		
		if (!Character.isDigit(s.charAt(0))) {
			msg("Valor invalido!");
			return lerOpcao(txt);
		}
		
		return Integer.parseInt(s);
	}
	
	public static Date lerData() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s = lerString("Data de nascimento [dd/mm/yyyy]").replace("/", "");
		
		if (s.length() == 8 && isNumeric(s)) 
			return sdf.parse(s);
		
		else
			return lerData();
	}
}
