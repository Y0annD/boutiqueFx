package fr.y0annd.boutique.app.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import fr.y0annd.boutique.metier.Configuration;

public class Product {

	private String nom;
	private String description;
	private float prix;
	private String categorie;
	private String url_image;
	private String url_details;
	private int ventes_2019;
	private int ventes_2020;
	
	public Product() {
		nom = "Nom";
		description = "description";
		prix = 1.1f;
		categorie = "Categorie";
		url_image="";
	}

	public Product(String data) {
		String dataSplitted[] = data.split(";");
		nom = dataSplitted[0];
		description = dataSplitted[1];
		prix = Float.valueOf(dataSplitted[2].replace(",", "."));
		categorie = dataSplitted[3];
		url_image = "file:///"+Configuration.getRepertoireImages()+dataSplitted[4];
		url_details = dataSplitted[5];
		ventes_2019 = Integer.valueOf(dataSplitted[6]);
		ventes_2019 = Integer.valueOf(dataSplitted[7]);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getUrl_image() {
		return url_image;
	}

	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}

	public String getUrl_details() {
		return url_details;
	}

	public void setUrl_details(String url_details) {
		this.url_details = url_details;
	}

	public int getVentes_2019() {
		return ventes_2019;
	}

	public void setVentes_2019(int ventes_2019) {
		this.ventes_2019 = ventes_2019;
	}

	public int getVentes_2020() {
		return ventes_2020;
	}

	public void setVentes_2020(int ventes_2020) {
		this.ventes_2020 = ventes_2020;
	}

}
