package it.polito.tdp.anagrammi.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.GeneraAnagrammi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AnagrammiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInsert;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;
    
    private GeneraAnagrammi model;
    
    public void setModel(GeneraAnagrammi model) {
    	this.model = model;
    }

    @FXML
    void handleCalcolaAnagrammi(ActionEvent event) {
    	String parola = txtInsert.getText().replaceAll("\\s", "").toLowerCase();
    	if(parola.equals("")) {
    		txtInsert.clear();
    		return;
    	}
    	List<String>[] result = model.generaAnagramma(parola);
    	List<String> errati = result[0];
    	List<String> corretti = result[1];
    	for(String corretta: corretti) {
    		txtCorretti.appendText(corretta + "\n");
    	}
    	for(String errata: errati) {
    		txtErrati.appendText(errata + "\n");
    	}
    }

    @FXML
    void handleReset(ActionEvent event) {
    	txtInsert.clear();
    	txtCorretti.clear();
    	txtErrati.clear();

    }

    @FXML
    void initialize() {
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Anagrammi.fxml'.";

    }
}


