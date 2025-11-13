# Projeto POO - Código Completo (Padrão da Professora)

**Data:** 12/11/2025  
**Total de arquivos:** 44

---

## 📦 Estrutura de Pacotes

```
br.edu.utfpr.model       - Entidades (já existem - não mexer)
br.edu.utfpr.enums       - Enums (já existem - não mexer)
br.edu.utfpr.dao         - Classes DAO (refatorar)
br.edu.utfpr.service     - Classes Service (criar)
br.edu.utfpr.util        - Utilitários (criar)
br.edu.utfpr.exception   - Exceções (criar)
```

---

# PARTE 1: Classes Base (3 arquivos)

## 1. DataAccessException.java

**Pacote:** `br.edu.utfpr.exception`

```java
package br.edu.utfpr.exception;

/**
 * Exceção personalizada para erros de acesso a dados.
 * Usada nas classes DAO para encapsular exceções do JPA/Hibernate.
 */
public class DataAccessException extends RuntimeException {

    /**
     * Construtor que recebe mensagem e causa da exceção.
     * 
     * @param message Mensagem descritiva do erro
     * @param cause Exceção original que causou o erro
     */
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Construtor que recebe apenas a mensagem.
     * 
     * @param message Mensagem descritiva do erro
     */
    public DataAccessException(String message) {
        super(message);
    }
}
```

---

## 2. JPAUtil.java

**Pacote:** `br.edu.utfpr.util`

```java
package br.edu.utfpr.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe utilitária para gerenciar a criação de EntityManager.
 * Implementa o padrão Singleton para o EntityManagerFactory.
 */
public class JPAUtil {

    /**
     * Instância única do EntityManagerFactory.
     * O nome "PostgresPU" deve corresponder ao definido no persistence.xml.
     */
    private static final EntityManagerFactory FACTORY = 
            Persistence.createEntityManagerFactory("PostgresPU");

    /**
     * Retorna uma nova instância de EntityManager.
     * Cada chamada cria um novo EntityManager a partir do Factory.
     * 
     * @return Nova instância de EntityManager
     */
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
    
    /**
     * Fecha o EntityManagerFactory.
     * Deve ser chamado ao encerrar a aplicação.
     */
    public static void close() {
        if (FACTORY != null && FACTORY.isOpen()) {
            FACTORY.close();
        }
    }
}
```

---

## 3. GenericDao.java

**Pacote:** `br.edu.utfpr.dao`

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import jakarta.persistence.EntityManager;

import java.util.List;

/**
 * Classe DAO genérica que implementa operações CRUD básicas.
 * Todas as classes DAO específicas devem herdar desta classe.
 * 
 * @param <T> Tipo da entidade gerenciada por este DAO
 */
public abstract class GenericDao<T> {

    /** EntityManager para operações com o banco de dados */
    protected EntityManager em;
    
    /** Classe da entidade gerenciada */
    private Class<T> entityClass;

    /**
     * Construtor que inicializa o DAO com EntityManager e classe da entidade.
     * 
     * @param em EntityManager para operações no banco
     * @param entityClass Classe da entidade gerenciada
     */
    public GenericDao(EntityManager em, Class<T> entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    /**
     * Cadastra (persiste) uma nova entidade no banco de dados.
     * 
     * @param entity Entidade a ser cadastrada
     * @throws DataAccessException se ocorrer erro na operação
     */
    public void cadastrar(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException(
                "Erro ao cadastrar a entidade: " + entityClass.getSimpleName(), e);
        }
    }

