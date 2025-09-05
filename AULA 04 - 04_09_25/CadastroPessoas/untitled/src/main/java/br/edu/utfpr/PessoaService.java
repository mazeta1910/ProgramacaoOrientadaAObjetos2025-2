package br.edu.utfpr;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PessoaService {

    public PessoaService() {
    }


    public Pessoa coletarDadosPessoa(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        int idade = -1;
        while (idade < 0) {
            try {
                System.out.print("Idade: ");
                idade = scanner.nextInt();
                scanner.nextLine();

                if (idade < 0) {
                    System.out.println("A idade não pode ser negativa!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                scanner.nextLine();
                idade = -1;
            }
        }

        List<String> hobbies = new ArrayList<>();
        String resposta = "s";

        do {
            System.out.print("Digite um hobbie: ");
            String hobbie = scanner.nextLine().trim();

            if (!hobbie.isEmpty()) {
                hobbies.add(hobbie);
            } else {
                System.out.println("Hobbie não pode ser vazio!");
                continue;
            }
            System.out.print("Deseja adicionar outro hobbie? (s/n): ");
            resposta = scanner.nextLine();

        } while (resposta.equalsIgnoreCase("s"));
        return new Pessoa(nome, idade, hobbies);
    }

    public void listarPessoas(List<Pessoa> pessoas) {
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa);
        }
    }

    public List<Pessoa> buscarPorNome(List<Pessoa> pessoas, String nome) {
        List<Pessoa> resultados = new ArrayList<>();
        for (Pessoa p : pessoas) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultados.add(p);
            }
        }
        return resultados;
    }
}
//
//    public void adicionarHobbies(Pessoa pessoa, List<String> hobbies) {
//        pessoa.setHobbies(hobbies);