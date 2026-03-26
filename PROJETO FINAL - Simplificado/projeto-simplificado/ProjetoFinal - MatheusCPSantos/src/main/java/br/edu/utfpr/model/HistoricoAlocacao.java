package br.edu.utfpr.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "historico_alocacoes")
public class HistoricoAlocacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalhador_id", nullable = false)
    private Trabalhador trabalhador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canteiro_id", nullable = false)
    private Canteiro canteiro;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Column(name = "funcao_exercida", nullable = false)
    private String funcaoExercida;

    public HistoricoAlocacao() {
    }

    public HistoricoAlocacao(Trabalhador trabalhador, Canteiro canteiro, LocalDate dataInicio, LocalDate dataFim, String funcaoExercida) {
        this.trabalhador = trabalhador;
        this.canteiro = canteiro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.funcaoExercida = funcaoExercida;
    }

    public Long getId() {
        return id;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getFuncaoExercida() {
        return funcaoExercida;
    }

    public void setFuncaoExercida(String funcaoExercida) {
        this.funcaoExercida = funcaoExercida;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HistoricoAlocacao{");
        sb.append("id=").append(id);
        sb.append(", trabalhador=").append(trabalhador);
        sb.append(", canteiro=").append(canteiro);
        sb.append(", dataInicio=").append(dataInicio);
        sb.append(", dataFim=").append(dataFim);
        sb.append(", funcaoExercida='").append(funcaoExercida).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
