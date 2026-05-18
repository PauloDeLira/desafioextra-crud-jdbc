# Sistema de Clientes e Pedidos

Sistema CRUD desenvolvido em Java com integração ao banco de dados MySQL e consumo da API ViaCEP.

## Tecnologias utilizadas

- Java 21
- Maven
- JDBC
- MySQL
- API ViaCEP

## Dependências

- mysql-connector-j 8.3.0
- dotenv-java 3.0.0
- jackson-databind 2.17.0

## Pré-requisitos

- Java 21 instalado
- MySQL instalado e rodando

## Configuração do banco de dados

Execute os seguintes comandos no MySQL:

```sql
CREATE DATABASE projeto_crud;

USE projeto_crud;

CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL
);

CREATE TABLE pedidos (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    cliente_id INT NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id_cliente) ON DELETE CASCADE
);
```

## Configuração do ambiente

1. Copie o arquivo `.env.example` e renomeie para `.env`
2. Preencha as variáveis com as informações do seu MySQL:
  DB_HOST=localhost
  DB_PORT=3306
  DB_NAME=projeto_crud
  DB_USER=root
  DB_PASSWORD=suasenha

## Como rodar

1. Clone o repositório
2. Configure o banco de dados conforme acima
3. Configure o arquivo `.env`
4. Execute a classe `Main.java`

## Funcionalidades

- Cadastro, listagem, atualização e exclusão de clientes
- Cadastro, listagem, atualização e exclusão de pedidos
- Busca automática de endereço pelo CEP via API ViaCEP
- Relacionamento entre clientes e pedidos
