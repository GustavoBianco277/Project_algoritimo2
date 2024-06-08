package clinica;

import javax.swing.JOptionPane;

import biblioteca.Livro;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Clinica {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = sdf.parse("12/01/2000");
		System.out.println("Data formatada: "+sdf.format(data2));
		
		ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
		
		int op = 0;
		
		do {
			op = menu();
			switch (op) {
			case 1: {
				cadastrar(pacientes);
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
		int op = Integer.parseInt(JOptionPane.showInputDialog(null, "1 - Cadastrar Paciente\n"
				+ "2 - Locaçizar Paciente pelo numero do SUS\n"
				+ "3 - Verificar diagnosticos pelo nome do paciente\n"
				+ "4 - Dados de todos os paciente nascidos em 2000\n"
				+ "5 - Listar pacientes por gênero\n"
				+ "6 - Sair"));
		
		return op;
	}
	
	private static void cadastrar(ArrayList<Paciente> pacientes) throws ParseException {
		Paciente p = new Paciente();
		p.nome = lerString("Nome");
		p.data_nascimento = lerData();
		p.genero = lerString("Gênero");
		p.nr_SUS = lerNumeroSUS();
		
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
	
	private static Date lerData() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s = lerString("Data de nascimento [dd/mm/yyyy]");
		
		if (s.replace("/", "").length() == 8) 
			return sdf.parse(s);
		
		else
			return lerData();
	}
	
	private static String lerNumeroSUS() {
		String s = lerString("Numero do SUS").replace("-", "");
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
				return lerNumeroSUS();
			}
		}
		
		else {
			msg("Numero invalido!");
			return lerNumeroSUS();
		}
	}
	
	private static ArrayList<Diagnostico> lerDiagnosticos(){
		ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		int op = 0;
		
		do {
		
			
		} while(op != 0);
		
		return diagnosticos;
	}

}
