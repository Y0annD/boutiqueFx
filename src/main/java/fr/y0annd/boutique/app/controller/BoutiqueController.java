package fr.y0annd.boutique.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import fr.y0annd.boutique.app.components.ProductContainerComponent;
import fr.y0annd.boutique.app.metier.Catalogue;
import fr.y0annd.boutique.app.model.Product;
import fr.y0annd.boutique.app.model.Rate;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.util.StringConverter;

public class BoutiqueController implements Initializable {
	private static final int ICON_SIZE = 30;

	@FXML
	private Label title;
	@FXML
	private TilePane panel;
	@FXML
	private Tab tabDetail;
	@FXML
	private Tab tabWeb;
	@FXML
	private Tab tabMedia;
	@FXML
	private Tab tabData;
	@FXML
	private Tab tabBackground;
	@FXML
	private TreeView<String> treeView;
	@FXML
	private TableView<Product> tableView;
	@FXML
	private TableColumn<Product, String> colNom;
	@FXML
	private TableColumn<Product, String> colDescription;
	@FXML
	private TableColumn<Product, Double> colPrice;
	@FXML
	private TableColumn<Product, Rate> colSells;
	@FXML
	private Accordion accordion;

	protected Dialog<ButtonType> createExceptionDialog(Throwable th) {
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();

		dialog.setTitle("Program exception");

		final DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.setContentText("Details of the problem:");
		dialogPane.getButtonTypes().addAll(ButtonType.OK);
		dialogPane.setContentText(th.getMessage());
		dialog.initModality(Modality.APPLICATION_MODAL);

		Label label = new Label("Exception stacktrace:");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		th.printStackTrace(pw);
		pw.close();

		TextArea textArea = new TextArea(sw.toString());
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane root = new GridPane();
		root.setVisible(false);
		root.setMaxWidth(Double.MAX_VALUE);
		root.add(label, 0, 0);
		root.add(textArea, 0, 1);
		dialogPane.setExpandableContent(root);
		dialog.showAndWait().filter(response -> response == ButtonType.OK)
				.ifPresent(response -> System.out.println("The exception was approved"));
		return dialog;
	}

	public ImageView getFolderView() {
		ImageView folderView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("folder.png")));
		folderView.setPreserveRatio(true);
		folderView.setFitWidth(ICON_SIZE);
		return folderView;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {

			List<Product> products = Catalogue.load();

			ObservableList<Product> observableList = FXCollections.observableArrayList(products);
			tableView.setItems(observableList);
			tableView.setEditable(true);
			colNom.setCellValueFactory(c -> c.getValue().getNomProperty());
			colNom.setCellFactory(TextFieldTableCell.forTableColumn());
			colDescription.setCellValueFactory(c -> c.getValue().getDescriptionProperty());
			colDescription.setCellFactory(TextFieldTableCell.forTableColumn());
			colPrice.setCellValueFactory(
					/* c -> c.getValue().getPrixProperty() */new PropertyValueFactory<Product, Double>("prix"));
			colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {

				@Override
				public Double fromString(String string) {
					return Double.valueOf(string);
				}

				@Override
				public String toString(Double object) {
					return object.toString();
				}
			}));
			colSells.setCellValueFactory(c -> new ReadOnlyObjectWrapper(c.getValue().getRateProperty()));
			colSells.setCellFactory(col -> {
				TableCell cell = new TableCell<>();
				cell.itemProperty().addListener((observableValue, o, newValue) -> {
					if (newValue != null) {
						Node graphic = new JaugeController((Rate)newValue);
						cell.graphicProperty()
								.bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));
					}
				});
				return cell;
			});
			Map<String, ObservableList<Product>> productsByCategory = new HashMap<>();
			for (Product product : observableList) {
				if (!productsByCategory.containsKey(product.getCategorie())) {
					productsByCategory.put(product.getCategorie(), FXCollections.observableArrayList());
				}
				productsByCategory.get(product.getCategorie()).add(product);
			} //
//			List<ProductComponent> components = products.stream().map(new ProductMapper()).collect(Collectors.toList());
			TreeItem<String> root = new TreeItem<>("Catalogue", getFolderView());

			for (ObservableList<Product> productList : productsByCategory.values()) {
				TreeItem<String> category = new TreeItem<String>(productList.get(0).getCategorie(), getFolderView());
				for (Product product : productList) {
					ImageView itemIcon = new ImageView(new Image(product.getUrl_image()));
					itemIcon.setPreserveRatio(true);
					itemIcon.setFitWidth(ICON_SIZE);
					TreeItem<String> item = new TreeItem(product.getNom(), itemIcon);
					item.valueProperty().bind(product.getNomProperty());
					category.getChildren().add(item);
				}
				root.getChildren().add(category);
				ProductContainerComponent container = new ProductContainerComponent(productList);
				accordion.getPanes().add(container.getTitle());
			}
			treeView.setRoot(root);
		} catch (IOException e) {
			e.printStackTrace();
			createExceptionDialog(e);
		}
		
	}

	
}
