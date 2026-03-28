# 📦 Catálogo de Produtos — Spring Boot

Aplicação web para gerenciamento de catálogo de produtos, desenvolvida com Spring Boot, Thymeleaf e banco de dados H2. Projeto acadêmico desenvolvido na **FATEC**.

---

## 🚀 Tecnologias

- **Java** (Spring Boot)
- **Spring MVC** — arquitetura em camadas (Controller, Service, Repository, Model)
- **Spring Data JPA** — persistência de dados
- **Thymeleaf** — templates HTML server-side
- **H2 Database** — banco de dados em memória
- **Maven** — gerenciamento de dependências

---

## 📁 Estrutura do Projeto

```
catalogo/
├── src/
│   └── main/
│       ├── java/br/com/fatec/catalogo/
│       │   ├── CatalogoApplication.java
│       │   ├── controllers/
│       │   │   └── ProdutoController.java
│       │   ├── models/
│       │   │   └── ProdutoModel.java
│       │   ├── repositories/
│       │   │   └── ProdutoRepository.java
│       │   └── services/
│       │       └── ProdutoService.java
│       └── resources/
│           ├── templates/
│           │   ├── lista-produtos.html
│           │   ├── cadastro-produto.html
│           │   └── editar-produto.html
│           ├── application.properties
│           └── import.sql
└── pom.xml
```

---

## ⚙️ Como executar

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Passos

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/catalogo-spring.git

# Acesse a pasta do projeto
cd catalogo-spring/catalogo

# Execute a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 🗄️ Banco de dados

O projeto utiliza **H2** (banco em memória). Os dados iniciais são carregados automaticamente via `import.sql` ao iniciar a aplicação.

O console do H2 pode ser acessado em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 🖥️ Funcionalidades

- Listagem de produtos
- Cadastro de novo produto
- Edição de produto existente
- Remoção de produto

---

## 👨‍🎓 Autor

Desenvolvido como projeto acadêmico na **FATEC JALES**.
