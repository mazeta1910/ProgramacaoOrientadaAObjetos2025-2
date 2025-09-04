package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static List<Produto> carregar() {
        try (FileInputStream fis = new FileInputStream("produtos.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (List<Produto>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Arquivo não existe → retorna lista vazia
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e); // Outros erros → propaga exceção
        }
    }

    public static void salvar(List<Produto> lista) {
        try (FileOutputStream fos = new FileOutputStream("produtos.dat");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
