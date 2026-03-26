package br.edu.utfpr.model;

import br.edu.utfpr.enums.Gravidade;
import br.edu.utfpr.enums.TipoOcorrencia;
import jakarta.persistence.*;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDate;

@Entity
@Table(name = "ocorrencias_seguranca")
public class OcorrenciaSeguranca implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ocorrencia")
    private TipoOcorrencia tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabalhador_id")
    private Trabalhador trabalhador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canteiro_id", nullable = false)
    private Canteiro canteiro;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "local", nullable = false)
    private String local;

    @Column(name = "causas", nullable = false)
    private String causas;

    @Column(name = "medidas_corretivas", nullable = false)
    private String medidasCorretivas;

    @Enumerated(EnumType.STRING)
    @Column(name = "gravidade")
    private Gravidade gravidade;

    public OcorrenciaSeguranca() {
    }

    public OcorrenciaSeguranca(TipoOcorrencia tipo, Trabalhador trabalhador, Canteiro canteiro, LocalDate data,
                               String descricao, String local, String causas, String medidasCorretivas, Gravidade gravidade) {
        this.tipo = tipo;
        this.trabalhador = trabalhador;
        this.canteiro = canteiro;
        this.data = data;
        this.descricao = descricao;
        this.local = local;
        this.causas = causas;
        this.medidasCorretivas = medidasCorretivas;
        this.gravidade = gravidade;
    }

    public Long getId() {
        return id;
    }

    public TipoOcorrencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoOcorrencia tipo) {
        this.tipo = tipo;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCausas() {
        return causas;
    }

    public void setCausas(String causas) {
        this.causas = causas;
    }

    public String getMedidasCorretivas() {
        return medidasCorretivas;
    }

    public void setMedidasCorretivas(String medidasCorretivas) {
        this.medidasCorretivas = medidasCorretivas;
    }

    public Gravidade getGravidade() {
        return gravidade;
    }

    public void setGravidade(Gravidade gravidade) {
        this.gravidade = gravidade;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OcorrenciaSeguranca{");
        sb.append("id=").append(id);
        sb.append(", tipo=").append(tipo);
        sb.append(", trabalhador=").append(trabalhador);
        sb.append(", canteiro=").append(canteiro);
        sb.append(", data=").append(data);
        sb.append(", descricao='").append(descricao).append('\'');
        sb.append(", local='").append(local).append('\'');
        sb.append(", causas='").append(causas).append('\'');
        sb.append(", medidasCorretivas='").append(medidasCorretivas).append('\'');
        sb.append(", gravidade=").append(gravidade);
        sb.append('}');
        return sb.toString();
    }
}
