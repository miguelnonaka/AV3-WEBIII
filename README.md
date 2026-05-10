# AV3 - AutoManager API

API REST desenvolvida com Spring Boot para gerenciamento de empresas, usuários, vendas, serviços, veículos e entidades relacionadas.

---

# Como executar o projeto

## 1. Clone o repositório

---

## 2.1 Configuração do banco de dados

### application.properties

```properties
spring.application.name=AutoBots

# URL de conexão com o banco MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/generaldb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Tomilho@0123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#comandos
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql: true
spring.jpa.properties.hibernate.format_sql=true

#logging.level.org.hibernate.SQL=debug
#logging.level.org.hibernate.type.descriptor.sql=trace
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


# EMPRESAS

Base:

```text
http://localhost:8080/empresas
```

## GET

### Listar empresas

```text
GET http://localhost:8080/empresas
```

### Buscar empresa por ID

```text
GET http://localhost:8080/empresas/{id}
```

---

## POST

### Criar empresa

```text
POST http://localhost:8080/empresas
```

```json
{
  "razaoSocial": "AutoBots Manutenção Ltda",
  "nomeFantasia": "AutoBots",
  "cadastro": "2024-01-15T10:00:00.000Z",
  "telefones": [
    {
      "ddd": "11",
      "numero": "99999-8888"
    }
  ],
  "endereco": {
    "estado": "SP",
    "cidade": "São Paulo",
    "bairro": "Centro",
    "rua": "Rua das Flores",
    "numero": "123",
    "codigoPostal": "01234-567",
    "informacoesAdicionais": "Próximo ao metrô"
  }
}
```

---

## PUT

### Atualizar empresa

```text
PUT http://localhost:8080/empresas/{id}
```

---

## DELETE

### Deletar empresa

```text
DELETE http://localhost:8080/empresas/{id}
```

---

# USUÁRIOS

Base:

```text
http://localhost:8080/usuarios
```

## GET

### Listar usuários

```text
GET http://localhost:8080/usuarios
```

### Buscar usuário por ID

```text
GET http://localhost:8080/usuarios/{id}
```

---

## POST

### Criar usuário

```text
POST http://localhost:8080/usuarios
```

```json
{
  "nome": "João Silva",
  "nomeSocial": "João",
  "perfis": ["CLIENTE"],
  "empresaId": 1,
  "telefones": [
    {
      "ddd": "11",
      "numero": "88888-7777"
    }
  ],
  "endereco": {
    "estado": "SP",
    "cidade": "São Paulo",
    "bairro": "Vila Mariana",
    "rua": "Rua dos Pinheiros",
    "numero": "456",
    "codigoPostal": "04123-456"
  },
  "documentos": [
    {
      "tipoDocumento": "CPF",
      "numero": "123.456.789-00",
      "dataEmissao": "2026-05-09"
    }
  ],
  "emails": [
    {
      "endereco": "joao@email.com"
    }
  ]
}
```

---

## PUT

### Atualizar usuário

```text
PUT http://localhost:8080/usuarios/{id}
```

---

## DELETE

### Deletar usuário

```text
DELETE http://localhost:8080/usuarios/{id}
```

---

# DOCUMENTOS

Base:

```text
http://localhost:8080/documentos
```

## GET

### Listar documentos

```text
GET http://localhost:8080/documentos
```

### Buscar documento por ID

```text
GET http://localhost:8080/documentos/{id}
```

---

## POST

### Criar documento

```text
POST http://localhost:8080/documentos
```

```json
{
  "tipoDocumento": "CPF",
  "dataEmissao": "2026-05-09",
  "numero": "123.456.789-00"
}
```

---

## PUT

### Atualizar documento

```text
PUT http://localhost:8080/documentos/{id}
```

---

## DELETE

### Deletar documento

```text
DELETE http://localhost:8080/documentos/{id}
```

---

# ENDEREÇOS

Base:

```text
http://localhost:8080/enderecos
```

## GET

### Listar endereços

```text
GET http://localhost:8080/enderecos
```

### Buscar endereço por ID

```text
GET http://localhost:8080/enderecos/{id}
```

---

## POST

### Criar endereço

```text
POST http://localhost:8080/enderecos
```

```json
{
  "estado": "SP",
  "cidade": "São Paulo",
  "bairro": "Centro",
  "rua": "Av. Paulista",
  "numero": "1000",
  "codigoPostal": "01310-100",
  "informacoesAdicionais": "Próximo ao MASP"
}
```

---

## PUT

### Atualizar endereço

```text
PUT http://localhost:8080/enderecos/{id}
```

---

## DELETE

### Deletar endereço

```text
DELETE http://localhost:8080/enderecos/{id}
```

---

# TELEFONES

Base:

```text
http://localhost:8080/telefones
```

## GET

### Listar telefones

```text
GET http://localhost:8080/telefones
```

### Buscar telefone por ID

```text
GET http://localhost:8080/telefones/{id}
```

---

## POST

### Criar telefone

```text
POST http://localhost:8080/telefones
```

```json
{
  "ddd": "11",
  "numero": "99999-9999"
}
```

---

## PUT

### Atualizar telefone

```text
PUT http://localhost:8080/telefones/{id}
```

---

## DELETE

### Deletar telefone

```text
DELETE http://localhost:8080/telefones/{id}
```

---

# EMAILS

Base:

```text
http://localhost:8080/emails
```

## GET

### Listar emails

```text
GET http://localhost:8080/emails
```

### Buscar email por ID

```text
GET http://localhost:8080/emails/{id}
```

---

## POST

### Criar email

```text
POST http://localhost:8080/emails
```

```json
{
  "endereco": "contato@autobots.com"
}
```

---

## PUT

### Atualizar email

```text
PUT http://localhost:8080/emails/{id}
```

---

## DELETE

### Deletar email

```text
DELETE http://localhost:8080/emails/{id}
```

---

# CREDENCIAIS SENHA

Base:

```text
http://localhost:8080/credenciais-senha
```

## GET

### Listar credenciais

```text
GET http://localhost:8080/credenciais-senha
```

### Buscar credencial por ID

```text
GET http://localhost:8080/credenciais-senha/{id}
```

---

## POST

### Criar credencial

```text
POST http://localhost:8080/credenciais-senha
```

```json
{
  "nomeUsuario": "joao.silva",
  "senha": "senha123",
  "criacao": "2024-01-15T10:00:00.000Z",
  "ultimoAcesso": "2024-01-15T10:00:00.000Z",
  "inativo": false
}
```

---

## PUT

### Atualizar credencial

```text
PUT http://localhost:8080/credenciais-senha/{id}
```

---

## DELETE

### Deletar credencial

```text
DELETE http://localhost:8080/credenciais-senha/{id}
```

---

# CREDENCIAIS CÓDIGO BARRA

Base:

```text
http://localhost:8080/credenciais-codigo-barra
```

## GET

### Listar credenciais

```text
GET http://localhost:8080/credenciais-codigo-barra
```

### Buscar credencial por ID

```text
GET http://localhost:8080/credenciais-codigo-barra/{id}
```

---

## POST

### Criar credencial

```text
POST http://localhost:8080/credenciais-codigo-barra
```

```json
{
  "codigo": 1234567890123,
  "criacao": "2024-01-15T10:00:00.000Z",
  "ultimoAcesso": "2024-01-15T10:00:00.000Z",
  "inativo": false
}
```

---

## PUT

### Atualizar credencial

```text
PUT http://localhost:8080/credenciais-codigo-barra/{id}
```

---

## DELETE

### Deletar credencial

```text
DELETE http://localhost:8080/credenciais-codigo-barra/{id}
```

---

# VEÍCULOS

Base:

```text
http://localhost:8080/veiculos
```

## GET

```text
GET http://localhost:8080/veiculos
```

```text
GET http://localhost:8080/veiculos/{id}
```

---

## POST

```text
POST http://localhost:8080/veiculos
```

```json
{
  "tipo": "CARRO",
  "modelo": "Toyota Corolla",
  "placa": "ABC-1234"
}
```

---

## PUT

```text
PUT http://localhost:8080/veiculos/{id}
```

---

## DELETE

```text
DELETE http://localhost:8080/veiculos/{id}
```

---

# MERCADORIAS

Base:

```text
http://localhost:8080/mercadorias
```

## GET

```text
GET http://localhost:8080/mercadorias
```

```text
GET http://localhost:8080/mercadorias/{id}
```

---

## POST

```text
POST http://localhost:8080/mercadorias
```

```json
{
  "nome": "Óleo de Motor 5W30",
  "descricao": "Óleo sintético para motores",
  "quantidade": 50,
  "valor": 45.90,
  "validade": "2025-12-31T23:59:59.000Z",
  "fabricao": "2024-01-01T00:00:00.000Z",
  "cadastro": "2024-01-15T10:00:00.000Z"
}
```

---

## PUT

```text
PUT http://localhost:8080/mercadorias/{id}
```

---

## DELETE

```text
DELETE http://localhost:8080/mercadorias/{id}
```

---

# SERVIÇOS

Base:

```text
http://localhost:8080/servicos
```

## GET

```text
GET http://localhost:8080/servicos
```

```text
GET http://localhost:8080/servicos/{id}
```

---

## POST

```text
POST http://localhost:8080/servicos
```

```json
{
  "nome": "Troca de Óleo",
  "valor": 120.00,
  "descricao": "Troca completa de óleo do motor com filtro"
}
```

---

## PUT

```text
PUT http://localhost:8080/servicos/{id}
```

---

## DELETE

```text
DELETE http://localhost:8080/servicos/{id}
```

---

# VENDAS

Base:

```text
http://localhost:8080/vendas
```

## GET

```text
GET http://localhost:8080/vendas
```

```text
GET http://localhost:8080/vendas/{id}
```

---

## POST

```text
POST http://localhost:8080/vendas
```

```json
{
  "cadastro": "2024-01-15T14:30:00.000Z",
  "identificacao": "VND-2024-001",
  "cliente": {
    "id": 1
  },
  "funcionario": {
    "id": 2
  },
  "mercadorias": [
    {
      "id": 1
    }
  ],
  "servicos": [
    {
      "id": 1
    }
  ],
  "veiculo": {
    "id": 1
  }
}
```

---

## PUT

```text
PUT http://localhost:8080/vendas/{id}
```

---

## DELETE

```text
DELETE http://localhost:8080/vendas/{id}
```
