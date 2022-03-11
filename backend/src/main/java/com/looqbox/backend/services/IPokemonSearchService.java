package com.looqbox.backend.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.looqbox.backend.payload.PokemonPayload;

public interface IPokemonSearchService {
	List<PokemonPayload> searchPokemon(String substring, String sorting) throws MalformedURLException, IOException;
}
