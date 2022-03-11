package com.looqbox.backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.looqbox.backend.payload.PokemonPayload;
import com.looqbox.backend.services.utils.JsonUtil;
import com.looqbox.backend.services.utils.LengthSortingUtil;
import com.looqbox.backend.services.utils.AlphabeticalSortingUtil;
import com.looqbox.backend.services.utils.comparators.AlphabeticalComparator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

@Service
public class PokemonSearchServiceImpl implements IPokemonSearchService{
	


	@Override
	public List<PokemonPayload> searchPokemon(String substring, String sorting) 
															throws MalformedURLException, IOException {
		
		URL sourceUrl = new URL("https://pokeapi.co/api/v2/pokemon-species/?limit=2000&offset=0");
		
		List<PokemonPayload> pokemonList = filterPokemon(getPokemonList(sourceUrl), substring);
		
		if (sorting.equals("alphabetical"))
			pokemonList = AlphabeticalSortingUtil.bubbleSort(pokemonList, new AlphabeticalComparator());
		
		
		if (sorting.equals("length"))
			pokemonList = LengthSortingUtil.countingSort(pokemonList);
		
		return pokemonList;
		
	}
	

	public List<PokemonPayload> filterPokemon(List<JsonNode> list, String substring)  {
		
		
		List<JsonNode> filteredList = list.stream()
				.filter(pokemon -> pokemon.get("name").toString().contains(substring))
				.collect(Collectors.toList());
		List<PokemonPayload> pokemonPayloadList = new ArrayList<PokemonPayload>();
		
		filteredList.forEach(pokemon -> pokemonPayloadList.add(new PokemonPayload(pokemon.get("name").textValue(), substring))); 
		
		return pokemonPayloadList;
	}
	
	
		@Cacheable(value = "Pokemon")
	 	public List<JsonNode> getPokemonList(URL url) throws IOException, MalformedURLException {
		JsonNode pokemonJson = JsonUtil.parse(url).get("results");	
		List<JsonNode> pokemonList = StreamSupport.stream(pokemonJson.spliterator(), false)
				.collect(Collectors.toList());
		
		return pokemonList;
	}
	 

}
