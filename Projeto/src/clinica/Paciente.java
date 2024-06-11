package clinica;

import java.util.Date;
import java.util.ArrayList;

public class Paciente {
	protected String nome;
	protected Date data_nascimento;
	protected String genero;
	protected String nr_SUS;
	protected ArrayList<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
}
