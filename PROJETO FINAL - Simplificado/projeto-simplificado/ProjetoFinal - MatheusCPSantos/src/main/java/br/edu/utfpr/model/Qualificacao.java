package br.edu.utfpr.model;

import br.edu.utfpr.enums.TipoQualificacao;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "qualificacoes")

public class Qualificacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_qualificacao")
    private TipoQualificacao tipo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "dataObtencao", nullable = false)
    private LocalDate dataObtencao;

    @Column(name = "dataValidade", nullable = false)
    private LocalDate dataValidade;

    @Column(name = "instituicao", nullable = false)
    private String instituicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalhador_id", nullable = false)
    private Trabalhador trabalhador;

    public Qualificacao() {
    }

    public Qualificacao(TipoQualificacao tipo, String nome, LocalDate dataObtencao, LocalDate dataValidade, String instituicao, Trabalhador trabalhador) {
        this.tipo = tipo;
        this.nome = nome;
        this.dataObtencao = dataObtencao;
        this.dataValidade = dataValidade;
        this.instituicao = instituicao;
        this.trabalhador = trabalhador;
    }

    public Long getId() {
        return id;
    }

    public TipoQualificacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoQualificacao tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataObtencao() {
        return dataObtencao;
    }

    public void setDataObtencao(LocalDate dataObtencao) {
        this.dataObtencao = dataObtencao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public Trabalhador getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Qualificacao{");
        sb.append("id=").append(id);
        sb.append(", tipo=").append(tipo);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", dataObtencao=").append(dataObtencao);
        sb.append(", dataValidade=").append(dataValidade);
        sb.append(", instituicao='").append(instituicao).append('\'');
        sb.append(", trabalhador=").append(trabalhador);
        sb.append('}');
        return sb.toString();
    }
}
