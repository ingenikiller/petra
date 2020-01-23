package com.genere.orders.tools;

import java.math.BigDecimal;

import com.genere.orders.config.WSConfigurer;
import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class UprcTools extends CommunTools{

	private String nomUprc;
	private String compteur;
	private String commentaire;
	private String calendrier;
	private String periode;
	private String heure;
	private String valeurCodsoc;
	private String elementActif;
	private String remplacementCodsoc = VariableRequeteRemplacement.INSTANCE.getRemplacement("CODSOC");
	
	private String nomSchema = "";
	
	/**
	 * Constructeur
	 * @param codsoc code de la société
	 * @param nomUprc
	 * @param compteur
	 */
	public UprcTools(String nomUprc, String compteur, String commentaire, String calendrier, String periode, String heure, String elementActif){
		this.nomUprc = nomUprc;
		this.compteur = compteur;
		this.commentaire = commentaire;
		this.calendrier = calendrier;
		this.periode = periode;
		this.heure = heure;
		this.elementActif = elementActif;
		if(ParamMoteur.INSTANCE.getSchemaBase()){
			nomSchema = WSConfigurer.INSTANCE.getProperty("base_user").toUpperCase()+ ".";
		}
		
	}
	
	public boolean execute() throws Exception{
		verifEntree(ValeursDirectives.NOMUPRC, this.nomUprc);
		verifEntree(ValeursDirectives.COMPTEURUPRC, this.compteur);
		
		valeurCodsoc = ParamRequeteReplacement.INSTANCE.getValeur("~societe");
		
		if (this.periode!=null && this.calendrier!=null){
			throw new GenereOrdersException(this.nomUprc+": calendrier et periode presentes à tort ensemble");
		}
		
		
		
		
		FichierEcritureSingleton ficSortie = FichierEcritureSingleton.INSTANCE;
		ficSortie.ecrire("----------------------------------------------------------------------------------");
		ficSortie.ecrire("--Extraction UPRC");
		//fic.ecrire("--codsoc:"+codsoc);
		ficSortie.ecrire("--nom:"+nomUprc);
		if(this.commentaire!=null && !this.commentaire.equals("")){
			ficSortie.ecrire("--"+this.commentaire);
		}
		ficSortie.ecrire("----------------------------------------------------------------------------------");
		
		//génération ligne uprc
		genBloc("UT_PRC", "CODSOC=~societe AND NOMPRC='"+this.nomUprc+"'");
		
		String reqDeleteUtPar="DELETE FROM "+nomSchema+"ut_par WHERE codsoc=~societe AND numero " +
				"IN (SELECT numero FROM "+nomSchema+"ut_spl WHERE ut_par.codsoc=ut_spl.codsoc AND nomprc='"+nomUprc+"');";
		String reqDeleteUtSplFils="DELETE FROM "+nomSchema+"ut_spl WHERE codsoc=~societe AND numpere<>0 AND nomprc='"+nomUprc+"';";
		
		ficSortie.ecrire("--suppression des ut_par existant de toute l'uprc");
		ficSortie.ecrire("--suppression des ut_spl fils");
		if(ParamMoteur.INSTANCE.getValorisationRequete()){
			ficSortie.ecrire(ParamRequeteReplacement.INSTANCE.valoriseClause(reqDeleteUtPar));
			ficSortie.ecrire(ParamRequeteReplacement.INSTANCE.valoriseClause(reqDeleteUtSplFils));
		} else {
			ficSortie.ecrire(reqDeleteUtPar);
			ficSortie.ecrire(reqDeleteUtSplFils);
		}
		ficSortie.ecrire("");
		this.sectionUtspl();
		
		//maj des ut_par pour paramètre codsoc
		//String rempCodsoc = VariableRequeteRemplacement.INSTANCE.getRemplacement("CODSOC");
		if(!ParamMoteur.INSTANCE.getValorisationRequete()){
			String requeteUp = "UPDATE "+nomSchema+"ut_par set param='NV                                      '||" 
						+ "LPAD(~societe, 4, ' ')" +"||'                 0' "
						+ "WHERE upper(nomchp)='CODSOC' AND codsoc=~societe"
						+ " AND numero IN (SELECT numero FROM "+nomSchema+"ut_spl WHERE codsoc=" + remplacementCodsoc + " AND nomprc='" + this.nomUprc + "');";
			ficSortie.ecrire("--requete update des valeurs de codsoc en paramètres");
			ficSortie.ecrire(requeteUp);
		}
		
		this.updateSt();
		
		return true;
	}
		
	/**
	 * 
	 * @param ficSortie
	 * @return
	 * @throws Exception
	 */
	private boolean sectionUtspl() throws Exception {
		//recherche le codsoc par mev
		//String codsocMev = GenerixTools.getMevCodsocPhy(codsoc, "UT_PRC", " ");
		
		//on sauvegarde la valeur de numéro si elle existait au paravant
		String sauvegardeCouple = "NUMERO="+VariableRequeteRemplacement.INSTANCE.getRemplacement("NUMERO");
		
		// on ajoute le remplacement du numero
		//String rempCodsoc = VariableRequeteRemplacement.INSTANCE.getRemplacement("CODSOC");
		if(ParamMoteur.INSTANCE.getValorisationRequete()){
			VariableRequeteRemplacement.INSTANCE.addCouple("NUMERO="+nomSchema+"PKG_COMMUN.f_reserve_cpt("+valeurCodsoc+", '"+compteur+"')");
		} else {
			VariableRequeteRemplacement.INSTANCE.addCouple("NUMERO="+nomSchema+"PKG_COMMUN.f_reserve_cpt(~societe, '"+compteur+"')");
		}
				
		RequeteUtSpl req = new RequeteUtSpl();
		/*if(elementActif!=null && elementActif.equals("O")){
			req.setClause("codsoc=~societe AND NOMPRC='"+nomUprc+"' AND numpere=0 AND ESHS='ES'");
		} else {*/
			req.setClause("codsoc=~societe AND NOMPRC='"+nomUprc+"' AND numpere=0");
			req.setElementActif(this.elementActif);
		//}
		
		req.execute();
		
		//reprise de NUMERO
		VariableRequeteRemplacement.INSTANCE.addCouple(sauvegardeCouple);
		
		return true;
	}
	
	private boolean updateSt() throws Exception {
		//String rempCodsoc = VariableRequeteRemplacement.INSTANCE.getRemplacement("CODSOC");
		String requete="UPDATE "+nomSchema+"ut_prc SET eshs='HS'";
		if(periode!=null&& !periode.equals("")){
			requete+=", periode='"+this.periode+"'";
			if(this.heure!=null && !heure.equals("")){
				BigDecimal n;
				try{
					n = new BigDecimal(heure);
					
				}catch(Exception e){
					throw new GenereOrdersException("UPRC "+this.nomUprc+": format de l'heure incorrect");
				}
				
				requete+=", Heudmd='"+n.toString()+"'";
			}
		}
		
		if(calendrier!=null && !calendrier.equals("")){
			requete+=", periode='CAL', nomcal='"+this.calendrier+"'";
		}
		
		
		if(ParamMoteur.INSTANCE.getValorisationRequete()){
			requete+=" WHERE codsoc="+valeurCodsoc;
		} else {
			requete+=" WHERE codsoc=~societe";
		}
		
		requete+=" AND nomprc='"+this.nomUprc+"';";
		FichierEcritureSingleton ficSortie = FichierEcritureSingleton.INSTANCE;
		ficSortie.ecrire("--maj statut UPRC");
		ficSortie.ecrire(requete);
		return true;
	}
	
	
}
