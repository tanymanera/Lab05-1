package it.polito.tdp.anagrammi.controller;
	
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import it.polito.tdp.anagrammi.db.DBConnect;
import it.polito.tdp.anagrammi.model.GeneraAnagrammi;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Anagrammi.fxml")) ;
			BorderPane root = (BorderPane)loader.load();
			
			AnagrammiController controller = loader.getController();
			controller.setModel(new GeneraAnagrammi());
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		Connection ds = DBConnect.getConnection();
		try {
			ds.close();
		} catch (SQLException e) {
			System.err.println("Errore connessione al DB");
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
