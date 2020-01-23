package com.genere.orders.parser.v2.analyse;

import java.util.Iterator;
import java.util.LinkedList;

import com.genere.orders.parser.v2.directives.AbstractDirective;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class FichierDescription {

	private boolean actif;
	private String nom;
	private LinkedList<AbstractDirective> listeDirective;
	
	/**
	 * Constructeur par défaut
	 * @param actif
	 */
	public FichierDescription(boolean actif, String nom) {
		this.actif = actif;
		this.nom = nom;
		listeDirective = new LinkedList<AbstractDirective>();
	}
	
	public void setActif(boolean actif){
		this.actif = actif;
	}
	
	/**
	 * ajoute une directive
	 * @param directive
	 */
	public void add(AbstractDirective directive){
		listeDirective.add(directive);
		//System.out.println("ajout "+directive.getClass());
	}
	
	public void execute() throws Exception{
		//boolean retour=true;
		//si l'exécution est active, on exécute la pile de directive
		if(actif){
			System.out.println("Traitement fichier:"+this.nom);
			//int nbDirective = listeDirective.size();
			//for (int i=0; i<nbDirective; i++){
			/*while(!listeDirective.isEmpty() && retour){
				retour = listeDirective.getFirst().execute();
				listeDirective.removeFirst();
			}*/
			
			Iterator<AbstractDirective> it = listeDirective.iterator();         
			while(it.hasNext())         {             
				AbstractDirective directive = it.next();             
				directive.execute();         
			}  


			
			FichierEcritureSingleton.INSTANCE.fermer();
			System.out.println("Fin traitement fichier");
		}
	}
	
}
