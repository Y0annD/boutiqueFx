package fr.y0annd.boutique.app;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

@TestInstance(Lifecycle.PER_CLASS)
public class SaisieNomVilleTest extends ApplicationTest{

	@BeforeAll
	public void setUp () throws Exception {
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SaisieNomVille.fxml"));
		
		//La racine est placee dans la scene.
		Scene scene = new Scene(root);
		
		//On affecte une feuille de style à la scene.
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//On place la scène dans la fenetre.
		stage.setScene(scene);
		
		stage.setTitle("Mon titre");
		
		//On montre la fenetre.
		stage.show();
	}
	
	 @Test
	  public void testEnglishInput () {
	    clickOn("#txtVille");
	    write("This is a test!");
	  }

	@AfterAll
	public void tearDown () throws Exception {
	  FxToolkit.hideStage();
	  release(new KeyCode[]{});
	  release(new MouseButton[]{});
	}
}
