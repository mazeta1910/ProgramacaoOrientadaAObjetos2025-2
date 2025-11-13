# Projeto POO - Classes Service (20 arquivos)

**Pacote:** `br.edu.utfpr.service`

---

## 1. TrabalhadorService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.TrabalhadorDao;
import br.edu.utfpr.model.Trabalhador;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

/**
 * Service para gerenciar operações de negócio relacionadas a Trabalhador.
 */
public class TrabalhadorService {
    
    private TrabalhadorDao trabalhadorDao;

    public TrabalhadorService(EntityManager em) {
        trabalhadorDao = new TrabalhadorDao(em);
    }

    public void inserir(Trabalhador trabalhador) {
        trabalhadorDao.cadastrar(trabalhador);
    }

    public void alterar(Trabalhador trabalhador) {
        trabalhadorDao.atualizar(trabalhador);
    }

    public void excluir(Trabalhador trabalhador) {
        trabalhadorDao.remover(trabalhador);
    }

    public Trabalhador buscarPorId(Long id) {
        return trabalhadorDao.buscarPorId(id);
    }

    public List<Trabalhador> buscarTodos() {
        return trabalhadorDao.buscarTodos();
    }

    public Optional<Trabalhador> buscarPorCpf(String cpf) {
        return trabalhadorDao.buscarPorCpf(cpf);
    }

    public List<Trabalhador> buscarPorStatus(String status) {
        return trabalhadorDao.buscarPorStatus(status);
    }

    public List<Trabalhador> buscarPorCanteiro(Long canteiroId) {
        return trabalhadorDao.buscarPorCanteiro(canteiroId);
    }

    public List<Trabalhador> buscarPorNome(String nome) {
        return trabalhadorDao.buscarPorNome(nome);
    }
}
```

---

## 2. CanteiroService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.CanteiroDao;
import br.edu.utfpr.model.Canteiro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CanteiroService {
    
    private CanteiroDao canteiroDao;

    public CanteiroService(EntityManager em) {
        canteiroDao = new CanteiroDao(em);
    }

    public void inserir(Canteiro canteiro) {
        canteiroDao.cadastrar(canteiro);
    }

    public void alterar(Canteiro canteiro) {
        canteiroDao.atualizar(canteiro);
    }

    public void excluir(Canteiro canteiro) {
        canteiroDao.remover(canteiro);
    }

    public Canteiro buscarPorId(Long id) {
        return canteiroDao.buscarPorId(id);
    }

    public List<Canteiro> buscarTodos() {
        return canteiroDao.buscarTodos();
    }

    public List<Canteiro> buscarPorProjeto(Long projetoId) {
        return canteiroDao.buscarPorProjeto(projetoId);
    }

    public List<Canteiro> buscarPorNome(String nome) {
        return canteiroDao.buscarPorNome(nome);
    }

    public List<Canteiro> buscarAtivos() {
        return canteiroDao.buscarAtivos();
    }
}
```

---

