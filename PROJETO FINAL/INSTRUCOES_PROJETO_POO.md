# 📚 Instruções - Projeto POO Completo

**Data:** 12/11/2025  
**Total de arquivos criados:** 44

---

## 📦 Arquivos Gerados

| Arquivo | Conteúdo |
|---------|----------|
| `projeto_poo_completo.md` | GenericDao + 20 DAOs + DataAccessException + JPAUtil |
| `projeto_poo_services.md` | 20 classes Service |
| `persistence.xml` | Configuração JPA/Hibernate |

---

## 🎯 O Que Você Precisa Fazer

### 1️⃣ **Criar os Pacotes**

No seu projeto, crie esses pacotes (se ainda não existirem):

```
br.edu.utfpr.dao         ← já existe, mas vai refatorar
br.edu.utfpr.service     ← CRIAR
br.edu.utfpr.util        ← CRIAR
br.edu.utfpr.exception   ← CRIAR
```

---

### 2️⃣ **Copiar os Códigos**

#### A. Classes Base (3 arquivos)

**Arquivo:** `projeto_poo_completo.md`

1. **DataAccessException.java**
   - Pacote: `br.edu.utfpr.exception`
   - Copiar código da seção "1. DataAccessException.java"

2. **JPAUtil.java**
   - Pacote: `br.edu.utfpr.util`
   - Copiar código da seção "2. JPAUtil.java"

3. **GenericDao.java**
   - Pacote: `br.edu.utfpr.dao`
   - Copiar código da seção "3. GenericDao.java"

#### B. Classes DAO (20 arquivos)

**Arquivo:** `projeto_poo_completo.md`

Pacote: `br.edu.utfpr.dao`

**IMPORTANTE:** Você já tem 20 DAOs antigas. **SUBSTITUA** elas pelas novas!

| Arquivo Antigo | Arquivo Novo | Ação |
|----------------|--------------|------|
| TrabalhadorDAO.java | TrabalhadorDao.java | **SUBSTITUIR** |
| CanteiroDAO.java | CanteiroDao.java | **SUBSTITUIR** |
| ProjetoDAO.java | ProjetoDao.java | **SUBSTITUIR** |
| AlertaDAO.java | AlertaDao.java | **SUBSTITUIR** |
| EPI_DAO.java | EPIDao.java | **SUBSTITUIR** |
| FeedbackDAO.java | FeedbackDao.java | **SUBSTITUIR** |
| HistoricoAlocacaoDAO.java | HistoricoAlocacaoDao.java | **SUBSTITUIR** |
| MetricaProdutividadeDAO.java | MetricaProdutividadeDao.java | **SUBSTITUIR** |
| ODS_DAO.java | ODSDao.java | **SUBSTITUIR** |
| OcorrenciaSegurancaDAO.java | OcorrenciaSegurancaDao.java | **SUBSTITUIR** |
| QualificacaoDAO.java | QualificacaoDao.java | **SUBSTITUIR** |
| RegistroEmissaoDAO.java | RegistroEmissaoDao.java | **SUBSTITUIR** |
| RegistroJornadaDAO.java | RegistroJornadaDao.java | **SUBSTITUIR** |
| RegistroMaterialDAO.java | RegistroMaterialDao.java | **SUBSTITUIR** |
| RegistroProdutividadeDAO.java | RegistroProdutividadeDao.java | **SUBSTITUIR** |
| RegistroRecursoDAO.java | RegistroRecursoDao.java | **SUBSTITUIR** |
| RegistroResiduoDAO.java | RegistroResiduoDao.java | **SUBSTITUIR** |
| RegistroSaudeDAO.java | RegistroSaudeDao.java | **SUBSTITUIR** |
| RelatorioDAO.java | RelatorioDao.java | **SUBSTITUIR** |
| AvaliacaoCondicoesDAO.java | AvaliacaoCondicoesDao.java | **SUBSTITUIR** |

**Copiar códigos das seções:**
- "1. TrabalhadorDao.java" até "20. RelatorioDao.java"

#### C. Classes Service (20 arquivos)

**Arquivo:** `projeto_poo_services.md`

Pacote: `br.edu.utfpr.service`

