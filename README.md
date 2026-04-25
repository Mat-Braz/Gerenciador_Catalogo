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
- **PostgreSQL** — banco de dados
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
│       │   │   └── UsuarioController.java
│       │   ├── models/
│       │   │   ├── ProdutoModel.java
│       │   │   └── UsuarioModel.java
│       │   ├── repositories/
│       │   │   ├── ProdutoRepository.java
│       │   │   └── UsuarioRepository.java
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

O projeto utiliza PostgreSQL rodando via Docker. Os dados são persistidos no container e mantidos entre sessões. 
- Para iniciar o banco antes de rodar a aplicação, execute docker start catalogo_db no PowerShell;
- O usuário admin inicial deve ser inserido manualmente com o comando docker exec -it catalogo_db psql -U postgres -c "INSERT INTO TB_USUARIO (username, password, role) VALUES ('admin', '{noop}123456', 'ADMIN');";
- Para visualizar os registros, acesse pelo IntelliJ na aba Database ou via terminal com docker exec -it catalogo_db psql -U postgres.

---

## 🔐 Autenticação

A aplicação utiliza Spring Security com autenticação via banco de dados. Os usuários são gerenciados pelo próprio sistema.

| Perfil | Permissões |
|--------|------------|
| USER   | Visualizar produtos |
| ADMIN  | Visualizar, cadastrar, editar e excluir produtos + cadastrar usuários |

- Usuários com perfil **USER** podem apenas visualizar os produtos.
- Usuários com perfil **ADMIN** têm acesso completo ao sistema.

---

## 🖥️ Funcionalidades

- Listagem de produtos
- Cadastro de novo produto
- Edição de produto existente
- Remoção de produto
- Autenticação com login e logout
- Visualização de senha na tela de login e cadastro
- Controle de acesso por perfil (USER e ADMIN)
- Botão "Novo Produto" visível apenas para administradores
- Cadastro de usuários pelo ADMIN com definição de perfil
- Tela de login personalizada com Bootstrap

---

## 👨‍🎓 Autor

Desenvolvido como projeto acadêmico na **FATEC JALES**.