## 3. ProjetoService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.ProjetoDao;
import br.edu.utfpr.model.Projeto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProjetoService {
    
    private ProjetoDao projetoDao;

    public ProjetoService(EntityManager em) {
        projetoDao = new ProjetoDao(em);
    }

    public void inserir(Projeto projeto) {
        projetoDao.cadastrar(projeto);
    }

    public void alterar(Projeto projeto) {
        projetoDao.atualizar(projeto);
    }

    public void excluir(Projeto projeto) {
        projetoDao.remover(projeto);
    }

    public Projeto buscarPorId(Long id) {
        return projetoDao.buscarPorId(id);
    }

    public List<Projeto> buscarTodos() {
        return projetoDao.buscarTodos();
    }

    public List<Projeto> buscarPorNome(String nome) {
        return projetoDao.buscarPorNome(nome);
    }

    public List<Projeto> buscarAtivos() {
        return projetoDao.buscarAtivos();
    }

    public List<Projeto> buscarPorLocalizacao(String localizacao) {
        return projetoDao.buscarPorLocalizacao(localizacao);
    }
}
```

---

## 4. AvaliacaoCondicoesService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.AvaliacaoCondicoesDao;
import br.edu.utfpr.model.AvaliacaoCondicoes;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class AvaliacaoCondicoesService {
    
    private AvaliacaoCondicoesDao avaliacaoCondicoesDao;

    public AvaliacaoCondicoesService(EntityManager em) {
        avaliacaoCondicoesDao = new AvaliacaoCondicoesDao(em);
    }

    public void inserir(AvaliacaoCondicoes avaliacaoCondicoes) {
        avaliacaoCondicoesDao.cadastrar(avaliacaoCondicoes);
    }

    public void alterar(AvaliacaoCondicoes avaliacaoCondicoes) {
        avaliacaoCondicoesDao.atualizar(avaliacaoCondicoes);
    }

    public void excluir(AvaliacaoCondicoes avaliacaoCondicoes) {
        avaliacaoCondicoesDao.remover(avaliacaoCondicoes);
    }

    public AvaliacaoCondicoes buscarPorId(Long id) {
        return avaliacaoCondicoesDao.buscarPorId(id);
    }

    public List<AvaliacaoCondicoes> buscarTodos() {
        return avaliacaoCondicoesDao.buscarTodos();
    }

    public List<AvaliacaoCondicoes> buscarPorTrabalhador(Long trabalhadorId) {
        return avaliacaoCondicoesDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<AvaliacaoCondicoes> buscarPorCanteiro(Long canteiroId) {
        return avaliacaoCondicoesDao.buscarPorCanteiro(canteiroId);
    }

    public List<AvaliacaoCondicoes> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return avaliacaoCondicoesDao.buscarPorPeriodo(dataInicio, dataFim);
    }

    public List<AvaliacaoCondicoes> buscarComPontuacaoBaixa(Double pontuacaoMaxima) {
        return avaliacaoCondicoesDao.buscarComPontuacaoBaixa(pontuacaoMaxima);
    }
}
```

---

## 5. AlertaService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.AlertaDao;
import br.edu.utfpr.enums.Prioridade;
import br.edu.utfpr.enums.TipoAlerta;
import br.edu.utfpr.model.Alerta;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlertaService {
    
    private AlertaDao alertaDao;

    public AlertaService(EntityManager em) {
        alertaDao = new AlertaDao(em);
    }

    public void inserir(Alerta alerta) {
        alertaDao.cadastrar(alerta);
    }

    public void alterar(Alerta alerta) {
        alertaDao.atualizar(alerta);
    }

    public void excluir(Alerta alerta) {
        alertaDao.remover(alerta);
    }

    public Alerta buscarPorId(Long id) {
        return alertaDao.buscarPorId(id);
    }

    public List<Alerta> buscarTodos() {
        return alertaDao.buscarTodos();
    }

    public List<Alerta> buscarPorCanteiro(Long canteiroId) {
        return alertaDao.buscarPorCanteiro(canteiroId);
    }

    public List<Alerta> buscarNaoResolvidos() {
        return alertaDao.buscarNaoResolvidos();
    }

    public List<Alerta> buscarPorPrioridade(Prioridade prioridade) {
        return alertaDao.buscarPorPrioridade(prioridade);
    }

    public List<Alerta> buscarPorTipo(TipoAlerta tipo) {
        return alertaDao.buscarPorTipo(tipo);
    }

    public List<Alerta> buscarNaoResolvidosPorCanteiro(Long canteiroId) {
        return alertaDao.buscarNaoResolvidosPorCanteiro(canteiroId);
    }
}
```

---

## 6. EPIService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.EPIDao;
import br.edu.utfpr.model.EPI;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EPIService {
    
    private EPIDao epiDao;

    public EPIService(EntityManager em) {
        epiDao = new EPIDao(em);
    }

    public void inserir(EPI epi) {
        epiDao.cadastrar(epi);
    }

    public void alterar(EPI epi) {
        epiDao.atualizar(epi);
    }

    public void excluir(EPI epi) {
        epiDao.remover(epi);
    }

    public EPI buscarPorId(Long id) {
        return epiDao.buscarPorId(id);
    }

    public List<EPI> buscarTodos() {
        return epiDao.buscarTodos();
    }

    public List<EPI> buscarPorTrabalhador(Long trabalhadorId) {
        return epiDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<EPI> buscarPorTipo(String tipo) {
        return epiDao.buscarPorTipo(tipo);
    }

    public List<EPI> buscarVencidos() {
        return epiDao.buscarVencidos();
    }

    public List<EPI> buscarVencendoEm(int dias) {
        return epiDao.buscarVencendoEm(dias);
    }
}
```