    /**
     * Atualiza uma entidade existente no banco de dados.
     * 
     * @param entity Entidade a ser atualizada
     * @throws DataAccessException se ocorrer erro na operação
     */
    public void atualizar(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException(
                "Erro ao atualizar a entidade: " + entityClass.getSimpleName(), e);
        }
    }

    /**
     * Remove uma entidade do banco de dados.
     * 
     * @param entity Entidade a ser removida
     * @throws DataAccessException se ocorrer erro na operação
     */
    public void remover(T entity) {
        try {
            em.getTransaction().begin();
            // Garante que a entidade está gerenciada antes de remover
            T managedEntity = em.contains(entity) ? entity : em.merge(entity);
            em.remove(managedEntity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DataAccessException(
                "Erro ao remover a entidade: " + entityClass.getSimpleName(), e);
        }
    }

    /**
     * Busca uma entidade pelo seu identificador (ID).
     * 
     * @param id Identificador da entidade
     * @return Entidade encontrada ou null se não existir
     * @throws DataAccessException se ocorrer erro na operação
     */
    public T buscarPorId(Long id) {
        try {
            return em.find(entityClass, id);
        } catch (Exception e) {
            throw new DataAccessException(
                "Erro ao buscar o id: " + id + " da entidade: " + entityClass.getSimpleName(), e);
        }
    }

    /**
     * Busca todas as entidades do tipo gerenciado.
     * 
     * @return Lista com todas as entidades
     * @throws DataAccessException se ocorrer erro na operação
     */
    public List<T> buscarTodos() {
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, entityClass).getResultList();
        } catch (Exception e) {
            throw new DataAccessException(
                "Erro ao buscar todos de: " + entityClass.getSimpleName(), e);
        }
    }
}
```

---

# PARTE 2: Classes DAO Refatoradas (20 arquivos)

## 1. TrabalhadorDao.java

**Pacote:** `br.edu.utfpr.dao`

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.Trabalhador;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

/**
 * DAO para operações com a entidade Trabalhador.
 */
public class TrabalhadorDao extends GenericDao<Trabalhador> {

    public TrabalhadorDao(EntityManager em) {
        super(em, Trabalhador.class);
    }

    /**
     * Busca trabalhador por CPF.
     */
    public Optional<Trabalhador> buscarPorCpf(String cpf) {
        try {
            String jpql = "SELECT t FROM Trabalhador t WHERE t.cpf = :cpf";
            List<Trabalhador> resultado = em.createQuery(jpql, Trabalhador.class)
                    .setParameter("cpf", cpf)
                    .getResultList();
            return resultado.isEmpty() ? Optional.empty() : Optional.of(resultado.get(0));
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar trabalhador por CPF: " + cpf, e);
        }
    }

    /**
     * Busca trabalhadores por status.
     */
    public List<Trabalhador> buscarPorStatus(String status) {
        try {
            String jpql = "SELECT t FROM Trabalhador t WHERE t.status = :status";
            return em.createQuery(jpql, Trabalhador.class)
                    .setParameter("status", status)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar trabalhadores por status", e);
        }
    }

    /**
     * Busca trabalhadores de um canteiro específico.
     */
    public List<Trabalhador> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT t FROM Trabalhador t WHERE t.canteiroAtual.id = :canteiroId";
            return em.createQuery(jpql, Trabalhador.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar trabalhadores por canteiro", e);
        }
    }

    /**
     * Busca trabalhadores por nome (contém).
     */
    public List<Trabalhador> buscarPorNome(String nome) {
        try {
            String jpql = "SELECT t FROM Trabalhador t WHERE LOWER(t.nome) LIKE LOWER(:nome)";
            return em.createQuery(jpql, Trabalhador.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar trabalhadores por nome", e);
        }
    }
}
```

---

