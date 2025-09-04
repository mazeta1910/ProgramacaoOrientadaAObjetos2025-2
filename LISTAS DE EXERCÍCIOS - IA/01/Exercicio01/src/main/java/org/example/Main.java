package org.example;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write("[INFO] Sistema iniciado\n");
            writer.write("[ERROR] Falha na conexão\n");
            writer.write("[INFO] Usuário logado\n");
            writer.write("[WARNING] Memória insuficiente\n");
            writer.write("[INFO] Operação concluída\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
                FileWriter writer = new FileWriter("erros.txt");
                FileWriter writer2 = new FileWriter("informacoes.txt")) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("[ERROR]")) {
                    writer.write(linha + "\n");
                } else if (linha.contains("[INFO]")) {
                    writer2.write(linha + "\n");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}