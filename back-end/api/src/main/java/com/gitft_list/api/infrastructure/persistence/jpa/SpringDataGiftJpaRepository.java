package com.gitft_list.api.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataGiftJpaRepository extends JpaRepository<GiftJpaEntity, Long> {
}
