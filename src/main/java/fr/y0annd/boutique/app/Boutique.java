package fr.y0annd.boutique.app;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Boutique extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			// Définition de l'url correspondant au fichier FXML.
			URL url = getClass().getClassLoader().getResource("Boutique.fxml");

			// Pour l'externalisation des libellés.
			ResourceBundle i18n = ResourceBundle.getBundle("fr.y0annd.boutique.internationalisation.boutique");
			
			// Parsing du fichier FXML
			Parent root = FXMLLoader.load(url, i18n);

			// La racine est placee dans la scene.
			Scene scene = new Scene(root);

			// On affecte une feuille de style à la scene.
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// On place la scène dans la fenetre.
			primaryStage.setScene(scene);

			primaryStage.setTitle("Mon titre");

			// On montre la fenetre.
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
