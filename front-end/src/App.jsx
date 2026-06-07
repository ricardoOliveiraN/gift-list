import { useCallback, useEffect, useMemo, useState } from 'react'
import axios from 'axios'
import {
  FaArrowUpWideShort,
  FaGift,
  FaLink,
  FaList,
  FaMoneyBillWave,
  FaTag,
} from 'react-icons/fa6'

const inputClassName =
  'w-full rounded-xl border border-[var(--border)] bg-white px-4 py-3 text-sm text-[var(--text-main)] shadow-sm transition focus:border-[var(--accent)] focus:outline-none focus:ring-2 focus:ring-[var(--accent)]/20'

function FieldLabel({ icon, text, htmlFor }) {
  return (
    <label
      htmlFor={htmlFor}
      className="mb-1 inline-flex items-center gap-2 text-sm font-semibold text-[var(--text-main)]"
    >
      <span className="text-[var(--accent-dark)]">{icon}</span>
      {text}
    </label>
  )
}

function Input({ id, name, value, onChange, placeholder, type, inputMode, required }) {
  return (
    <input
      id={id}
      name={name}
      value={value}
      onChange={onChange}
      placeholder={placeholder}
      type={type}
      inputMode={inputMode}
      required={required}
      className={inputClassName}
    />
  )
}

