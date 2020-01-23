package com.genere.orders.tools.fichiers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.genere.orders.config.WSConfigurer;
import com.genere.orders.tools.Fichier;
import com.genere.orders.tools.GenereOrdersException;

public class FichierEcriture extends Fichier {

	private FileWriter writer;
	
	public FichierEcriture(String nomFichier) throws IOException, GenereOrdersException{
		super(nomFichier);
		/*try{
			this.writer = new FileWriter(WSConfigurer.INSTANCE.getProperty("dossier_generation")+this.cheminFichier, false);
		}catch(IOException e){
			throw new GenereOrdersException(e.getMessage());
		}
	    System.out.println("Création fichier OK ==> "+ nomFichier);*/
		String dossier_generation = WSConfigurer.INSTANCE.getProperty("dossier_generation");
		System.out.println("Dossier:"+dossier_generation);
		if(dossier_generation==null){
			dossier_generation = new String("");
		}
//		nbLigne=0;
		this.writer = new FileWriter(new File(dossier_generation+nomFichier));
		cheminFichier=nomFichier;
		System.out.println("Création fichier OK ==> "+ nomFichier);
	}
	
	public void rotationFichier(String nomFichier) throws IOException{
		if(writer!= null){
			this.writer.close();
		}
		this.writer = new FileWriter(nomFichier, false);
		cheminFichier=nomFichier;
		System.out.println("Création fichier OK ==> "+ nomFichier);
	}
	
	public void ecrire(String ligne) throws IOException{
		this.writer.write(ligne+"\n",0,(ligne+"\n").length());
	}
	
	
	public void fermer() throws IOException{
		this.ecrire("-- Fin Corps du script");
		this.writer.close();

	}
}
