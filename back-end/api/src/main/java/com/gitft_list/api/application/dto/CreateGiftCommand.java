package com.gitft_list.api.application.dto;

import java.math.BigDecimal;

import com.gitft_list.api.domain.enums.GiftCategory;
import com.gitft_list.api.domain.enums.GiftPriority;

public record CreateGiftCommand(
	String nome,
	GiftCategory categoria,
	BigDecimal valorEstimado,
	String linkLoja,
	GiftPriority prioridade
) {
}
