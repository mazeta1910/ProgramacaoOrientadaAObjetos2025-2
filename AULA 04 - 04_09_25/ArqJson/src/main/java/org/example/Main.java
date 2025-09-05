package org.example;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Carro> carros = new ArrayList<>();

        Carro meuCarro = new Carro("Toyota", "Yaris", 2021, "Cinza");
        Carro meuCarro1 = new Carro("Honda", "Civic", 2021, "Cinza");
        Carro meuCarro2 = new Carro("Volkswagen", "Gol", 2023, "Branco");

        carros.add(meuCarro);
        carros.add(meuCarro1);
        carros.add(meuCarro2);

        System.out.println("-- Serializando o objeto Carro para Json. --");
        try {
            File arquivoJson = new File("meucarro.json");
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(arquivoJson, carros);
            System.out.println("Objetos salvo com sucesso! Seu diretório: " + arquivoJson.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo.");
        }

        System.out.println("--- Desserializando Json para o Objeto Carro ---");
        try {
            List<Carro> carrosDesserializados = new ArrayList<>();
            File arquivoParaler = new File("meucarro.json");
            if (arquivoParaler.exists()) {
                //Carro carroDoArquivo = objectMapper.readValue(arquivoParaler, Carro.class);
                carrosDesserializados = objectMapper.readValue(arquivoParaler, objectMapper.getTypeFactory().constructCollectionType(List.class, Carro.class));
                System.out.println("Objeto lido com sucesso.");
                System.out.println(carrosDesserializados);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
