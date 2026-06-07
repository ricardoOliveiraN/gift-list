package com.gitft_list.api.presentation.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gitft_list.api.application.dto.CreateGiftCommand;
import com.gitft_list.api.domain.enums.GiftCategory;
import com.gitft_list.api.domain.enums.GiftPriority;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGiftHttpRequest(
	@NotBlank(message = "nome obrigatorio")
	String nome,

	@NotNull(message = "categoria obrigatoria")
	GiftCategory categoria,

	@NotNull(message = "valor obrigatorio")
	@DecimalMin(value = "0.01", message = "valor deve ser maior que zero")
	@JsonAlias({ "valor_estimado", "valorEstimado" })
	BigDecimal valor,

	@NotBlank(message = "linkLoja obrigatorio")
	@URL(message = "linkLoja deve ser uma URL valida")
	@JsonAlias({ "link_loja", "linkLoja" })
	String linkLoja,

	@NotNull(message = "prioridade obrigatoria")
	GiftPriority prioridade,

	@JsonAlias({ "valor_formatado", "valorFormatado" })
	String valorFormatado
) {
	public CreateGiftCommand toCommand() {
		return new CreateGiftCommand(nome, categoria, valor, linkLoja, prioridade);
	}
}