---

## 7. FeedbackService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.FeedbackDao;
import br.edu.utfpr.model.Feedback;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class FeedbackService {
    
    private FeedbackDao feedbackDao;

    public FeedbackService(EntityManager em) {
        feedbackDao = new FeedbackDao(em);
    }

    public void inserir(Feedback feedback) {
        feedbackDao.cadastrar(feedback);
    }

    public void alterar(Feedback feedback) {
        feedbackDao.atualizar(feedback);
    }

    public void excluir(Feedback feedback) {
        feedbackDao.remover(feedback);
    }

    public Feedback buscarPorId(Long id) {
        return feedbackDao.buscarPorId(id);
    }

    public List<Feedback> buscarTodos() {
        return feedbackDao.buscarTodos();
    }

    public List<Feedback> buscarPorCanteiro(Long canteiroId) {
        return feedbackDao.buscarPorCanteiro(canteiroId);
    }

    public List<Feedback> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return feedbackDao.buscarPorPeriodo(dataInicio, dataFim);
    }

    public List<Feedback> buscarPorAvaliacao(Integer avaliacao) {
        return feedbackDao.buscarPorAvaliacao(avaliacao);
    }

    public List<Feedback> buscarComAvaliacaoMinima(Integer avaliacaoMinima) {
        return feedbackDao.buscarComAvaliacaoMinima(avaliacaoMinima);
    }
}
```

---

## 8. HistoricoAlocacaoService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.HistoricoAlocacaoDao;
import br.edu.utfpr.model.HistoricoAlocacao;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class HistoricoAlocacaoService {
    
    private HistoricoAlocacaoDao historicoAlocacaoDao;

    public HistoricoAlocacaoService(EntityManager em) {
        historicoAlocacaoDao = new HistoricoAlocacaoDao(em);
    }

    public void inserir(HistoricoAlocacao historicoAlocacao) {
        historicoAlocacaoDao.cadastrar(historicoAlocacao);
    }

    public void alterar(HistoricoAlocacao historicoAlocacao) {
        historicoAlocacaoDao.atualizar(historicoAlocacao);
    }

    public void excluir(HistoricoAlocacao historicoAlocacao) {
        historicoAlocacaoDao.remover(historicoAlocacao);
    }

    public HistoricoAlocacao buscarPorId(Long id) {
        return historicoAlocacaoDao.buscarPorId(id);
    }

    public List<HistoricoAlocacao> buscarTodos() {
        return historicoAlocacaoDao.buscarTodos();
    }

    public List<HistoricoAlocacao> buscarPorTrabalhador(Long trabalhadorId) {
        return historicoAlocacaoDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<HistoricoAlocacao> buscarPorCanteiro(Long canteiroId) {
        return historicoAlocacaoDao.buscarPorCanteiro(canteiroId);
    }

    public List<HistoricoAlocacao> buscarAlocacoesAtivas() {
        return historicoAlocacaoDao.buscarAlocacoesAtivas();
    }

    public List<HistoricoAlocacao> buscarPorPeriodoEntrada(LocalDate dataInicio, LocalDate dataFim) {
        return historicoAlocacaoDao.buscarPorPeriodoEntrada(dataInicio, dataFim);
    }
}
```

---