## 2. CanteiroDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.Canteiro;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CanteiroDao extends GenericDao<Canteiro> {

    public CanteiroDao(EntityManager em) {
        super(em, Canteiro.class);
    }

    public List<Canteiro> buscarPorProjeto(Long projetoId) {
        try {
            String jpql = "SELECT c FROM Canteiro c WHERE c.projeto.id = :projetoId";
            return em.createQuery(jpql, Canteiro.class)
                    .setParameter("projetoId", projetoId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar canteiros por projeto", e);
        }
    }

    public List<Canteiro> buscarPorNome(String nome) {
        try {
            String jpql = "SELECT c FROM Canteiro c WHERE LOWER(c.nome) LIKE LOWER(:nome)";
            return em.createQuery(jpql, Canteiro.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar canteiros por nome", e);
        }
    }

    public List<Canteiro> buscarAtivos() {
        try {
            String jpql = "SELECT c FROM Canteiro c WHERE c.ativo = true";
            return em.createQuery(jpql, Canteiro.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar canteiros ativos", e);
        }
    }
}
```

---

## 3. ProjetoDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.Projeto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProjetoDao extends GenericDao<Projeto> {

    public ProjetoDao(EntityManager em) {
        super(em, Projeto.class);
    }

    public List<Projeto> buscarPorNome(String nome) {
        try {
            String jpql = "SELECT p FROM Projeto p WHERE LOWER(p.nome) LIKE LOWER(:nome)";
            return em.createQuery(jpql, Projeto.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar projetos por nome", e);
        }
    }

    public List<Projeto> buscarAtivos() {
        try {
            String jpql = "SELECT p FROM Projeto p WHERE p.ativo = true";
            return em.createQuery(jpql, Projeto.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar projetos ativos", e);
        }
    }

    public List<Projeto> buscarPorLocalizacao(String localizacao) {
        try {
            String jpql = "SELECT p FROM Projeto p WHERE LOWER(p.localizacao) LIKE LOWER(:localizacao)";
            return em.createQuery(jpql, Projeto.class)
                    .setParameter("localizacao", "%" + localizacao + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar projetos por localização", e);
        }
    }
}
```

---

## 4. AvaliacaoCondicoesDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.AvaliacaoCondicoes;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class AvaliacaoCondicoesDao extends GenericDao<AvaliacaoCondicoes> {

    public AvaliacaoCondicoesDao(EntityManager em) {
        super(em, AvaliacaoCondicoes.class);
    }

    public List<AvaliacaoCondicoes> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT a FROM AvaliacaoCondicoes a WHERE a.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, AvaliacaoCondicoes.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar avaliações por trabalhador", e);
        }
    }

    public List<AvaliacaoCondicoes> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT a FROM AvaliacaoCondicoes a WHERE a.canteiro.id = :canteiroId";
            return em.createQuery(jpql, AvaliacaoCondicoes.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar avaliações por canteiro", e);
        }
    }

    public List<AvaliacaoCondicoes> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT a FROM AvaliacaoCondicoes a WHERE a.dataAvaliacao BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, AvaliacaoCondicoes.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar avaliações por período", e);
        }
    }

    public List<AvaliacaoCondicoes> buscarComPontuacaoBaixa(Double pontuacaoMaxima) {
        try {
            String jpql = "SELECT a FROM AvaliacaoCondicoes a WHERE a.pontuacao < :pontuacaoMaxima";
            return em.createQuery(jpql, AvaliacaoCondicoes.class)
                    .setParameter("pontuacaoMaxima", pontuacaoMaxima)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar avaliações com pontuação baixa", e);
        }
    }
}
```

---

## 5. AlertaDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.enums.Prioridade;
import br.edu.utfpr.enums.TipoAlerta;
import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.Alerta;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlertaDao extends GenericDao<Alerta> {

    public AlertaDao(EntityManager em) {
        super(em, Alerta.class);
    }

    public List<Alerta> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT a FROM Alerta a WHERE a.canteiro.id = :canteiroId";
            return em.createQuery(jpql, Alerta.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar alertas por canteiro", e);
        }
    }

    public List<Alerta> buscarNaoResolvidos() {
        try {
            String jpql = "SELECT a FROM Alerta a WHERE a.resolvido = false";
            return em.createQuery(jpql, Alerta.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar alertas não resolvidos", e);
        }
    }

    public List<Alerta> buscarPorPrioridade(Prioridade prioridade) {
        try {
            String jpql = "SELECT a FROM Alerta a WHERE a.prioridade = :prioridade";
            return em.createQuery(jpql, Alerta.class)
                    .setParameter("prioridade", prioridade)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar alertas por prioridade", e);
        }
    }

    public List<Alerta> buscarPorTipo(TipoAlerta tipo) {
        try {
            String jpql = "SELECT a FROM Alerta a WHERE a.tipo = :tipo";
            return em.createQuery(jpql, Alerta.class)
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar alertas por tipo", e);
        }
    }

    public List<Alerta> buscarNaoResolvidosPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT a FROM Alerta a WHERE a.canteiro.id = :canteiroId AND a.resolvido = false";
            return em.createQuery(jpql, Alerta.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar alertas não resolvidos por canteiro", e);
        }
    }
}
```

