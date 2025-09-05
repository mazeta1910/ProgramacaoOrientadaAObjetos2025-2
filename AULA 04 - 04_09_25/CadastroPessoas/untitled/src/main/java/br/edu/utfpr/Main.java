package br.edu.utfpr;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ManipuladorJSON manipulador = new ManipuladorJSON();
        PessoaService service = new PessoaService();

        // Carregar dados existentes
        List<Pessoa> pessoas = manipulador.carregarPessoas();

        //Trabalhando com a lista (cadastrar, listar, buscar)
        String continuar = "s";
        while (continuar.equalsIgnoreCase("s")) {
            Pessoa novaPessoa = service.coletarDadosPessoa(input);
            pessoas.add(novaPessoa);

            System.out.println("Deseja cadastrar outra pessoa? (s/n)");
            continuar = input.nextLine();
        }

        manipulador.salvarPessoas(pessoas);
        input.close();
    }
}