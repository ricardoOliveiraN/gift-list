# Banco de dados - Desenvolvimento

Módulo responsável por disponibilizar o MySQL local via Docker Compose para uso da API em ambiente de desenvolvimento.

## O que este módulo faz

- Sobe uma instância MySQL para desenvolvimento.
- Define credenciais padrão para integração com o back-end.
- Mantém dados persistidos em volume Docker.

## Tecnologias utilizadas

- MySQL (container)
- Docker Compose

## Arquitetura de dados

- Engine de persistência: MySQL.
- Gestão de schema: Flyway executado pela API.
- Modelo físico inicial criado pela migration `V1__create_gifts_table.sql` no back-end.

Importante:

- Este módulo não aplica migrations sozinho.
- O versionamento e criação de tabelas são responsabilidade da API no startup.

## Como subir

```bash
docker compose up -d
```

## Como derrubar

```bash
docker compose down
```

## Como limpar dados (opcional)

```bash
docker compose down -v
```

## Dados de conexão

- Host: `localhost`
- Porta: `3306`
- Banco: `gift_list`
- Usuário: `gift_user`
- Senha: `gift_password`

JDBC da API:

```text
jdbc:mysql://localhost:3306/gift_list?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
```

## Observações operacionais

- Se a porta `3306` estiver em uso, pare o serviço conflitante antes de subir o container.
- Após subir o banco, inicie a API para executar as migrations e criar a estrutura necessária.
