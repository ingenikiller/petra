package com.genere.orders.tools;

import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class PpeTools extends CommunTools {

	private String fonction;
	private String codePpe;
	private String commentaire;
	
	
	/**
	 * constructeur par défaut
	 * @param codePpe codpar du PPE
	 * @param fonction liste de fonction à rechercher
	 * @param commentaire
	 */
	public PpeTools(String codePpe, String fonction, String commentaire){
		this.codePpe = codePpe;
		this.fonction = fonction;
		this.commentaire = commentaire;
	}
	
	public void execute() throws Exception{
		
		verifEntree(ValeursDirectives.CODEPPE, this.codePpe);
		
		FichierEcritureSingleton fichierSortie = FichierEcritureSingleton.INSTANCE;
		fichierSortie.ecrire("");
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Extraction PPE ");
		fichierSortie.ecrire("--Code: "+this.codePpe);
		fichierSortie.ecrire("--Fonctions: "+this.fonction);
		if(this.commentaire!=null && !this.commentaire.equals("")){
			fichierSortie.ecrire("--"+this.commentaire);
		}
		fichierSortie.ecrire("---------------------------");
		
		String listeFonction = "";
		//remplacement de la liste de fonction par une liste utilisable dans une clause IN
		if(fonction!=null && !fonction.equals("")){
			listeFonction = this.replaceVirgule(fonction);
		} else {
			listeFonction="' '";
		}
		
		genBloc("PARAV", "codsoc=~societe AND codpar='"+codePpe+"' AND codfct IN ("+listeFonction+")");
		
		//verifEntree(MODE, this.mode);
		
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Fin PPE");
		fichierSortie.ecrire("---------------------------\n");
	}
	
	
}
