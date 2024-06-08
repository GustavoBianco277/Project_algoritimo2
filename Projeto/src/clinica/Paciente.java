package clinica;

import java.util.Date;
import java.util.ArrayList;

public class Paciente {
	String nome;
	Date data_nascimento;
	String genero;
	String nr_SUS;
	ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
}
