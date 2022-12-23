package fr.y0annd.boutique.app.metier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.y0annd.boutique.app.model.Product;
import fr.y0annd.boutique.metier.Configuration;

public class Catalogue {

	public static List<Product> load() throws IOException {
		Configuration conf = new Configuration();
		List<Product> products = new ArrayList<>();
		Scanner sc = new Scanner(new File(conf.getFichierArticles()));
		sc.nextLine();
		while (sc.hasNextLine()) {
			products.add(new Product(sc.nextLine()));
		}
//		List<String> lines = Files.readAllLines(Path.of( conf.getFichierArticles()));
//		for (int index = 1; index < lines.size(); index++) {
//			products.add(new Product(lines.get(index)));
//		}
		return products;

	}

	public static void main(String args[]) {
		List<Product> catalogue;
		try {
			catalogue = Catalogue.load();
			catalogue.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
