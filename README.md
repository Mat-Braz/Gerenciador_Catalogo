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
CatalogoSpring/
└── catalogo/
    ├── data/
    ├── src/
    │   └── main/
    │       ├── java/br/com/fatec/catalogo/
    │       │   ├── controllers/
    │       │   │   ├── AuthController.java
    │       │   │   ├── CategoriaController.java
    │       │   │   ├── ProdutoController.java
    │       │   │   └── UsuarioController.java
    │       │   ├── models/
    │       │   │   ├── CategoriaModel.java
    │       │   │   ├── ProdutoModel.java
    │       │   │   └── UsuarioModel.java
    │       │   ├── repositories/
    │       │   │   ├── CategoriaRepository.java
    │       │   │   ├── ProdutoRepository.java
    │       │   │   └── UsuarioRepository.java
    │       │   ├── security/
    │       │   │   ├── SecurityConfig.java
    │       │   │   └── UsuarioDetailsService.java
    │       │   ├── services/
    │       │   │   ├── CategoriaService.java
    │       │   │   ├── ProdutoService.java
    │       │   │   └── UsuarioService.java
    │       │   └── CatalogoApplication.java
    │       └── resources/
    │           ├── static/
    │           ├── templates/
    │           │   ├── cadastrar-categoria.html
    │           │   ├── cadastro-produto.html
    │           │   ├── cadastro-usuario.html
    │           │   ├── editar-produto.html
    │           │   ├── lista-produtos.html
    │           │   └── login.html
    │           ├── application.properties
    │           └── import.sql
    └── test/
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

## 🧩 Relacionamento Categoria x Produto

- Uma categoria pode conter múltiplos produtos (`@OneToMany`)
- Um produto pertence a uma única categoria (`@ManyToOne`)

~~~java
// CategoriaModel.java
@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<ProdutoModel> produtos;
~~~

~~~java
// ProdutoModel.java
@ManyToOne(optional = false)
@JoinColumn(name = "id_categoria", nullable = false)
private CategoriaModel categoria;
~~~

### 🔹 Impacto
- Todo produto deve possuir uma categoria
- Uma categoria agrupa e organiza múltiplos produtos
- Integridade referencial garantida no banco via chave estrangeira (`id_categoria`)
- Remoção em cascata configurável via `CascadeType`

---

## 🖥️ Funcionalidades

### 📦 Produtos
- Listagem de produtos
- Cadastro de novo produto
- Edição de produto existente
- Remoção de produto
- **Busca por categoria** — filtra produtos pela categoria selecionada

### 🗂️ Categorias
- **Cadastro de categorias** — formulário com validação de nome (mínimo 2 caracteres)
- Listagem de todas as categorias
- Integração bidirecional com produtos via `@OneToMany`

### 👤 Usuários
- Cadastro de usuários
- Definição de perfil (USER / ADMIN)

### 🔐 Segurança
- Login e logout
- Controle de acesso por perfil
- Restrição de ações administrativas

---

## 🔍 Busca por Categoria

A busca por categoria permite filtrar os produtos exibidos na listagem de acordo com a categoria selecionada.

### 🔹 Como funciona

Na tela de listagem de produtos, o usuário seleciona uma categoria no filtro disponível. A aplicação retorna apenas os produtos vinculados àquela categoria.

### 🔹 Implementação no Repository

~~~java
// ProdutoRepository.java
List<ProdutoModel> findByCategoria_IdCategoria(Long idCategoria);

// Ou por nome da categoria
List<ProdutoModel> findByCategoria_NomeContainingIgnoreCase(String nome);
~~~

### 🔹 Implementação no Controller

~~~java
// ProdutoController.java
@GetMapping("/produtos")
public String listar(
    @RequestParam(required = false) Long categoriaId,
    Model model
) {
    List<ProdutoModel> produtos = (categoriaId != null)
        ? produtoService.buscarPorCategoria(categoriaId)
        : produtoService.listarTodos();

    model.addAttribute("produtos", produtos);
    model.addAttribute("categorias", categoriaService.listarTodas());
    model.addAttribute("categoriaSelecionada", categoriaId);
    return "lista-produtos";
}
~~~

---

## 🔐 Autenticação

| Perfil | Permissões |
|--------|------------|
| USER   | Visualizar e buscar produtos por categoria |
| ADMIN  | Gerenciar produtos, categorias e usuários |

---

## 🧪 Observações

- O banco deve estar ativo antes de iniciar o sistema
- O usuário admin deve existir para acesso inicial
- Em caso de erro de estrutura, recriar o banco `catalogo`
- Utilizar `spring.jpa.hibernate.ddl-auto=update` durante o desenvolvimento
- Ao recriar o banco, **cadastrar as categorias antes de inserir produtos**

---

## 👨‍🎓 Autor

Desenvolvido como projeto acadêmico na **FATEC JALES**.
