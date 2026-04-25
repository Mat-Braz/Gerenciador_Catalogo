# 📦 Catálogo de Produtos — Spring Boot

Aplicação web para gerenciamento de catálogo de produtos, desenvolvida com Spring Boot, Thymeleaf e PostgreSQL. Projeto acadêmico desenvolvido na **FATEC**.

---

## 🚀 Tecnologias

- **Java** (Spring Boot)
- **Spring MVC** — arquitetura em camadas (Controller, Service, Repository, Model)
- **Spring Data JPA** — persistência de dados
- **Spring Security** — autenticação e controle de acesso
- **Thymeleaf** — templates HTML server-side
- **Thymeleaf Extras Spring Security 6** — integração de segurança nos templates
- **PostgreSQL** — banco de dados
- **Docker** — containerização do banco
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
│       │   │   ├── ProdutoController.java
│       │   │   ├── UsuarioController.java
│       │   │   └── CategoriaController.java
│       │   ├── models/
│       │   │   ├── ProdutoModel.java
│       │   │   ├── UsuarioModel.java
│       │   │   └── CategoriaModel.java
│       │   ├── repositories/
│       │   │   ├── ProdutoRepository.java
│       │   │   ├── UsuarioRepository.java
│       │   │   └── CategoriaRepository.java
│       │   ├── security/
│       │   │   ├── SecurityConfig.java
│       │   │   └── UsuarioDetailsService.java
│       │   └── services/
│       │       ├── ProdutoService.java
│       │       └── UsuarioService.java
│       └── resources/
│           ├── templates/
│           │   ├── login.html
│           │   ├── lista-produtos.html
│           │   ├── cadastro-produto.html
│           │   ├── cadastro-usuario.html
│           │   ├── editar-produto.html
│           │   └── cadastro-categoria.html
│           ├── application.properties
│           └── import.sql
└── pom.xml
~~~

---

## ⚙️ Como executar

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Docker

### Passos

~~~bash
# Clone o repositório
git clone https://github.com/Mat-Braz/Gerenciador_Catalogo.git

# Acesse a pasta do projeto
cd Gerenciador_Catalogo/catalogo

# Inicie o banco (Docker)
docker start catalogo_db

# Execute a aplicação
./mvnw spring-boot:run
~~~

A aplicação estará disponível em:  
👉 http://localhost:8080

---

## 🗄️ Banco de dados

O projeto utiliza PostgreSQL rodando via Docker.

### 🔹 Configuração
- Banco: `catalogo`
- Porta: `5432`
- Usuário: `postgres`

### 🔹 Inicialização

~~~bash
docker start catalogo_db
~~~

### 🔹 Inserir usuário admin

~~~bash
docker exec -it catalogo_db psql -U postgres -c "INSERT INTO tb_usuario (username, password, role) VALUES ('admin', '{noop}123456', 'ADMIN');"
~~~

### 🔹 Acessar banco

~~~bash
docker exec -it catalogo_db psql -U postgres
~~~

---

## 🧩 Relacionamento Produto x Categoria

- Um produto pertence a uma categoria
- Implementado com `@ManyToOne`

~~~java
@ManyToOne(optional = false)
@JoinColumn(name = "id_categoria", nullable = false)
private CategoriaModel categoria;
~~~

### 🔹 Impacto
- Todo produto deve possuir uma categoria
- Integridade referencial garantida no banco
- Uso de chave estrangeira (`id_categoria`)

---

## 🖥️ Funcionalidades

### 📦 Produtos
- Listagem de produtos
- Cadastro de novo produto
- Edição de produto existente
- Remoção de produto

### 🗂️ Categorias
- Cadastro de categorias
- Validação de nome (mínimo 2 caracteres)
- Integração com produtos

### 👤 Usuários
- Cadastro de usuários
- Definição de perfil (USER / ADMIN)

### 🔐 Segurança
- Login e logout
- Controle de acesso por perfil
- Restrição de ações administrativas

---

## 🔐 Autenticação

| Perfil | Permissões |
|--------|------------|
| USER   | Visualizar produtos |
| ADMIN  | Gerenciar produtos, categorias e usuários |

---

## 🧪 Observações

- O banco deve estar ativo antes de iniciar o sistema
- O usuário admin deve existir para acesso inicial
- Em caso de erro de estrutura, recriar o banco `catalogo`
- Utilizar `spring.jpa.hibernate.ddl-auto=update` durante o desenvolvimento

---

## 👨‍🎓 Autor

Desenvolvido como projeto acadêmico na **FATEC JALES**.
