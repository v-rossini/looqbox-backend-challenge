package com.looqbox.backend.services.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.looqbox.backend.payload.PokemonPayload;
import com.looqbox.backend.services.utils.comparators.AlphabeticalComparator;

class AlphabeticalSortingUtilTest {

	@Test
	void testAbraBeforeKadabra() {
		
		List<PokemonPayload> pokemonList = new ArrayList<PokemonPayload>();
		PokemonPayload abra = new PokemonPayload("abra", "br");
		PokemonPayload kadabra = new PokemonPayload("kadabra", "br");
		PokemonPayload alakazam = new PokemonPayload("alakazam", "br");
		pokemonList.add(kadabra);
		pokemonList.add(abra);
		pokemonList.add(alakazam);
		
		AlphabeticalComparator comparator = new AlphabeticalComparator();
		
		
		AlphabeticalSortingUtil.bubbleSort(pokemonList, comparator);
		
		for (int i = 0; i< pokemonList.size()-1; i++)
			assertTrue(comparator.compare(pokemonList.get(i), pokemonList.get(i+1)) <= 0);
	}
	
	@Test
	void testOneElementList() {
		
		List<PokemonPayload> pokemonList = new ArrayList<PokemonPayload>();
		PokemonPayload abra = new PokemonPayload("abra", "br");
		pokemonList.add(abra);
		
		assertEquals(pokemonList, AlphabeticalSortingUtil.bubbleSort(pokemonList, new AlphabeticalComparator()));

	}
	
	@Test
	void emptyList() {
		
		List<PokemonPayload> pokemonList = new ArrayList<PokemonPayload>();
		
		assertEquals(pokemonList, AlphabeticalSortingUtil.bubbleSort(pokemonList, new AlphabeticalComparator()));
		
	}
}
