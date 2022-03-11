package com.looqbox.backend.services.utils;

import java.io.IOException;
import java.net.URL;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper objectMapper = getDefaultObjectMapper();
	
	private static ObjectMapper getDefaultObjectMapper() {
		ObjectMapper defaultObjectMapper = new ObjectMapper();
		//...
		return defaultObjectMapper;
	}
	
	public static JsonNode parse(URL url) throws IOException {
		JsonNode node;
		try {
			node = objectMapper.readTree(url);
		} catch (IOException e) {
		throw new IOException("URL inválida");
		}
		return node;
	}
	
	
}