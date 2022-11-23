package fr.y0annd.boutique.metier;

import java.util.HashMap;
import java.util.Map;

public class Article  {

	private String nom;
	private String description;
	private String categorie;
	private String image;
	private Double prix;
	private Map<Integer,Integer> mapVentes;
	

	
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

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Map<Integer, Integer> getMapVentes() {
		return mapVentes;
	}

	public void setMapVentes(Map<Integer, Integer> mapVentes) {
		this.mapVentes = mapVentes;
	}

	public String getImage() {
		return image;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	
	public Article()
	{

		mapVentes = new HashMap<>();
		
		setNom("Nom  par defaut");
		setDescription("description par defaut");
		setCategorie("categorie par defaut");
		setPrix(0.0);
		setImage( Configuration.getImageParDefaut() );
		
		
		
	}
	
	public Article(String nom, String description, String categorie, double prix)
	{

		mapVentes = new HashMap<>();
		
		setNom(nom);
		setDescription(description);
		setCategorie(categorie);
		setPrix(prix);
		setImage(Configuration.getImageParDefaut());
	}




	
	public void setImage(String image) {
		this.image = image;
	}

	public void setVentesUnitaires(int annee, int valeur)
	{
		mapVentes.put(annee,valeur);
		
	}
	
	
	public int getVentesUnitaires(int annee)
	{
		return mapVentes.get(annee);
	}

	@Override
	public String toString() {
		return "Article [nom=" + nom + ", description=" + description + ", categorie=" + categorie + ", image=" + image
				+ ", prix=" + prix + ", mapVentes=" + mapVentes + "]";
	}
	
	
	
}
