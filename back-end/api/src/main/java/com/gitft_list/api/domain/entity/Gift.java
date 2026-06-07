package com.gitft_list.api.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gitft_list.api.domain.enums.GiftCategory;
import com.gitft_list.api.domain.enums.GiftPriority;

public class Gift {
	private final Long id;
	private final String nome;
	private final GiftCategory categoria;
	private final BigDecimal valorEstimado;
	private final String linkLoja;
	private final GiftPriority prioridade;
	private final LocalDateTime createdAt;
	private final LocalDateTime updatedAt;

	public Gift(
		Long id,
		String nome,
		GiftCategory categoria,
		BigDecimal valorEstimado,
		String linkLoja,
		GiftPriority prioridade,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
	) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.valorEstimado = valorEstimado;
		this.linkLoja = linkLoja;
		this.prioridade = prioridade;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public GiftCategory getCategoria() {
		return categoria;
	}

	public BigDecimal getValorEstimado() {
		return valorEstimado;
	}

	public String getLinkLoja() {
		return linkLoja;
	}

	public GiftPriority getPrioridade() {
		return prioridade;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
