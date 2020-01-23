package com.genere.orders.parser.v2.analyse;

import java.util.Iterator;
import java.util.LinkedList;

import com.genere.orders.tools.ParamMoteur;
import com.genere.orders.tools.ParamRequeteReplacement;
import com.genere.orders.tools.VariableRequeteRemplacement;

public class AnalyseFinale {

	private LinkedList<FichierDescription> liste;
	
	public AnalyseFinale(){
		this.liste = new LinkedList<FichierDescription>();
	}
	
	/**
	 * ajouter un fichier de description à l'analyse
	 * @param fichier
	 */
	public void add(FichierDescription fichier){
		this.liste.add(fichier);
	}
	
	/**
	 * lance le traitement des fichiers analysés
	 * @throws Exception
	 */
	public void execute() throws Exception{
		System.out.println("Lancement traitement");
		/*while(!this.liste.isEmpty()){
			//on vide le paramétrage entre chaque fichier
			ParamMoteur.INSTANCE.videListe();
			ParamRequeteReplacement.INSTANCE.videListe();
			VariableRequeteRemplacement.INSTANCE.videListe();
			this.liste.getFirst().execute();
			this.liste.removeFirst();
		}*/
		
		Iterator<FichierDescription> it = this.liste.iterator();         
		while(it.hasNext())         {             
			ParamMoteur.INSTANCE.videListe();
			ParamRequeteReplacement.INSTANCE.videListe();
			VariableRequeteRemplacement.INSTANCE.videListe();
			FichierDescription fichier = it.next();             
			fichier.execute();         
		}  
		
	}
	
}
