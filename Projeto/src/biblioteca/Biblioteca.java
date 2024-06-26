package biblioteca;


import javax.swing.*;
import metodos_utilizados.Metodos;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;


public class Biblioteca {

    public static void main(String[] args) throws ParseException, IOException {
        ArrayList<Livro> livros = lerDados();

        /*- Cadastra o livro -- (Método )
1-Pode criar um método inteiro de cadastro passando as informações e as
validações.
2- Localiza um livro pelo seu código (Função- ListLivros )
3- Perguntar um nome e verificar se o usuário pegou emprestado (Função- Lista
Livros)
4- Dados de todos os livros publicados em 2020 (Função – ListLivros )
5- Listar todos os livros de um determinado gênero
*/

        int op = 0;
        do {
            op = menu();
            switch (op) {
                case 1:
                    cadastrarLivro(livros);
                    break;
                case 2:
                    pegaCodigoLivroISBN(livros);
                    break;
                case 3: mostrarLivrosEmprestado(livros);
                    break;
                case 4:
                    todosLivros2020(livros);
                    break;
                case 5: listaGenerosLivros(livros);
                    break;
                case 6 :
                	salvaDados(livros);
                    JOptionPane.showMessageDialog(null,"SAINDOOOOOOOO.");
            }
        } while (op != 6);
    }

    public static int menu() {
        String op = "1 - Cadastrar livros \n" +
                "2 - Procura código do livro \n" +
                "3 - Nome e ver se tem o livro emprestado \n" +
                "4 - Todos os livros de 2020 \n" +
                "5 - Mostrar livros do gênero \n" +
                "6-  Sair";

        return Integer.parseInt(JOptionPane.showInputDialog(null, op));
    }

    public static void cadastrarLivro(ArrayList<Livro> livros) throws ParseException {
        Livro l = new Livro();
        l.titulo = Metodos.lerNome("Titulo");
        l.autor = Metodos.lerNome("Autor");
        l.ano_publicacao = Metodos.lerData("Data de criação [dd/mm/yyyy]");
        l.genero = Metodos.lerNome("Genero do livro");
        l.nr_isbn = lerISBN(livros);
        l.pessoas = salvaPessoas();

        livros.add(l);
    }


    private static ArrayList<Pessoa> salvaPessoas() throws ParseException {
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        int op;
        boolean cadastrado = false;

        do {
            op = Metodos.lerInt("1 - Adicionar pessoas que pegaram este livro\n"
                    + "2 - Sair");

            switch (op) {
                case 1:
                    pessoas.add(lePessoa());
                    cadastrado = true;
                    Metodos.msg("Cadastrado!");
                    break;
                    
                case 2:
                	if (!cadastrado)
                        Metodos.msg("Sem cadastro de pessoas!");
                	
                    break;
                default:
                    Metodos.msg("Opção inválida! Por favor, escolha novamente.");
            }
        } while (op != 2);

        return pessoas;
    }

    public static Livro pegaCodigoLivroISBN(ArrayList<Livro> livros) {
        String codigoBusca = Metodos.lerString("Código: ");
        for (Livro livro : livros) {
            if (livro.nr_isbn.equalsIgnoreCase(codigoBusca)) {
                String saida = "Livro achado ! " + "\n" + mostraLivro(livro);
                Metodos.msg(saida);
                return livro;
            }
        }
        Metodos.msg("Nenhum livro encontrado com o ISBN: " + codigoBusca);
        return null;
    }

    private static void mostrarLivrosEmprestado(ArrayList<Livro> livros) {
        String nomePessoa = Metodos.lerString("Nome da pessoa");
        String saida = "";

        for (Livro l : livros) {
            for (Pessoa ps : l.pessoas){
                if (nomePessoa.equalsIgnoreCase(ps.nome) && ps.livroEmprestado.equalsIgnoreCase("s")){
                    saida += "\nNome : " + ps.nome + "\n" +
                            "Idade: " + Metodos.calculaIdade(ps.data_nascimento) + "\n" + mostraLivro(l);
                }
            }
        }
        if (saida.equals("")) {
            saida = "Nenhuma pessoa encotrada !";
        }
        Metodos.msg(saida);
    }

    private static void todosLivros2020(ArrayList<Livro> livros) throws ParseException {
        String output = "Livros de 2020\n\n";
        Calendar c = Calendar.getInstance();

        for (Livro l : livros) {
            c.setTime(l.ano_publicacao);

            if (c.get(Calendar.YEAR) == 2020) {
                output += mostraLivro(l);
            }
        }
        Metodos.msg(output);
    }

