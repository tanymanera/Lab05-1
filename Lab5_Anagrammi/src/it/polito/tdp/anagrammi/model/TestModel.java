package it.polito.tdp.anagrammi.model;

import java.util.List;
import java.util.Set;

import it.polito.tdp.anagrammi.db.ParolaDAO;

public class TestModel {
	
	private void runTest() {
		GeneraAnagrammi ga = new GeneraAnagrammi();
		
		List<List<String>> result = ga.generaAnagramma("eat");
		System.out.println(String.format("Parole corrette: %s", result.get(1).toString()));
		System.out.println(String.format("Parole errate: %s", result.get(0).toString()));
		result = ga.generaAnagramma("atta");
		System.out.println(String.format("Parole corrette: %s", result.get(1).toString()));
		System.out.println(String.format("Parole errate: %s", result.get(0).toString()));
	}

	public static void main(String[] args) {
		TestModel test = new TestModel();
		
		test.runTest();

	}

}
