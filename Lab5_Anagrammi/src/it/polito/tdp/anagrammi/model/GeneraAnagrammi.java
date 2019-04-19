package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.db.ParolaDAO;
import javafx.concurrent.Task;

public class GeneraAnagrammi {

	private Set<String> listAnagrammi;
	private static ParolaDAO dao;
	
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
	/**
	 * questo metodo fa un po' troppe cose:
	 * 	1.inizializza tutte le proprietà (cosa che dovrebbe fare un costruttore)
	 * 	2.fa partire la ricorsione chiamando anagrammaRicorsivo -ricorsione livello zero
	 * 	3.popola le due liste che contengono le parole corrette ed errate con chiamata a dao
	 * 	4.visto che le liste sono due, viene creato un vettorino.
	 * 
	 * @param parola Stringa da anagrammare - non deve contenere spazi.
	 */
	public List<String>[] generaAnagramma(String parola) {
		listAnagrammi = new HashSet<String>();
		List<String> corretti = new ArrayList<>();
		List<String> errati = new ArrayList<>();
		List<String>[] result = new ArrayList[2];
		dao = new ParolaDAO();
		StringBuilder parziale = new StringBuilder("");
		StringBuilder restanti = new StringBuilder(parola);
		anagrammaRicorsivo(parziale, restanti);
		
		for(String s: listAnagrammi) {
			if(dao.isCorrect(s)) {
				corretti.add(s);
			} else {
				errati.add(s);
			}
		}
		result[0] = errati;
		result[1] = corretti;
		
		return result;
	}

}
