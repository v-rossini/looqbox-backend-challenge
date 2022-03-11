package com.looqbox.backend.services.utils;

import java.util.Arrays;
import java.util.List;

import com.looqbox.backend.payload.PokemonPayload;

public class LengthSortingUtil {
	
	
	
	/*
	 * Implementa��o do m�todo Counting Sort
	 * 
	 * Este m�todo n�o emprega compara��es diretas entre os dados, em vez disso contando com um vetor de 
	 * frequencias acumuladas para determinar a posi��o de cada entrada na lista ordenada. Basicamente, temos uma 
	 * estrutura onde armazenamos em cada �ndice a quantidade de valores menores ou iguais �quele �ndice.
	 * Com essas informa��es, percorre-se a lista original de tr�s para frente e, a cada valor, sabemos quantos
	 * outros elementos menores que o mesmo existem, e ent�o o colocamos na posi��o correta.
	 * 
	 ***** Exemplo: [1 3 2 2 4 5 1 2]
	 ***** Podemos obervar que h� 8 elementos menores ou iguais a 5; 
	 ***** 7 maiores ou iguais a 4;
	 ***** 6 maiores ou uguais a 3;
	 ***** 5 maiores ou iguais a 2;
	 ***** 2 maiores ou iguais a 1;
	 ***** 
	 *****  Ao percorrermos a lista na ordem inversa encontrarmos o primeiro 2, e sabemos que podemos coloc�-lo na posi��o 5.
	 *****  Em seguida, encontramos o 1, e podemos coloc�-lo na posi��o 2.
	 *****  Podemos colocar o 5 na posi��o 8.
	 *****  Podemos colocar o 4 na posi��o 7.
	 *****  Podemos colocar o pr�ximo 2 na posi��o 4 (uma vez que o outro 2 j� foi posicionado)...
	 *****  E assim sucessivamente.
	 * 
	 * Esse m�todo possui complexidade linear, uma vez que a lista apenas ser� percorrida para preencher o vetor de 
	 * frequencias, e depois novamente para reposicionar cada elemento.
	 * No entanto, h� uma complexidade espacial adicional em rela��o � maioria dos outros m�todos de ordena��o,
	 * uma vez que � necess�rio armazenar o vetor de frequencias e, em alguns casos, uma estrutura auxiliar para armazar
	 * o vetor ordenado (como n�o h� compara��es e os elementos s�o simplesmente reposicionados no �ndice correto,
	 * n�o podemos fazer substitui��es na lista inicial)
	 * 
	 * Sendo assim, esse m�todo � recomendado quando todas as entradas podem ser identificadas por um n�mero de chaves
	 * pequeno (por exemplo, n�meros inteiros em um intervalo conhecido, ou objetos separados em 
	 * categorias hier�rquicas).
	 * Como usaremos esse m�todo para ordenar as entradas em rela��o ao comprimento do nome, podemos assumir
	 * com relativa certeza que isso ser� verdade, fazendo com que esse seja o caso de uso ideal para esse m�todo.
	 * 
	 */
	public static List<PokemonPayload> countingSort (List<PokemonPayload> list) {
		
		if (list.size() <= 1)
			return list;
		
		int max = 0;
		
		for (PokemonPayload pokemon : list) {
			int len = pokemon.getName().length();
			
			if (len > max)
				max = len;  // encontrando o comprimento m�ximo, que ser� o tamanho do nosso vetor de frequencias
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
																		// com comprimento igual � lista original
		
		for (int i = sortedArray.length - 1; i >= 0; i-- ) {
			int pos = list.get(i).getName().length();
			sortedArray[ frequencyArray[ pos ] - 1 ] = list.get(i); // cada valor � colocado na posi��o certa...
			frequencyArray[ pos ] -= 1; // ...e a frequencia referente �quele valor � decrementada.
		}
		
		list.clear(); // limpamos a lista...
		
		for (int i = 0; i < sortedArray.length; i++)
			list.add(sortedArray[i]); //... e inserimos os elementos na ordem certa
	
		return list;
	}
	
}
