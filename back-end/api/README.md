# Back-end API - Gift List

API REST responsável por regras de negócio, validação e persistência dos presentes.

## O que este módulo faz

- Expõe endpoints HTTP para criar e listar presentes.
- Valida dados de entrada.
- Persiste no MySQL via JPA.
- Executa migrations automaticamente no startup com Flyway.

## Tecnologias utilizadas

- Java 21
- Spring Boot 3.5
- Spring Web
- Spring Data JPA
- Spring Validation
- MySQL Connector/J
- Flyway (`flyway-core` e `flyway-mysql`)
- Maven Wrapper

## Arquitetura utilizada

Estrutura baseada em Clean Architecture (organização por responsabilidade):

- `domain`
  - Entidades e regras centrais de domínio.
  - Contratos de repositório.
- `application`
  - Casos de uso da aplicação.
  - DTOs de entrada e saída.
- `infrastructure`
  - Implementações técnicas (JPA/MySQL).
  - Mapeamentos de persistência.
- `presentation`
  - Controllers REST.
  - DTOs HTTP e tratamento de erros.

## Endpoints

- `POST /api/gifts`
- `GET /api/gifts?page=1&limit=10`

## Contrato de criação (POST)

Exemplo de payload:

```json
{
  "nome": "Air Fryer",
  "categoria": "Cozinha",
  "valor": 499.90,
  "valorFormatado": "R$ 499,90",
  "linkLoja": "https://loja.com/produto/airfryer",
  "prioridade": "Alta"
}
```

Regras principais:

- `nome` obrigatório.
- `categoria` obrigatória (`Eletronicos`, `Cozinha`, `Decoracao`, `Experiencia`).
- `valor` maior que zero.
- `linkLoja` deve ser URL válida.
- `prioridade` obrigatória (`Alta`, `Media`, `Baixa`).

Observação:

- `valorFormatado` é aceito por compatibilidade com front-end, mas não é persistido.

## Padrão de resposta

Sucesso:

```json
{
  "success": true,
  "data": {}
}
```

Erro:

```json
{
  "success": false,
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Dados de entrada invalidos",
    "details": {}
  }
}
```

## Banco de dados e migrations

- Migrations em: `src/main/resources/db/migration`
- Migration inicial: `V1__create_gifts_table.sql`
- Execução automática no startup da API.

## Variáveis de ambiente

Referência em `.env.example`:

```bash
APP_PORT=8080
DB_URL=jdbc:mysql://localhost:3306/gift_list?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
DB_USERNAME=gift_user
DB_PASSWORD=gift_password
```

## Como rodar em desenvolvimento

1. Suba o banco local:

```bash
docker compose -f ../../banco-dados/docker-compose.yml up -d
```

2. Execute a API:

```bash
./mvnw spring-boot:run
```

3. Teste rápido:

```bash
curl "http://localhost:8080/api/gifts?page=1&limit=10"
```
