# 📦 Catálogo de Produtos — Spring Boot

Aplicação web para gerenciamento de catálogo de produtos, desenvolvida com Spring Boot, Thymeleaf e banco de dados H2. Projeto acadêmico desenvolvido na **FATEC**.

---

## 🚀 Tecnologias

- **Java** (Spring Boot)
- **Spring MVC** — arquitetura em camadas (Controller, Service, Repository, Model)
- **Spring Data JPA** — persistência de dados
- **Spring Security** - autenticação e controle de acesso
- **Thymeleaf** — templates HTML server-side
- **Thymeleaf Extras Spring Security 6** - integração de segurança nos templates
- **H2 Database** — banco de dados em memória
- **Maven** — gerenciamento de dependências

---

## 📁 Estrutura do Projeto

~~~
catalogo/
├── src/
│   └── main/
│       ├── java/br/com/fatec/catalogo/
│       │   ├── CatalogoApplication.java
│       │   ├── controllers/
│       │   │   ├── AuthController.java
│       │   │   └── ProdutoController.java
│       │   ├── models/
│       │   │   └── ProdutoModel.java
│       │   ├── repositories/
│       │   │   └── ProdutoRepository.java
│       │   ├── security/
│       │   │   └── SecurityConfig.java
│       │   └── services/
│       │       └── ProdutoService.java
│       └── resources/
│           ├── templates/
│           │   ├── login.html
│           │   ├── lista-produtos.html
│           │   ├── cadastro-produto.html
│           │   └── editar-produto.html
│           ├── application.properties
│           └── import.sql
└── pom.xml
~~~

---

## ⚙️ Como executar

### Pré-requisitos

- Java 17+
- Maven 3.8+

### Passos

~~~bash
# Clone o repositório
git clone https://github.com/Mat-Braz/Gerenciador_Catalogo.git

# Acesse a pasta do projeto
cd Gerenciador_Catalogo/catalogo

# Execute a aplicação
./mvnw spring-boot:run
~~~

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 🗄️ Banco de dados

O projeto utiliza **H2** (banco em memória). Os dados iniciais são carregados automaticamente via `import.sql` ao iniciar a aplicação.

O console do H2 pode ser acessado em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---

## 🔐 Autenticação

A aplicação utiliza Spring Security com usuários em memória:

| Usuário | Senha | Perfil      |
|---------|-------|-------------|
| aluno   | 12345 | USER        |
| admin   | 12345 | ADMIN, USER |

- Usuários com perfil **USER** podem apenas visualizar os produtos.
- Usuários com perfil **ADMIN** têm acesso completo (cadastrar, editar e excluir).

---

## 🖥️ Funcionalidades

- Listagem de produtos
- Cadastro de novo produto
- Edição de produto existente
- Remoção de produto
- Autenticação com login e logout
- Controle de acesso por perfil (USER e ADMIN)
- Botão "Novo Produto" visível apenas para administradores
- Tela de login personalizada com Bootstrap

---

## 👨‍🎓 Autor

Desenvolvido como projeto acadêmico na **FATEC JALES**.
