package com.looqbox.backend.services.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.looqbox.backend.payload.PokemonPayload;


class LengthSortingUtilTest {


	@Test
	void testAbraBeforeKadabra() {
		
		List<PokemonPayload> pokemonList = new ArrayList<PokemonPayload>();
		PokemonPayload abra = new PokemonPayload("abra", "br");
		PokemonPayload kadabra = new PokemonPayload("kadabra", "br");
		PokemonPayload alakazam = new PokemonPayload("alakazam", "br");
		pokemonList.add(kadabra);
		pokemonList.add(abra);
		pokemonList.add(alakazam);
		
		LengthSortingUtil.countingSort(pokemonList);
		
		for (int i = 0; i< pokemonList.size()-1; i++)
			assertTrue(pokemonList.get(i).getName().length() <= pokemonList.get(i+1).getName().length());
		

	}
	
	@Test
	void testOneElementList() {
		
		List<PokemonPayload> pokemonList = new ArrayList<PokemonPayload>();
		PokemonPayload abra = new PokemonPayload("abra", "br");
		pokemonList.add(abra);
		
		assertEquals(pokemonList, LengthSortingUtil.countingSort(pokemonList));

	}
	
	@Test
	void emptyList() {
		
		List<PokemonPayload> pokemonList = new ArrayList<PokemonPayload>();
		
		assertEquals(pokemonList, LengthSortingUtil.countingSort(pokemonList));
		
	}
}
