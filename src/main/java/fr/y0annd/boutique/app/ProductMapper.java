package fr.y0annd.boutique.app;

import java.io.IOException;
import java.util.function.Function;

import fr.y0annd.boutique.app.components.ProductComponent;
import fr.y0annd.boutique.app.model.Product;

public class ProductMapper implements Function<Product, ProductComponent> {

	@Override
	public ProductComponent apply(Product t) {
		ProductComponent component;
		try {
			component = new ProductComponent(t);
		} catch (IOException e) {
			component = null;
			e.printStackTrace();
		}
		return component;
	}

}
