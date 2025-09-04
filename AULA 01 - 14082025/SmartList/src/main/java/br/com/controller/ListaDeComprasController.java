package br.com.controller;

import br.com.model.ListaDeCompras;
import br.com.view.ListaDeComprasView;
import br.com.model.Produto;

public class ListaDeComprasController {
    private ListaDeCompras model;
    private ListaDeComprasView view;

    public ListaDeComprasController(ListaDeCompras model, ListaDeComprasView view) {
        this.model = model;
        this.view = view;
    }

    public void iniciar() {
        int opcao;
        do {
            view.exibirMenu();
            opcao = view.lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
    }

    private void salvarEmAqrTexto() {
        model.salvarEmArquivoTexto("lista_compras.txt"); //ou "D:/dev/lista_compras.txt"

    }

    private void carregarDeArqTexto() {
        model.carregarDeArquivoTexto("lista_compras.txt"); //ou "D:/dev/lista_compras.txt"

    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarProduto();
                break;
            case 2:
                removerProduto();
                break;
            case 3:
                exibirLista();
                break;
            case 4:
                salvarEmAqrTexto();
                break;
            case 5:
                carregarDeArqTexto();
                break;
            case 0:
                view.exibirMensagem("Saindo...");
                break;
            default:
                view.exibirMensagem("Opção inválida!");
        }
    }

    private void adicionarProduto() {
        String nome = view.lerNomeProduto();
        int quantidade = view.lerQuantidade();
        double preco = view.lerPreco();
        model.adicionarProduto(new Produto(nome, quantidade, preco));
    }

    private void removerProduto() {
        String nome = view.lerNomeProduto();
        model.removerProduto(nome);
    }

    private void exibirLista() {
        view.exibirMensagem(model.toString());
    }
}