**CRIAR** os 20 arquivos:

1. TrabalhadorService.java
2. CanteiroService.java
3. ProjetoService.java
4. AvaliacaoCondicoesService.java
5. AlertaService.java
6. EPIService.java
7. FeedbackService.java
8. HistoricoAlocacaoService.java
9. MetricaProdutividadeService.java
10. ODSService.java
11. OcorrenciaSegurancaService.java
12. QualificacaoService.java
13. RegistroEmissaoService.java
14. RegistroJornadaService.java
15. RegistroMaterialService.java
16. RegistroProdutividadeService.java
17. RegistroRecursoService.java
18. RegistroResiduoService.java
19. RegistroSaudeService.java
20. RelatorioService.java

#### D. Arquivo persistence.xml

**Arquivo:** `persistence.xml`

**Localização:** `src/main/resources/META-INF/persistence.xml`

1. Crie a pasta `META-INF` dentro de `src/main/resources/` (se não existir)
2. Copie o arquivo `persistence.xml` para lá
3. **IMPORTANTE:** Edite a linha:
   ```xml
   <property name="javax.persistence.jdbc.password" value="sua_senha_aqui"/>
   ```
   Substitua `sua_senha_aqui` pela senha do seu PostgreSQL

4. Se necessário, ajuste também:
   - Nome do banco: `trabalho_decente` (linha do jdbc.url)
   - Usuário: `postgres` (linha do jdbc.user)
   - Porta: `5432` (linha do jdbc.url)

---

## 🗂️ Estrutura Final do Projeto

```
src/main/java/br/edu/utfpr/
├── model/              ✅ JÁ TEM (não mexer)
│   ├── Trabalhador.java
│   ├── Canteiro.java
│   └── ... (20 entidades)
│
├── enums/              ✅ JÁ TEM (não mexer)
│   ├── Prioridade.java
│   ├── TipoAlerta.java
│   └── ...
│
├── dao/                ⚠️ REFATORAR (substituir 20 DAOs)
│   ├── GenericDao.java          ← NOVO
│   ├── TrabalhadorDao.java      ← SUBSTITUIR
│   ├── CanteiroDao.java         ← SUBSTITUIR
│   └── ... (mais 18 DAOs)
│
├── service/            ⭐ CRIAR (20 Services)
│   ├── TrabalhadorService.java
│   ├── CanteiroService.java
│   └── ... (mais 18 Services)
│
├── util/               ⭐ CRIAR
│   └── JPAUtil.java
│
└── exception/          ⭐ CRIAR
    └── DataAccessException.java

src/main/resources/
└── META-INF/           ⭐ CRIAR
    └── persistence.xml
```

---

## ✅ Checklist de Implementação

### Fase 1: Classes Base
- [ ] Criar pacote `br.edu.utfpr.exception`
- [ ] Criar `DataAccessException.java`
- [ ] Criar pacote `br.edu.utfpr.util`
- [ ] Criar `JPAUtil.java`
- [ ] Criar `GenericDao.java` no pacote `dao`

### Fase 2: Refatorar DAOs
- [ ] Substituir `TrabalhadorDAO.java` por `TrabalhadorDao.java`
- [ ] Substituir `CanteiroDAO.java` por `CanteiroDao.java`
- [ ] Substituir `ProjetoDAO.java` por `ProjetoDao.java`
- [ ] Substituir `AvaliacaoCondicoesDAO.java` por `AvaliacaoCondicoesDao.java`
- [ ] Substituir `AlertaDAO.java` por `AlertaDao.java`
- [ ] Substituir `EPI_DAO.java` por `EPIDao.java`
- [ ] Substituir `FeedbackDAO.java` por `FeedbackDao.java`
- [ ] Substituir `HistoricoAlocacaoDAO.java` por `HistoricoAlocacaoDao.java`
- [ ] Substituir `MetricaProdutividadeDAO.java` por `MetricaProdutividadeDao.java`
- [ ] Substituir `ODS_DAO.java` por `ODSDao.java`
- [ ] Substituir `OcorrenciaSegurancaDAO.java` por `OcorrenciaSegurancaDao.java`
- [ ] Substituir `QualificacaoDAO.java` por `QualificacaoDao.java`
- [ ] Substituir `RegistroEmissaoDAO.java` por `RegistroEmissaoDao.java`
- [ ] Substituir `RegistroJornadaDAO.java` por `RegistroJornadaDao.java`
- [ ] Substituir `RegistroMaterialDAO.java` por `RegistroMaterialDao.java`
- [ ] Substituir `RegistroProdutividadeDAO.java` por `RegistroProdutividadeDao.java`
- [ ] Substituir `RegistroRecursoDAO.java` por `RegistroRecursoDao.java`
- [ ] Substituir `RegistroResiduoDAO.java` por `RegistroResiduoDao.java`
- [ ] Substituir `RegistroSaudeDAO.java` por `RegistroSaudeDao.java`
- [ ] Substituir `RelatorioDAO.java` por `RelatorioDao.java`

