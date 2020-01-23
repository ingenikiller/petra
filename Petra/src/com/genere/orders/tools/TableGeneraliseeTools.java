package com.genere.orders.tools;

import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class TableGeneraliseeTools extends CommunTools{

	private String tbl;
	private String mode;
	private String type;
	private String commentaire;
	private String traduction;
	
	/**
	 * constructeur par défaut
	 * @param tbl
	 * @param mode TTU ou TBL
	 * @param commentaire
	 */
	public TableGeneraliseeTools(String tbl, String mode, String type, String commentaire, String traduction){
		this.tbl = tbl;
		this.mode = mode;
		this.type = type;
		//this.table = table;
		this.commentaire = commentaire;
		this.traduction = traduction;
	}
	
	/**
	 * exécution du traitement
	 * @throws Exception
	 */
	public void execute() throws Exception{
		verifEntree(ValeursDirectives.CODETBL, this.tbl);
		verifEntree(ValeursDirectives.MODE, this.mode);
		verifEntree(ValeursDirectives.TYPE, this.mode);
		
		FichierEcritureSingleton fichierSortie = FichierEcritureSingleton.INSTANCE;
		fichierSortie.ecrire("");
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Tbl "+this.tbl);
		if(this.commentaire!=null && !this.commentaire.equals("")){
			fichierSortie.ecrire("--"+this.commentaire);
		}
		fichierSortie.ecrire("---------------------------");
		
		if(this.mode.equals("DECLARATION")){
			genBloc("TBL", "codsoc=~societe AND codtbl='"+ type.toLowerCase() +"' AND cletbl='"+this.tbl+"'");
			if(this.traduction!= null && this.traduction.equals("O")){
				genBloc("TBLLAN", "codsoc=~societe AND codtbl='"+ type.toLowerCase() +"' AND cletbl='"+this.tbl+"'");
			}
		}else{
		
			if(this.mode.equals("COMPLET")){
				genBloc("TBL", "codsoc=~societe AND codtbl='"+ type.toLowerCase() +"' AND cletbl='"+this.tbl+"'");
			}
			genBloc("TBL", "codsoc=~societe AND codtbl ='"+this.tbl+"'");
			
			if(this.traduction!= null && this.traduction.equals("O")){
				if(this.mode.equals("COMPLET")){
					genBloc("TBLLAN", "codsoc=~societe AND codtbl='"+ type.toLowerCase() +"' AND cletbl='"+this.tbl+"'");
				}
				genBloc("TBLLAN", "codsoc=~societe AND codtbl ='"+this.tbl+"'");
				
			}
		}
		
	}
	
}
