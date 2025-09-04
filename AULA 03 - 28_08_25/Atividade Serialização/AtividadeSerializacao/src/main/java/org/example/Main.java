package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        List<Produto> produtos = new ArrayList<>();

        produtos = ProdutoDAO.carregar();

        String resposta;
        do {
            System.out.println("Deseja cadastrar um novo produto? Digite 's' para sair ou qualquer outra tecla para continuar");
            resposta = input.nextLine();

            if (resposta.equalsIgnoreCase("s")){
                System.out.println("Encerrando o programa...");
                break;
            }

            Produto produto = new Produto();
            System.out.println("Nome: ");
            String nome = input.nextLine();
            produto.setNome(nome);

            System.out.println("Valor Unitário: R$");
            double valor = input.nextDouble();
            produto.setValorUnitario(valor);

            System.out.println("Quantidade: ");
            double quantidade = input.nextDouble();
            produto.setQuantidade(quantidade);

            input.nextLine(); //Consumir a quebra de linha deixada

            produtos.add(produto);
        } while (true);
        ProdutoDAO.salvar(produtos);
        List<Produto> produtosLidos = ProdutoDAO.carregar();

        for (Produto p : produtosLidos) {
            System.out.println(p);
        }

    }
}