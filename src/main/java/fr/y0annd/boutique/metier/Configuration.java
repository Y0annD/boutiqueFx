package fr.y0annd.boutique.metier;

import java.util.ResourceBundle;

public class Configuration {
	private static ResourceBundle getConfigurationBundle()
	{
		return ResourceBundle.getBundle("fr.y0annd.boutique.conf.configuration");
	}
	
	public static String getRepertoireRacine()
	{
		return getConfigurationBundle().getString("repertoireRessources");
	}
	
	public static String getRepertoireImages()
	{
		return getRepertoireRacine()+"images/";
	}
	
	public static String getTitreApplication()
	{
		return getConfigurationBundle().getString("titreApplication");
	}
	
	public static String getFichierArticles()
	{
		return getRepertoireRacine()+"/"+getConfigurationBundle().getString("fichierArticles");
	}
	
	public static String getImageParDefaut()
	{
		return getConfigurationBundle().getString("imageParDefaut");
	}
	
	
}
