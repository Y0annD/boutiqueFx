package fr.y0annd.boutique.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SaisieNomVille extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SaisieNomVille.fxml"));
			
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
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
