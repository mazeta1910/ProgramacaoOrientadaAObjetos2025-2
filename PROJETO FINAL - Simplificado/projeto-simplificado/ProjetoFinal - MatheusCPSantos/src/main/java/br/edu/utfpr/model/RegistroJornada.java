package br.edu.utfpr.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "registros_jornadas")
public class RegistroJornada implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalhador_id", nullable = false)
    private Trabalhador trabalhador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canteiro_id", nullable = false)
    private Canteiro canteiro;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "horas_normais", nullable = false)
    private Double horasNormais;

    @Column(name = "horas_extras", nullable = false)
    private Double horasExtras;

    @Column(name = "falta", nullable = false)
    private Boolean falta;

    @Column(name = "atestado")
    private Boolean atestado;

    @Column(name = "afastamento")
    private Boolean afastamento;

    @Column(name = "motivo_afastamento")
    private String motivoAfastamento;

    @Column(name = "observacoes")
    private String observacoes;

    public RegistroJornada() {
    }


    public RegistroJornada(Trabalhador trabalhador, Canteiro canteiro, LocalDate data, Double horasNormais, Double horasExtras,
                           Boolean falta, Boolean atestado, Boolean afastamento, String motivoAfastamento, String observacoes) {
        this.trabalhador = trabalhador;
        this.canteiro = canteiro;
        this.data = data;
        this.horasNormais = horasNormais;
        this.horasExtras = horasExtras;
        this.falta = falta;
        this.atestado = atestado;
        this.afastamento = afastamento;
        this.motivoAfastamento = motivoAfastamento;
        this.observacoes = observacoes;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getHorasNormais() {
        return horasNormais;
    }

    public void setHorasNormais(Double horasNormais) {
        this.horasNormais = horasNormais;
    }

    public Double getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Double horasExtras) {
        this.horasExtras = horasExtras;
    }

    public Boolean getFalta() {
        return falta;
    }

    public void setFalta(Boolean falta) {
        this.falta = falta;
    }

    public Boolean getAtestado() {
        return atestado;
    }

    public void setAtestado(Boolean atestado) {
        this.atestado = atestado;
    }

    public Boolean getAfastamento() {
        return afastamento;
    }

    public void setAfastamento(Boolean afastamento) {
        this.afastamento = afastamento;
    }

    public String getMotivoAfastamento() {
        return motivoAfastamento;
    }

    public void setMotivoAfastamento(String motivoAfastamento) {
        this.motivoAfastamento = motivoAfastamento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void validarRegrasNegocio() {

        if (Boolean.TRUE.equals(afastamento) && (motivoAfastamento == null || motivoAfastamento.trim().isEmpty())) {
            throw new IllegalArgumentException("Motivo do afastamento é obrigatório quando há afastamento.");
        }

        if (Boolean.TRUE.equals(falta) && Boolean.TRUE.equals(atestado)) {
            throw new IllegalArgumentException("Não pode ter falta e atestado simultaneamente.");
        }

        if (horasNormais < 0 || horasExtras < 0) {
            throw new IllegalArgumentException("Horas não podem ser negativas.");
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegistroJornada{");
        sb.append("id=").append(id);
        sb.append(", trabalhador=").append(trabalhador);
        sb.append(", canteiro=").append(canteiro);
        sb.append(", data=").append(data);
        sb.append(", horasNormais=").append(horasNormais);
        sb.append(", horasExtras=").append(horasExtras);
        sb.append(", falta=").append(falta);
        sb.append(", atestado=").append(atestado);
        sb.append(", afastamento=").append(afastamento);
        sb.append(", motivoAfastamento='").append(motivoAfastamento).append('\'');
        sb.append(", observacoes='").append(observacoes).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
