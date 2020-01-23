package com.genere.orders.parser;

import java.io.IOException;
import java.sql.Statement;

import com.genere.orders.bdd.ConnexionBDD;
import com.genere.orders.config.WSConfigurer;
import com.genere.orders.objets.LigneFichier;
import com.genere.orders.objets.Requete;
import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.FonctionTools;
import com.genere.orders.tools.GenereOrdersException;
import com.genere.orders.tools.GestionBloc;
import com.genere.orders.tools.Habilitations;
import com.genere.orders.tools.ParamRequeteReplacement;
import com.genere.orders.tools.TableTools;
import com.genere.orders.tools.UprcTools;
import com.genere.orders.tools.Utilitaires;
import com.genere.orders.tools.VariableRequeteRemplacement;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;
import com.genere.orders.tools.fichiers.FichierLecture;

public class TemplateParser extends AbstractParser{

	private static final String FICHIER = "FIC";

	private static final String DEB_ENTETE = "ENT";
	private static final String TITRE = "TIT";
	private static final String SUJET = "SUJ";
	private static final String FIN_ENTETE = "FNT";

	private static final String REPLACEVALEUR = "RVA";
	private static final String SUPPVALEUR = "SVA";
	private static final String REPLACEPARAM = "RPA";
	private static final String SUPPPARAM = "SPA";

	private static final String COMMENTAIRE = "COM";

	private static final String INSTRUCTION_SQL = "ISD";

	private static final String DEBUTDECLARATIONINSERT = "DDI";
	private static final String TABLE = "TAB";
	private static final String CLAUSE = "CLS";
	private static final String FINDECLARATIONINSERT = "FDI";

	private static final String DEBUTUPRC = "DUP";
	private static final String NOMUPRC = "NUP";
	private static final String COMPTEURUPRC = "CUP";
	private static final String FINUPRC = "FUP";

	private static final String DEBUTFONCTIONNALITE = "DFO";
	private static final String NOMFONCTIONNALITE = "NFO";
	private static final String MODE = "MOD";
	private static final String GENPEV = "PEV";
	private static final String FINFONCTIONNALITE = "FFO";

	private static final String DEBUTHABILITATION = "DHB";
	private static final String PROFILMETIER = "PFM";
	private static final String CODECHAPITRE = "CCH";
	private static final String CODEFONCTION = "FCT";
	private static final String UTI="UTI";
	private static final String FINHABILITATION = "FHB";

	private static final String COMM_TECH = "///";

	private FichierLecture fileIn;
	private FichierEcritureSingleton fileOut = FichierEcritureSingleton.INSTANCE;

	//
	TableTools ttools = null;

	public TemplateParser(String fileIn) throws IOException,
			GenereOrdersException {
		this.fileIn = new FichierLecture(fileIn);
	}

	/**
	 * lance la génération du fichier sql
	 * 
	 * @throws Exception
	 * @Deprecated
	 */
	public void execute() throws Exception {
		String ligne;
		int i=1;
		Statement stt = ConnexionBDD.getInstance().createStatement();
		stt.execute("ALTER SESSION SET NLS_TERRITORY='FRANCE'");
		System.out.println("Alter session OK");
		this.ttools = TableTools.INSTANCE;
		System.out.println("Fichier à analyser:"+fileIn.getCheminFichier());
		while ((ligne = fileIn.lireLigne()) != null) {
			
			LigneFichier lf;
			// si le fichier en sortie n'est pas initialisé, on le crée
			if (i == 1) {
				lf = new LigneFichier(ligne);
				// si la première directive n'est pas une demande de fichier, on
				// sort
				if (lf.entete == null || !lf.entete.equals(FICHIER)) {
					throw new GenereOrdersException(
							"Directive FIC absente en début de fichier");
				}
				fileOut.rotationFichier(lf.contenu);
				// this.genereCartouche();
			} else if (ligne.equals("")) {
				fileOut.ecrire("");
			} else {

				lf = new LigneFichier(ligne);
				if (lf.entete == null) {
					//
				} else if (lf.entete == COMM_TECH) {
					//
				} else {
					System.out.println("Bloc:" + lf.entete);
					// WSLogger.INSTANCE.LOGGER.info("Bloc:"+lf.entete);
					if (lf.entete.equals(FICHIER)) {
						fileOut.rotationFichier(lf.contenu);
					} else if (lf.entete.equals(DEB_ENTETE)) {
						this.genereCartouche();
					} else if (lf.entete.equals(REPLACEVALEUR)) {
						VariableRequeteRemplacement.INSTANCE
								.addCouple(lf.contenu);
					} else if (lf.entete.equals(SUPPVALEUR)) {
						VariableRequeteRemplacement.INSTANCE
								.suppCouple(lf.contenu);
					} else if (lf.entete.equals(REPLACEPARAM)) {
						ParamRequeteReplacement.INSTANCE.addCouple(lf.contenu,
								false);
					} else if (lf.entete.equals(SUPPPARAM)) {
						ParamRequeteReplacement.INSTANCE.suppCouple(lf.contenu,
								false);
					} else if (lf.entete.equals(COMMENTAIRE)) {
						fileOut.ecrire(lf.contenu);
					} else if (lf.entete.equals(INSTRUCTION_SQL)) {
						fileOut.ecrire(lf.contenu);
					} else if (lf.entete.equals(DEBUTDECLARATIONINSERT)) {
						genereBlocInsert();
					} else if (lf.entete.equals(DEBUTUPRC)) {
						genereBloCUPRC();
					} else if (lf.entete.equals(DEBUTFONCTIONNALITE)) {
						genereBlocFonction();
					} else if (lf.entete.equals(DEBUTHABILITATION)) {
						genereBlocHabilitation();
					}
				}
				
			}
			i++;
		}
		// fermeture fichier

		//fileOut.fermer();
		ttools.finalize();
		System.out.println("Fin programme");
	}

