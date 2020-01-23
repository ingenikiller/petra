package com.genere.orders.tools;

import java.util.HashMap;
import java.util.Map;

import com.genere.orders.objets.LigneFichier;
import com.genere.orders.tools.fichiers.FichierLecture;

public class GestionBloc {
	/**
	 * Map recueuillant les données de la requete
	 */
	private Map<String, String> mapDonnees = new HashMap<String, String>();
	
	/**
	 * parse les données d'entrée
	 * @param fileIn fichier d'entrée
	 * @param directiveDebut début de bloc
	 * @param directiveFin fin de bloc
	 * @param maxInstruction nombre max d'élément du bloc
	 * @throws Exception
	 */
	public void parseBloc(FichierLecture fileIn, String directiveDebut, String directiveFin, int maxInstruction) throws Exception{
		int i=0;
		LigneFichier lf;
		do {
			i++;
			if(i>maxInstruction){
				throw new GenereOrdersException("Bloc "+directiveDebut+" ... "+directiveFin+" non ferme");
			}
			lf = new LigneFichier(fileIn.lireLigne());
			if(lf.entete==null){
				throw new GenereOrdersException("Bloc "+directiveDebut+" mal formé: pas de ligne vide");
			}
			
			mapDonnees.put(lf.entete, lf.contenu);
			
			if(i>maxInstruction){
				throw new GenereOrdersException("Bloc "+directiveDebut+" ... "+directiveFin+" non ferme");
			}
		}while(!lf.entete.equals(directiveFin));
	}

	/**
	 * retourne la valeur de la directive
	 * @param directive valeur de la directive
	 * @return
	 */
	public String getDirectiveValeur(String directive){
		return this.mapDonnees.get(directive);
	}
	
}
