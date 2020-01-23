package com.genere.orders.tools;

import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class SegmentTools extends CommunTools{

	private String segment;
	private String commentaire;
	
	/**
	 * constructeur par défaut
	 * @param segment
	 * @param commentaire
	 */
	public SegmentTools(String segment, String commentaire){
		this.segment = segment;
	}
	
	/**
	 * exécution de la directive
	 * @throws Exception
	 */
	public void execute() throws Exception{
		verifEntree(ValeursDirectives.SEGMENT, this.segment);
		
		FichierEcritureSingleton fichierSortie = FichierEcritureSingleton.INSTANCE;
		fichierSortie.ecrire("");
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Segment "+this.segment);
		if(this.commentaire!=null && !this.commentaire.equals("")){
			fichierSortie.ecrire("--"+this.commentaire);
		}
		fichierSortie.ecrire("---------------------------");
		
		genBloc("TBL", "codsoc=~societe AND codtbl='rzo' AND cletbl='"+this.segment+"'");
		genBloc("TBL", "codsoc=~societe AND codtbl='zon' AND cletbl like'"+this.segment+"___'");
		
	}
	
}
