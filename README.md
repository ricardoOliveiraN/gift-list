# Gift List

Aplicação de lista de presentes com interface web, API REST e banco MySQL para estudo prático de deploy e operação de ambientes.

## Visão geral

Este repositório está organizado em 3 blocos principais:

- `front-end`: aplicação React (Vite) para cadastro e listagem de presentes.
- `back-end/api`: API Spring Boot responsável por regras de negócio e persistência.
- `banco-dados`: stack Docker Compose para MySQL em ambiente de desenvolvimento.

## Arquitetura da solução

Arquitetura lógica da aplicação:

- Front-end SPA consumindo API REST.
- Back-end orientado a casos de uso (estilo Clean Architecture).
- Persistência em MySQL com versionamento de schema via Flyway.

Fluxo principal:

1. Usuário cria presente no front-end.
2. Front-end envia `POST /api/gifts`.
3. API valida e persiste no MySQL.
4. Front-end consulta `GET /api/gifts` para renderizar lista atualizada.

## Tecnologias utilizadas

- Front-end: React 19, Vite 8, Axios, Tailwind CSS.
- Back-end: Java 21, Spring Boot 3.5, Spring Web, Spring Data JPA, Validation.
- Banco: MySQL 8 (container dev).
- Migração: Flyway (`flyway-core` + `flyway-mysql`).
- Containerização: Docker e Docker Compose.

## Estrutura do repositório

```text
gift-list/
	README.md
	front-end/
	back-end/
		api/
	banco-dados/
```

## Como rodar em desenvolvimento

1. Subir banco:

```bash
docker compose -f banco-dados/docker-compose.yml up -d
```

2. Rodar API (`back-end/api`) pela IDE ou Maven Wrapper:

```bash
./mvnw spring-boot:run
```

3. Rodar front (`front-end`):

```bash
npm install
npm run dev
```

## Endpoints principais

- `POST /api/gifts`
- `GET /api/gifts?page=1&limit=10`

## Adendo sobre o desenvolvimento

Este projeto foi desenvolvido integralmente com suporte de IA.

O objetivo principal foi acelerar a construção da aplicação para concentrar o aprendizado em infraestrutura de nuvem e operação de ambiente. Por isso, a prioridade foi velocidade de entrega da solução funcional.