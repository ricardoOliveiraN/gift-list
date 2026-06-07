package com.gitft_list.api.presentation.dto;

public record ApiErrorResponse(
	boolean success,
	ApiError error
) {
	public static ApiErrorResponse of(String code, String message, Object details) {
		return new ApiErrorResponse(false, new ApiError(code, message, details));
	}

	public record ApiError(String code, String message, Object details) {
	}
}
