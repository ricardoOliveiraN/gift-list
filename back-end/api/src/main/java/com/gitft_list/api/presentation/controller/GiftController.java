package com.gitft_list.api.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gitft_list.api.application.dto.GiftResponseDto;
import com.gitft_list.api.application.dto.ListGiftsResponseDto;
import com.gitft_list.api.application.usecase.CreateGiftUseCase;
import com.gitft_list.api.application.usecase.ListGiftsUseCase;
import com.gitft_list.api.presentation.dto.ApiSuccessResponse;
import com.gitft_list.api.presentation.dto.CreateGiftHttpRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/api/gifts")
public class GiftController {
	private final CreateGiftUseCase createGiftUseCase;
	private final ListGiftsUseCase listGiftsUseCase;

	public GiftController(CreateGiftUseCase createGiftUseCase, ListGiftsUseCase listGiftsUseCase) {
		this.createGiftUseCase = createGiftUseCase;
		this.listGiftsUseCase = listGiftsUseCase;
	}

	@PostMapping
	public ResponseEntity<ApiSuccessResponse<GiftResponseDto>> create(@Valid @RequestBody CreateGiftHttpRequest request) {
		GiftResponseDto created = createGiftUseCase.execute(request.toCommand());
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiSuccessResponse.of(created));
	}

	@GetMapping
	public ResponseEntity<ApiSuccessResponse<ListGiftsResponseDto>> list(
		@RequestParam(defaultValue = "1") @Min(1) int page,
		@RequestParam(defaultValue = "10") @Min(1) @Max(100) int limit
	) {
		ListGiftsResponseDto response = listGiftsUseCase.execute(page, limit);
		return ResponseEntity.ok(ApiSuccessResponse.of(response));
	}
}
