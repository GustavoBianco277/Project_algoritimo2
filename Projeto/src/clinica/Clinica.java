package clinica;
import metodos_utilizados.Metodos;
import java.util.ArrayList;
import java.util.Calendar;

import java.text.ParseException;

public class Clinica {

	public static void main(String[] args) throws ParseException {
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
				procurarPeloSUS(pacientes);
				break;
			}
			case 3:
				procurarPeloNome(pacientes);
				break;
			case 4:
				todosNascidosEm2000(pacientes);
				break;
			case 5:
				todosDeUmGenero(pacientes);
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
		int op = Metodos.lerInt("1 - Cadastrar Paciente\n"
				+ "2 - Localizar Paciente pelo numero do SUS\n"
				+ "3 - Verificar diagnosticos pelo nome do paciente\n"
				+ "4 - Dados de todos os paciente nascidos em 2000\n"
				+ "5 - Listar pacientes por gênero\n"
				+ "6 - Sair");
		
		return op;
	}
	
	private static void cadastrar(ArrayList<Paciente> pacientes) throws ParseException {
		Paciente p = new Paciente();
		p.nome = Metodos.lerNome("Nome");
		p.data_nascimento = Metodos.lerData("Data de nascimento [dd/mm/yyyy]");
		p.genero = Metodos.lerGenero("Gênero [m/f]");
		p.nr_SUS = lerNumeroSUS(pacientes);
		p.diagnosticos = salvaDiagnosticos();
		
		pacientes.add(p);
	}
	
	private static void procurarPeloSUS(ArrayList<Paciente> pacientes) {
		String nr_SUS = lerNumeroSUS(pacientes);
		String output = "Nenhum paciente encontrado !";
		
		for (Paciente p : pacientes) {
			if (p.nr_SUS.equals(nr_SUS)) {
				output = "Diagnosticos de " + p.nome +"\n\n";
				
				for (Diagnostico d : p.diagnosticos) {
					output += mostrarDiagnosticos(d) + "\n";
				}
			}
		}
		Metodos.msg(output);
	}
	
	private static void procurarPeloNome(ArrayList<Paciente> pacientes) {
		String nome = Metodos.lerNome("Nome");
		String output = "Nenhum paciente encontrado !";
		
		for (Paciente p : pacientes) {
			if (p.nome.equals(nome)) {
				output = "Diagnosticos de " + p.nome +"\n\n";
				
				for (Diagnostico d : p.diagnosticos) {
					output += mostrarDiagnosticos(d) + "\n";
				}
			}
		}
		Metodos.msg(output);
	}
	
	private static void todosNascidosEm2000(ArrayList<Paciente> pacientes) throws ParseException {
		String output = "Pacientes Nascidos em 2000\n\n";
		Calendar c = Calendar.getInstance();
		
		for (Paciente p : pacientes) {
			c.setTime(p.data_nascimento);
			
			if (c.get(Calendar.YEAR) == 2000) {
				output += mostraPaciente(p);
			}
		}
		Metodos.msg(output);
	}
	
	private static void todosDeUmGenero(ArrayList<Paciente> pacientes) {
		String genero = Metodos.lerGenero("Gênero [m/f]");
		String output = "Pacientes do Gênero "+genero + "\n\n";
		
		for (Paciente p : pacientes) {
			if (p.genero.equals(genero)) {
				output += mostraPaciente(p);
			}
		}
		Metodos.msg(output);
	}
	
	private static String mostrarDiagnosticos(Diagnostico diagnostico) {
		return String.format("Sintomas: %s\n"
				+ "Descrição: %s\n"
				+ "Data: %s\n"
				+ "Medico responsavel: %s\n", diagnostico.sintomas, diagnostico.descricao, Metodos.escreveData(diagnostico.data), diagnostico.medicoResponsavel);
	}
	
	private static String lerNumeroSUS(ArrayList<Paciente> pacientes) {
		String s = Metodos.lerString("Numero do SUS").replace("-", "").replace(" ", "");
		
		if (s.length() != 15) 
		{
			Metodos.msg("Número inválido! O SUS deve ter 15 dígitos.");
			return lerNumeroSUS(pacientes);
		}
		
		else if (!Metodos.isNumeric(s)) {
			Metodos.msg("Número inválido! Deve conter apenas dígitos.");
			return lerNumeroSUS(pacientes);
		}
		
		else if (verificaCadastroSUS(pacientes, s)) {
			Metodos.msg("Número inválido! Já foi cadastrado.");
			return lerNumeroSUS(pacientes);
		}
			
		else {
			return s;
		}
	}
	
	private static boolean verificaCadastroSUS(ArrayList<Paciente> pacientes, String nr_SUS) {
		for (Paciente p : pacientes) {
			if (p.nr_SUS.equalsIgnoreCase(nr_SUS)) {
				return true;
			}
		}
		
		return false;
	}
	
	private static ArrayList<Diagnostico> salvaDiagnosticos() throws ParseException{
		ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		int op = 0;
		int quantidade = 0;
		
		do {
			op = Metodos.lerInt("1 - Adicionar diagnostico\n"
					+ "2 - Sair");
			
			if (op == 1) {
				diagnosticos.add(leDiagnostico());
				quantidade ++;
			}
				
			if (op == 2) {
				if (quantidade == 0) {
					Metodos.msg("Cadastre ao menos 1 diagnostico");
					op = 0;
				}
				else
					Metodos.msg("Cadastrado !");
			}
			
		} while(op != 2);
		
		return diagnosticos;
	}
	
	private static Diagnostico leDiagnostico() throws ParseException {
		Diagnostico d = new Diagnostico();
		d.sintomas = Metodos.lerNome("Sintomas");
		d.descricao = Metodos.lerNome("Descrição");
		d.data = Metodos.lerData("Data de diagnostico [dd/mm/yyyy]");
		d.medicoResponsavel = Metodos.lerNome("Nome do medico responsavel");
		return d;
		
	}
	private static String mostraPaciente(Paciente p){
		return String.format("Nome: %s\n"
				+ "Data de nascimento: %s\n"
				+ "Gênero: %s\n"
				+ "Numero do SUS: %s\n", p.nome, Metodos.escreveData(p.data_nascimento), p.genero, escreveSUS(p.nr_SUS));
	}
	
	private static String escreveSUS(String nr_SUS) {
		String newNr_SUS = "";
		for (int i = 0; i < nr_SUS.length(); i++) {
			if (i == 3 || i == 7 || i == 11)
				newNr_SUS += " ";
			
			newNr_SUS += nr_SUS.charAt(i);
		}
		return newNr_SUS;
	}
}