function App() {
  const [formData, setFormData] = useState({
    nome: '',
    categoria: 'Eletronicos',
    valor: '',
    linkLoja: '',
    prioridade: 'Media',
  })
  const [giftList, setGiftList] = useState([])
  const [statusMessage, setStatusMessage] = useState('')
  const [isLoadingList, setIsLoadingList] = useState(true)

  const api = useMemo(
    () =>
      axios.create({
        baseURL: import.meta.env.VITE_API_URL || '/api',
        timeout: 5000,
      }),
    [],
  )

  const loadGifts = useCallback(async () => {
    try {
      const response = await api.get('/gifts', {
        params: {
          page: 1,
          limit: 100,
        },
      })

      const items = response.data?.data?.items ?? []
      const giftsFromApi = items.map((gift) => ({
        id: gift.id,
        nome: gift.nome,
        categoria: gift.categoria,
        prioridade: gift.prioridade,
        linkLoja: gift.linkLoja,
        valorFormatado: Number(gift.valorEstimado || 0).toLocaleString('pt-BR', {
          style: 'currency',
          currency: 'BRL',
        }),
      }))

      setGiftList(giftsFromApi)
      setStatusMessage('')
    } catch {
      setStatusMessage('Nao foi possivel carregar a lista de presentes do back-end.')
    } finally {
      setIsLoadingList(false)
    }
  }, [api])

  useEffect(() => {
    loadGifts()
  }, [loadGifts])

  const formatCurrencyInput = (value) => {
    const onlyDigits = value.replace(/\D/g, '')
    if (!onlyDigits) {
      return ''
    }

    const normalizedValue = Number(onlyDigits) / 100

    return normalizedValue.toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    })
  }

  const handleChange = (event) => {
    const { name, value } = event.target

    if (name === 'valor') {
      setFormData((previous) => ({
        ...previous,
        valor: formatCurrencyInput(value),
      }))
      return
    }

    setFormData((previous) => ({
      ...previous,
      [name]: value,
    }))
  }

  const parseValueToNumber = (maskedValue) => {
    const onlyDigits = maskedValue.replace(/\D/g, '')
    return onlyDigits ? Number(onlyDigits) / 100 : 0
  }

  const resetForm = () => {
    setFormData({
      nome: '',
      categoria: 'Eletronicos',
      valor: '',
      linkLoja: '',
      prioridade: 'Media',
    })
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    const payload = {
      nome: formData.nome.trim(),
      categoria: formData.categoria,
      valor: parseValueToNumber(formData.valor),
      valorFormatado: formData.valor,
      linkLoja: formData.linkLoja.trim(),
      prioridade: formData.prioridade,
    }

    if (!payload.nome || !payload.valor || !payload.linkLoja) {
      setStatusMessage('Preencha nome, valor e link da loja para continuar.')
      return
    }

    try {
      await api.post('/gifts', payload)
      await loadGifts()
      setStatusMessage('Presente adicionado com sucesso.')
      resetForm()
    } catch {
      setStatusMessage('Nao foi possivel salvar o presente no back-end.')
    }
  }

  return (
    <main className="mx-auto flex min-h-screen w-full max-w-6xl flex-col justify-center px-4 py-10 sm:px-6 lg:px-8">
      <div className="relative overflow-hidden rounded-[2rem] border border-[var(--border)] bg-[var(--card)]/95 p-6 shadow-[var(--shadow-soft)] backdrop-blur-sm sm:p-10">
        <div className="pointer-events-none absolute -left-20 -top-20 h-48 w-48 rounded-full bg-[var(--highlight)]/20 blur-2xl" />
        <div className="pointer-events-none absolute -bottom-24 -right-10 h-56 w-56 rounded-full bg-[var(--accent)]/20 blur-3xl" />

        <header className="relative mb-10 text-center">
          <p className="mb-3 inline-flex items-center gap-2 rounded-full border border-[var(--border)] bg-[#faf5ee] px-4 py-1 text-xs font-semibold uppercase tracking-[0.22em] text-[var(--text-soft)]">
            Lista de Presentes
          </p>
          <h1 className="font-display text-3xl text-[var(--text-main)] sm:text-4xl lg:text-5xl">
            Registro de Presentes Elegante
          </h1>
          <p className="mx-auto mt-3 max-w-2xl text-sm text-[var(--text-soft)] sm:text-base">
            Organize itens para casamento ou cha de bebe com uma experiencia sofisticada,
            responsiva e pronta para integracao com back-end.
          </p>
        </header>

        <section className="relative grid gap-8 lg:grid-cols-[1.2fr_1fr]">
          <form
            onSubmit={handleSubmit}
            className="space-y-5 rounded-3xl border border-[var(--border)] bg-[#fffaf4] p-5 sm:p-7"
          >
            <FieldLabel icon={<FaGift />} text="Nome do Presente" htmlFor="nome" />
            <Input
              id="nome"
              name="nome"
              value={formData.nome}
              onChange={handleChange}
              placeholder="Ex: Cafeteira Espresso"
              type="text"
              required
            />

            <FieldLabel icon={<FaList />} text="Categoria" htmlFor="categoria" />
            <select
              id="categoria"
              name="categoria"
              value={formData.categoria}
              onChange={handleChange}
              className={inputClassName}
            >
              <option>Eletronicos</option>
              <option>Cozinha</option>
              <option>Decoracao</option>
              <option>Experiencia</option>
            </select>

            <FieldLabel icon={<FaMoneyBillWave />} text="Valor Estimado" htmlFor="valor" />
            <Input
              id="valor"
              name="valor"
              value={formData.valor}
              onChange={handleChange}
              placeholder="R$ 0,00"
              type="text"
              inputMode="numeric"
              required
            />

            <FieldLabel icon={<FaLink />} text="Link da Loja" htmlFor="linkLoja" />
            <Input
              id="linkLoja"
              name="linkLoja"
              value={formData.linkLoja}
              onChange={handleChange}
              placeholder="https://www.loja.com/produto"
              type="url"
              required
            />

            <div className="space-y-3">
              <FieldLabel icon={<FaArrowUpWideShort />} text="Prioridade" htmlFor="prioridade" />
              <div className="grid grid-cols-3 gap-2 sm:gap-3">
                {['Alta', 'Media', 'Baixa'].map((priority) => (
                  <label
                    key={priority}
                    className="cursor-pointer rounded-xl border border-[var(--border)] bg-white px-3 py-2 text-center text-sm font-medium text-[var(--text-main)] transition hover:-translate-y-0.5 hover:border-[var(--accent)]"
                  >
                    <input
                      type="radio"
                      name="prioridade"
                      value={priority}
                      checked={formData.prioridade === priority}
                      onChange={handleChange}
                      className="sr-only"
                    />
                    <span
                      className={
                        formData.prioridade === priority
                          ? 'text-[var(--accent-dark)]'
                          : 'text-[var(--text-main)]'
                      }
                    >
                      {priority}
                    </span>
                  </label>
                ))}
              </div>
            </div>

            <button
              type="submit"
              className="mt-2 inline-flex w-full items-center justify-center gap-2 rounded-2xl bg-[var(--accent)] px-6 py-3 font-semibold text-white transition duration-300 hover:-translate-y-0.5 hover:bg-[var(--accent-dark)] hover:shadow-lg"
            >
              <FaTag /> Adicionar Presente
            </button>

            {statusMessage && (
              <p className="rounded-xl bg-[#f6eee2] px-3 py-2 text-sm text-[var(--text-soft)]">
                {statusMessage}
              </p>
            )}
          </form>

          <aside className="rounded-3xl border border-[var(--border)] bg-[#fffdfa] p-5 sm:p-7">
            <h2 className="font-display text-2xl text-[var(--text-main)]">Visualizacao Rapida</h2>
            <p className="mb-5 mt-2 text-sm text-[var(--text-soft)]">
              Itens adicionados aparecem em tempo real abaixo.
            </p>

            {isLoadingList ? (
              <div className="rounded-2xl border border-dashed border-[var(--border)] bg-[#fbf6ef] p-5 text-sm text-[var(--text-soft)]">
                Carregando presentes...
              </div>
            ) : giftList.length === 0 ? (
              <div className="rounded-2xl border border-dashed border-[var(--border)] bg-[#fbf6ef] p-5 text-sm text-[var(--text-soft)]">
                Nenhum item adicionado ainda.
              </div>
            ) : (
              <ul className="space-y-3">
                {giftList.map((gift) => (
                  <li
                    key={gift.id}
                    className="rounded-2xl border border-[var(--border)] bg-white p-4 shadow-sm"
                  >
                    <div className="flex items-start justify-between gap-3">
                      <strong className="text-sm text-[var(--text-main)] sm:text-base">{gift.nome}</strong>
                      <span className="rounded-full bg-[#f2ebdf] px-3 py-1 text-xs font-semibold text-[var(--accent-dark)]">
                        {gift.prioridade}
                      </span>
                    </div>
                    <div className="mt-2 flex flex-wrap items-center gap-2 text-xs text-[var(--text-soft)] sm:text-sm">
                      <span>{gift.categoria}</span>
                      <span>|</span>
                      <span>{gift.valorFormatado}</span>
                    </div>
                    <a
                      href={gift.linkLoja}
                      target="_blank"
                      rel="noreferrer"
                      className="mt-3 inline-flex text-sm font-medium text-[var(--accent-dark)] underline decoration-transparent transition hover:decoration-current"
                    >
                      Ver produto
                    </a>
                  </li>
                ))}
              </ul>
            )}
          </aside>
        </section>
      </div>
    </main>
  )
}

export default App
