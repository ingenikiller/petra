package com.genere.orders.parser.v2.directives;

/**
 * Classe référençant les directives utilisables dans les fichiers tsql
 * @author oljean
 *
 */
public class ValeursDirectives {

	public static final String FICHIER = "FIC";

	public static final String PARAM_MOTEUR = "PMO";
	
	public static final String DEB_ENTETE = "ENT";
	public static final String TITRE = "TIT";
	public static final String SUJET = "SUJ";
	public static final String FIN_ENTETE = "FNT";

	public static final String REPLACEVALEUR = "RVA";
	public static final String SUPPVALEUR = "SVA";
	public static final String REPLACEPARAM = "RPA";
	public static final String SUPPPARAM = "SPA";

	public static final String COMMENTAIRE = "COM";

	public static final String INSTRUCTION_SQL = "ISD";

	public static final String DEBUTDECLARATIONINSERT = "DDI";
	public static final String TABLE = "TAB";
	public static final String CLAUSE = "CLS";
	public static final String FINDECLARATIONINSERT = "FDI";

	public static final String DEBUTUPRC = "DUP";
	public static final String NOMUPRC = "NUP";
	public static final String COMPTEURUPRC = "CUP";
	public static final String CALENDRIER = "CAL";
	public static final String PERIODE = "PER";
	public static final String HEURE = "HEU";
	public static final String FINUPRC = "FUP";
	public static final String ELEMENTACTIF = "EAC";

	public static final String DEBUTFONCTIONNALITE = "DFO";
	public static final String NOMFONCTIONNALITE = "NFO";
	public static final String MODE = "MOD";
	public static final String GENPEV = "PEV";
	public static final String LANGUE = "LAN";
	public static final String TRADUCTION = "TRD";
	public static final String FINFONCTIONNALITE = "FFO";

	public static final String DEBUTHABILITATION = "DHB";
	public static final String PROFILMETIER = "PFM";
	public static final String CODECHAPITRE = "CCH";
	public static final String CODEFONCTION = "FCT";
	public static final String UTI = "UTI";
	public static final String FINHABILITATION = "FHB";

	public static final String DEBUTSEGMENT = "DSE";
	public static final String SEGMENT = "SEG";
	public static final String FINSEGMENT = "FSE";
	
	public static final String DEBUTTBL = "DTB";
	public static final String CODETBL = "TBL";
	public static final String TYPE = "TYP";
	public static final String FINTBL = "FTB";
	
	public static final String DEBUTPPE = "DPP";
	public static final String CODEPPE = "COD";
	//codefonction existe déjà
	//public static final String CODFCT = "FCT";
	public static final String FINPPE = "FDP";
	
	public static final String COMM_TECH = "///";
}
