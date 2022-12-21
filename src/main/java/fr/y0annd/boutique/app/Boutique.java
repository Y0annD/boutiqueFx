package fr.y0annd.boutique.app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Boutique extends Application {

	@Override
	public void start(Stage primaryStage) {
		long start = System.currentTimeMillis();
		try {
			// Définition de l'url correspondant au fichier FXML.
			URL url = getClass().getClassLoader().getResource("Boutique.fxml");
			System.out.println("Get URL: " + (System.currentTimeMillis() - start));
			// Pour l'externalisation des libellés.
			ResourceBundle i18n = ResourceBundle.getBundle("fr.y0annd.boutique.internationalisation.boutique");

			// Parsing du fichier FXML
			FXMLLoader loader = new FXMLLoader(url);
			System.out.println("Loader: " + (System.currentTimeMillis() - start));
			Parent root = loader.load(url, i18n);
			System.out.println("Load: " + (System.currentTimeMillis() - start));
//			((BoutiqueController)loader.getController()).init();
			// La racine est placee dans la scene.
			Scene scene = new Scene(root);

			// On affecte une feuille de style à la scene.
			URL css = getClass().getClassLoader().getResource("application.css");

			scene.getStylesheets().add(css.toExternalForm());
			System.out.println("Add stylesheet: " + (System.currentTimeMillis() - start));
			// On place la scène dans la fenetre.
			primaryStage.setScene(scene);
			System.out.println("Set scene: " + (System.currentTimeMillis() - start));
			primaryStage.setTitle("Mon titre");

			// On montre la fenetre.
			primaryStage.show();
			System.out.println("Show in: " + (System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
