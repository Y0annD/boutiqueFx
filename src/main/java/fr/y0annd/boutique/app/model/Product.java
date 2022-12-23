package fr.y0annd.boutique.app.model;

import java.util.Random;

import fr.y0annd.boutique.metier.Configuration;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Product {

	private StringProperty nom;
	private StringProperty description;
	private DoubleProperty prix;
	private StringProperty categorie;
	private ObjectProperty<Image> image;
	private StringProperty url_image;
	private StringProperty url_details;
	private IntegerProperty ventes_2019;
	private IntegerProperty ventes_2020;
	private BooleanProperty available;
	private Rate rate;

	public Product() {
		nom = new SimpleStringProperty("Nom");
		description = new SimpleStringProperty("description");
		prix = new SimpleDoubleProperty(1.1);
		categorie = new SimpleStringProperty("Categorie");
		url_image = new SimpleStringProperty("");
		url_details = new SimpleStringProperty("");
		ventes_2019 = new SimpleIntegerProperty(0);
		ventes_2020 = new SimpleIntegerProperty();
		available = new SimpleBooleanProperty(true);
		Random rdm = new Random();
		rate = new Rate();
		image = new SimpleObjectProperty<Image>();
		int max = rdm.nextInt(10, 10000);
		int value = rdm.nextInt(max + 10);
		rate = new Rate(value, max);
	}

	public Product(String data) {
		this();
		String dataSplitted[] = data.split(";");
		setNom(dataSplitted[0]);
		setDescription(dataSplitted[1]);
		setPrix(Float.valueOf(dataSplitted[2].replace(",", ".")));
		setCategorie(dataSplitted[3]);
		setUrl_image("file:///" + Configuration.getRepertoireImages() + dataSplitted[4]);
		setUrl_details(dataSplitted[5]);
		setVentes_2019(Integer.valueOf(dataSplitted[6]));
		setVentes_2020(Integer.valueOf(dataSplitted[7]));
//		rate = new SimpleDoubleProperty();

		image = new SimpleObjectProperty<>(new Image(url_image.get()));
	}

	public String getNom() {
		return nom.get();
	}

	public void setNom(String n) {
		this.nom.set(n);
	}

	public StringProperty getNomProperty() {
		return nom;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public StringProperty getDescriptionProperty() {
		return description;
	}

	public double getPrix() {
		return prix.get();
	}

	public void setPrix(double prix) {
		this.prix.set(prix);
	}

	public DoubleProperty prixProperty() {
		return prix;
	}

	public String getCategorie() {
		return categorie.get();
	}

	public void setCategorie(String categorie) {
		this.categorie.set(categorie);
	}

	public StringProperty getCategorieProperty() {
		return categorie;
	}

	public String getUrl_image() {
		return url_image.get();
	}

	public void setUrl_image(String url_image) {
		this.url_image.set(url_image);
	}

	public StringProperty getUrl_ImageProperty() {
		return url_image;
	}

	public String getUrl_details() {
		return url_details.get();
	}

	public void setUrl_details(String url_details) {
		this.url_details.set(url_details);
	}

	public StringProperty getUrl_detailsProperty() {
		return url_details;
	}

	public int getVentes_2019() {
		return ventes_2019.get();
	}

	public void setVentes_2019(int ventes_2019) {
		this.ventes_2019.set(ventes_2019);
	}

	public IntegerProperty getVentes_2019Property() {
		return ventes_2019;
	}

	public int getVentes_2020() {
		return ventes_2020.get();
	}

	public void setVentes_2020(int ventes_2020) {
		this.ventes_2020.set(ventes_2020);
	}

	public IntegerProperty getVentes_2020Property() {
		return ventes_2020;
	}

	public Rate getRateProperty() {
		return rate;
	}

	public String getImage() {
		return image.get().getUrl();
	}

	public void setImage(String url) {
		image.set(new Image(url));
	}

	public ObjectProperty<Image> getImageProperty() {
		return image;
	}
	
	public boolean getAvailable() {
		return available.get();
	}
	
	public void setAvailable(boolean available) {
		this.available.set(available);
	}
	
	public BooleanProperty getAvailableProperty() {
		return available;
	}
}
