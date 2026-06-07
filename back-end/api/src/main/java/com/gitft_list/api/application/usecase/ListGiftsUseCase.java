package com.gitft_list.api.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gitft_list.api.application.dto.GiftResponseDto;
import com.gitft_list.api.application.dto.ListGiftsResponseDto;
import com.gitft_list.api.domain.entity.Gift;
import com.gitft_list.api.domain.repository.GiftRepository;

@Service
public class ListGiftsUseCase {
	private final GiftRepository giftRepository;

	public ListGiftsUseCase(GiftRepository giftRepository) {
		this.giftRepository = giftRepository;
	}

	public ListGiftsResponseDto execute(int page, int limit) {
		int safePage = Math.max(page, 1);
		int safeLimit = Math.max(1, Math.min(limit, 100));

		List<GiftResponseDto> items = giftRepository.findAll(safePage, safeLimit)
			.stream()
			.map(this::toResponse)
			.toList();

		long total = giftRepository.countAll();
		return new ListGiftsResponseDto(items, safePage, safeLimit, total);
	}

	private GiftResponseDto toResponse(Gift gift) {
		return new GiftResponseDto(
			gift.getId(),
			gift.getNome(),
			gift.getCategoria().value(),
			gift.getValorEstimado(),
			gift.getLinkLoja(),
			gift.getPrioridade().value(),
			gift.getCreatedAt(),
			gift.getUpdatedAt()
		);
	}
}
