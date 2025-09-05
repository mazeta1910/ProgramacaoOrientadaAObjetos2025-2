package br.edu.utfpr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//b) Crie uma classe ManipuladorJSON com os métodos:
//
//void salvarPessoas(List<Pessoa> pessoas) — salva os dados em um arquivo JSON;
//List<Pessoa> carregarPessoas() — lê os dados do arquivo JSON.

public class ManipuladorJSON {
    ObjectMapper objectMapper = new ObjectMapper();

    public void salvarPessoas(List<Pessoa> pessoas) {

        try {
            File arquivoJson = new File("listapessoas.json");
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(arquivoJson, pessoas);
            System.out.println("Lista salva com sucesso! Seu diretório: " + arquivoJson.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo!");
        }
    }

    public List<Pessoa> carregarPessoas() {
        try {
            File arquivoParaLeitura = new File("listapessoas.json");
            if (arquivoParaLeitura.exists()) {
                List<Pessoa> carregamentoPessoas = objectMapper.readValue(arquivoParaLeitura, objectMapper.getTypeFactory().constructCollectionType(List.class, Pessoa.class));
                System.out.println("Lista lida com sucesso!");
                return carregamentoPessoas;
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar arquivo: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}