---

## 6. EPIDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.EPI;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class EPIDao extends GenericDao<EPI> {

    public EPIDao(EntityManager em) {
        super(em, EPI.class);
    }

    public List<EPI> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT e FROM EPI e WHERE e.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, EPI.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar EPIs por trabalhador", e);
        }
    }

    public List<EPI> buscarPorTipo(String tipo) {
        try {
            String jpql = "SELECT e FROM EPI e WHERE e.tipo = :tipo";
            return em.createQuery(jpql, EPI.class)
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar EPIs por tipo", e);
        }
    }

    public List<EPI> buscarVencidos() {
        try {
            String jpql = "SELECT e FROM EPI e WHERE e.dataValidade < :dataAtual";
            return em.createQuery(jpql, EPI.class)
                    .setParameter("dataAtual", LocalDate.now())
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar EPIs vencidos", e);
        }
    }

    public List<EPI> buscarVencendoEm(int dias) {
        try {
            LocalDate dataLimite = LocalDate.now().plusDays(dias);
            String jpql = "SELECT e FROM EPI e WHERE e.dataValidade BETWEEN :dataAtual AND :dataLimite";
            return em.createQuery(jpql, EPI.class)
                    .setParameter("dataAtual", LocalDate.now())
                    .setParameter("dataLimite", dataLimite)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar EPIs vencendo em breve", e);
        }
    }
}
```

---

## 7. FeedbackDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.Feedback;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class FeedbackDao extends GenericDao<Feedback> {

    public FeedbackDao(EntityManager em) {
        super(em, Feedback.class);
    }

    public List<Feedback> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT f FROM Feedback f WHERE f.canteiro.id = :canteiroId";
            return em.createQuery(jpql, Feedback.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar feedbacks por canteiro", e);
        }
    }

    public List<Feedback> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT f FROM Feedback f WHERE f.dataFeedback BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, Feedback.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar feedbacks por período", e);
        }
    }

    public List<Feedback> buscarPorAvaliacao(Integer avaliacao) {
        try {
            String jpql = "SELECT f FROM Feedback f WHERE f.avaliacao = :avaliacao";
            return em.createQuery(jpql, Feedback.class)
                    .setParameter("avaliacao", avaliacao)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar feedbacks por avaliação", e);
        }
    }

    public List<Feedback> buscarComAvaliacaoMinima(Integer avaliacaoMinima) {
        try {
            String jpql = "SELECT f FROM Feedback f WHERE f.avaliacao >= :avaliacaoMinima";
            return em.createQuery(jpql, Feedback.class)
                    .setParameter("avaliacaoMinima", avaliacaoMinima)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar feedbacks com avaliação mínima", e);
        }
    }
}
```

---

## 8. HistoricoAlocacaoDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.HistoricoAlocacao;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class HistoricoAlocacaoDao extends GenericDao<HistoricoAlocacao> {

    public HistoricoAlocacaoDao(EntityManager em) {
        super(em, HistoricoAlocacao.class);
    }

    public List<HistoricoAlocacao> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT h FROM HistoricoAlocacao h WHERE h.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, HistoricoAlocacao.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar histórico por trabalhador", e);
        }
    }

    public List<HistoricoAlocacao> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT h FROM HistoricoAlocacao h WHERE h.canteiro.id = :canteiroId";
            return em.createQuery(jpql, HistoricoAlocacao.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar histórico por canteiro", e);
        }
    }

    public List<HistoricoAlocacao> buscarAlocacoesAtivas() {
        try {
            String jpql = "SELECT h FROM HistoricoAlocacao h WHERE h.dataSaida IS NULL";
            return em.createQuery(jpql, HistoricoAlocacao.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar alocações ativas", e);
        }
    }

    public List<HistoricoAlocacao> buscarPorPeriodoEntrada(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT h FROM HistoricoAlocacao h WHERE h.dataEntrada BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, HistoricoAlocacao.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar histórico por período de entrada", e);
        }
    }
}
```

---

## 9. MetricaProdutividadeDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.MetricaProdutividade;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class MetricaProdutividadeDao extends GenericDao<MetricaProdutividade> {

    public MetricaProdutividadeDao(EntityManager em) {
        super(em, MetricaProdutividade.class);
    }

    public Optional<MetricaProdutividade> buscarPorNome(String nome) {
        try {
            String jpql = "SELECT m FROM MetricaProdutividade m WHERE m.nome = :nome";
            List<MetricaProdutividade> resultado = em.createQuery(jpql, MetricaProdutividade.class)
                    .setParameter("nome", nome)
                    .getResultList();
            return resultado.isEmpty() ? Optional.empty() : Optional.of(resultado.get(0));
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar métrica por nome", e);
        }
    }

    public List<MetricaProdutividade> buscarAtivas() {
        try {
            String jpql = "SELECT m FROM MetricaProdutividade m WHERE m.ativa = true";
            return em.createQuery(jpql, MetricaProdutividade.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar métricas ativas", e);
        }
    }

    public List<MetricaProdutividade> buscarPorUnidadeMedida(String unidadeMedida) {
        try {
            String jpql = "SELECT m FROM MetricaProdutividade m WHERE m.unidadeMedida = :unidadeMedida";
            return em.createQuery(jpql, MetricaProdutividade.class)
                    .setParameter("unidadeMedida", unidadeMedida)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar métricas por unidade de medida", e);
        }
    }
}
```

---

