package com.genere.orders.tools;

import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class FonctionTools extends CommunTools{

	private final static String PORTAIL = "PORTAIL";
	private final static String FONCTION = "FONCTION";
	private final static String PEV = "PEV";
	private final static String EDITION = "EDITION";
	private final static String LANCEUR = "LANCEUR";
	
	
	private String nomFonctionnalite;
	private String mode;
	private String nomPev;
	private String commentaire;
	private String langue;
	private String traduction;
	
	/**
	 * Constructeur par défaut
	 * @param nomFonctionnalite
	 * @param mode
	 * @param nomPev
	 * @param commentaire
	 */
	public FonctionTools(String nomFonctionnalite, String mode, String nomPev, String commentaire, String langue, String traduction) {
		this.nomFonctionnalite = nomFonctionnalite;
		this.mode = mode;
		this.nomPev = nomPev;
		this.commentaire = commentaire;
		this.langue = langue;
		this.traduction = traduction;
	}
	
	/**
	 * execute le traitement
	 * @return
	 * @throws Exception
	 */
	public void execute() throws Exception {

		FichierEcritureSingleton fichierSortie = FichierEcritureSingleton.INSTANCE;
		//fichierSortie.ecrire("");
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Fonctionnalité "+this.nomFonctionnalite);
		fichierSortie.ecrire("--Type: "+this.mode);
		if(this.commentaire!=null && !this.commentaire.equals("")){
			fichierSortie.ecrire("--"+this.commentaire);
		}
		
		
		verifEntree(ValeursDirectives.NOMFONCTIONNALITE, this.nomFonctionnalite);
		verifEntree(ValeursDirectives.MODE, this.mode);
		
		
		if(PORTAIL.equals(this.mode)){
			
			fichierSortie.ecrire("---------------------------");
			
			genBloc("UT_FCG", "codsoc=~societe AND fct='" + nomFonctionnalite + "'");
			genBloc("UT_PORTAIL_FCT", "codsoc=~societe AND fct_portail='" + nomFonctionnalite + "'");
			genBloc("UT_PORTAIL_GFP", "codsoc=~societe AND fct_portail='" + nomFonctionnalite + "'");
			genBloc("UT_BV_DEF", "codsoc=~societe AND bv_rgp='" + nomFonctionnalite + "'");
		}
		
		if(FONCTION.equals(this.mode)){
			
			fichierSortie.ecrire("---------------------------");
			
			verifEntree(ValeursDirectives.GENPEV, this.nomPev);
			genBloc("UT_FCG", "codsoc=~societe AND fct='" + nomFonctionnalite + "'");
			genBloc("UT_BV_DEF", "codsoc=~societe AND bv_rgp='" + nomFonctionnalite + "'");
			genBloc("UT_FCG", "codsoc=~societe AND fct='" + this.nomPev + "'");
			genBloc("PEV", "codsoc=~societe AND codpev='" + this.nomPev + "'");
			genBloc("PARAV", "codsoc=~societe AND codfct='" + this.nomPev + "'");
			if(this.traduction!=null && ("O").equals(this.traduction )){
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_FCG' AND cleban='" + nomFonctionnalite + "'");
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_FCG' AND cleban='" + nomPev + "'");
			}
		}
		
		if(PEV.equals(this.mode)){
			
			fichierSortie.ecrire("---------------------------");
			
			verifEntree(ValeursDirectives.GENPEV, this.nomPev);
			genBloc("UT_FCG", "codsoc=~societe AND fct='" + this.nomPev + "'");
			genBloc("PEV", "codsoc=~societe AND codpev='" + this.nomPev + "'");
			genBloc("PARAV", "codsoc=~societe AND codfct='" + this.nomPev + "'");
			
			if(this.traduction!=null && ("O").equals(this.traduction )){
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_FCG' AND cleban='" + nomFonctionnalite + "'");
			}
		}
		
		if(EDITION.equals(this.mode)){
			
			fichierSortie.ecrire("---------------------------");
			
			verifEntree(ValeursDirectives.GENPEV, this.nomPev);
			genBloc("UT_FCG", "codsoc=~societe AND fct='" + this.nomFonctionnalite + "'");
			genBloc("PEV", "codsoc=~societe AND codpev='" + this.nomFonctionnalite + "'");
			genBloc("PARAV", "codsoc=~societe AND codfct='" + this.nomFonctionnalite + "'");
			if(this.langue!=null && !("").equals(this.langue)){
				genBloc("UT_PLED", "codsoc=~societe AND codfct='" + this.nomFonctionnalite + "' AND codlan='"+this.langue+"'");
				genBloc("UT_PLEDC", "codsoc=~societe AND codfct='" + this.nomFonctionnalite + "' AND codlan='"+this.langue+"'");
			} else {
				genBloc("UT_PLED", "codsoc=~societe AND codfct='" + this.nomFonctionnalite + "'");
				genBloc("UT_PLEDC", "codsoc=~societe AND codfct='" + this.nomFonctionnalite + "'");
			}
			//System.out.println("Trad:"+this.traduction);
			if(this.traduction!=null && ("O").equals(this.traduction )){
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_FCG' AND cleban='" + nomFonctionnalite + "'");
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_PLED' AND cleban LIKE '" + nomFonctionnalite + "%'");
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_PLEDC' AND cleban LIKE '" + nomFonctionnalite + "%'");
			}
		}
		
		if(LANCEUR.equals(this.mode)){
			verifEntree(ValeursDirectives.GENPEV, this.nomPev);
			
			fichierSortie.ecrire("--Pev:"+this.nomPev);
			fichierSortie.ecrire("--Lanceur:"+this.nomFonctionnalite);
			fichierSortie.ecrire("---------------------------");
			
			if(this.langue!=null && !("").equals(this.langue)){
				genBloc("UT_PLED", "codsoc=~societe AND codfct='" +this.nomPev + "' AND codpled='" + this.nomFonctionnalite + "' AND codlan='"+this.langue+"'");
				genBloc("UT_PLEDC", "codsoc=~societe AND codfct='" +this.nomPev + "' AND codpled='" + this.nomFonctionnalite + "' AND codlan='"+this.langue+"'");
			} else {
				genBloc("UT_PLED", "codsoc=~societe AND codfct='" +this.nomPev + "' AND codpled='" + this.nomFonctionnalite + "'");
				genBloc("UT_PLEDC", "codsoc=~societe AND codfct='" +this.nomPev + "' AND codpled='" + this.nomFonctionnalite + "'");
			}
			if(this.traduction!=null && ("O").equals(this.traduction )){
				//genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_FCG' AND cleban LIKE'" + this.nomPev + "/" + this.nomFonctionnalite + "%'");
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_PLED' AND cleban LIKE '" + this.nomPev + "/" + this.nomFonctionnalite + "%'");
				genBloc("UT_TRAD", "codsoc=~societe AND codent='UT_PLEDC' AND cleban LIKE '" + this.nomPev + "/" + this.nomFonctionnalite + "%'");
			}
		}
		
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Fin fonctionnalité "+this.nomFonctionnalite);
		fichierSortie.ecrire("---------------------------\n");
	}

}
