package com.gitft_list.api.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GiftResponseDto(
	Long id,
	String nome,
	String categoria,
	BigDecimal valorEstimado,
	String linkLoja,
	String prioridade,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {
}
