package com.gitft_list.api.application.dto;

import java.util.List;

public record ListGiftsResponseDto(
	List<GiftResponseDto> items,
	int page,
	int limit,
	long total
) {
}
