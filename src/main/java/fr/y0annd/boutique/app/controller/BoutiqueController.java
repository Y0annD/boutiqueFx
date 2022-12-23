package fr.y0annd.boutique.app.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import fr.y0annd.boutique.app.components.ProductContainerComponent;
import fr.y0annd.boutique.app.metier.Catalogue;
import fr.y0annd.boutique.app.model.Product;
import fr.y0annd.boutique.app.model.Rate;
import fr.y0annd.boutique.metier.Configuration;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
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
	private TableColumn<Product, Boolean> colAvailable;
	@FXML
	private TableColumn<Product, Rate> colSells;
	@FXML
	private Accordion accordion;
	@FXML
	private ComboBox<String> filterCbo;

	public ImageView getFolderView() {
		ImageView folderView = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("folder.png")));
		folderView.setPreserveRatio(true);
		folderView.setFitWidth(ICON_SIZE);
		return folderView;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			if (!new File(Configuration.getRepertoireImages()).exists()) {
				Alert alertDlg = new Alert(AlertType.ERROR);
				alertDlg.setContentText("Impossible de charger les images");
				Optional<ButtonType> result = alertDlg.showAndWait();

				result.get();
				System.exit(-1);
			}
			List<Product> products = Catalogue.load();
			Map<String, ObservableList<Product>> productsByCategory = new HashMap<>();
			ObservableList<Product> observableList = FXCollections.observableArrayList(products);
			for (Product product : observableList) {
				if (!productsByCategory.containsKey(product.getCategorie())) {
					productsByCategory.put(product.getCategorie(), FXCollections.observableArrayList());
				}
				productsByCategory.get(product.getCategorie()).add(product);
			}

			ObservableList<String> categories = FXCollections
					.observableList(new ArrayList<>(productsByCategory.keySet()));
			categories.add(null);
			filterCbo.itemsProperty().set(categories);
			FilteredList<Product> filteredData = new FilteredList<>(observableList, p -> {
				String selectedItem = filterCbo.getSelectionModel().getSelectedItem();
				if (null != selectedItem && !"".equals(selectedItem)) {
					return p.getCategorie().equals(selectedItem);
				} else {
					return true;
				}
			});
			filterCbo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
				if(newValue==null) {
					tableView.setItems(FXCollections.observableArrayList(products));
				}else if(newValue != oldValue) {
					tableView.setItems(FXCollections.observableArrayList(products.stream().filter(p->p.getCategorie().equals(newValue)).collect(Collectors.toList())));
				}
			});
			filterCbo.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(Observable observable) {
					filteredData.setPredicate(filteredData.getPredicate());

				}
			});
			;
			tableView.setItems(filteredData);
			tableView.setEditable(true);
			tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			colNom.setCellValueFactory(c -> c.getValue().getNomProperty());
			colNom.setCellFactory(TextFieldTableCell.forTableColumn());
			colDescription.setCellValueFactory(c -> c.getValue().getDescriptionProperty());
			colDescription.setCellFactory(TextFieldTableCell.forTableColumn());
			colPrice.setCellValueFactory(
					/* c -> c.getValue().getPrixProperty() */new PropertyValueFactory<Product, Double>("prix"));
			colAvailable.setCellValueFactory(c -> c.getValue().getAvailableProperty());
			colAvailable.setCellFactory(CheckBoxTableCell.forTableColumn(colAvailable));
//			colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
//
//				@Override
//				public Double fromString(String string) {
//					return Double.valueOf(string);
//				}
//
//				@Override
//				public String toString(Double object) {
//					return object.toString();
//				}
//			}));
			colPrice.setCellFactory(column -> {
				TextFieldTableCell<Product, Double> cell = new TextFieldTableCell<>();
				cell.setConverter(new StringConverter<Double>() {

					@Override
					public Double fromString(String string) {
						return Double.valueOf(string);
					}

					@Override
					public String toString(Double object) {
						return object.toString();
					}
				});
				cell.itemProperty().addListener((observableValue, o, newValue) -> {
					if (null != newValue && newValue < 50) {
						cell.setStyle("-fx-text-fill: green");
					} else {
						cell.setStyle("-fx-text-fill: red");
					}
//					cell.styleProperty()
//							.bind(Bindings.when(Bindings.createDoubleBinding(() -> newValue).lessThan(50))
//									.then("-fx-text-fill: green;").otherwise("-fx-text-fill: red"));
				});
				return cell;
			});
			colSells.setCellValueFactory(c -> new ReadOnlyObjectWrapper(c.getValue().getRateProperty()));
			colSells.setCellFactory(col -> {
				TableCell<Product, Rate> cell = new TableCell<>();
				cell.itemProperty().addListener((observableValue, o, newValue) -> {
					if (newValue != null) {
						if (cell.getGraphic() == null) {
							Node graphic = new JaugeController((Rate) newValue);
							cell.graphicProperty()
									.bind(Bindings.when(cell.emptyProperty()).then((Node) null).otherwise(graphic));
						}
					}
				});
				return cell;
			});

			// observable list of items that fires updates when the selectedProperty of
			// any item in the list changes:
			ObservableList<Product> selectionList = FXCollections
					.observableArrayList(item -> new Observable[] { item.getAvailableProperty() });

			// bind contents to items selected in the table:
			tableView.getSelectionModel().getSelectedItems().addListener((Change<? extends Product> c) -> selectionList
					.setAll(tableView.getSelectionModel().getSelectedItems()));

			// add listener so that any updates in the selection list are propagated to all
			// elements:
			selectionList.addListener(new ListChangeListener<Product>() {

				private boolean processingChange = false;

				@Override
				public void onChanged(Change<? extends Product> c) {
					if (!processingChange) {
						while (c.next()) {
							if (c.wasUpdated() && c.getTo() - c.getFrom() == 1) {
								boolean selectedVal = c.getList().get(c.getFrom()).getAvailable();
								processingChange = true;
								tableView.getSelectionModel().getSelectedItems()
										.forEach(item -> item.setAvailable(selectedVal));
								processingChange = false;
							}
						}
					}
				}

			});

			//
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
			TranslateTransition translate = new TranslateTransition(Duration.seconds(5), title);
			translate.toXProperty().set(500);
			RotateTransition rotation = new RotateTransition(Duration.seconds(5), title);
			rotation.byAngleProperty().set(45);
			ParallelTransition transition = new ParallelTransition(translate, rotation);
			transition.setAutoReverse(true);
			transition.setCycleCount(Timeline.INDEFINITE);
			transition.play();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
