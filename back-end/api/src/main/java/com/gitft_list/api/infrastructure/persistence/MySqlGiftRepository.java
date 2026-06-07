package com.gitft_list.api.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.gitft_list.api.domain.entity.Gift;
import com.gitft_list.api.domain.repository.GiftRepository;
import com.gitft_list.api.infrastructure.persistence.jpa.GiftJpaEntity;
import com.gitft_list.api.infrastructure.persistence.jpa.SpringDataGiftJpaRepository;

@Repository
public class MySqlGiftRepository implements GiftRepository {
	private final SpringDataGiftJpaRepository repository;

	public MySqlGiftRepository(SpringDataGiftJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Gift save(Gift gift) {
		LocalDateTime now = LocalDateTime.now();

		GiftJpaEntity entity = new GiftJpaEntity();
		entity.setNome(gift.getNome());
		entity.setCategoria(gift.getCategoria());
		entity.setValorEstimado(gift.getValorEstimado());
		entity.setLinkLoja(gift.getLinkLoja());
		entity.setPrioridade(gift.getPrioridade());
		entity.setCreatedAt(now);
		entity.setUpdatedAt(now);

		GiftJpaEntity saved = repository.save(entity);
		return toDomain(saved);
	}

	@Override
	public List<Gift> findAll(int page, int limit) {
		PageRequest pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
		return repository.findAll(pageable).stream().map(this::toDomain).toList();
	}

	@Override
	public long countAll() {
		return repository.count();
	}

	private Gift toDomain(GiftJpaEntity entity) {
		return new Gift(
			entity.getId(),
			entity.getNome(),
			entity.getCategoria(),
			entity.getValorEstimado(),
			entity.getLinkLoja(),
			entity.getPrioridade(),
			entity.getCreatedAt(),
			entity.getUpdatedAt()
		);
	}
}
