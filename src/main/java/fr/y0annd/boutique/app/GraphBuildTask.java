package fr.y0annd.boutique.app;

import java.util.List;

import fr.y0annd.boutique.app.metier.Catalogue;
import fr.y0annd.boutique.app.model.Product;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart.Data;

public class GraphBuildTask extends Task<Data<String, Number>[]> {

	public GraphBuildTask() {

	}

	@Override
	protected Data<String, Number>[] call() throws Exception {
		Data<String, Number>[] value = new Data[2];

		List<Product> catalogue = Catalogue.load();
		for (Product product : catalogue) {
			Data<String, Number> data2019 = new Data<String, Number>(product.getNom(), product.getVentes_2019());
			Data<String, Number> data2020 = new Data<String, Number>(product.getNom(), product.getVentes_2020());
			updateValue(new Data[] { data2019, data2020 });
			if (isCancelled()) {
				return value;
			}
			System.out.println(value);
		}
		return value;
	}

}