## 9. MetricaProdutividadeService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.MetricaProdutividadeDao;
import br.edu.utfpr.model.MetricaProdutividade;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class MetricaProdutividadeService {
    
    private MetricaProdutividadeDao metricaProdutividadeDao;

    public MetricaProdutividadeService(EntityManager em) {
        metricaProdutividadeDao = new MetricaProdutividadeDao(em);
    }

    public void inserir(MetricaProdutividade metricaProdutividade) {
        metricaProdutividadeDao.cadastrar(metricaProdutividade);
    }

    public void alterar(MetricaProdutividade metricaProdutividade) {
        metricaProdutividadeDao.atualizar(metricaProdutividade);
    }

    public void excluir(MetricaProdutividade metricaProdutividade) {
        metricaProdutividadeDao.remover(metricaProdutividade);
    }

    public MetricaProdutividade buscarPorId(Long id) {
        return metricaProdutividadeDao.buscarPorId(id);
    }

    public List<MetricaProdutividade> buscarTodos() {
        return metricaProdutividadeDao.buscarTodos();
    }

    public Optional<MetricaProdutividade> buscarPorNome(String nome) {
        return metricaProdutividadeDao.buscarPorNome(nome);
    }

    public List<MetricaProdutividade> buscarAtivas() {
        return metricaProdutividadeDao.buscarAtivas();
    }

    public List<MetricaProdutividade> buscarPorUnidadeMedida(String unidadeMedida) {
        return metricaProdutividadeDao.buscarPorUnidadeMedida(unidadeMedida);
    }
}
```

---

## 10. ODSService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.ODSDao;
import br.edu.utfpr.model.ODS;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ODSService {
    
    private ODSDao odsDao;

    public ODSService(EntityManager em) {
        odsDao = new ODSDao(em);
    }

    public void inserir(ODS ods) {
        odsDao.cadastrar(ods);
    }

    public void alterar(ODS ods) {
        odsDao.atualizar(ods);
    }

    public void excluir(ODS ods) {
        odsDao.remover(ods);
    }

    public ODS buscarPorId(Long id) {
        return odsDao.buscarPorId(id);
    }

    public List<ODS> buscarTodos() {
        return odsDao.buscarTodos();
    }

    public Optional<ODS> buscarPorNumero(Integer numero) {
        return odsDao.buscarPorNumero(numero);
    }

    public List<ODS> buscarPorNome(String nome) {
        return odsDao.buscarPorNome(nome);
    }

    public List<ODS> buscarTodasOrdenadas() {
        return odsDao.buscarTodasOrdenadas();
    }
}
```

---

## 11. OcorrenciaSegurancaService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.OcorrenciaSegurancaDao;
import br.edu.utfpr.enums.Gravidade;
import br.edu.utfpr.model.OcorrenciaSeguranca;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class OcorrenciaSegurancaService {
    
    private OcorrenciaSegurancaDao ocorrenciaSegurancaDao;

    public OcorrenciaSegurancaService(EntityManager em) {
        ocorrenciaSegurancaDao = new OcorrenciaSegurancaDao(em);
    }

    public void inserir(OcorrenciaSeguranca ocorrenciaSeguranca) {
        ocorrenciaSegurancaDao.cadastrar(ocorrenciaSeguranca);
    }

    public void alterar(OcorrenciaSeguranca ocorrenciaSeguranca) {
        ocorrenciaSegurancaDao.atualizar(ocorrenciaSeguranca);
    }

    public void excluir(OcorrenciaSeguranca ocorrenciaSeguranca) {
        ocorrenciaSegurancaDao.remover(ocorrenciaSeguranca);
    }

    public OcorrenciaSeguranca buscarPorId(Long id) {
        return ocorrenciaSegurancaDao.buscarPorId(id);
    }

    public List<OcorrenciaSeguranca> buscarTodos() {
        return ocorrenciaSegurancaDao.buscarTodos();
    }

    public List<OcorrenciaSeguranca> buscarPorTrabalhador(Long trabalhadorId) {
        return ocorrenciaSegurancaDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<OcorrenciaSeguranca> buscarPorCanteiro(Long canteiroId) {
        return ocorrenciaSegurancaDao.buscarPorCanteiro(canteiroId);
    }

    public List<OcorrenciaSeguranca> buscarPorGravidade(Gravidade gravidade) {
        return ocorrenciaSegurancaDao.buscarPorGravidade(gravidade);
    }

    public List<OcorrenciaSeguranca> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return ocorrenciaSegurancaDao.buscarPorPeriodo(dataInicio, dataFim);
    }
}
```

---

## 12. QualificacaoService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.QualificacaoDao;
import br.edu.utfpr.model.Qualificacao;
import jakarta.persistence.EntityManager;

import java.util.List;

public class QualificacaoService {
    
    private QualificacaoDao qualificacaoDao;

    public QualificacaoService(EntityManager em) {
        qualificacaoDao = new QualificacaoDao(em);
    }

    public void inserir(Qualificacao qualificacao) {
        qualificacaoDao.cadastrar(qualificacao);
    }

    public void alterar(Qualificacao qualificacao) {
        qualificacaoDao.atualizar(qualificacao);
    }

    public void excluir(Qualificacao qualificacao) {
        qualificacaoDao.remover(qualificacao);
    }

    public Qualificacao buscarPorId(Long id) {
        return qualificacaoDao.buscarPorId(id);
    }

    public List<Qualificacao> buscarTodos() {
        return qualificacaoDao.buscarTodos();
    }

    public List<Qualificacao> buscarPorTrabalhador(Long trabalhadorId) {
        return qualificacaoDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<Qualificacao> buscarPorTipo(String tipo) {
        return qualificacaoDao.buscarPorTipo(tipo);
    }

    public List<Qualificacao> buscarValidas() {
        return qualificacaoDao.buscarValidas();
    }

    public List<Qualificacao> buscarVencidas() {
        return qualificacaoDao.buscarVencidas();
    }
}
```

