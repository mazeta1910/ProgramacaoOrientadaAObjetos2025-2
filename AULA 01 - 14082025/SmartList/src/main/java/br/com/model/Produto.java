package br.com.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private int quantidade;
    private double preco;

    public Produto() {

    }

    public Produto(String nome, int quantidade, double preco) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - " + quantidade + " unidades - " + String.format("R$ %.2f \n", preco);
    }
}
