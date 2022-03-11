package com.looqbox.backend.services.utils.comparators;

import java.util.Comparator;

import com.looqbox.backend.payload.PokemonPayload;

public class LengthComparator implements Comparator<PokemonPayload> {

	@Override
	public int compare(PokemonPayload pokemon1, PokemonPayload pokemon2) {
		if (pokemon1.getName().length() > pokemon2.getName().length() )
			return 1;
		return -1;
	}

}