---

## 13. RegistroEmissaoService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RegistroEmissaoDao;
import br.edu.utfpr.model.RegistroEmissao;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroEmissaoService {
    
    private RegistroEmissaoDao registroEmissaoDao;

    public RegistroEmissaoService(EntityManager em) {
        registroEmissaoDao = new RegistroEmissaoDao(em);
    }

    public void inserir(RegistroEmissao registroEmissao) {
        registroEmissaoDao.cadastrar(registroEmissao);
    }

    public void alterar(RegistroEmissao registroEmissao) {
        registroEmissaoDao.atualizar(registroEmissao);
    }

    public void excluir(RegistroEmissao registroEmissao) {
        registroEmissaoDao.remover(registroEmissao);
    }

    public RegistroEmissao buscarPorId(Long id) {
        return registroEmissaoDao.buscarPorId(id);
    }

    public List<RegistroEmissao> buscarTodos() {
        return registroEmissaoDao.buscarTodos();
    }

    public List<RegistroEmissao> buscarPorCanteiro(Long canteiroId) {
        return registroEmissaoDao.buscarPorCanteiro(canteiroId);
    }

    public List<RegistroEmissao> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return registroEmissaoDao.buscarPorPeriodo(dataInicio, dataFim);
    }

    public List<RegistroEmissao> buscarPorTipo(String tipoEmissao) {
        return registroEmissaoDao.buscarPorTipo(tipoEmissao);
    }
}
```

---

## 14. RegistroJornadaService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RegistroJornadaDao;
import br.edu.utfpr.model.RegistroJornada;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroJornadaService {
    
    private RegistroJornadaDao registroJornadaDao;

    public RegistroJornadaService(EntityManager em) {
        registroJornadaDao = new RegistroJornadaDao(em);
    }

    public void inserir(RegistroJornada registroJornada) {
        registroJornadaDao.cadastrar(registroJornada);
    }

    public void alterar(RegistroJornada registroJornada) {
        registroJornadaDao.atualizar(registroJornada);
    }

    public void excluir(RegistroJornada registroJornada) {
        registroJornadaDao.remover(registroJornada);
    }

    public RegistroJornada buscarPorId(Long id) {
        return registroJornadaDao.buscarPorId(id);
    }

    public List<RegistroJornada> buscarTodos() {
        return registroJornadaDao.buscarTodos();
    }

    public List<RegistroJornada> buscarPorTrabalhador(Long trabalhadorId) {
        return registroJornadaDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<RegistroJornada> buscarPorCanteiro(Long canteiroId) {
        return registroJornadaDao.buscarPorCanteiro(canteiroId);
    }

    public List<RegistroJornada> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return registroJornadaDao.buscarPorPeriodo(dataInicio, dataFim);
    }

    public List<RegistroJornada> buscarComHorasExtras(Double horasMinimas) {
        return registroJornadaDao.buscarComHorasExtras(horasMinimas);
    }
}
```

---

