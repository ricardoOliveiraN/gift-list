package com.gitft_list.api.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GiftCategory {
	ELETRONICOS("Eletronicos"),
	COZINHA("Cozinha"),
	DECORACAO("Decoracao"),
	EXPERIENCIA("Experiencia");

	private final String value;

	GiftCategory(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static GiftCategory fromValue(String raw) {
		if (raw == null) {
			return null;
		}

		for (GiftCategory category : values()) {
			if (category.value.equalsIgnoreCase(raw.trim()) || category.name().equalsIgnoreCase(raw.trim())) {
				return category;
			}
		}

		throw new IllegalArgumentException("categoria invalida: " + raw);
	}
}
