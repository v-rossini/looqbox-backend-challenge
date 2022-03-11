package com.looqbox.backend.services.utils;

import java.util.Arrays;
import java.util.List;

import com.looqbox.backend.payload.PokemonPayload;

public class LengthSortingUtil {
	
	
	
	/*
	 * Implementação do método Counting Sort
	 * 
	 * Este método não emprega comparações diretas entre os dados, em vez disso contando com um vetor de 
	 * frequencias acumuladas para determinar a posição de cada entrada na lista ordenada. Basicamente, temos uma 
	 * estrutura onde armazenamos em cada índice a quantidade de valores menores ou iguais àquele índice.
	 * Com essas informações, percorre-se a lista original de trás para frente e, a cada valor, sabemos quantos
	 * outros elementos menores que o mesmo existem, e então o colocamos na posição correta.
	 * 
	 ***** Exemplo: [1 3 2 2 4 5 1 2]
	 ***** Podemos obervar que há 8 elementos menores ou iguais a 5; 
	 ***** 7 maiores ou iguais a 4;
	 ***** 6 maiores ou uguais a 3;
	 ***** 5 maiores ou iguais a 2;
	 ***** 2 maiores ou iguais a 1;
	 ***** 
	 *****  Ao percorrermos a lista na ordem inversa encontrarmos o primeiro 2, e sabemos que podemos colocá-lo na posição 5.
	 *****  Em seguida, encontramos o 1, e podemos colocá-lo na posição 2.
	 *****  Podemos colocar o 5 na posição 8.
	 *****  Podemos colocar o 4 na posição 7.
	 *****  Podemos colocar o próximo 2 na posição 4 (uma vez que o outro 2 já foi posicionado)...
	 *****  E assim sucessivamente.
	 * 
	 * Esse método possui complexidade linear, uma vez que a lista apenas será percorrida para preencher o vetor de 
	 * frequencias, e depois novamente para reposicionar cada elemento.
	 * No entanto, há uma complexidade espacial adicional em relação à maioria dos outros métodos de ordenação,
	 * uma vez que é necessário armazenar o vetor de frequencias e, em alguns casos, uma estrutura auxiliar para armazar
	 * o vetor ordenado (como não há comparações e os elementos são simplesmente reposicionados no índice correto,
	 * não podemos fazer substituições na lista inicial)
	 * 
	 * Sendo assim, esse método é recomendado quando todas as entradas podem ser identificadas por um número de chaves
	 * pequeno (por exemplo, números inteiros em um intervalo conhecido, ou objetos separados em 
	 * categorias hierárquicas).
	 * Como usaremos esse método para ordenar as entradas em relação ao comprimento do nome, podemos assumir
	 * com relativa certeza que isso será verdade, fazendo com que esse seja o caso de uso ideal para esse método.
	 * 
	 */
	public static List<PokemonPayload> countingSort (List<PokemonPayload> list) {
		
		if (list.size() <= 1)
			return list;
		
		int max = 0;
		
		for (PokemonPayload pokemon : list) {
			int len = pokemon.getName().length();
			
			if (len > max)
				max = len;  // encontrando o comprimento máximo, que será o tamanho do nosso vetor de frequencias
		}
		
		
		int[] frequencyArray = new int[max + 1 ];
		Arrays.fill(frequencyArray, 0);
		
		for (PokemonPayload pokemon : list) {
			int len = pokemon.getName().length();
			frequencyArray[ len ] += 1 ; // calculando quantas vezes cada comprimento aparece
		}
		
		for (int i = 1; i < frequencyArray.length; i++)
			frequencyArray[i] += frequencyArray[i - 1]; // calculado as frequencias acumuladas, ou seja, quantos valores
														// com comprimento menor ou igual a K foram encontrados
		
		PokemonPayload[] sortedArray = new PokemonPayload[list.size()]; // estrutura para armazenar os valores ordenados
																		// com comprimento igual à lista original
		
		for (int i = sortedArray.length - 1; i >= 0; i-- ) {
			int pos = list.get(i).getName().length();
			sortedArray[ frequencyArray[ pos ] - 1 ] = list.get(i); // cada valor é colocado na posição certa...
			frequencyArray[ pos ] -= 1; // ...e a frequencia referente àquele valor é decrementada.
		}
		
		list.clear(); // limpamos a lista...
		
		for (int i = 0; i < sortedArray.length; i++)
			list.add(sortedArray[i]); //... e inserimos os elementos na ordem certa
	
		return list;
	}
	
}
