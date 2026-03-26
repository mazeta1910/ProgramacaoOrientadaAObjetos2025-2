package br.edu.utfpr.model;

import br.edu.utfpr.enums.StatusEPI;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "epis")
public class EPI implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "data_entrega", nullable = false)
    private LocalDate dataEntrega;

    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;

    @Column(name = "data_validade", nullable = false)
    private LocalDate dataValidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalhador_id", nullable = false)
    private Trabalhador trabalhador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canteiro_id")
    private Canteiro canteiro;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_epi")
    private StatusEPI statusEPI;

    public EPI() {
    }

    public EPI(String tipo, String nome, String marca, LocalDate dataEntrega, LocalDate dataDevolucao, LocalDate dataValidade,
               Trabalhador trabalhador, Canteiro canteiro, StatusEPI statusEPI) {
        this.tipo = tipo;
        this.nome = nome;
        this.marca = marca;
        this.dataEntrega = dataEntrega;
        this.dataDevolucao = dataDevolucao;
        this.dataValidade = dataValidade;
        this.trabalhador = trabalhador;
        this.canteiro = canteiro;
        this.statusEPI = statusEPI;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public Trabalhador getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }

    public Canteiro getCanteiro() {
        return canteiro;
    }

    public void setCanteiro(Canteiro canteiro) {
        this.canteiro = canteiro;
    }

    public StatusEPI getStatusEPI() {
        return statusEPI;
    }

    public void setStatusEPI(StatusEPI statusEPI) {
        this.statusEPI = statusEPI;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EPI{");
        sb.append("id=").append(id);
        sb.append(", tipo='").append(tipo).append('\'');
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", marca='").append(marca).append('\'');
        sb.append(", dataEntrega=").append(dataEntrega);
        sb.append(", dataDevolucao=").append(dataDevolucao);
        sb.append(", dataValidade=").append(dataValidade);
        sb.append(", trabalhador=").append(trabalhador);
        sb.append(", canteiro=").append(canteiro);
        sb.append(", statusEPI=").append(statusEPI);
        sb.append('}');
        return sb.toString();
    }
}
