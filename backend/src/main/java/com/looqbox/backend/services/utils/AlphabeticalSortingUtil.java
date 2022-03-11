package com.looqbox.backend.services.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.looqbox.backend.payload.PokemonPayload;

public class AlphabeticalSortingUtil {
	
	/*
	 * Implementa��o simples de um algor�timo Bubblesort
	 * 
	 * A lista � percorrida diversas vezes, comparando-se os elementos com o elemento seguinte a
	 * cada passagem, e trocando-os de lugar se estiverem na ordem errada.
	 * 
	 * Esse processo faz com que, na primeira itera��o, o maior elemento chegue na �ltima posi��o;
	 * na segunda itera��o, o segundo maior na pen�ltima posi��o;
	 * e assim sucessivamente.
	 * 
	 * Portanto, n�o precisamos percorrer a lista at� o final a cada itera��o, bastando chegar 
	 * ao �ltimo elemento que n�o sabemos se est� fora de posi��o.
	 * 
	 * Se em alguma itera��o n�o forem realizadas nehuma troca, isso quer dizer que a lista est� ordenada
	 * e n�o � necess�rio continuar.
	 * 
	 * No pior caso, temos uma lista na ordem inversa, de forma que ela ter� que ser percorrida N vezes, 
	 * realizando N-1 compara��es a cada passagem, o que nos d� um complexidade da
	 * ordem de N^2
	 */

	public static List<PokemonPayload> bubbleSort(List<PokemonPayload> list, Comparator<PokemonPayload> c) {
		
		if (list.size() <= 1)
			return list;
		
		for (int i = 0; i < list.size(); i++) {  //percorre a lista N vezes
			boolean swapped = false; // marca que ainda n�o foi feita nenhuma troca na itera��o atual 
			for (int j = 0; j < list.size() - i - 1; j++) { // compara os elementos com o seguinte
				if (c.compare(list.get(j), list.get(j+1)) > 0) {
					Collections.swap(list, j, j+1);
					swapped = true;
				}
					
			
			}
		if (!swapped) //verifica se, ao final da itera��o, foi realizada alguma troca
			break; // se n�o tiver sido realizada, a lista est� ordenada, e o algor�timo pode ser interrompido
		}
		
		
		return list;
		
	}
		
}
	
