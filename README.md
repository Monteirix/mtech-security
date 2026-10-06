# MTech Security

API REST de autenticação desenvolvida em Java com Spring Boot, com foco em autenticação e autorização utilizando Spring Security e JWT.

O projeto foi desenvolvido como prática de **Backend Java**, aplicando conceitos de desenvolvimento de APIs REST, persistência de dados, segurança, testes automatizados, documentação de APIs e execução com Docker.

## Tecnologias

| Tecnologia               | Utilização                                         |
| ------------------------ | -------------------------------------------------- |
| Java 23                  | Linguagem de programação                           |
| Spring Boot 4.1.1        | Framework principal                                |
| Spring Security 7.1.1    | Autenticação e autorização                         |
| Spring Data JPA          | Persistência de dados                              |
| Hibernate                | ORM                                                |
| PostgreSQL 16            | Banco de dados principal                           |
| H2                       | Banco de dados utilizado em desenvolvimento/testes |
| JJWT 0.12.6              | Geração e validação de JWT                         |
| Bean Validation          | Validação de dados                                 |
| Lombok                   | Redução de código boilerplate                      |
| Maven                    | Gerenciamento e build do projeto                   |
| Docker                   | Containerização                                    |
| Docker Compose           | Orquestração dos containers                        |
| Springdoc OpenAPI        | Documentação Swagger/OpenAPI                       |
| JUnit / Spring Boot Test | Testes automatizados                               |
| Git / GitHub             | Versionamento e hospedagem                         |

---

## Funcionalidades

* Cadastro de usuários
* Autenticação com email e senha
* Geração de JWT
* Proteção de endpoints
* Persistência com PostgreSQL
* Dockerização da aplicação
* Documentação Swagger/OpenAPI
* Testes automatizados
* Validação de dados
* Tratamento de exceções

---

## Arquitetura

O projeto utiliza uma organização em camadas para separar responsabilidades:

* **Controller** — recebe as requisições HTTP e expõe os endpoints da API.
* **Service** — concentra as regras e operações relacionadas ao domínio.
* **Repository** — responsável pelo acesso aos dados através do Spring Data JPA.
* **Entity** — representa as entidades persistidas no banco de dados.
* **DTO** — define os objetos utilizados na comunicação entre API e cliente.
* **Mapper** — auxilia na conversão entre diferentes representações de dados.
* **Security / Configuration** — concentra configurações relacionadas à segurança e autenticação.
* **Exception handling** — centraliza o tratamento das exceções e respostas de erro.

---

## Autenticação e Segurança

O fluxo de autenticação funciona da seguinte forma:

1. O usuário realiza o cadastro.
2. O usuário realiza login utilizando email e senha.
3. O Spring Security autentica as credenciais.
4. A aplicação gera um JWT.
5. O cliente envia o JWT no header `Authorization`.
6. O filtro JWT valida o token.
7. O usuário autenticado pode acessar endpoints protegidos.

Para acessar um endpoint protegido, o token deve ser enviado no seguinte formato:

```http
Authorization: Bearer <JWT>
```

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

**Resposta:** `201 Created`

Em caso de tentativa de cadastro com um email já existente:

**Resposta:** `409 Conflict`

---

### `POST /login`

Realiza a autenticação do usuário utilizando email e senha.

**Request:**

```json
{
  "email": "matheus.teste@gmail.com",
  "password": "123456"
}
```

Em caso de autenticação bem-sucedida, a API retorna o **JWT diretamente como `String`**.

**Resposta:** `200 OK`

Credenciais inválidas resultam em:

**Resposta:** `401 Unauthorized`

---

### `GET /users/protected`

Endpoint protegido que retorna os dados do usuário autenticado.

É necessário enviar um JWT válido no header `Authorization`.

**Request:**

```http
Authorization: Bearer <JWT>
```

**Resposta:** `200 OK`

```json
{
  "id": 2,
  "name": "Matheus Teste",
  "email": "matheus.teste@gmail.com",
  "role": "USER"
}
```

Sem autenticação válida:

**Resposta:** `401 Unauthorized`

---

## Swagger / OpenAPI

A API possui documentação interativa utilizando Swagger/OpenAPI.

Com a aplicação em execução, a documentação pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

O Swagger permite:

* visualizar os endpoints disponíveis;
* visualizar os schemas utilizados pela API;
* executar requisições diretamente pela interface;
* autenticar utilizando JWT através do botão `Authorize`.

---

## Docker

O projeto possui configuração para execução utilizando Docker Compose, com:

* container da aplicação;
* container PostgreSQL.

### Build da aplicação

Utilizando o Maven Wrapper:

```bash
.\mvnw.cmd clean package
```

### Subir os containers

```bash
docker compose up -d --build
```

### Verificar os containers em execução

```bash
docker ps
```

---

## Execução Local

### Pré-requisitos

* Java 23
* Docker Desktop
* Git

O projeto possui **Maven Wrapper**, portanto não é necessário instalar o Maven globalmente.

### Fluxo de execução

Clone o repositório:

```bash
git clone <repository-url>
```

Entre na pasta do projeto:

```bash
cd mtech-security
```

Gere o build:

```bash
.\mvnw.cmd clean package
```

Suba os containers:

```bash
docker compose up -d --build
```

Com os containers em execução, a API estará disponível na aplicação configurada no projeto.

A documentação pode ser acessada pelo Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Testes

O projeto possui testes automatizados relacionados à geração de tokens, serviço de usuários e contexto da aplicação.

Testes existentes:

* `TokenServiceTest`
* `UserServiceTest`
* `SecurityApplicationTests`

Para executar os testes:

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

A estrutura segue uma separação de responsabilidades entre as principais camadas da aplicação.

---

## Aprendizados

O projeto foi desenvolvido com foco no aprendizado prático de:

* Spring Security;
* autenticação e autorização;
* JWT;
* desenvolvimento de APIs REST;
* PostgreSQL;
* Docker;
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
* tratamento e documentação mais detalhada das respostas de erro;
* deploy da aplicação.

---

## Objetivo

Este projeto tem como objetivo demonstrar, na prática, conhecimentos de **Backend Java**, aplicando **Spring Boot, Spring Security, JWT, PostgreSQL, Docker, testes automatizados e Swagger/OpenAPI** no desenvolvimento de uma API REST de autenticação.
