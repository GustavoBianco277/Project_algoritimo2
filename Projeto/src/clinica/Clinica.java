package clinica;

import javax.swing.JOptionPane;
import javax.swing.ProgressMonitorInputStream;

import biblioteca.Livro;
import metodos_utilizados.Metodos;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Clinica {

	public static void main(String[] args) throws ParseException {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Date data2 = sdf.parse("12/01/2000");
		//System.out.println("Data formatada: "+sdf.format(data2));
		
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
		int op = Metodos.leOpcao("1 - Cadastrar Paciente\n"
				+ "2 - Localizar Paciente pelo numero do SUS\n"
				+ "3 - Verificar diagnosticos pelo nome do paciente\n"
				+ "4 - Dados de todos os paciente nascidos em 2000\n"
				+ "5 - Listar pacientes por gênero\n"
				+ "6 - Sair");
		
		return op;
	}
	
	private static void cadastrar(ArrayList<Paciente> pacientes) throws ParseException {
		Paciente p = new Paciente();
		p.nome = Metodos.leNome("Nome");
		p.data_nascimento = Metodos.lerData("Data de nascimento [dd/mm/yyyy]");
		p.genero = Metodos.leGenero("Gênero");
		p.nr_SUS = lerNumeroSUS();
		p.diagnosticos = salvaDiagnosticos();
		
		pacientes.add(p);
	}
	
	private static void procurarPeloSUS(ArrayList<Paciente> pacientes) {
		String nr_SUS = lerNumeroSUS();
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
		String nome = Metodos.leNome("Nome");
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
	
	private static void todosNascidosEm2000(ArrayList<Paciente> pacientes) {
		String output = "Pacientes Nascidos em 2000\n\n";
		for (Paciente p : pacientes) {
			if (p.data_nascimento.getYear() == 2000) {
				output += mostraPaciente(p);
			}
		}
		Metodos.msg(output);
	}
	
	private static void todosDeUmGenero(ArrayList<Paciente> pacientes) {
		String genero = Metodos.leGenero("Gênero");
		String output = "Pacientes do Gênero "+genero;
		
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
				+ "Medico responsavel: %s\n", diagnostico.nome, diagnostico.descricao, Metodos.escreveData(diagnostico.data), diagnostico.medicoResponsavel);
	}
	
	private static String lerNumeroSUS() {
		String s = Metodos.leString("Numero do SUS").replace("-", "").replace(" ", "");
		
		if (s.length() == 15) {
			if (Metodos.isNumeric(s))
				return s;
			
			else {
				Metodos.msg("Numero invalido!");
				return lerNumeroSUS();
			}
		}
		
		else {
			Metodos.msg("Numero invalido!");
			return lerNumeroSUS();
		}
	}
	
	private static ArrayList<Diagnostico> salvaDiagnosticos() throws ParseException{
		ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
		int op = 0;
		int quantidade = 0;
		
		do {
			op = Metodos.leOpcao("1 - Adicionar diagnostico\n"
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
		d.nome = Metodos.leNome("Sintomas");
		d.descricao = Metodos.leNome("Descrição");
		d.data = Metodos.lerData("Data de diagnostico [dd/mm/yyyy]");
		d.medicoResponsavel = Metodos.leNome("Nome do medico responsavel");
		return d;
		
	}
	private static String mostraPaciente(Paciente p){
		return String.format("Nome; %s \n"
				+ "Data de nascimento: %s /n"
				+ "Gênero: %s /n"
				+ "Numero do SUS: %s \n", p.nome, Metodos.escreveData(p.data_nascimento), p.genero, p.nr_SUS);
	}

}
