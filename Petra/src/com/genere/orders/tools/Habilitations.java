package com.genere.orders.tools;

import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class Habilitations extends CommunTools{

	private String profilMetier;
	private String chapitre;
	private String fct;
	private String mode;
	private String uti;
	
	private static final String PM_COMPLET = "PM_COMPLET";
	private static final String CHP_COMPLET = "CHP_COMPLET";
	private static final String FCT = "FCT";
	private final static String UTIPM = "UTIPM";
	
	
	/**
	 * Constructeur par défaut
	 * @param profilMetier
	 * @param chapitre
	 * @param fct
	 * @param mode
	 * @param uti
	 */
	public Habilitations(String profilMetier, String chapitre, String fct, String mode, String uti){
		this.profilMetier = profilMetier;
		this.chapitre = chapitre;
		this.fct = fct;
		this.mode = mode;
		this.uti = uti;
	}
	
	/**
	 * exécution du traitement
	 * @return état de l'exécution
	 * @throws Exception 
	 */
	public boolean execute() throws Exception{
		FichierEcritureSingleton fichierSortie = FichierEcritureSingleton.INSTANCE;
		verifEntree(ValeursDirectives.MODE, this.mode);
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Habilitation");
		
		if(!mode.equals(PM_COMPLET)&&!mode.equals(CHP_COMPLET)&&!mode.equals(FCT)&&!mode.equals(UTIPM)){
			throw new GenereOrdersException("Mode d'extraction habilitation '"+this.mode+"' inconnu");
		}
		
		if(this.mode.equals(PM_COMPLET)){
			fichierSortie.ecrire("--Profil métier complet:"+this.profilMetier);
			fichierSortie.ecrire("---------------------------");
			verifEntree(ValeursDirectives.PROFILMETIER, this.profilMetier);
			genBloc("UT_PM", "codsoc=~societe AND codpm='"+this.profilMetier+"'");
			genBloc("UT_PM_CHP", "codsoc=~societe AND codpm='"+this.profilMetier+"'");
			genBloc("UT_HAB_FCT", "codsoc=~societe AND codpm='"+this.profilMetier+"'");
		}
		
		if(this.mode.equals(CHP_COMPLET)){
			fichierSortie.ecrire("--Chapitre complet:"+this.chapitre);
			fichierSortie.ecrire("---------------------------");
			verifEntree(ValeursDirectives.PROFILMETIER, this.profilMetier);
			verifEntree(ValeursDirectives.CODECHAPITRE, this.chapitre);
			String listeChapitre = replaceVirgule(this.chapitre);
			genBloc("UT_PM_CHP", "codsoc=~societe AND codpm='"+this.profilMetier+"' AND codchp IN("+listeChapitre+")");
			genBloc("UT_HAB_FCT", "codsoc=~societe AND codpm='"+this.profilMetier+"' AND codchp IN("+listeChapitre+")");
		}
		
		if(this.mode.equals(FCT)){
			fichierSortie.ecrire("--Profil métier:"+this.profilMetier);
			fichierSortie.ecrire("--Chapitre:"+this.chapitre);
			fichierSortie.ecrire("--Fonctions:"+this.fct);
			fichierSortie.ecrire("---------------------------");
			verifEntree(ValeursDirectives.PROFILMETIER, this.profilMetier);
			verifEntree(ValeursDirectives.CODECHAPITRE, this.chapitre);
			verifEntree(ValeursDirectives.CODEFONCTION, this.fct);
			String chaine = replaceVirgule(this.fct);
			genBloc("UT_HAB_FCT", "codsoc=~societe AND codpm='"+this.profilMetier+"' AND codchp ='"+this.chapitre+"' AND (fct in ("+chaine+") OR fct_portail IN ("+chaine+"))");
		}
		
		
		if(this.mode.equals(UTIPM)){
			fichierSortie.ecrire("--Profil métier:"+this.profilMetier);
			fichierSortie.ecrire("--Utilisateur:"+this.uti);
			verifEntree(ValeursDirectives.UTI, this.uti);
			if(this.profilMetier==null || "".equals(profilMetier.trim())){
				genBloc("UT_UTI_HAB", "codsoc=~societe AND uti='"+this.uti+"'");
			} else {
				String chaine=replaceVirgule(this.profilMetier);
				genBloc("UT_UTI_HAB", "codsoc=~societe AND uti='"+this.uti+"' AND codpm IN ("+chaine+")");
			}
		}
		
		/*fichierSortie.ecrire("--profil métier: "+this.profilMetier);
				
		String clause="codsoc=~societe AND codpm='"+this.profilMetier+"'";
		if(this.chapitre!=null && !"".equals(this.chapitre)){
			String chaine = replaceVirgule(this.chapitre);
			clause+= " AND codchp in ("+chaine+")";
			fichierSortie.ecrire("--chapitre: "+this.profilMetier);
		}
		if(this.fct!=null && !"".equals(this.fct)){
			String chaine = replaceVirgule(this.fct);
			clause+= " AND (fct in ("+chaine+") OR fct_portail IN ("+chaine+"))";
			fichierSortie.ecrire("--fct: "+this.fct);
		}
		fichierSortie.ecrire("---------------------------");
		
		genBloc("UT_HAB_FCT", clause);
		*/
		fichierSortie.ecrire("---------------------------");
		fichierSortie.ecrire("--Fin habilitation");
		fichierSortie.ecrire("---------------------------\n");
		
		return true;
	}
	
	
}
