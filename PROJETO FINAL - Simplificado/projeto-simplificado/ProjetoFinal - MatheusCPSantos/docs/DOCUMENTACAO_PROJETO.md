# Sistema de Gestão de Trabalho Decente (ODS 8)

## Projeto Final da disciplina de Programação Orientada a Objetos

---

## 📋 Visão Geral

Sistema de gerenciamento para construção civil focado no **Objetivo de Desenvolvimento Sustentável 8 (ODS 8)** - Trabalho Decente e Crescimento Econômico.

O sistema gerencia **Trabalhadores**, **Canteiros de Obra** e **EPIs (Equipamentos de Proteção Individual)** com funcionalidades completas de CRUD, relatórios, exportação de dados e backup.

---

## 🎯 Requisitos Atendidos

### ✅ Funcionalidades Implementadas

1. **Manipulação de Arquivos**
   - Exportação em TXT (texto formatado)
   - Exportação em JSON (usando Jackson)
   - Exportação em BIN (binário)

2. **Serialização**
   - Sistema de backup completo
   - Restauração de dados serializados

3. **Streams API**
   - 9 métodos diferentes implementados em TrabalhadorService
   - Filtros, agrupamentos, contagens e mapeamentos

4. **CRUD Completo**
   - Create (inserir)
   - Read (buscarPorId, buscarTodos)
   - Update (atualizar/alterar)
   - Delete (remover/excluir)

5. **Relatórios**
   - Trabalhadores por Canteiro
   - EPIs Vencidos
   - Estatísticas Gerais

6. **Interface Console**
   - Menu intuitivo e organizado
   - Validações de entrada
   - Mensagens claras

7. **Validações**
   - CPF com algoritmo completo
   - CREA para engenheiros/arquitetos
   - Registro profissional para trabalhadores especializados

---

## 🏗️ Arquitetura do Projeto

```
br.edu.utfpr/
├── model/
│   ├── Trabalhador.java       (Entidade principal)
│   ├── Canteiro.java          (Canteiros de obra)
│   └── EPI.java               (Equipamentos de segurança)
├── dao/
│   └── GenericDao.java        (Operações de banco de dados)
├── service/
│   ├── TrabalhadorService.java (Lógica de negócio + Streams)
│   ├── CanteiroService.java
│   └── EPIService.java
├── util/
│   ├── JPAUtil.java           (Configuração JPA)
│   ├── ValidacaoUtil.java     (CPF, CREA, Registros)
│   ├── ExportacaoUtil.java    (TXT, JSON, BIN)
│   └── BackupUtil.java        (Serialização)
├── MenuConsoleSimplificado.java (Interface principal)
└── PopularBancoDados.java     (Dados iniciais)
```

---

## 🚀 Como Executar

### 1. Pré-requisitos

- Java 17+
- PostgreSQL 17
- Maven

### 2. Configurar Banco de Dados

```sql
CREATE DATABASE trabalho_decente;
```

Configurar `persistence.xml`:
```xml
<property name="jakarta.persistence.jdbc.url" 
          value="jdbc:postgresql://localhost:5432/trabalho_decente"/>
<property name="jakarta.persistence.jdbc.user" value="seu_usuario"/>
<property name="jakarta.persistence.jdbc.password" value="sua_senha"/>
```

### 3. Popular Banco de Dados

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="br.edu.utfpr.PopularBancoDados"
```

### 4. Executar Sistema

```bash
mvn exec:java -Dexec.mainClass="br.edu.utfpr.MenuConsoleSimplificado"
```

---

## 📊 Demonstração das Funcionalidades

### 1. CRUD de Trabalhadores

**Cadastrar:**
- Nome completo
- CPF (com validação)
- Função
- CREA (se engenheiro/arquiteto)
- Registro profissional (se pedreiro/eletricista/etc)
- Tipo de contrato (CLT/PJ/TEMPORARIO)
- Canteiro atual

**Listar, Buscar, Atualizar e Remover:**
- Todas as operações disponíveis no menu

### 2. Streams API (6 exemplos no menu)

```java
// 1. Filtrar por função
filtrarPorFuncao("Pedreiro")