## 15. RegistroMaterialService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RegistroMaterialDao;
import br.edu.utfpr.model.RegistroMaterial;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroMaterialService {
    
    private RegistroMaterialDao registroMaterialDao;

    public RegistroMaterialService(EntityManager em) {
        registroMaterialDao = new RegistroMaterialDao(em);
    }

    public void inserir(RegistroMaterial registroMaterial) {
        registroMaterialDao.cadastrar(registroMaterial);
    }

    public void alterar(RegistroMaterial registroMaterial) {
        registroMaterialDao.atualizar(registroMaterial);
    }

    public void excluir(RegistroMaterial registroMaterial) {
        registroMaterialDao.remover(registroMaterial);
    }

    public RegistroMaterial buscarPorId(Long id) {
        return registroMaterialDao.buscarPorId(id);
    }

    public List<RegistroMaterial> buscarTodos() {
        return registroMaterialDao.buscarTodos();
    }

    public List<RegistroMaterial> buscarPorCanteiro(Long canteiroId) {
        return registroMaterialDao.buscarPorCanteiro(canteiroId);
    }

    public List<RegistroMaterial> buscarPorTipo(String tipoMaterial) {
        return registroMaterialDao.buscarPorTipo(tipoMaterial);
    }

    public List<RegistroMaterial> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return registroMaterialDao.buscarPorPeriodo(dataInicio, dataFim);
    }
}
```

---

## 16. RegistroProdutividadeService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RegistroProdutividadeDao;
import br.edu.utfpr.model.RegistroProdutividade;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroProdutividadeService {
    
    private RegistroProdutividadeDao registroProdutividadeDao;

    public RegistroProdutividadeService(EntityManager em) {
        registroProdutividadeDao = new RegistroProdutividadeDao(em);
    }

    public void inserir(RegistroProdutividade registroProdutividade) {
        registroProdutividadeDao.cadastrar(registroProdutividade);
    }

    public void alterar(RegistroProdutividade registroProdutividade) {
        registroProdutividadeDao.atualizar(registroProdutividade);
    }

    public void excluir(RegistroProdutividade registroProdutividade) {
        registroProdutividadeDao.remover(registroProdutividade);
    }

    public RegistroProdutividade buscarPorId(Long id) {
        return registroProdutividadeDao.buscarPorId(id);
    }

    public List<RegistroProdutividade> buscarTodos() {
        return registroProdutividadeDao.buscarTodos();
    }

    public List<RegistroProdutividade> buscarPorCanteiro(Long canteiroId) {
        return registroProdutividadeDao.buscarPorCanteiro(canteiroId);
    }

    public List<RegistroProdutividade> buscarPorTrabalhador(Long trabalhadorId) {
        return registroProdutividadeDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<RegistroProdutividade> buscarPorMetrica(Long metricaId) {
        return registroProdutividadeDao.buscarPorMetrica(metricaId);
    }

    public List<RegistroProdutividade> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return registroProdutividadeDao.buscarPorPeriodo(dataInicio, dataFim);
    }
}
```

---

## 17. RegistroRecursoService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RegistroRecursoDao;
import br.edu.utfpr.model.RegistroRecurso;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroRecursoService {
    
    private RegistroRecursoDao registroRecursoDao;

    public RegistroRecursoService(EntityManager em) {
        registroRecursoDao = new RegistroRecursoDao(em);
    }

    public void inserir(RegistroRecurso registroRecurso) {
        registroRecursoDao.cadastrar(registroRecurso);
    }

    public void alterar(RegistroRecurso registroRecurso) {
        registroRecursoDao.atualizar(registroRecurso);
    }

    public void excluir(RegistroRecurso registroRecurso) {
        registroRecursoDao.remover(registroRecurso);
    }

    public RegistroRecurso buscarPorId(Long id) {
        return registroRecursoDao.buscarPorId(id);
    }

    public List<RegistroRecurso> buscarTodos() {
        return registroRecursoDao.buscarTodos();
    }

    public List<RegistroRecurso> buscarPorCanteiro(Long canteiroId) {
        return registroRecursoDao.buscarPorCanteiro(canteiroId);
    }

    public List<RegistroRecurso> buscarPorTipo(String tipoRecurso) {
        return registroRecursoDao.buscarPorTipo(tipoRecurso);
    }

    public List<RegistroRecurso> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return registroRecursoDao.buscarPorPeriodo(dataInicio, dataFim);
    }
}
```

---

## 18. RegistroResiduoService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RegistroResiduoDao;
import br.edu.utfpr.model.RegistroResiduo;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroResiduoService {
    
    private RegistroResiduoDao registroResiduoDao;

    public RegistroResiduoService(EntityManager em) {
        registroResiduoDao = new RegistroResiduoDao(em);
    }

    public void inserir(RegistroResiduo registroResiduo) {
        registroResiduoDao.cadastrar(registroResiduo);
    }

    public void alterar(RegistroResiduo registroResiduo) {
        registroResiduoDao.atualizar(registroResiduo);
    }

    public void excluir(RegistroResiduo registroResiduo) {
        registroResiduoDao.remover(registroResiduo);
    }

    public RegistroResiduo buscarPorId(Long id) {
        return registroResiduoDao.buscarPorId(id);
    }

    public List<RegistroResiduo> buscarTodos() {
        return registroResiduoDao.buscarTodos();
    }

    public List<RegistroResiduo> buscarPorCanteiro(Long canteiroId) {
        return registroResiduoDao.buscarPorCanteiro(canteiroId);
    }

    public List<RegistroResiduo> buscarPorTipo(String tipoResiduo) {
        return registroResiduoDao.buscarPorTipo(tipoResiduo);
    }

    public List<RegistroResiduo> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return registroResiduoDao.buscarPorPeriodo(dataInicio, dataFim);
    }
}
```

