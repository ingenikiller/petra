package com.genere.orders.tools.fichiers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.genere.orders.config.WSConfigurer;
import com.genere.orders.tools.Fichier;

public class FichierEcritureSingleton extends Fichier {

	public static FichierEcritureSingleton INSTANCE = new FichierEcritureSingleton("");

	private BufferedOutputStream writer;
	
//	private int nbLigne=0;
//	private int limite=1000;
	
	/**
	 * constructeur privé pour gestion singleton
	 * @param cheminFichier
	 */
	private FichierEcritureSingleton(String cheminFichier) {
		super(cheminFichier);
	}
	
	/**
	 * permet de changer de fichier. Si fichier absent, il est créé
	 * @param nomFichier
	 * @throws IOException
	 */
	public void rotationFichier(String nomFichier) throws IOException{
		if(writer!= null){
			this.writer.close();
		}
		
		String dossier_generation = WSConfigurer.INSTANCE.getProperty("dossier_generation");
		System.out.println("Dossier:"+dossier_generation);
		if(dossier_generation==null){
			dossier_generation = new String("");
		}
//		nbLigne=0;
		this.writer = new BufferedOutputStream(new FileOutputStream(new File(dossier_generation+nomFichier)));
		cheminFichier=nomFichier;
		System.out.println("Création fichier OK ==> "+ nomFichier);
	}
	
	/**
	 * écrit une ligne dans le fichier
	 * @param ligne
	 * @throws IOException
	 */
	public void ecrire(String ligne) throws IOException{
		//gestionTampon();
		this.writer.write((ligne+"\n").getBytes(),0,(ligne+"\n").length());
	}
	
	/**
	 * écrit une ligne vide
	 * @throws IOException
	 */
	public void ecrire() throws IOException{
		//gestionTampon();
		this.writer.write("\n".getBytes(),0,("\n").length());
	}
	
	/**
	 * fermeture de fichier
	 * @throws IOException
	 */
	public void fermer() throws IOException{
		this.ecrire("-- Fin Corps du script");
		this.writer.close();

	}
	
//	private void gestionTampon() throws IOException{
//		if(nbLigne>limite){
//			System.out.println("Vidage tampon fichier");
//			this.writer.flush();
//			nbLigne=0;
//		} else {
//			nbLigne++;
//		}
//	}

}
