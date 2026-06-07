# Front-end - Gift List

Aplicação web responsável por cadastro e visualização da lista de presentes.

## O que este módulo faz

- Renderiza formulário de criação de presentes.
- Envia novos itens para a API (`POST /api/gifts`).
- Busca e exibe lista atualizada diretamente da API (`GET /api/gifts`).
- Mantém experiência responsiva para desktop e mobile.

## Stack utilizada

- React 19
- Vite 8
- Axios
- Tailwind CSS
- ESLint

## Arquitetura do front

Arquitetura simples de SPA:

- `src/App.jsx`: componente principal da interface e fluxo de dados.
- `axios.create(...)`: cliente HTTP central para comunicação com o back-end.
- Carregamento de dados no ciclo inicial com `useEffect`.
- Revalidação da lista após cada criação de item.

## Configuração de ambiente

### Desenvolvimento

Arquivo: `.env.development`

- `VITE_API_URL=/api`
- `VITE_DEV_API_TARGET=http://localhost:8080`

O Vite faz proxy de `/api` para `VITE_DEV_API_TARGET`.

### Produção

Arquivo: `.env.production`

- `VITE_API_URL=/api`

Em produção, o roteamento de `/api` deve ser tratado pelo servidor web/reverse proxy.

## Como rodar

```bash
npm install
npm run dev
```

Aplicação disponível em `http://localhost:5173` (porta padrão do Vite, salvo configuração diferente).

## Build de produção

```bash
npm run build
npm run preview
```

## Integração com API

Contratos usados pelo front-end:

- Criação: `POST /api/gifts`
- Listagem: `GET /api/gifts?page=1&limit=100`

Campos esperados no payload de criação:

- `nome`
- `categoria`
- `valor`
- `valorFormatado`
- `linkLoja`
- `prioridade`