// 2. Buscar por CPF
buscarPorCPF("12345678909")

// 3. Contar por tipo de contrato
contarPorTipoContrato() // Map<String, Long>

// 4. Listar apenas nomes
listarNomes() // List<String> ordenada

// 5. Calcular estatísticas
calcularMediaSalarial()

// 6. Buscar com CREA
buscarComCREA()
```

### 3. Exportação de Dados

**Formatos disponíveis:**
- **TXT**: Arquivo texto formatado com cabeçalho
- **JSON**: Usando Jackson (jackson-databind + jackson-datatype-jsr310)
- **BIN**: Serialização binária

**Exemplo de uso:**
1. Menu Principal → 5. Exportar Dados
2. Escolher entidade (Trabalhadores/Canteiros/EPIs)
3. Escolher formato (TXT/JSON/BIN)
4. Informar nome do arquivo
5. Sistema mostra caminho completo do arquivo

### 4. Backup e Restauração

**Fazer Backup:**
- Serializa lista de trabalhadores
- Inclui data/hora do backup
- Salva em `backups/trabalhadores.bak`

**Restaurar:**
- Lê arquivo serializado
- Mostra informações do backup
- Retorna lista de objetos

### 5. Relatórios

**1. Trabalhadores por Canteiro:**
- Agrupa trabalhadores por canteiro usando Streams
- Mostra quantidade por canteiro

**2. EPIs Vencidos:**
- Filtra EPIs com data de validade vencida
- Mostra trabalhador associado

**3. Estatísticas Gerais:**
- Total de trabalhadores, canteiros e EPIs
- Quantidade com CREA
- EPIs vencidos

---

## 🔧 Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Java | 17+ | Linguagem principal |
| JPA/Hibernate | 6.6.1 | ORM |
| PostgreSQL | 17.6 | Banco de dados |
| Jackson | 2.16.0 | JSON (databind + jsr310) |
| Maven | 3.x | Gerenciamento de dependências |

---

## 📝 Validações Implementadas

### CPF
Algoritmo completo de validação:
- Verifica tamanho (11 dígitos)
- Rejeita sequências repetidas (11111111111)
- Calcula e valida dígitos verificadores

### CREA
- Obrigatório para: Engenheiro Civil, Engenheiro de Segurança, Engenheiro Eletricista, Arquiteto
- Formato: 6 a 10 dígitos

### Registro Profissional
- Obrigatório para: Pedreiro, Eletricista, Encanador, Carpinteiro, Pintor
- Formato: 4 a 8 dígitos

---

## 📦 Estrutura de Diretórios Criados

```
projeto/
├── exports/          (arquivos TXT, JSON, BIN)
├── backups/          (arquivos de backup .bak)
└── src/main/java/    (código fonte)
```

---

## ⚠️ Observações Importantes

1. **Dados de Teste**: Execute `PopularBancoDados.java` antes da primeira execução
2. **Jackson**: Projeto usa Jackson (não GSON) conforme requisito do professor
3. **Formatos**: TXT, JSON e BIN (não CSV) conforme especificado
4. **Comentários**: Remover todos os comentários antes da entrega final
5. **Apresentação**: Sistema foi projetado para ser simples de demonstrar

---

## 🎯 Diferenciais do Projeto

✅ Código limpo e organizado
✅ Validações profissionais (CPF, CREA)
✅ Interface console intuitiva
✅ Múltiplos exemplos de Streams API
✅ Sistema de backup robusto
✅ Relatórios úteis e práticos
✅ Arquitetura escalável (DAO + Service)
✅ Tratamento de erros adequado

---

## 👨‍💻 Autor

Projeto desenvolvido para o Projeto Final de Programação Orientada a Objetos
UTFPR - Universidade Tecnológica Federal do Paraná

---

## 📄 Licença

Projeto acadêmico - Uso educacional
