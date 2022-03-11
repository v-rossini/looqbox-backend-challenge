package com.looqbox.backend.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PokemonPayload {
	
	private String name;
	private String url;
	private String highlight;
	
	public PokemonPayload(String name, String substring) {
		this.name = name;
		this.highlight = name;
		
		
		StringBuilder stringBuilder = new StringBuilder();
		
		if (substring != "") {
			
			stringBuilder.append("<pre>");
			stringBuilder.append(substring);
			stringBuilder.append("</pre>");
			
			this.highlight = name.replace(substring, stringBuilder).toString();
			stringBuilder.setLength(0);
		}

		
		stringBuilder.append("https://pokeapi.co/api/v2/pokemon/");
		stringBuilder.append(name);
		
		this.url = stringBuilder.toString();
		
		
	}
}
