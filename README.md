# MTech Security

API REST de autenticação desenvolvida em **Java 23 com Spring Boot**, utilizando **Spring Security e JWT** para autenticação e proteção de endpoints.

O projeto foi desenvolvido com foco em prática de **Backend Java**, explorando segurança, persistência de dados, testes automatizados, documentação de APIs e containerização.

**Java 23 · Spring Boot · Spring Security · JWT · PostgreSQL · Docker · Swagger/OpenAPI**

---

## Tecnologias

| Tecnologia               | Utilização                    |
| ------------------------ | ----------------------------- |
| Java 23                  | Linguagem de programação      |
| Spring Boot 4.1.1        | Framework principal           |
| Spring Security 7.1.1    | Autenticação e autorização    |
| Spring Data JPA          | Persistência de dados         |
| Hibernate                | ORM                           |
| PostgreSQL 16            | Banco de dados principal      |
| H2                       | Banco de dados em memória     |
| JJWT 0.12.6              | Geração e validação de JWT    |
| Bean Validation          | Validação de dados            |
| Lombok                   | Redução de código boilerplate |
| Maven                    | Gerenciamento e build         |
| Docker                   | Containerização               |
| Docker Compose           | Orquestração dos containers   |
| Springdoc OpenAPI 3.1.0  | Documentação Swagger/OpenAPI  |
| JUnit / Spring Boot Test | Testes automatizados          |
| Git / GitHub             | Versionamento e hospedagem    |

---

## Funcionalidades

* Cadastro de usuários
* Autenticação com email e senha
* Geração de JWT
* Proteção de endpoints
* Persistência de usuários com PostgreSQL
* Validação de dados
* Tratamento de exceções
* Documentação interativa com Swagger/OpenAPI
* Testes automatizados
* Execução da aplicação com Docker Compose

---

## Arquitetura

O projeto utiliza uma organização em camadas, separando responsabilidades entre os principais componentes da aplicação:

* **Controller** — exposição dos endpoints e processamento das requisições HTTP.
* **Service** — implementação das regras e operações da aplicação.
* **Repository** — acesso e persistência dos dados através do Spring Data JPA.
* **Entity** — representação das entidades persistidas no banco.
* **DTO** — objetos utilizados na entrada e saída de dados da API.
* **Mapper** — conversão entre diferentes representações de dados.
* **Security / Configuration** — configurações relacionadas à autenticação e segurança.
* **Exception** — tratamento das exceções e padronização das respostas de erro.

Essa organização mantém as responsabilidades separadas e facilita a manutenção e evolução da aplicação.

---

## Autenticação e Segurança

A autenticação utiliza **Spring Security** e **JWT**.

O fluxo principal é:

```text
Cadastro
   ↓
Login
   ↓
Spring Security autentica as credenciais
   ↓
JWT é gerado
   ↓
Cliente envia o JWT
   ↓
Filtro JWT valida o token
   ↓
Acesso ao endpoint protegido
```

O token deve ser enviado no header `Authorization` utilizando o formato:

```http
Authorization: Bearer <JWT>
```

Endpoints protegidos exigem um JWT válido.

---

## Endpoints

### `POST /users/register`

Realiza o cadastro de um novo usuário.

**Request:**

```json
{
  "name": "Matheus Teste",
  "email": "matheus.teste@gmail.com",
  "password": "123456"
}
```

**Resposta de sucesso:**

```text
201 Created
```

Caso o email já esteja cadastrado:

```text
409 Conflict
```

---

### `POST /login`

Realiza a autenticação utilizando email e senha.

**Request:**

```json
{
  "email": "matheus.teste@gmail.com",
  "password": "123456"
}
```

Em caso de autenticação bem-sucedida, o endpoint retorna **o próprio JWT como `String`**.

**Resposta de sucesso:**

```text
200 OK
```

Credenciais inválidas:

```text
401 Unauthorized
```

---

### `GET /users/protected`

Endpoint protegido que retorna os dados do usuário autenticado.

É necessário enviar um JWT válido:

```http
Authorization: Bearer <JWT>
```

**Resposta de sucesso:**

```json
{
  "id": 2,
  "name": "Matheus Teste",
  "email": "matheus.teste@gmail.com",
  "role": "USER"
}
```

```text
200 OK
```

Sem autenticação válida:

```text
401 Unauthorized
```

---

## Swagger / OpenAPI

A API possui documentação interativa utilizando **Swagger/OpenAPI**.

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

O Swagger permite:

* visualizar os endpoints;
* consultar os schemas utilizados pela API;
* executar requisições;
* informar o JWT através do botão `Authorize`;
* testar endpoints protegidos diretamente pela interface.

---

## Docker

O projeto utiliza **Docker Compose** para executar a aplicação e o PostgreSQL em containers separados.

### Containers

| Container            | Função                |
| -------------------- | --------------------- |
| `mtech-security-api` | Aplicação Spring Boot |
| `mtech-postgres`     | Banco PostgreSQL 16   |

A aplicação se conecta ao PostgreSQL através da rede criada pelo Docker Compose.

### Build

O projeto possui Maven Wrapper, portanto o Maven não precisa estar instalado globalmente.

Para gerar o build:

```bash
.\mvnw.cmd clean package
```

### Subir a aplicação

```bash
docker compose up -d --build
```

### Verificar os containers

```bash
docker ps
```

---

## Execução

### Pré-requisitos

* Java 23
* Docker Desktop
* Git

### 1. Clonar o repositório

```bash
git clone https://github.com/Monteirix/mtech-security.git
```

### 2. Entrar na pasta

```bash
cd mtech-security
```

### 3. Gerar o build

```bash
.\mvnw.cmd clean package
```

### 4. Subir os containers

```bash
docker compose up -d --build
```

### 5. Acessar a documentação

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Testes

O projeto possui testes automatizados relacionados a diferentes componentes da aplicação:

* `TokenServiceTest`
* `UserServiceTest`
* `SecurityApplicationTests`

Os testes abrangem componentes relacionados à geração de tokens, serviço de usuários e contexto da aplicação.

Para executar:

```bash
.\mvnw.cmd test
```

---

## Estrutura do Projeto

```text
src
├── main
│   └── java
│       └── com.mtech.security
│           ├── config
│           ├── controller
│           ├── dto
│           ├── entities
│           ├── exception
│           ├── filter
│           ├── mapper
│           ├── repository
│           ├── service
│           └── ...
└── test
```

A estrutura segue a separação de responsabilidades utilizada pela aplicação.

---

## Aprendizados

O projeto foi desenvolvido com foco no aprendizado prático de:

* Spring Boot;
* Spring Security;
* autenticação e autorização;
* JWT;
* desenvolvimento de APIs REST;
* PostgreSQL;
* Docker e Docker Compose;
* testes automatizados;
* documentação de APIs;
* organização em camadas;
* integração entre diferentes componentes de uma aplicação backend.

---

## Próximos Passos

As funcionalidades abaixo são possibilidades de evolução e **não estão implementadas atualmente**:

* gerenciamento de permissões mais granular;
* implementação de refresh token;
* ampliação dos testes de integração;
* documentação mais detalhada das respostas de erro;
* deploy da aplicação.

---

## Objetivo

O MTech Security é um projeto de estudo e portfólio focado em **Backend Java**, no qual foram aplicados conceitos e tecnologias como **Spring Boot, Spring Security, JWT, PostgreSQL, Docker, testes automatizados e Swagger/OpenAPI** no desenvolvimento de uma API REST de autenticação.