---

## 19. RegistroSaudeService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RegistroSaudeDao;
import br.edu.utfpr.model.RegistroSaude;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroSaudeService {
    
    private RegistroSaudeDao registroSaudeDao;

    public RegistroSaudeService(EntityManager em) {
        registroSaudeDao = new RegistroSaudeDao(em);
    }

    public void inserir(RegistroSaude registroSaude) {
        registroSaudeDao.cadastrar(registroSaude);
    }

    public void alterar(RegistroSaude registroSaude) {
        registroSaudeDao.atualizar(registroSaude);
    }

    public void excluir(RegistroSaude registroSaude) {
        registroSaudeDao.remover(registroSaude);
    }

    public RegistroSaude buscarPorId(Long id) {
        return registroSaudeDao.buscarPorId(id);
    }

    public List<RegistroSaude> buscarTodos() {
        return registroSaudeDao.buscarTodos();
    }

    public List<RegistroSaude> buscarPorTrabalhador(Long trabalhadorId) {
        return registroSaudeDao.buscarPorTrabalhador(trabalhadorId);
    }

    public List<RegistroSaude> buscarPorTipoExame(String tipoExame) {
        return registroSaudeDao.buscarPorTipoExame(tipoExame);
    }

    public List<RegistroSaude> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return registroSaudeDao.buscarPorPeriodo(dataInicio, dataFim);
    }

    public List<RegistroSaude> buscarPorResultado(String resultado) {
        return registroSaudeDao.buscarPorResultado(resultado);
    }
}
```

---

## 20. RelatorioService.java

```java
package br.edu.utfpr.service;

import br.edu.utfpr.dao.RelatorioDao;
import br.edu.utfpr.model.Relatorio;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RelatorioService {
    
    private RelatorioDao relatorioDao;

    public RelatorioService(EntityManager em) {
        relatorioDao = new RelatorioDao(em);
    }

    public void inserir(Relatorio relatorio) {
        relatorioDao.cadastrar(relatorio);
    }

    public void alterar(Relatorio relatorio) {
        relatorioDao.atualizar(relatorio);
    }

    public void excluir(Relatorio relatorio) {
        relatorioDao.remover(relatorio);
    }

    public Relatorio buscarPorId(Long id) {
        return relatorioDao.buscarPorId(id);
    }

    public List<Relatorio> buscarTodos() {
        return relatorioDao.buscarTodos();
    }

    public List<Relatorio> buscarPorCanteiro(Long canteiroId) {
        return relatorioDao.buscarPorCanteiro(canteiroId);
    }

    public List<Relatorio> buscarPorTipo(String tipoRelatorio) {
        return relatorioDao.buscarPorTipo(tipoRelatorio);
    }

    public List<Relatorio> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return relatorioDao.buscarPorPeriodo(dataInicio, dataFim);
    }

    public List<Relatorio> buscarTodosOrdenados() {
        return relatorioDao.buscarTodosOrdenados();
    }
}
```

---

**Total:** 20 classes Service completas! ✅

