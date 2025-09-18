package br.com.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaJSON implements PersistenciaStrategy {
    @Override
    public void salvar(List<Produto> produtos, String caminhoArquivo) {
        if (!produtos.isEmpty()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Formata o JSON para ser legível
                objectMapper.writeValue(new File(caminhoArquivo), produtos);
            } catch (IOException e) {
                System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Lista vazia!");
        }
    }

    @Override
    public List<Produto> carregar(String caminhoArquivo) {
        try {
            List<Produto> produtos = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            //getTypeFactory(): acessa o TypeFactory, que é responsável por construir tipos genéricos e complexos que Jackson não consegue inferir automaticamente (como listas, mapas...)
            //constructCollectionType(): cria um tipo genérico que representa uma coleção (List) de elementos do tipo Produto.
            produtos = objectMapper.readValue(new File(caminhoArquivo), objectMapper.getTypeFactory().constructCollectionType(List.class, Produto.class));
            return produtos;
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            return null;
        }
    }
}
