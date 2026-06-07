package com.gitft_list.api.application.usecase;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.gitft_list.api.application.dto.CreateGiftCommand;
import com.gitft_list.api.application.dto.GiftResponseDto;
import com.gitft_list.api.domain.entity.Gift;
import com.gitft_list.api.domain.repository.GiftRepository;

@Service
public class CreateGiftUseCase {
	private final GiftRepository giftRepository;

	public CreateGiftUseCase(GiftRepository giftRepository) {
		this.giftRepository = giftRepository;
	}

	public GiftResponseDto execute(CreateGiftCommand command) {
		validate(command);

		Gift giftToCreate = new Gift(
			null,
			command.nome().trim(),
			command.categoria(),
			command.valorEstimado(),
			command.linkLoja().trim(),
			command.prioridade(),
			null,
			null
		);

		Gift created = giftRepository.save(giftToCreate);
		return toResponse(created);
	}

	private void validate(CreateGiftCommand command) {
		if (Objects.isNull(command)) {
			throw new IllegalArgumentException("Payload obrigatorio.");
		}
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
