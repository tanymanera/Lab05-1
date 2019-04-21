package it.polito.tdp.anagrammi.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.GeneraAnagrammi;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
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
	
	@FXML
    private Button btnCalcolaAnagrammi;
	
	@FXML
	private Button btnReset;
	
	@FXML
    private ProgressBar bar;

	private GeneraAnagrammi model;

	public void setModel(GeneraAnagrammi model) {
		this.model = model;
	}

	@FXML
	void handleCalcolaAnagrammi(ActionEvent event) {
		String parola = txtInsert.getText().replaceAll("\\s", "").toLowerCase();
		if (parola.equals("")) {
			txtInsert.clear();
			return;
		}

		btnCalcolaAnagrammi.setDisable(true);
		btnReset.setDisable(true);
		
		Task<List<String>[]> task = new Task<List<String>[]>() {

			@Override
			protected List<String>[] call() throws Exception {
				updateProgress(-1, -1);
//				In generale per non avere thread diversi che 
//				operano sulle stesse variabili è meglio che
//				ogni thread operi sulla sua istanza.
//				Qui si opera su un solo thread per volta quindi è
//				inutile.
//				GeneraAnagrammi genera = new GeneraAnagrammi();
//				List<String>[] result = genera.generaAnagramma(parola);
				List<String>[] result = model.generaAnagramma(parola);
				updateProgress(1, 1);
				return result;
			}

		};

		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

			@Override
			public void handle(WorkerStateEvent event) {
				List<String>[] result = task.getValue();
				List<String> errati = result[0];
				List<String> corretti = result[1];
				for (String corretta : corretti) {
					txtCorretti.appendText(corretta + "\n");
				}
				for (String errata : errati) {
					txtErrati.appendText(errata + "\n");

				}
				
				btnReset.setDisable(false);
			}
		});
		//Collego la property progress della barra a quella del task
		bar.progressProperty().bind(task.progressProperty());

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();

	}

	@FXML
	void handleReset(ActionEvent event) {
		txtInsert.clear();
		txtCorretti.clear();
		txtErrati.clear();
		btnCalcolaAnagrammi.setDisable(false);

	}

	@FXML
	void initialize() {
		assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'Anagrammi.fxml'.";
		assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Anagrammi.fxml'.";
		assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Anagrammi.fxml'.";
		assert btnCalcolaAnagrammi != null : "fx:id=\"btnCalcolaAnagrammi\" was not injected: check your FXML file 'Anagrammi.fxml'.";
		assert bar != null : "fx:id=\"bar\" was not injected: check your FXML file 'Anagrammi.fxml'.";
		
	}
}
