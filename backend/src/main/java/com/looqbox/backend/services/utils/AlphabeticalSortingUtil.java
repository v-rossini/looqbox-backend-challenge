package com.looqbox.backend.services.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.looqbox.backend.payload.PokemonPayload;

public class AlphabeticalSortingUtil {
	
	/*
	 * Implementação simples de um algorítimo Bubblesort
	 * 
	 * A lista é percorrida diversas vezes, comparando-se os elementos com o elemento seguinte a
	 * cada passagem, e trocando-os de lugar se estiverem na ordem errada.
	 * 
	 * Esse processo faz com que, na primeira iteração, o maior elemento chegue na última posição;
	 * na segunda iteração, o segundo maior na penúltima posição;
	 * e assim sucessivamente.
	 * 
	 * Portanto, não precisamos percorrer a lista até o final a cada iteração, bastando chegar 
	 * ao último elemento que não sabemos se está fora de posição.
	 * 
	 * Se em alguma iteração não forem realizadas nehuma troca, isso quer dizer que a lista está ordenada
	 * e não é necessário continuar.
	 * 
	 * No pior caso, temos uma lista na ordem inversa, de forma que ela terá que ser percorrida N vezes, 
	 * realizando N-1 comparações a cada passagem, o que nos dá um complexidade da
	 * ordem de N^2
	 */

	public static List<PokemonPayload> bubbleSort(List<PokemonPayload> list, Comparator<PokemonPayload> c) {
		
		if (list.size() <= 1)
			return list;
		
		for (int i = 0; i < list.size(); i++) {  //percorre a lista N vezes
			boolean swapped = false; // marca que ainda não foi feita nenhuma troca na iteração atual 
			for (int j = 0; j < list.size() - i - 1; j++) { // compara os elementos com o seguinte
				if (c.compare(list.get(j), list.get(j+1)) > 0) {
					Collections.swap(list, j, j+1);
					swapped = true;
				}
					
			
			}
		if (!swapped) //verifica se, ao final da iteração, foi realizada alguma troca
			break; // se não tiver sido realizada, a lista está ordenada, e o algorítimo pode ser interrompido
		}
		
		
		return list;
		
	}
		
}
	
