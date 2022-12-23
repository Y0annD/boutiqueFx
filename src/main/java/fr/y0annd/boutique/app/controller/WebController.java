package fr.y0annd.boutique.app.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fr.y0annd.boutique.app.metier.Catalogue;
import fr.y0annd.boutique.app.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WebController implements Initializable {
	@FXML
	private WebView webView;
	@FXML
	private Button previousBtn;
	@FXML
	private Button nextBtn;
	@FXML
	private TextField urlTxt;
	@FXML
	private Button goBtn;
	@FXML
	private Button statsBtn;

	// Event Listener on Button[#previousBtn].onAction
	@FXML
	public void onPrevious(ActionEvent event) {
		WebHistory history = webView.getEngine().getHistory();
		history.go(-1);
		urlTxt.setText(history.getEntries().get(history.getCurrentIndex()).getUrl());

	}

	// Event Listener on Button[#nextBtn].onAction
	@FXML
	public void onNext(ActionEvent event) {
		WebHistory history = webView.getEngine().getHistory();
		history.go(1);
		urlTxt.setText(history.getEntries().get(history.getCurrentIndex()).getUrl());

	}

	// Event Listener on Button[#goBtn].onAction
	@FXML
	public void onGo(ActionEvent event) {
		System.out.println("Go to: " + urlTxt.getText());
		URL url = null;
		try {
			url = new URL(urlTxt.getText());
			webView.getEngine().load(url.toString());
		} catch (MalformedURLException e) {
			System.err.println("L'URL est incorrecte");
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#statsBtn].onAction
	@FXML
	public void onStats(ActionEvent event) throws IOException {
// Ouvre une nouvelle fenêtre
		// afficher un line chart avec en une série pour 2018 avec en absices le nom des
		// articles et en ordonnée les ventes
		Stage stage = new Stage();
		stage.initModality(Modality.WINDOW_MODAL);
		CategoryAxis articlesNoms = new CategoryAxis();
		NumberAxis ordonnees = new NumberAxis();
		articlesNoms.setLabel("Articles");
		ordonnees.setLabel("Ventes 2018");
		List<Product> products = Catalogue.load();
		XYChart.Series<String, Number> serie2018 = new XYChart.Series();
		XYChart.Series<String, Number> serie2020 = new XYChart.Series();
		serie2018.setName("2018");
		serie2020.setName("2020");
		for(Product product: products) {
			serie2018.getData().add(new Data<String, Number>(product.getNom(), product.getVentes_2019()));
			serie2020.getData().add(new Data<String, Number>(product.getNom(), product.getVentes_2020()));
		}
		LineChart<String, Number> chart = new LineChart<>(articlesNoms, ordonnees);
		chart.getData().add(serie2018);
		chart.getData().add(serie2020);
		Scene scene = new Scene(chart);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		nextBtn.disableProperty().bind(webView.getEngine().getHistory().currentIndexProperty()
//				.lessThan(webView.getEngine().getHistory().getEntries().size()));
		previousBtn.disableProperty().bind(webView.getEngine().getHistory().currentIndexProperty().isEqualTo(0));
	}
}