## 10. ODSDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.ODS;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class ODSDao extends GenericDao<ODS> {

    public ODSDao(EntityManager em) {
        super(em, ODS.class);
    }

    public Optional<ODS> buscarPorNumero(Integer numero) {
        try {
            String jpql = "SELECT o FROM ODS o WHERE o.numero = :numero";
            List<ODS> resultado = em.createQuery(jpql, ODS.class)
                    .setParameter("numero", numero)
                    .getResultList();
            return resultado.isEmpty() ? Optional.empty() : Optional.of(resultado.get(0));
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar ODS por número", e);
        }
    }

    public List<ODS> buscarPorNome(String nome) {
        try {
            String jpql = "SELECT o FROM ODS o WHERE LOWER(o.nome) LIKE LOWER(:nome)";
            return em.createQuery(jpql, ODS.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar ODS por nome", e);
        }
    }

    public List<ODS> buscarTodasOrdenadas() {
        try {
            String jpql = "SELECT o FROM ODS o ORDER BY o.numero ASC";
            return em.createQuery(jpql, ODS.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar todas as ODS ordenadas", e);
        }
    }
}
```

---

## 11-20. Demais DAOs

**(Continuação no próximo bloco devido ao tamanho...)**

---

## 11. OcorrenciaSegurancaDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.enums.Gravidade;
import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.OcorrenciaSeguranca;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class OcorrenciaSegurancaDao extends GenericDao<OcorrenciaSeguranca> {

    public OcorrenciaSegurancaDao(EntityManager em) {
        super(em, OcorrenciaSeguranca.class);
    }

    public List<OcorrenciaSeguranca> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT o FROM OcorrenciaSeguranca o WHERE o.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, OcorrenciaSeguranca.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar ocorrências por trabalhador", e);
        }
    }

    public List<OcorrenciaSeguranca> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT o FROM OcorrenciaSeguranca o WHERE o.canteiro.id = :canteiroId";
            return em.createQuery(jpql, OcorrenciaSeguranca.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar ocorrências por canteiro", e);
        }
    }

    public List<OcorrenciaSeguranca> buscarPorGravidade(Gravidade gravidade) {
        try {
            String jpql = "SELECT o FROM OcorrenciaSeguranca o WHERE o.gravidade = :gravidade";
            return em.createQuery(jpql, OcorrenciaSeguranca.class)
                    .setParameter("gravidade", gravidade)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar ocorrências por gravidade", e);
        }
    }

    public List<OcorrenciaSeguranca> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT o FROM OcorrenciaSeguranca o WHERE o.dataOcorrencia BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, OcorrenciaSeguranca.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar ocorrências por período", e);
        }
    }
}
```

---

## 12. QualificacaoDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.Qualificacao;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class QualificacaoDao extends GenericDao<Qualificacao> {

    public QualificacaoDao(EntityManager em) {
        super(em, Qualificacao.class);
    }

    public List<Qualificacao> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT q FROM Qualificacao q WHERE q.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, Qualificacao.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar qualificações por trabalhador", e);
        }
    }

    public List<Qualificacao> buscarPorTipo(String tipo) {
        try {
            String jpql = "SELECT q FROM Qualificacao q WHERE q.tipo = :tipo";
            return em.createQuery(jpql, Qualificacao.class)
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar qualificações por tipo", e);
        }
    }

    public List<Qualificacao> buscarValidas() {
        try {
            String jpql = "SELECT q FROM Qualificacao q WHERE q.valida = true";
            return em.createQuery(jpql, Qualificacao.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar qualificações válidas", e);
        }
    }

    public List<Qualificacao> buscarVencidas() {
        try {
            String jpql = "SELECT q FROM Qualificacao q WHERE q.dataValidade < :dataAtual";
            return em.createQuery(jpql, Qualificacao.class)
                    .setParameter("dataAtual", LocalDate.now())
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar qualificações vencidas", e);
        }
    }
}
```

---

## 13. RegistroEmissaoDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.RegistroEmissao;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroEmissaoDao extends GenericDao<RegistroEmissao> {

    public RegistroEmissaoDao(EntityManager em) {
        super(em, RegistroEmissao.class);
    }

    public List<RegistroEmissao> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT r FROM RegistroEmissao r WHERE r.canteiro.id = :canteiroId";
            return em.createQuery(jpql, RegistroEmissao.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de emissão por canteiro", e);
        }
    }

    public List<RegistroEmissao> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM RegistroEmissao r WHERE r.dataRegistro BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, RegistroEmissao.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de emissão por período", e);
        }
    }

    public List<RegistroEmissao> buscarPorTipo(String tipoEmissao) {
        try {
            String jpql = "SELECT r FROM RegistroEmissao r WHERE r.tipoEmissao = :tipoEmissao";
            return em.createQuery(jpql, RegistroEmissao.class)
                    .setParameter("tipoEmissao", tipoEmissao)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de emissão por tipo", e);
        }
    }
}
```

---

