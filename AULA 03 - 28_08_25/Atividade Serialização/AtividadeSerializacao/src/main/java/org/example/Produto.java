package org.example;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private double valorUnitario;
    private double quantidade;
    private double total;

    public Produto() {
    }

    public Produto(String nome, double valorUnitario, double quantidade, double total) {
        this.nome = nome;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.total = total;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return this.quantidade*this.valorUnitario;
    }

    @Override
    public String toString() {
        return "Nome do produto: " + nome + " | " + String.format("Valor unitário: R$%.2f", valorUnitario) + " | Quantidade: " + quantidade +
                String.format(" | Valor Total: R$%.2f", getTotal());
    }
}
