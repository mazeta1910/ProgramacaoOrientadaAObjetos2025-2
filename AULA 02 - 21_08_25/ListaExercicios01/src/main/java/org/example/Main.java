package org.example;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*
        File file = new File("arquivo1.txt");
        File file2 = new File("arquivo2.txt");
        */
        int num1 = 5;
        int resultado = 0;

        // GRAVAÇÃO DE DADOS //
        try (FileWriter writer = new FileWriter("arquivo1.txt")) {
            for (int i = 0; i <= 10; i++) {
                resultado = num1 * i;
                writer.write("5 * " + i + " = " + resultado + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                BufferedReader reader = new BufferedReader(new FileReader("arquivo1.txt"));

                FileWriter writer = new FileWriter("arquivo2.txt")
        ) {
            String linhaTabuada;
            while ((linhaTabuada = reader.readLine()) != null) {
                writer.write(linhaTabuada + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}