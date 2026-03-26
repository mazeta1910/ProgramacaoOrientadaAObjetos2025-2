# Sistema de Gestão de Trabalho Decente (ODS 8)

Sistema de gerenciamento para construção civil focado no **Objetivo de Desenvolvimento Sustentável 8**.

## 🚀 Execução Rápida

### 1. Configurar banco de dados

```sql
CREATE DATABASE trabalho_decente;
```

Editar `src/main/resources/META-INF/persistence.xml`:
```xml
<property name="jakarta.persistence.jdbc.user" value="seu_usuario"/>
<property name="jakarta.persistence.jdbc.password" value="sua_senha"/>
```

### 2. Popular dados iniciais

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="br.edu.utfpr.PopularBancoDados"
```

### 3. Executar sistema

```bash
mvn exec:java -Dexec.mainClass="br.edu.utfpr.MenuConsoleSimplificado"
```

## 📋 Funcionalidades

✅ CRUD completo (Trabalhadores, Canteiros, EPIs)
✅ Streams API (9 métodos)
✅ Exportação (TXT, JSON, BIN)
✅ Serialização (Backup/Restore)
✅ Relatórios
✅ Validações (CPF, CREA)

## 📚 Documentação

- **DOCUMENTACAO_PROJETO.md** - Documentação completa

## 🛠️ Tecnologias

- Java 17+
- JPA/Hibernate 6.6.1
- PostgreSQL 17
- Jackson 2.16.0
- Maven

## 📦 Estrutura

```
br.edu.utfpr/
├── model/          (Entidades)
├── dao/            (Acesso a dados)
├── service/        (Lógica de negócio)
├── util/           (Utilitários)
├── MenuConsoleSimplificado.java
└── PopularBancoDados.java
```

## 👨‍💻 Autor

Projeto de Programação Orientada a Objetos
UTFPR
