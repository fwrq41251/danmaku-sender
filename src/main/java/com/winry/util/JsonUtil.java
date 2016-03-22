package com.winry.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonUtil {
	private JsonUtil() {
	}

	private static ObjectMapper objectMapper = new ObjectMapper();


	public static <T> T toObject(Class<T> clazz, String json) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
