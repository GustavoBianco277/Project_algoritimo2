package biblioteca;


import javax.swing.*;

import metodos_utilizados.Metodos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ListResourceBundle;


public class Biblioteca {

    public static void main(String[] args) throws ParseException {
        ArrayList<Livro> livros = new ArrayList<Livro>();

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
                    pegaCódigoLivroISBN(livros);
                    break;
                case 3: mostrarLivrosEmprestado(livros);
                    break;
                case 4:
                    todosLivros2020(livros);
                    break;
                case 5: listaGenerosLivros(livros);
                    break;
                case 6 :
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
        l.ano = Metodos.lerData("Data de criação [dd/mm/yyyy]");
        l.genero = Metodos.lerNome("Genero");
        l.isbn = lerISBN();
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
                    break;
                case 2:
                    if (op == 2) {
                        Metodos.msg("Sem cadastro de pessoas!");
                        break;
                    } else {
                        Metodos.msg("Cadastrado!");
                    }
                    break;
                default:
                    Metodos.msg("Opção inválida! Por favor, escolha novamente.");
            }
        } while (op != 2);

        return pessoas;
    }

    public static Livro pegaCódigoLivroISBN(ArrayList<Livro> livros) {
        String codigoBusca = Metodos.lerString("Código: ");
        for (Livro livro : livros) {
            if (livro.isbn.equalsIgnoreCase(codigoBusca)) {
                String saida = "Livro achado ! " + "\n" +
                        "Título: " + livro.titulo + "\n" +
                        "Autor: " + livro.autor + "\n" +
                        "Ano: " + Metodos.escreveData(livro.ano) + "\n" +
                        "Gênero: " + livro.genero + "\n" +
                        "ISBN: " + livro.isbn;
                Metodos.msg(saida);
                return livro;
            }
        }
        Metodos.msg("Nenhum livro encontrado com o ISBN: " + codigoBusca);
        return null;
    }

    private static void mostrarLivrosEmprestado(ArrayList<Livro> livros) {
        String nomePessoa = Metodos.lerString("Nome pessoa");
        String saida = "";

        for (Livro l : livros) {
            for (Pessoa ps : l.pessoas){
                if (nomePessoa.equalsIgnoreCase(ps.nome) && ps.livroEmprestado.equalsIgnoreCase("s")){
                    saida += "\nNome : " + ps.nome + "\n" +
                            "Idade: " + ps.idade + "\n" +
                            "Título: " + l.titulo + "\n" +
                            "Autor: " + l.autor + "\n" +
                            "Ano: " + Metodos.escreveData(l.ano) + "\n" +
                            "Gênero: " + l.genero + "\n" +
                            "ISBN: " + l.isbn + "\n\n";

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
            c.setTime(l.ano);

            if (c.get(Calendar.YEAR) == 2020) {
                output += mostraLivro(l);
            }
        }
        Metodos.msg(output);
    }

    private static void listaGenerosLivros(ArrayList<Livro> livros) {
        boolean continuar = true;

        while (continuar) {
            String generoLivro = Metodos.lerString("Genêro livro: ");
            String saida = "";

            for (Livro l : livros) {
                if (generoLivro.equalsIgnoreCase(l.genero)) {
                    saida += mostraLivro(l);
                }
            }

            if (saida.equalsIgnoreCase("")) {
                saida = "Nenhum livro encontrado com o gênero: " + generoLivro;
            }
            Metodos.msg(saida);

            if (saida.equalsIgnoreCase("Nenhum livro encontrado com o gênero: " + generoLivro)) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja tentar novamente?", "Pergunta", JOptionPane.YES_NO_OPTION);
                if (resposta != JOptionPane.YES_OPTION) {
                    continuar = false;
                }
            } else {
                continuar = false;
            }
        }
    }

    private static Pessoa lePessoa() throws ParseException {
        Pessoa p = new Pessoa();
        p.nome = Metodos.lerNome("Nome");
        p.idade = Metodos.lerData("Data de nascimento: ");
        p.livroEmprestado = Metodos.lerString("Está com o livro emprestado ainda? s/n");
        return p;
    }


    private static String lerISBN() {
        String s = Metodos.lerString("Digite o ISBN (somente números, sem hífens com 13 Numeros)").replace("-", "").replace(" ", "");

        if (s.length() == 13) {
            if (Metodos.isNumeric(s))
                return s;
            else {
                Metodos.msg("Número inválido! Deve conter apenas dígitos.");
                return lerISBN();
            }
        } else {
            Metodos.msg("Número inválido! O ISBN deve ter 13 dígitos.");
            return lerISBN();
        }
    }

    private static String mostraLivro(Livro l) {

        return String.format("Título: %s\n"
                + "Data de lançamento: %s\n"
                + "Gênero: %s\n"
                + "Número do ISBN: %s\n", l.titulo, Metodos.escreveData(l.ano), l.genero, l.isbn);
    }

}

