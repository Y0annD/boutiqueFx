package fr.y0annd.boutique.app.components;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import fr.y0annd.boutique.app.ProductMapper;
import fr.y0annd.boutique.app.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class ProductContainerComponent extends AnchorPane {
	@FXML
	private TitledPane title;

	@FXML
	private TilePane tile;

	public ProductContainerComponent() {
	}

	public ProductContainerComponent(List<Product> products) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductContainerComponent.fxml"),
				ResourceBundle.getBundle("fr.y0annd.boutique.internationalisation.boutique"));
		loader.setRoot(this);
		loader.setController(this);
		loader.load();
		setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		title.setText(products.get(0).getCategorie());
		List<ProductComponent> components = products.stream().map(new ProductMapper()).collect(Collectors.toList());
		tile.getChildren().addAll(components);
	}

	public TitledPane getTitle() {
		return title;
	}
}
