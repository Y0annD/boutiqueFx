package fr.y0annd.boutique.app.components;

import java.io.IOException;
import java.util.ResourceBundle;

import fr.y0annd.boutique.app.model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class ProductComponent extends VBox {

	@FXML
	private Label title;
	@FXML
	private ImageView image;
	@FXML
	private Label description;
	@FXML
	private Label price;
	private Product product;

//	private Product product;

	public ProductComponent() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductComponent.fxml"),
				ResourceBundle.getBundle("fr.y0annd.boutique.internationalisation.boutique"));
		loader.setRoot(this);
		loader.setController(this);
		loader.load();
	}
	
	@FXML
	public void onDragDetected(MouseEvent event) {
		System.out.println("Drag detected for Product: " + product.getNom());
		Dragboard clipBoard = startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
		content.put(DataFormat.PLAIN_TEXT, product.getNom());
		content.put(DataFormat.IMAGE, image.getImage());
		clipBoard.setContent(content);
	}

	public String getImage() {
		return "";
	}

	public ProductComponent(Product p) throws IOException {
		this();
		product = p;
		setTitle(p.getNom());
		setImage(p.getUrl_image());
		setDescription(p.getDescription());
		setPrice(String.valueOf(p.getPrix()));
	}

	public void setTitle(String t) {
		title.setText(t);
	}

	public String getTitle() {
		return title.getText();
	}

	public void setImage(String url) {
		image.setImage(new Image(url));
	}

	public void setDescription(String d) {
		description.setText(d);
	}

	public String getDescription() {
		return description.getText();
	}

	public void setPrice(String p) {
		price.setText(p);
	}

	public String getPrice() {
		return price.getText();
	}

}