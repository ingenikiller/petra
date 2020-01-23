package com.genere.orders.tools.fichiers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.genere.orders.tools.Fichier;
import com.genere.orders.tools.GenereOrdersException;

public class FichierLecture extends Fichier {
	protected BufferedReader buffer;
	
	/**
	 * constructeur
	 * @param nomFichier nom du fichier complet
	 * @throws FileNotFoundException
	 * @throws GenereOrdersException
	 */
	public FichierLecture(String nomFichier) throws GenereOrdersException{
		super(nomFichier);
		try{
			this.buffer = new BufferedReader(new FileReader(cheminFichier));
		}catch(FileNotFoundException e){
			throw new GenereOrdersException(e.getMessage());
		}
	}
	
	/**
	 * renvoie la ligne en cours du fichier
	 * @return ligne lue dans le fichier
	 * @throws IOException
	 */
	public String lireLigne() throws IOException{
		return this.buffer.readLine(); 
	}
	
//	public String getCheminFichier(){
//		return super.getCheminFichier();
//	}
	
}
