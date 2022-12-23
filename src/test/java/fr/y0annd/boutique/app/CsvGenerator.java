package fr.y0annd.boutique.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class CsvGenerator {

	Random random = new Random();
	public static final String CSV_SEPARATOR = ";";

	public CsvGenerator() {
		File outputFile = new File("out.csv");
		try (PrintWriter writer = new PrintWriter(outputFile)) {
			generateHeader(writer);
			int nbGeneration = 2000;
			generateCategory("chapeaux", nbGeneration, writer);
			generateCategory("chaussures", nbGeneration, writer);
			generateCategory("costume", nbGeneration, writer);
			generateCategory("veste", nbGeneration, writer);
			writer.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generateHeader(PrintWriter writer) {
		writer.write(
				"nom;description;prix;categorie;url image;url d�tails;Ventes unitaires en 2018;Ventes unitaires en 2019;Ventes unitaires en 2020");
		writer.write(System.lineSeparator());
	}

	public void generateCategory(String categoryName, int nb, PrintWriter writer) {
		for (int index = 0; index < nb; index++) {
			StringBuilder builder = new StringBuilder();
			builder.append(categoryName + " " + (index + 1));
			builder.append(CSV_SEPARATOR);
			builder.append("Description ").append(categoryName).append(" ").append((index + 1));
			builder.append(CSV_SEPARATOR);
			builder.append(random.nextDouble(1000));
			builder.append(CSV_SEPARATOR);
			builder.append(categoryName);
			builder.append(CSV_SEPARATOR);
			builder.append("chapeaux/chapeaux1.png");
			builder.append(CSV_SEPARATOR);
			builder.append(random.nextInt(5000));
			builder.append(CSV_SEPARATOR);
			builder.append(random.nextInt(5000));
			builder.append(CSV_SEPARATOR);
			builder.append(random.nextInt(5000));
			builder.append(CSV_SEPARATOR);
			builder.append(System.lineSeparator());
			writer.write(builder.toString());
		}
	}

	public static void main(String[] args) {
		CsvGenerator generator = new CsvGenerator();

	}

}
