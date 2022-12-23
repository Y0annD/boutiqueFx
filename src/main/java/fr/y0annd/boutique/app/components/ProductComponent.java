package fr.y0annd.boutique.app.components;

import java.io.IOException;
import java.util.ResourceBundle;

import fr.y0annd.boutique.app.model.Product;
import javafx.animation.RotateTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

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
	private GaussianBlur effect;

//	private Product product;

	public ProductComponent() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductComponent.fxml"),
				ResourceBundle.getBundle("fr.y0annd.boutique.internationalisation.boutique"));
		loader.setRoot(this);
		loader.setController(this);
		loader.load();
		effect = new GaussianBlur(5);
		effect.setRadius(10);
		setEffect(effect);
		final RotateTransition rotation = new RotateTransition(Duration.seconds(1), this);
		rotation.byAngleProperty().set(360);
		rotation.setAxis(Rotate.Y_AXIS);
		setOnMouseClicked(e -> {
			rotation.play();
		});
		
	}

	@FXML
	public void onDragDetected(MouseEvent event) {
		System.out.println("Drag detected for Product: " + product.getNom());
		Dragboard clipBoard = startDragAndDrop(TransferMode.ANY);
		ClipboardContent content = new ClipboardContent();
//		content.put(DataFormat.PLAIN_TEXT, product.getNom());
		content.putString(product.getNom());
		content.putImage(image.getImage());
		clipBoard.setContent(content);
	}

	@FXML
	public void onDragDropped(DragEvent event) {
		System.out.println("Drag done");
		Dragboard clipboard = event.getDragboard();

		if (clipboard.hasString()) {
			event.acceptTransferModes(TransferMode.ANY);
			product.setDescription(clipboard.getString());
			description.setText(product.getDescription());
			System.out.println("Nouvelle description: " + product.getDescription());
		}
		if (clipboard.hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
			product.setUrl_image(clipboard.getFiles().get(0).getAbsolutePath());
			image.setImage(new Image(product.getUrl_image()));
		}
		event.consume();
	}

	@FXML
	public void onDragOver(DragEvent event) {
		System.out.println("Drag entered, " + event);
		Dragboard clipboard = event.getDragboard();
		if (clipboard.hasString() || clipboard.hasFiles()) {
			event.acceptTransferModes(TransferMode.ANY);
			System.out.println("Accept content");
		}

	}

	public String getImage() {
		return "";
	}

	public ProductComponent(Product p) throws IOException {
		this();
		product = p;
		title.textProperty().bind(p.getNomProperty());
		image.imageProperty().bind(p.getImageProperty());
		description.textProperty().bind(p.getDescriptionProperty());
		price.textProperty().bind(p.prixProperty().asString());
		effectProperty().bind(Bindings.when(product.getAvailableProperty()).then((Effect)null).otherwise(effect));
//		effect.radiusProperty().bind(Bindings.when(product.getAvailableProperty()).then(0)
//				.otherwise(10));

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
