package it.polito.tdp.anagrammi.model;

import java.util.Set;

import it.polito.tdp.anagrammi.db.parolaDAO;

public class TestModel {
	
	private void runTest() {
		GeneraAnagrammi ga = new GeneraAnagrammi();
		parolaDAO dao = new parolaDAO();
		Set<String> lista = ga.GeneraAnagramma("eat");
		System.out.println(lista);
		lista = ga.GeneraAnagramma("atta");
		System.out.println(lista);
		for(String anagramma: lista) {
			if(dao.isCorrect(anagramma)) {
				System.out.println(String.format("la parola %s è corretta.",
						anagramma));
			} else {
				System.out.println(String.format("la parola %s non esiste.",
						anagramma));
			}
		}
	}

	public static void main(String[] args) {
		TestModel test = new TestModel();
		
		test.runTest();

	}

}
