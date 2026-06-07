package com.gitft_list.api.infrastructure.persistence.jpa;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gitft_list.api.domain.enums.GiftCategory;
import com.gitft_list.api.domain.enums.GiftPriority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gifts")
public class GiftJpaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 120)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private GiftCategory categoria;

	@Column(name = "valor_estimado", nullable = false, precision = 10, scale = 2)
	private BigDecimal valorEstimado;

	@Column(name = "link_loja", nullable = false, length = 500)
	private String linkLoja;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private GiftPriority prioridade;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GiftCategory getCategoria() {
		return categoria;
	}

	public void setCategoria(GiftCategory categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValorEstimado() {
		return valorEstimado;
	}

	public void setValorEstimado(BigDecimal valorEstimado) {
		this.valorEstimado = valorEstimado;
	}

	public String getLinkLoja() {
		return linkLoja;
	}

	public void setLinkLoja(String linkLoja) {
		this.linkLoja = linkLoja;
	}

	public GiftPriority getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(GiftPriority prioridade) {
		this.prioridade = prioridade;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