## 14. RegistroJornadaDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.RegistroJornada;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroJornadaDao extends GenericDao<RegistroJornada> {

    public RegistroJornadaDao(EntityManager em) {
        super(em, RegistroJornada.class);
    }

    public List<RegistroJornada> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT r FROM RegistroJornada r WHERE r.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, RegistroJornada.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de jornada por trabalhador", e);
        }
    }

    public List<RegistroJornada> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT r FROM RegistroJornada r WHERE r.canteiro.id = :canteiroId";
            return em.createQuery(jpql, RegistroJornada.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de jornada por canteiro", e);
        }
    }

    public List<RegistroJornada> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM RegistroJornada r WHERE r.data BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, RegistroJornada.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de jornada por período", e);
        }
    }

    public List<RegistroJornada> buscarComHorasExtras(Double horasMinimas) {
        try {
            String jpql = "SELECT r FROM RegistroJornada r WHERE r.horasExtras > :horasMinimas";
            return em.createQuery(jpql, RegistroJornada.class)
                    .setParameter("horasMinimas", horasMinimas)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros com horas extras", e);
        }
    }
}
```

---

## 15. RegistroMaterialDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.RegistroMaterial;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroMaterialDao extends GenericDao<RegistroMaterial> {

    public RegistroMaterialDao(EntityManager em) {
        super(em, RegistroMaterial.class);
    }

    public List<RegistroMaterial> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT r FROM RegistroMaterial r WHERE r.canteiro.id = :canteiroId";
            return em.createQuery(jpql, RegistroMaterial.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de material por canteiro", e);
        }
    }

    public List<RegistroMaterial> buscarPorTipo(String tipoMaterial) {
        try {
            String jpql = "SELECT r FROM RegistroMaterial r WHERE r.tipoMaterial = :tipoMaterial";
            return em.createQuery(jpql, RegistroMaterial.class)
                    .setParameter("tipoMaterial", tipoMaterial)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de material por tipo", e);
        }
    }

    public List<RegistroMaterial> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM RegistroMaterial r WHERE r.dataRegistro BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, RegistroMaterial.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de material por período", e);
        }
    }
}
```

---

## 16. RegistroProdutividadeDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.RegistroProdutividade;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroProdutividadeDao extends GenericDao<RegistroProdutividade> {

    public RegistroProdutividadeDao(EntityManager em) {
        super(em, RegistroProdutividade.class);
    }

    public List<RegistroProdutividade> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT r FROM RegistroProdutividade r WHERE r.canteiro.id = :canteiroId";
            return em.createQuery(jpql, RegistroProdutividade.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de produtividade por canteiro", e);
        }
    }

    public List<RegistroProdutividade> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT r FROM RegistroProdutividade r WHERE r.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, RegistroProdutividade.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de produtividade por trabalhador", e);
        }
    }

    public List<RegistroProdutividade> buscarPorMetrica(Long metricaId) {
        try {
            String jpql = "SELECT r FROM RegistroProdutividade r WHERE r.metrica.id = :metricaId";
            return em.createQuery(jpql, RegistroProdutividade.class)
                    .setParameter("metricaId", metricaId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de produtividade por métrica", e);
        }
    }

    public List<RegistroProdutividade> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM RegistroProdutividade r WHERE r.dataRegistro BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, RegistroProdutividade.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de produtividade por período", e);
        }
    }
}
```

---

## 17. RegistroRecursoDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.RegistroRecurso;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroRecursoDao extends GenericDao<RegistroRecurso> {

    public RegistroRecursoDao(EntityManager em) {
        super(em, RegistroRecurso.class);
    }

    public List<RegistroRecurso> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT r FROM RegistroRecurso r WHERE r.canteiro.id = :canteiroId";
            return em.createQuery(jpql, RegistroRecurso.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de recurso por canteiro", e);
        }
    }

    public List<RegistroRecurso> buscarPorTipo(String tipoRecurso) {
        try {
            String jpql = "SELECT r FROM RegistroRecurso r WHERE r.tipoRecurso = :tipoRecurso";
            return em.createQuery(jpql, RegistroRecurso.class)
                    .setParameter("tipoRecurso", tipoRecurso)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de recurso por tipo", e);
        }
    }

    public List<RegistroRecurso> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM RegistroRecurso r WHERE r.dataRegistro BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, RegistroRecurso.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de recurso por período", e);
        }
    }
}
```

---

