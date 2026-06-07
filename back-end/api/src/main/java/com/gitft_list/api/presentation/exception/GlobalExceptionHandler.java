package com.gitft_list.api.presentation.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gitft_list.api.presentation.dto.ApiErrorResponse;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, String> details = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (first, second) -> first));

		ApiErrorResponse body = ApiErrorResponse.of(
			"VALIDATION_ERROR",
			"Dados de entrada invalidos",
			details
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, ConstraintViolationException.class, IllegalArgumentException.class })
	public ResponseEntity<ApiErrorResponse> handleBadRequest(Exception ex) {
		ApiErrorResponse body = ApiErrorResponse.of(
			"INVALID_REQUEST",
			"Requisicao invalida",
			ex.getMessage()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResponse> handleUnexpected(Exception ex) {
		ApiErrorResponse body = ApiErrorResponse.of(
			"INTERNAL_ERROR",
			"Erro interno",
			null
		);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
	}
}
