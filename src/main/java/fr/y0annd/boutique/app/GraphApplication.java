package fr.y0annd.boutique.app;

import java.util.List;
import java.util.concurrent.Executors;

import fr.y0annd.boutique.app.metier.Catalogue;
import fr.y0annd.boutique.app.model.Product;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.stage.Stage;

public class GraphApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Ouvre une nouvelle fenêtre
		// afficher un line chart avec en une série pour 2018 avec en absices le nom des
		// articles et en ordonnée les ventes
		CategoryAxis articlesNoms = new CategoryAxis();
		NumberAxis ordonnees = new NumberAxis();
		articlesNoms.setLabel("Articles");
		ordonnees.setLabel("Ventes 2018");
		long start = System.currentTimeMillis();
		System.out.println("Load time: " + (System.currentTimeMillis() - start));
		XYChart.Series<String, Number> serie2018 = new XYChart.Series();
		XYChart.Series<String, Number> serie2020 = new XYChart.Series();
		serie2018.setName("2018");
		serie2020.setName("2020");
		Task buildGraphTask = new GraphBuildTask();
		buildGraphTask.valueProperty().addListener((obs, old, newVal) -> {
			Data[] newData = (Data[]) newVal;
			Platform.runLater(()->{
				if (newData[0] instanceof Data) {
					serie2018.getData().add(newData[0]);
				}else {
					System.out.println("Data non conforme");
				}
				if (newData[1] instanceof Data) {
					serie2020.getData().add(newData[1]);
				}else {
					System.out.println("Data 1 non conforme");
				}
			});
		});
		Executors.newSingleThreadExecutor().execute(buildGraphTask);
//		for (Product product : products) {
//			serie2018.getData().add(new Data<String, Number>(product.getNom(), product.getVentes_2019()));
//			serie2020.getData().add(new Data<String, Number>(product.getNom(), product.getVentes_2020()));
//		}
		LineChart<String, Number> chart = new LineChart<>(articlesNoms, ordonnees);
		chart.getData().add(serie2018);
		chart.getData().add(serie2020);
		Scene scene = new Scene(chart);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
