
# 📦 project-supplier-portal

Sistema de gerenciamento de **consultores**, **clientes** e **pontos**, com autenticação JWT, 
filtros avançados e exportação de planilhas `.xlsx`. Desenvolvido com Java 17 e Spring Boot 3.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL 8+
- Lombok
- Flyway
- Swagger (springdoc-openapi)
- Apache POI (exportação Excel)
- JWT (autenticação)
- Docker (build da aplicação)
- Docker Compose (somente app)

---

## 📁 Requisitos

- IntelliJ IDEA (ou outro IDE)
- MySQL 8+ (externo)
- Java 17
- Maven 3.8+
- Docker (opcional)

---

## ⚙️ Subindo o projeto no IntelliJ

1. Clone o projeto:
   ```bash
   git clone https://github.com/seu-usuario/project-supplier-portal.git
   ```

2. Crie o banco de dados manualmente no MySQL:
   ```sql
   CREATE DATABASE db-supplier-portal CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. Configure seu `application.yml` com usuário e senha do banco:

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/db-supplier-portal
       username: root
       password: sua_senha
   ```

4. Rode o projeto:
   - Pela IDE (classe `SupplierPortalApplication`)
   - Ou pelo terminal:
     ```bash
     ./mvnw spring-boot:run
     ```

5. O Flyway criará automaticamente as tabelas:
   - `tb_consultor`
   - `tb_cliente`
   - `tb_ponto`
   - `tb_usuario`

---

## 🔐 Autenticação

1. Usuário padrão cadastrado pelo Flyway:
   - **Usuário:** `admin`
   - **Senha:** `admin`

2. Obtenha o token JWT:
   - `POST /auth/login`
   ```json
   {
     "username": "admin",
     "password": "admin"
   }
   ```

3. O retorno será:
   ```json
   { "token": "eyJhbGciOi..." }
   ```

4. Use o token em chamadas protegidas:
   - Header:  
     `Authorization: Bearer eyJhbGciOi...`

---

## 🧪 Testando no Swagger

1. Acesse: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
2. Clique em `Authorize` e cole o token JWT.
3. Teste os endpoints:
   - `/api/consultores`
   - `/api/clientes`
   - `/api/pontos`
   - `/api/pontos/filtro`
   - `/api/pontos/filtro/excel`

---

## 📬 Testando no Postman

1. Requisição de login:

   **POST** `http://localhost:8080/auth/login`
   ```json
   {
     "username": "admin",
     "password": "admin"
   }
   ```

2. Use o token retornado em outras requisições com Header:

   ```
   Authorization: Bearer <token>
   ```

3. Exemplo de filtro de pontos:

   **GET** `http://localhost:8080/api/pontos/filtro?dataInicial=2024-01-01&dataFinal=2024-12-31`

---

## 🐳 Docker (apenas app, banco é externo)

### Build e execução

```bash
docker-compose up --build -d
```

- Acesse [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- O app se conecta ao banco externo via `host.docker.internal`

---

## 📂 Estrutura de pacotes

```
com.portal.supplierportal
├── config           → configurações Swagger, segurança
├── controller       → REST controllers
├── dto              → Data Transfer Objects
├── exception        → tratadores globais
├── mapper           → conversores entre DTO e entidade
├── model            → entidades JPA
├── repository       → repositórios Spring Data
├── security         → JWT e filtros de autenticação
├── service          → regras de negócio
└── SupplierPortalApplication.java
```

---

## 📑 Endpoints principais

### 🔐 Autenticação
- `POST /auth/login`

### 👤 Consultores
- `GET /api/consultores`
- `POST /api/consultores`
- `PUT /api/consultores/{id}`
- `DELETE /api/consultores/{id}`
- `GET /api/consultores/cpf/{cpf}`

### 🏢 Clientes
- `GET /api/clientes`
- `POST /api/clientes`
- `GET /api/clientes/cnpj/{cnpj}`

### ⏱ Pontos
- `GET /api/pontos`
- `POST /api/pontos`
- `GET /api/pontos/filtro?dataInicial=...&dataFinal=...`
- `GET /api/pontos/filtro/excel` → gera planilha `.xlsx`

---

## 📄 Licença

Distribuído para fins comerciais. Abstract Tech.
