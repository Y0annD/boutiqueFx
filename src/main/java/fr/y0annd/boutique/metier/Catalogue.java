package fr.y0annd.boutique.metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Catalogue {

	
	
	public static List<Article> load()
	{
		
		List<Article> listeArticles=new ArrayList<Article>();
		
		//Chargement du fichier d'articles
				try
				{
				
				File fichier=new File( Configuration.getFichierArticles() );
				
				BufferedReader reader=new BufferedReader(new FileReader(fichier));
				String ligne;
				boolean premiereLigne=true;
				
				while ( (ligne=reader.readLine()) !=null)
				{
					if ( !premiereLigne )
					{
						String[] tab=ligne.split(";");
						Article art=new Article();
						art.setNom(tab[0]);
						
						String lePrix=tab[2].replace(',','.');
						art.setPrix(Double.parseDouble(lePrix));
						art.setDescription(tab[1]);
						
						
						//Affectation de l'image.
						String img=Configuration.getRepertoireImages()+tab[4];
						
						art.setImage(img);
						
						art.setVentesUnitaires(2018,Integer.parseInt(tab[6]));
						
						listeArticles.add(art);
					}
					premiereLigne=false;
				}
				
				reader.close();
				}
				catch (Exception exc)
				{
					System.out.println("Probleme lors du chargement des articles: "+exc.getMessage());
					exc.printStackTrace();
				}
				
			return listeArticles;
	}
	
	
	public static void main(String args[])
	{
		List<Article> catalogue = Catalogue.load();
		
		catalogue.forEach(System.out::println);
	}
}
