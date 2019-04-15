package it.polito.tdp.anagrammi.model;

import java.util.Set;

import it.polito.tdp.anagrammi.db.ParolaDAO;

public class TestModel {
	
	private void runTest() {
		GeneraAnagrammi ga = new GeneraAnagrammi();
		
		ga.generaAnagramma("eat");
		System.out.println(String.format("Parole corrette: %s", GeneraAnagrammi.getCorretti().toString()));
		System.out.println(String.format("Parole errate: %s", GeneraAnagrammi.getErrati().toString()));
		ga.generaAnagramma("atta");
		System.out.println(String.format("Parole corrette: %s", GeneraAnagrammi.getCorretti().toString()));
		System.out.println(String.format("Parole errate: %s", GeneraAnagrammi.getErrati().toString()));
	}

	public static void main(String[] args) {
		TestModel test = new TestModel();
		
		test.runTest();

	}

}
