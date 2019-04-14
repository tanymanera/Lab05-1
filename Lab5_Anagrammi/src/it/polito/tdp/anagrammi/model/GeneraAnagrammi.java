package it.polito.tdp.anagrammi.model;

import java.util.HashSet;
import java.util.Set;

public class GeneraAnagrammi {

	private static Set<String> listAnagrammi;
	
	private void anagrammaRicorsivo(StringBuilder parziale, StringBuilder restanti) {
		/*
		//E -- sequenza di istruzioni da eseguire sempre
		//	da usare solo in rari casi
		
		//A -- condizione di terminazione
		if(condizione di terminazione) {
			doSomething();
			return;
		}
		
		while(){
			//B
			generaSoluzioneParziale();
			
			if(filtro) {
				//C
				anagrammaRicorsivo(nuovoParziale, level + 1);
			}
			//D -- backtracking
		}
			 *
			 */
		//A
		if(restanti.length() == 0) {
			String charSequence = parziale.toString();
			listAnagrammi.add(charSequence);
			return;
		}

		//B
		for(int index = 0; index < restanti.length(); index++) {
			char ch = restanti.charAt(index);
			StringBuilder newParziale = new StringBuilder(parziale);
			StringBuilder newRestanti = new StringBuilder(restanti);
			newParziale.append(ch);
			newRestanti.deleteCharAt(index);

			//C
			anagrammaRicorsivo(newParziale, newRestanti);
		
		}

	}
	
	public Set<String> GeneraAnagramma(String parola) {
		listAnagrammi = new HashSet<String>();
		StringBuilder parziale = new StringBuilder("");
		StringBuilder restanti = new StringBuilder(parola);
		anagrammaRicorsivo(parziale, restanti);
		return listAnagrammi;
	}

}
