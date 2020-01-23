package com.genere.orders.parser.v2.process;

import com.genere.orders.parser.v2.directives.DirectiveEntete;
import com.genere.orders.parser.v2.directives.DirectiveFonctionnalite;
import com.genere.orders.parser.v2.directives.DirectiveHabilitation;
import com.genere.orders.parser.v2.directives.DirectiveInsert;
import com.genere.orders.parser.v2.directives.DirectivePPE;
import com.genere.orders.parser.v2.directives.DirectiveSegment;
import com.genere.orders.parser.v2.directives.DirectiveTableGeneralisee;
import com.genere.orders.parser.v2.directives.DirectiveUtPrc;
import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.tools.GestionBloc;
import com.genere.orders.tools.fichiers.FichierLecture;

public abstract class GenerateurDirective {

	/**
	 * génère une directive entête
	 * @param fileIn
	 * @return directive d'entête complète
	 * @throws Exception
	 */
	public static DirectiveEntete genereEntete(FichierLecture fileIn)
			throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEB_ENTETE,
				ValeursDirectives.FIN_ENTETE, 3);

		DirectiveEntete dir = new DirectiveEntete(
				gb.getDirectiveValeur(ValeursDirectives.SUJET),
				gb.getDirectiveValeur(ValeursDirectives.TITRE));

		return dir;
	}
	
	/**
	 * Génère une directive d'insert
	 * @param fileIn
	 * @return
	 * @throws Exception
	 */
	public static DirectiveInsert genereInsert(FichierLecture fileIn) throws Exception{
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEBUTDECLARATIONINSERT, ValeursDirectives.FINDECLARATIONINSERT, 3);

		DirectiveInsert dir = new DirectiveInsert(
			gb.getDirectiveValeur(ValeursDirectives.TABLE),
			gb.getDirectiveValeur(ValeursDirectives.CLAUSE));
		
		return dir;
	}
	
	/**
	 * génère une directive fonctionnalité
	 * @param fileIn
	 * @return directive fonctionnalité complète
	 * @throws Exception
	 */
	public static DirectiveFonctionnalite genereFonctionnalite(FichierLecture fileIn) throws Exception{
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEBUTFONCTIONNALITE, ValeursDirectives.FINFONCTIONNALITE, 6);

		DirectiveFonctionnalite dir = new DirectiveFonctionnalite(
			gb.getDirectiveValeur(ValeursDirectives.NOMFONCTIONNALITE),
			gb.getDirectiveValeur(ValeursDirectives.MODE),
			gb.getDirectiveValeur(ValeursDirectives.GENPEV),
			gb.getDirectiveValeur(ValeursDirectives.COMMENTAIRE),
			gb.getDirectiveValeur(ValeursDirectives.LANGUE),
			gb.getDirectiveValeur(ValeursDirectives.TRADUCTION)
			);
		
		return dir;
	}
	
	/**
	 * génère une directive habilitation
	 * @param fileIn
	 * @return
	 * @throws Exception
	 */
	public static DirectiveHabilitation genereHabilitations(FichierLecture fileIn) throws Exception{
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEBUTHABILITATION, ValeursDirectives.FINHABILITATION, 7);
		DirectiveHabilitation hab = new DirectiveHabilitation(
				gb.getDirectiveValeur(ValeursDirectives.PROFILMETIER),
				gb.getDirectiveValeur(ValeursDirectives.CODECHAPITRE),
				gb.getDirectiveValeur(ValeursDirectives.CODEFONCTION),
				gb.getDirectiveValeur(ValeursDirectives.MODE),
				gb.getDirectiveValeur(ValeursDirectives.UTI));
		return hab;
	}

	/**
	 * Génère une directive Uprc
	 * @param fileIn
	 * @return
	 * @throws Exception
	 */
	public static DirectiveUtPrc genereUprc(FichierLecture fileIn) throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEBUTUPRC, ValeursDirectives.FINUPRC, 8);

		return  new DirectiveUtPrc(gb.getDirectiveValeur(ValeursDirectives.NOMUPRC),
				gb.getDirectiveValeur(ValeursDirectives.COMPTEURUPRC),
				gb.getDirectiveValeur(ValeursDirectives.COMMENTAIRE),
				gb.getDirectiveValeur(ValeursDirectives.CALENDRIER),
				gb.getDirectiveValeur(ValeursDirectives.PERIODE),
				gb.getDirectiveValeur(ValeursDirectives.HEURE),
				gb.getDirectiveValeur(ValeursDirectives.ELEMENTACTIF));
		
	}
	
	/**
	 * Génère une directive Segment
	 * @param fileIn
	 * @return
	 * @throws Exception
	 */
	public static DirectiveSegment genereSegment(FichierLecture fileIn) throws Exception{
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEBUTSEGMENT, ValeursDirectives.FINSEGMENT, 4);
		
		return new DirectiveSegment(gb.getDirectiveValeur(ValeursDirectives.SEGMENT), 
				gb.getDirectiveValeur(ValeursDirectives.COMMENTAIRE));
	}
	
	/**
	 * 
	 * @param fileIn
	 * @return
	 * @throws Exception
	 */
	public static DirectiveTableGeneralisee genereTbl(FichierLecture fileIn) throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEBUTTBL, ValeursDirectives.FINTBL, 6);
		
		return new DirectiveTableGeneralisee(gb.getDirectiveValeur(ValeursDirectives.CODETBL), 
				gb.getDirectiveValeur(ValeursDirectives.MODE),
				gb.getDirectiveValeur(ValeursDirectives.TYPE),
				gb.getDirectiveValeur(ValeursDirectives.COMMENTAIRE),
				gb.getDirectiveValeur(ValeursDirectives.TRADUCTION));
	}
	
	/**
	 * 
	 * @param fileIn
	 * @return
	 * @throws Exception
	 */
	public static DirectivePPE generePpe(FichierLecture fileIn) throws Exception {
		GestionBloc gb = new GestionBloc();
		gb.parseBloc(fileIn, ValeursDirectives.DEBUTPPE, ValeursDirectives.FINPPE, 4);
		
		return new DirectivePPE(gb.getDirectiveValeur(ValeursDirectives.CODEPPE), 
				gb.getDirectiveValeur(ValeursDirectives.CODEFONCTION),
				gb.getDirectiveValeur(ValeursDirectives.COMMENTAIRE));
	}
	
}