    private static void listaGenerosLivros(ArrayList<Livro> livros) {
        boolean continuar = true;
        
        HashSet<String> generosUnicos = new HashSet<>();   // USEI O HASH PARA QUE NÃO HOUVESSEM REPETIÇÕES DE GENERO
        for (Livro l : livros) {                           // Estava ocorrendo então coloquei e armazeneio num hash para evitar duplicar
            generosUnicos.add(l.genero);
        }

        String saidaGeneros = "Gêneros disponíveis:\n";
        for (String genero : generosUnicos) {
            saidaGeneros += genero + "\n";
        }
        Metodos.msg(saidaGeneros);
        
        while (continuar) {
            String generoLivro = Metodos.lerString("Genêro livro: ");
            String saida = "";

            for (Livro l : livros) {
                if (generoLivro.equalsIgnoreCase(l.genero)) {
                    saida += mostraLivro(l);
                }
            }

            if (saida.isEmpty()) {
                saida = "Nenhum livro encontrado com o gênero: " + generoLivro;
            }
            Metodos.msg(saida);

            if (saida.equalsIgnoreCase("Nenhum livro encontrado com o gênero: " + generoLivro)) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja tentar novamente?", "Pergunta", JOptionPane.YES_NO_OPTION);
                
                if (resposta != JOptionPane.YES_OPTION) {
                    continuar = false;
                }
            } 
            else {
                continuar = false;
            }
        }
    }

    private static Pessoa lePessoa() throws ParseException {
        Pessoa p = new Pessoa();
        p.nome = Metodos.lerNome("Nome");
        p.data_nascimento = Metodos.lerData("Data de nascimento: ");
        p.livroEmprestado = Metodos.lerString("Está com o livro emprestado ainda? [s/n]");
        return p;
    }

    private static String lerISBN(ArrayList<Livro> livros) {
        String s = Metodos.lerString("Digite o ISBN (somente números, sem hífens com 13 Numeros)").replace("-", "").replace(" ", "");

        if (s.length() != 13) {
        	Metodos.msg("Número inválido! O ISBN deve ter 13 dígitos.");
        	return lerISBN(livros);
        }
        	
        else if (!Metodos.isNumeric(s)) {
        	Metodos.msg("Número inválido! Deve conter apenas dígitos.");
        	return lerISBN(livros);
        }
        
        else if (verificaCadastroISBN(livros, s)) {
        	Metodos.msg("Número inválido! Já foi cadastrado.");
        	return lerISBN(livros);
        }
                
        else {
            return s;
        }
    }
    
    private static boolean verificaCadastroISBN(ArrayList<Livro> livros, String nr_ISBN) {
		for (Livro l : livros) {
			if (l.nr_isbn.equalsIgnoreCase(nr_ISBN)) {
				return true;
			}
		}
		
		return false;
	}

    private static String mostraLivro(Livro l) {
        return String.format("Título: %s\n"
        		+ "Autor: %s\n"
                + "Data de lançamento: %s\n"
                + "Gênero: %s\n"
                + "Número do ISBN: %s\n", l.titulo, l.autor, Metodos.escreveData(l.ano_publicacao), l.genero, escreveISBN(l.nr_isbn));
    }
    private static String escreveISBN(String nr_ISBN) {
		String newNr_ISBN = "";
		for (int i = 0; i < nr_ISBN.length(); i++) {
			if (i == 3 || i == 5 || i == 9 || i == 12 || i == 13)
				newNr_ISBN += "-";
			
			newNr_ISBN += nr_ISBN.charAt(i);
		}
		return newNr_ISBN;
	}
    
 // Salva todos os dados
 	private static void salvaDados(ArrayList<Livro> livros) throws IOException {
 		ArrayList<String> linhas = new ArrayList<String>();
 		
 		for (Livro l : livros) {
 			String line = String.format("%s_%s_%s_%s_%s", l.titulo, l.autor, Metodos.escreveData(l.ano_publicacao), l.genero, l.nr_isbn);
 			for (Pessoa p : l.pessoas) {
 				line += String.format("_%s_%s_%s", p.nome, Metodos.escreveData(p.data_nascimento), p.livroEmprestado);
 			}
 			linhas.add(line);
 		}
 		Metodos.salvaMemoria(linhas, 1);
 	}
 	
 	// Le todos os dados
 	private static ArrayList<Livro> lerDados() throws IOException, ParseException{
 		ArrayList<Livro> livros = new ArrayList<Livro>();
 		
 		for (String dados : Metodos.lerMemoria(1)) {
 			Livro l = new Livro();
 			String[] livro = dados.split("_");
 			
 			// le Paciente
 			if (livro.length >= 5) {
 				l.titulo = livro[0];
 				l.autor = livro[1];
 				l.ano_publicacao = Metodos.converteData(livro[2]);
 				l.genero = livro[3];
 				l.nr_isbn = livro[4];
 				
 				//le Diagnosticos
 				ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
 				
 				for (int i = 5; i < livro.length; i+=3) {
 					Pessoa p = new Pessoa();
 					p.nome = livro[i];
 					p.data_nascimento = Metodos.converteData(livro[i+1]);
 					p.livroEmprestado = livro[i+2];
 					pessoas.add(p);
 				}
 				l.pessoas = pessoas;
 				livros.add(l);
 			}
 		}
 		return livros;
 	}
}

