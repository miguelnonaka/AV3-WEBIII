# AV2
---

## Como executar o projeto

### 1. Clone o repositório

---

### 2. Configuração do banco

#### 2.1 Configure o `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/generaldb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

#### 2.2 Importante

- Certifique-se de que o MySQL está rodando
- O banco `generaldb` será criado automaticamente

---

### 3. Execução do projeto

#### 3.1 Caminho da aplicação

```text
src/main/java/com/autobots/automanager/AutomanagerApplication.java
```

#### 3.2 Execução

Execute pela IDE.

---

### 4. Teste das rotas

Ferramentas: Insomnia ou Postman

---

# CLIENTES

Base: `/clientes`

## GET

### Listar todos
```text
GET localhost:8080/clientes
```

### Buscar por ID
```text
GET localhost:8080/clientes/{id}
```

---

## POST

### Criar cliente
```text
POST localhost:8080/clientes
```

### Body (JSON completo permitido pelo DTO atual)
```json
{
  "nome": "Pedro Alcântara de Bragança e Bourbon",
  "nomeSocial": "Dom Pedro II",
  "dataNascimento": "2002-06-15T00:00:00.000+00:00"
}
```

---

## PUT

### Atualizar cliente
```text
PUT localhost:8080/clientes/{id}
```

### Body (JSON completo)
```json
{
  "nome": "Pedro Atualizado",
  "nomeSocial": "Dom Pedro Atualizado",
  "dataNascimento": "2000-01-01T00:00:00.000+00:00"
}
```

### Respostas esperadas
- `200 OK` quando atualizado
- `404 Not Found` quando não existir

---

## DELETE

### Excluir cliente
```text
DELETE localhost:8080/clientes/{id}
```

---

# DOCUMENTO

Base: `/documento`

## GET

### Listar todos
```text
GET localhost:8080/documento
```

### Buscar por ID
```text
GET localhost:8080/documento/{id}
```

---

## POST

### Criar documento com cliente associado
```text
POST localhost:8080/documento
```

### Body (JSON completo)
```json
{
  "tipo": "CPF",
  "numero": "12345678900",
  "cliente": {
    "id": 1
  }
}
```

---

## PUT

### Atualizar documento
```text
PUT localhost:8080/documento
```

### Body (JSON completo)
```json
{
  "id": 1,
  "tipo": "CPF",
  "numero": "99999999999",
  "cliente": {
    "id": 1
  }
}
```

---

## DELETE

### Excluir documento
```text
DELETE localhost:8080/documento/{id}
```

---

# ENDEREÇOS

Base: `/enderecos`

## GET

### Listar todos
```text
GET localhost:8080/enderecos
```

### Buscar por ID
```text
GET localhost:8080/enderecos/{id}
```

---

## POST

### Criar endereço com cliente associado
```text
POST localhost:8080/enderecos
```

### Body (JSON completo)
```json
{
  "estado": "SP",
  "cidade": "São José dos Campos",
  "bairro": "Centro",
  "rua": "Rua A",
  "numero": "100",
  "codigoPostal": "12200000",
  "informacoesAdicionais": "Casa",
  "cliente": {
    "id": 1
  }
}
```

---

## PUT

### Atualizar endereço
```text
PUT localhost:8080/enderecos
```

### Body (JSON completo)
```json
{
  "id": 1,
  "estado": "RJ",
  "cidade": "Rio de Janeiro",
  "bairro": "Copacabana",
  "rua": "Avenida Atlântica",
  "numero": "1702",
  "codigoPostal": "22021001",
  "informacoesAdicionais": "Hotel Copacabana Palace",
  "cliente": {
    "id": 1
  }
}
```

---

## DELETE

### Excluir endereço
```text
DELETE localhost:8080/enderecos/{id}
```

---

# TELEFONES

Base: `/telefones`

## GET

### Listar todos
```text
GET localhost:8080/telefones
```

### Buscar por ID
```text
GET localhost:8080/telefones/{id}
```

---

## POST

### Criar telefone com cliente associado
```text
POST localhost:8080/telefones
```

### Body (JSON completo)
```json
{
  "ddd": "12",
  "numero": "999999999",
  "cliente": {
    "id": 1
  }
}
```

---

## PUT

### Atualizar telefone
```text
PUT localhost:8080/telefones
```

### Body (JSON completo)
```json
{
  "id": 1,
  "ddd": "11",
  "numero": "988888888",
  "cliente": {
    "id": 1
  }
}
```

---

## DELETE

### Excluir telefone
```text
DELETE localhost:8080/telefones/{id}
```

---

