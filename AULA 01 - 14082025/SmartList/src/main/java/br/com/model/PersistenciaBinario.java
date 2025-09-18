package br.com.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaBinario implements PersistenciaStrategy {
    @Override
    public void salvar(List<Produto> produtos, String caminhoArquivo) {
        if (!produtos.isEmpty()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
                oos.writeObject(produtos);
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Lista vazia!");
        }
    }

    @Override
    public List<Produto> carregar(String caminhoArquivo) {
        List<Produto> produtos = new ArrayList<>();
        produtos.clear();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            produtos = (List<Produto>) ois.readObject();
            return produtos;
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            return null;
        }
    }
}