### Fase 3: Criar Services
- [ ] Criar pacote `br.edu.utfpr.service`
- [ ] Criar `TrabalhadorService.java`
- [ ] Criar `CanteiroService.java`
- [ ] Criar `ProjetoService.java`
- [ ] Criar `AvaliacaoCondicoesService.java`
- [ ] Criar `AlertaService.java`
- [ ] Criar `EPIService.java`
- [ ] Criar `FeedbackService.java`
- [ ] Criar `HistoricoAlocacaoService.java`
- [ ] Criar `MetricaProdutividadeService.java`
- [ ] Criar `ODSService.java`
- [ ] Criar `OcorrenciaSegurancaService.java`
- [ ] Criar `QualificacaoService.java`
- [ ] Criar `RegistroEmissaoService.java`
- [ ] Criar `RegistroJornadaService.java`
- [ ] Criar `RegistroMaterialService.java`
- [ ] Criar `RegistroProdutividadeService.java`
- [ ] Criar `RegistroRecursoService.java`
- [ ] Criar `RegistroResiduoService.java`
- [ ] Criar `RegistroSaudeService.java`
- [ ] Criar `RelatorioService.java`

### Fase 4: Configuração
- [ ] Criar pasta `src/main/resources/META-INF/`
- [ ] Criar `persistence.xml`
- [ ] Configurar senha do PostgreSQL no `persistence.xml`
- [ ] Verificar nome do banco, usuário e porta

---

## 🎯 Exemplo de Uso

Depois de implementar tudo, você pode usar assim:

```java
import br.edu.utfpr.util.JPAUtil;
import br.edu.utfpr.service.TrabalhadorService;
import br.edu.utfpr.model.Trabalhador;
import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        // Criar EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        
        // Criar Service
        TrabalhadorService trabalhadorService = new TrabalhadorService(em);
        
        // Criar trabalhador
        Trabalhador trabalhador = new Trabalhador();
        trabalhador.setNome("João Silva");
        trabalhador.setCpf("123.456.789-00");
        trabalhador.setStatus("ATIVO");
        
        // Inserir no banco
        trabalhadorService.inserir(trabalhador);
        System.out.println("Trabalhador cadastrado com sucesso!");
        
        // Buscar todos
        List<Trabalhador> todos = trabalhadorService.buscarTodos();
        System.out.println("Total de trabalhadores: " + todos.size());
        
        // Fechar EntityManager
        em.close();
        JPAUtil.close();
    }
}
```

---

## ⚠️ Observações Importantes

1. **Não mexa nas entidades (model)** - Elas já estão corretas com os relacionamentos
2. **Substitua as DAOs antigas** - As novas herdam de GenericDao e eliminam duplicação
3. **Configure o persistence.xml** - Principalmente a senha do banco
4. **Teste gradualmente** - Comece testando uma entidade (ex: Trabalhador) antes de usar todas

---

## 🚀 Próximos Passos (Depois de Implementar)

1. Testar inserção de um Trabalhador
2. Testar busca por ID
3. Testar relacionamentos (ex: Trabalhador com EPIs)
4. Criar testes JUnit (opcional)
5. Criar interface gráfica ou API REST (opcional)

---

**Boa sorte com a implementação!** 💪

Se tiver dúvidas, me avise! 😊

