package br.com.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDeCompras {

    private List<Produto> produtos;

    public ListaDeCompras() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(String nome) {
        this.produtos.removeIf(p -> p.getNome().equalsIgnoreCase(nome));
    }

    public void salvarEmArquivoTexto(String nomeArquivo) {
        if (!produtos.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, false))) {
                for (Produto produto : produtos) {
                    writer.write(produto.getNome() + " - " + produto.getQuantidade() + " - " + produto.getPreco());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Lista vazia!");
        }
    }


    public void carregarDeArquivoTexto(String nomeArquivo) {
        produtos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(" - ");
                produtos.add(new Produto(partes[0], Integer.parseInt(partes[1]), Double.parseDouble(partes[2])));
            }
            System.out.println("Lista do Arquivo de Texto");
            System.out.println(this.toString());

        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        if (produtos.isEmpty()) {
            return "A lista está vazia!";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- LISTA DE PRODUTOS ---\n");
        /*for (int i = 0; i < produtos.size(); i++) {
            sb.append((i + 1)).append(". ").append(produtos.get(i).toString());
        }*/
        for (Produto p : produtos) {
            sb.append(p.toString());
        }
        return sb.toString();
    }

/*    public double valorTotalLista(){
        double valorLista = 0;
        for (Produto p : produtos){
            p.getPreco();
            valorLista += p.getPreco();
        }
        return valorLista;
    }*/
}