	private boolean genereBlocInsert() throws Exception {
		Requete req = new Requete();

		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, DEBUTDECLARATIONINSERT, FINDECLARATIONINSERT, 3);

		req.setTable(gb.getDirectiveValeur(TABLE));
		req.setClause(gb.getDirectiveValeur(CLAUSE));
		System.out.println("Recherche table:" + req.getTable());
		// Table table = ttools.getTableDefinition(req.getTable());
		// if(table==null){
		// System.out.println("Table inexistante");
		// return false;
		// }

		// ecrire requete delete
		// fileOut.ecrire(req.getRequeteDelete());
		req.execute();
		// fileOut.ecrire("--");
		// fileOut.ecrire(req.getRequeteCount()+";");
		System.out.println("Fin Bloc DDI");
		return true;
	}

	private boolean genereBloCUPRC() throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, DEBUTUPRC, FINUPRC, 8);

		UprcTools upt = new UprcTools(gb.getDirectiveValeur(NOMUPRC),
				gb.getDirectiveValeur(COMPTEURUPRC),
				gb.getDirectiveValeur(ValeursDirectives.COMMENTAIRE),
				gb.getDirectiveValeur(ValeursDirectives.CALENDRIER),
				gb.getDirectiveValeur(ValeursDirectives.PERIODE),
				gb.getDirectiveValeur(ValeursDirectives.HEURE),
				gb.getDirectiveValeur(ValeursDirectives.ELEMENTACTIF)
				);
		upt.execute();

		return true;
	}

	private boolean genereBlocFonction() throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, DEBUTFONCTIONNALITE, FINFONCTIONNALITE, 5);

		FonctionTools ft = new FonctionTools(
				gb.getDirectiveValeur(NOMFONCTIONNALITE),
				gb.getDirectiveValeur(MODE), gb.getDirectiveValeur(GENPEV),
				gb.getDirectiveValeur(COMMENTAIRE),
				gb.getDirectiveValeur(""),
				gb.getDirectiveValeur(""));
		ft.execute();
		return true;
	}

	/**
	 * générer le cartouche d'entête du fichier
	 * 
	 * @throws Exception
	 */
	private void genereCartouche() throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, DEB_ENTETE, FIN_ENTETE, 3);
		fileOut.ecrire("/**************************************************************************************************");
		fileOut.ecrire("\t" + gb.getDirectiveValeur(TITRE) + ": "
				+ gb.getDirectiveValeur(SUJET));
		fileOut.ecrire("");
		fileOut.ecrire("");
		fileOut.ecrire("Création le: " + Utilitaires.dateDuJour());
		fileOut.ecrire("Auteur: "
				+ WSConfigurer.INSTANCE.getProperty("utilisateur"));
		fileOut.ecrire("");
		fileOut.ecrire("****************************************************************************************************/");
	}

	/**
	 * génère le bloc habilitation
	 * @throws Exception
	 */
	private void genereBlocHabilitation() throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, DEBUTHABILITATION, FINHABILITATION, 5);
		Habilitations hab = new Habilitations(
				gb.getDirectiveValeur(PROFILMETIER),
				gb.getDirectiveValeur(CODECHAPITRE),
				gb.getDirectiveValeur(CODEFONCTION),
				gb.getDirectiveValeur(MODE),
				gb.getDirectiveValeur(UTI));
		hab.execute();
	}
}
