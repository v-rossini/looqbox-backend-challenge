package com.looqbox.backend.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.looqbox.backend.payload.PokemonPayload;
import com.looqbox.backend.services.IPokemonSearchService;

@RestController
@RequestMapping("/pokemon")
public class PokemonSearchController {

	@Autowired
	IPokemonSearchService pokemonService;
	
	@GetMapping()
	ResponseEntity<List<PokemonPayload>> searchPokemon(@RequestParam(value = "q", defaultValue = "", required=false) String substring, 
			@RequestParam(value = "sorting", defaultValue = "", required=false) String sorting) throws IOException, MalformedURLException{
		
		return new ResponseEntity<>(pokemonService.searchPokemon(substring, sorting), HttpStatus.OK);
		
	}
	
	
}


