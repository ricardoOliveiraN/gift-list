package com.gitft_list.api.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GiftPriority {
	ALTA("Alta"),
	MEDIA("Media"),
	BAIXA("Baixa");

	private final String value;

	GiftPriority(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static GiftPriority fromValue(String raw) {
		if (raw == null) {
			return null;
		}

		for (GiftPriority priority : values()) {
			if (priority.value.equalsIgnoreCase(raw.trim()) || priority.name().equalsIgnoreCase(raw.trim())) {
				return priority;
			}
		}

		throw new IllegalArgumentException("prioridade invalida: " + raw);
	}
}
