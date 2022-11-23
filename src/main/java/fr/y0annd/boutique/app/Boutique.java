package fr.y0annd.boutique.app;

import fr.y0annd.boutique.metier.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Boutique extends Application{


	
	@Override
	public void start(Stage primaryStage) {
		try {
			
Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Boutique.fxml"));
			
			//La racine est placee dans la scene.
			Scene scene = new Scene(root);
			
			//On affecte une feuille de style à la scene.
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//On place la scène dans la fenetre.
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("Mon titre");
			
			//On montre la fenetre.
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
