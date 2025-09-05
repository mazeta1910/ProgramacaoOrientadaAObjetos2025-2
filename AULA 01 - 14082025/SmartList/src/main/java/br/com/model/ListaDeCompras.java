package br.com.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

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

    public void salvarEmArquivoBinario(String nomeArquivo) {
        if (!produtos.isEmpty()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
                oos.writeObject(produtos);
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Lista vazia!");
        }
    }

    @SuppressWarnings("unchecked")
    // Suprime avisos de operações não verificadas, esta anotação é usada para silenciar aviso do compilador.
    public void carregarDeArquivoBinario(String nomeArquivo) {
        produtos.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            produtos = (List<Produto>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    public void salvarEmArquivoJson(String nomeArquivo) {
        if (!produtos.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Formata o JSON para ser legível
                objectMapper.writeValue(new File(nomeArquivo), produtos);
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Lista vazia!");
        }

    }

    public void carregarDeArquivoJson(String nomeArquivo) {
        produtos.clear();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            //getTypeFactory(): acessa o TypeFactory, que é responsável por construir tipos genéricos e complexos que Jackson não consegue inferir automaticamente (como listas, mapas...)
            //constructCollectionType(): cria um tipo genérico que representa uma coleção (List) de elementos do tipo Produto.
            produtos = objectMapper.readValue(new File(nomeArquivo), objectMapper.getTypeFactory().constructCollectionType(List.class, Produto.class));
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
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
