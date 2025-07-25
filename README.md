
# 📦 project-supplier-portal

Sistema de gerenciamento de **consultores**, **clientes** e **pontos**, com autenticação JWT, 
filtros avançados e exportação de planilhas `.xlsx`. Desenvolvido com Java 17 e Spring Boot 3.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
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
     # Com Docker:
     ./mvn spring-boot:run -Dspring.docker.compose.enabled=true
     ```
     ```bash
     # Sem Docker:
     ./mvn spring-boot:run -Dspring-boot.run.profiles=local -Dspring.docker.compose.enabled=false
      ```
     
5. O Flyway criará automaticamente as tabelas:
   - `tb_user`
   - `tb_consultor`
   - `tb_cliente`
   - `tb_ponto`
   

---

## 🔐 Autenticação

1. Usuário padrão cadastrado pelo Flyway:
   - **Usuário:** `admin`
   - **Senha:** `admin`

2. Obtenha o token JWT:
   # Cadastro:
   - `POST /api/v1/auth/signup`
   ```json
   {
    "firstName": "Admin",
    "lastName": "Admin",
    "email": "admin@rhtalentos.tec.br",
    "password": "admin" 
   }
   ```
   Retorno:   
   ```json
   { "token": "eyJhbGciOi..." }
   ```
   
   # Gerar Token:
   - `POST /api/v1/auth/signin`
   ```json
   {
    "email": "admin@rhtalentos.tec.br",
    "password": "admin" 
   }
   ```
   Retorno:
   ```json
   { "token": "eyJhbGciOi..." }
   ```  

   # Validar o Token:
   - `GET /api/v1/resource`
   ```header:
         Authorization: Bearer [token gerado no enpoint /api/v1/auth/signin
   ```

3. Use o token em chamadas protegidas:
   - Header:  
     `Authorization: Bearer eyJhbGciOi...`

---

## 🧪 Testando no Swagger

1. Acesse: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
2. Clique em `Authorize` e cole o token JWT.
3. Teste os endpoints:
   - `/api/v1/consultores`
   - `/api/v1/clientes`
   - `/api/v1/pontos`
   - `/api/v1/pontos/filtro`
   - `/api/v1/pontos/filtro/excel`

---

## 📬 Testando no Postman

1. Requisição de login:

   **POST** `http://localhost:8080/api/v1/auth/signup`
   - cURL:
        curl --location 'http://localhost:8080/api/v1/auth/signup' \
        --header 'Content-Type: application/json' \
        --header 'Cookie: JSESSIONID=3D837A1374CB577B65FC623AB4B25030' \
        --data-raw '{
        "firstName": "Admin",
        "lastName": "Admin",
        "email": "admin@rhtalentos.tec.br",
        "password": "admin"   
        }'

2. Requisição de Token:

   **POST** `http://localhost:8080/api/v1/auth/signin`
   - cURL:
        curl --location 'http://localhost:8080/api/v1/auth/signin' \
        --header 'Content-Type: application/json' \
        --header 'Cookie: JSESSIONID=3D837A1374CB577B65FC623AB4B25030' \
        --data-raw '{
        "email": "admin@rhtalentos.tec.br",
        "password": "admin"   
        }'

3. Listar todos os users ou por nome :
   **GET**
    - http://localhost:8080/api/v1/auth/users
   - http://localhost:8080/api/v1/auth/users?nome=admin

4. Alterar o user:
   **PUT**
   - cURL:
         curl --location --request PUT 'http://localhost:8080/api/v1/auth/update' \
         --header 'Content-Type: application/json' \
         --data-raw '    {
         "id": 1,
         "firstName": "Admin",
         "lastName": "Administrator",
         "email": "admin@rhtalentos.tec.br",
         "password": "$2a$10$J7riw3vmIJDqPzVylot0Nepz2xajhd.yxWgKRnEHklmYVKWgl.xey",
         "role": "USER"
         }'
5. Use o token retornado em outras requisições com Header:

   ```
   Authorization: Bearer <token>
   ```

6. Exemplo de filtro de pontos:

   **GET** `http://localhost:8080/api/v1/pontos/filtro?dataInicial=2025-07-01&dataFinal=2025-07-31`

---

## 🐳 Docker (apenas app, banco é externo)

### Build e execução

```bash
docker-compose up --build -d
```

- Acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- O app se conecta ao banco externo via `host.docker.internal`

---

## 📂 Estrutura de pacotes

```
com.portal.supplierportal
├── config           → configurações Swagger, segurança
├── controller       → REST controllers
├── dto              → Data Transfer Objects
|+++++ request
|+++++ response
├── exception        → tratadores globais
├── mapper           → conversores entre DTO e entidade
├── model            → entidades JPA
├── repository       → repositórios Spring Data
├── service          → regras de negócio
|+++++ impl
└── SupplierPortalApplication.java
```

---

## 📑 Endpoints principais

### 🔐 Autenticação
- `POST api/v1/auth/signup`
- `POST api/v1/auth/signin`
- `GET api/v1/auth/users`
- `PUT api/v1/auth/update`

### 👤 Consultores
- `GET /api/v1/consultores`
- `POST /api/v1/consultores`
- `PUT /api/v1/consultores/{id}`
- `DELETE /api/v1/consultores/{id}`
- `GET /api/v1/consultores/cpf/{cpf}`

### 🏢 Clientes
- `GET /api/v1/clientes`
- `POST /api/v1/clientes`
- `GET /api/v1/clientes/cnpj/{cnpj}`

### ⏱ Pontos
- `GET /api/v1/pontos`
- `POST /api/v1/pontos`
- `GET /api/v1/pontos`
- `GET /api/v1/pontos/pagina`
- `GET /api/v1/pontos/filtro?dataInicial=...&dataFinal=...`
- `GET /api/v1/pontos/filtro/excel` → gera planilha `.xlsx`
- `GET /api/v1/pontos/gerar/excel` → gera planilha `.xlsx`

---

## 📄 Licença

Distribuído para fins comerciais. Company: Abstract Tech.
