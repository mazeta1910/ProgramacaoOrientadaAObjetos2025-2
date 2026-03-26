package br.edu.utfpr.model;

import br.edu.utfpr.enums.TipoContrato;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "trabalhadores")
public class Trabalhador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "cpf", unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(name = "funcao")
    private String funcao;

    @Column(name = "numero_crea", length = 20)
    private String numeroCREA;

    @Column(name = "numero_registro_profissional", length = 50)
    private String numeroRegistroProfissional;

    @Column(name = "especialidade", length = 100)
    private String especialidade;

    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contrato")
    private TipoContrato tipoContrato;

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistoricoAlocacao> historicoAlocacoes = new ArrayList<>();

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Qualificacao> qualificacoes = new ArrayList<>();

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegistroJornada> registrosJornada = new ArrayList<>();

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OcorrenciaSeguranca> ocorrenciasSeguranca = new ArrayList<>();

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EPI> episEntregues = new ArrayList<>();

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegistroSaude> registrosSaude = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "canteiro_atual_id")
    private Canteiro canteiroAtual;

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AvaliacaoCondicoes> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "trabalhador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegistroProdutividade> registrosProdutividade = new ArrayList<>();


    public Trabalhador() {
    }

    public Trabalhador(String nomeCompleto, String cpf, String funcao, LocalDate dataContratacao, TipoContrato tipoContrato, Canteiro canteiroAtual) {
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.funcao = funcao;
        this.dataContratacao = dataContratacao;
        this.tipoContrato = tipoContrato;
        this.canteiroAtual = canteiroAtual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getNumeroCREA() {
        return numeroCREA;
    }

    public void setNumeroCREA(String numeroCREA) {
        this.numeroCREA = numeroCREA;
    }

    public String getNumeroRegistroProfissional() {
        return numeroRegistroProfissional;
    }

    public void setNumeroRegistroProfissional(String numeroRegistroProfissional) {
        this.numeroRegistroProfissional = numeroRegistroProfissional;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Canteiro getCanteiroAtual() {
        return canteiroAtual;
    }

    public void setCanteiroAtual(Canteiro canteiroAtual) {
        this.canteiroAtual = canteiroAtual;
    }

    public List<HistoricoAlocacao> getHistoricoAlocacoes() {
        return historicoAlocacoes;
    }

    public void setHistoricoAlocacoes(List<HistoricoAlocacao> historicoAlocacoes) {
        this.historicoAlocacoes = historicoAlocacoes;
    }

    public List<Qualificacao> getQualificacoes() {
        return qualificacoes;
    }

    public void setQualificacoes(List<Qualificacao> qualificacoes) {
        this.qualificacoes = qualificacoes;
    }

    public List<RegistroJornada> getRegistrosJornada() {
        return registrosJornada;
    }

    public void setRegistrosJornada(List<RegistroJornada> registrosJornada) {
        this.registrosJornada = registrosJornada;
    }

    public List<OcorrenciaSeguranca> getOcorrenciasSeguranca() {
        return ocorrenciasSeguranca;
    }

    public void setOcorrenciasSeguranca(List<OcorrenciaSeguranca> ocorrenciasSeguranca) {
        this.ocorrenciasSeguranca = ocorrenciasSeguranca;
    }

    public List<EPI> getEpisEntregues() {
        return episEntregues;
    }

    public void setEpisEntregues(List<EPI> episEntregues) {
        this.episEntregues = episEntregues;
    }

    public List<RegistroSaude> getRegistrosSaude() {
        return registrosSaude;
    }

    public void setRegistrosSaude(List<RegistroSaude> registrosSaude) {
        this.registrosSaude = registrosSaude;
    }

    public List<AvaliacaoCondicoes> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<AvaliacaoCondicoes> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<RegistroProdutividade> getRegistrosProdutividade() {
        return registrosProdutividade;
    }

    public void setRegistrosProdutividade(List<RegistroProdutividade> registrosProdutividade) {
        this.registrosProdutividade = registrosProdutividade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nomeCompleto).append("\n");
        sb.append("CPF: ").append(cpf).append("\n");
        sb.append("Função: ").append(funcao).append("\n");

        if (numeroCREA != null && !numeroCREA.isEmpty()) {
            sb.append("CREA: ").append(numeroCREA).append("\n");
        }

        if (numeroRegistroProfissional != null && !numeroRegistroProfissional.isEmpty()) {
            sb.append("Registro Profissional: ").append(numeroRegistroProfissional).append("\n");
        }

        if (especialidade != null && !especialidade.isEmpty()) {
            sb.append("Especialidade: ").append(especialidade).append("\n");
        }

        sb.append("Data Contratação: ").append(dataContratacao).append("\n");
        sb.append("Tipo Contrato: ").append(tipoContrato).append("\n");

        if (canteiroAtual != null) {
            sb.append("Canteiro Atual: ").append(canteiroAtual.getNome()).append("\n");
        }

        return sb.toString();
    }
}

