package clinica;

import javax.swing.JOptionPane;

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
		p.nome = Metodos.lerString("Nome");
		p.data_nascimento = Metodos.lerData("Data de nascimento [dd/mm/yyyy]");
		p.genero = Metodos.lerString("Gênero");
		p.nr_SUS = lerNumeroSUS();
		p.diagnosticos = salvaDiagnosticos();
		
		pacientes.add(p);
	}
	
	
	
	private static String lerNumeroSUS() {
		String s = Metodos.lerString("Numero do SUS").replace("-", "").replace(" ", "");
		
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
			op = Metodos.lerOpcao("1 - Adicionar diagnostico\n"
					+ "2 - Sair");
			
			if (op == 1) {
				diagnosticos.add(lerDiagnostico());
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
	
	private static Diagnostico lerDiagnostico() throws ParseException {
		Diagnostico d = new Diagnostico();
		d.nome = Metodos.lerString("Nome");
		d.descricao = Metodos.lerString("Descrição");
		d.data = Metodos.lerData("Data de diagnostico [dd/mm/yyyy]");
		d.medicoResponsavel = Metodos.lerString("Nome do medico responsavel");
		return d;
		
	}

}