## 18. RegistroResiduoDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.RegistroResiduo;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroResiduoDao extends GenericDao<RegistroResiduo> {

    public RegistroResiduoDao(EntityManager em) {
        super(em, RegistroResiduo.class);
    }

    public List<RegistroResiduo> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT r FROM RegistroResiduo r WHERE r.canteiro.id = :canteiroId";
            return em.createQuery(jpql, RegistroResiduo.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de resíduo por canteiro", e);
        }
    }

    public List<RegistroResiduo> buscarPorTipo(String tipoResiduo) {
        try {
            String jpql = "SELECT r FROM RegistroResiduo r WHERE r.tipoResiduo = :tipoResiduo";
            return em.createQuery(jpql, RegistroResiduo.class)
                    .setParameter("tipoResiduo", tipoResiduo)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de resíduo por tipo", e);
        }
    }

    public List<RegistroResiduo> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM RegistroResiduo r WHERE r.dataRegistro BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, RegistroResiduo.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de resíduo por período", e);
        }
    }
}
```

---

## 19. RegistroSaudeDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.RegistroSaude;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RegistroSaudeDao extends GenericDao<RegistroSaude> {

    public RegistroSaudeDao(EntityManager em) {
        super(em, RegistroSaude.class);
    }

    public List<RegistroSaude> buscarPorTrabalhador(Long trabalhadorId) {
        try {
            String jpql = "SELECT r FROM RegistroSaude r WHERE r.trabalhador.id = :trabalhadorId";
            return em.createQuery(jpql, RegistroSaude.class)
                    .setParameter("trabalhadorId", trabalhadorId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de saúde por trabalhador", e);
        }
    }

    public List<RegistroSaude> buscarPorTipoExame(String tipoExame) {
        try {
            String jpql = "SELECT r FROM RegistroSaude r WHERE r.tipoExame = :tipoExame";
            return em.createQuery(jpql, RegistroSaude.class)
                    .setParameter("tipoExame", tipoExame)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de saúde por tipo de exame", e);
        }
    }

    public List<RegistroSaude> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM RegistroSaude r WHERE r.dataExame BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, RegistroSaude.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de saúde por período", e);
        }
    }

    public List<RegistroSaude> buscarPorResultado(String resultado) {
        try {
            String jpql = "SELECT r FROM RegistroSaude r WHERE r.resultado = :resultado";
            return em.createQuery(jpql, RegistroSaude.class)
                    .setParameter("resultado", resultado)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar registros de saúde por resultado", e);
        }
    }
}
```

---

## 20. RelatorioDao.java

```java
package br.edu.utfpr.dao;

import br.edu.utfpr.exception.DataAccessException;
import br.edu.utfpr.model.Relatorio;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

public class RelatorioDao extends GenericDao<Relatorio> {

    public RelatorioDao(EntityManager em) {
        super(em, Relatorio.class);
    }

    public List<Relatorio> buscarPorCanteiro(Long canteiroId) {
        try {
            String jpql = "SELECT r FROM Relatorio r WHERE r.canteiro.id = :canteiroId";
            return em.createQuery(jpql, Relatorio.class)
                    .setParameter("canteiroId", canteiroId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar relatórios por canteiro", e);
        }
    }

    public List<Relatorio> buscarPorTipo(String tipoRelatorio) {
        try {
            String jpql = "SELECT r FROM Relatorio r WHERE r.tipoRelatorio = :tipoRelatorio";
            return em.createQuery(jpql, Relatorio.class)
                    .setParameter("tipoRelatorio", tipoRelatorio)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar relatórios por tipo", e);
        }
    }

    public List<Relatorio> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        try {
            String jpql = "SELECT r FROM Relatorio r WHERE r.dataGeracao BETWEEN :dataInicio AND :dataFim";
            return em.createQuery(jpql, Relatorio.class)
                    .setParameter("dataInicio", dataInicio)
                    .setParameter("dataFim", dataFim)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar relatórios por período", e);
        }
    }

    public List<Relatorio> buscarTodosOrdenados() {
        try {
            String jpql = "SELECT r FROM Relatorio r ORDER BY r.dataGeracao DESC";
            return em.createQuery(jpql, Relatorio.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataAccessException("Erro ao buscar todos os relatórios ordenados", e);
        }
    }
}
```

---

# PARTE 3: Classes Service (20 arquivos)

**(Documento muito longo - continuando...)**

---

*Devido ao limite de tamanho, vou criar um arquivo separado para os Services. Aguarde...*

