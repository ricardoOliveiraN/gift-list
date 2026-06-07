package com.gitft_list.api.domain.repository;

import java.util.List;

import com.gitft_list.api.domain.entity.Gift;

public interface GiftRepository {
	Gift save(Gift gift);

	List<Gift> findAll(int page, int limit);

	long countAll();
}
