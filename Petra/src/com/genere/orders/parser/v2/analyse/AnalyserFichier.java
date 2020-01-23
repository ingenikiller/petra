package com.genere.orders.parser.v2.analyse;

import com.genere.orders.objets.LigneFichier;
import com.genere.orders.parser.v2.directives.DirectiveCommentaire;
import com.genere.orders.parser.v2.directives.DirectiveFichier;
import com.genere.orders.parser.v2.directives.DirectiveInstructionSql;
import com.genere.orders.parser.v2.directives.DirectiveParamMoteur;
import com.genere.orders.parser.v2.directives.DirectiveRemplacementParam;
import com.genere.orders.parser.v2.directives.DirectiveRemplacementVariable;
import com.genere.orders.parser.v2.directives.DirectiveSuppressionParam;
import com.genere.orders.parser.v2.directives.DirectiveSuppressionVariable;
import com.genere.orders.parser.v2.directives.ValeursDirectives;
import com.genere.orders.parser.v2.process.GenerateurDirective;
import com.genere.orders.tools.GenereOrdersException;
import com.genere.orders.tools.fichiers.FichierLecture;

public class AnalyserFichier {
	
	private String nomFichier;
	private FichierLecture fileIn;
	
	public AnalyserFichier(String nomFichier) throws GenereOrdersException{
		this.nomFichier = nomFichier; 
		
		this.fileIn = new FichierLecture(this.nomFichier);
		
	}
	
	/**
	 * Analyse le fichier
	 * @throws Exception 
	 */
	public AnalyseFinale execute() throws Exception{
		System.out.println("Lancement analyse");
		AnalyseFinale finale = new AnalyseFinale();
		FichierDescription fichierDescription = null;
		String ligne;
		int i=1;
		while ((ligne = fileIn.lireLigne()) != null) {
			LigneFichier lf = new LigneFichier(ligne);
			if(i==1 && !ValeursDirectives.FICHIER.equals(lf.entete)){
				//fichier mal formé
				throw new GenereOrdersException(
						"Directive FIC absente en début de fichier");
			}
			
			i++;
			
			if (lf.entete == null) {
				//
			} else if (lf.entete == ValeursDirectives.COMM_TECH) {
				//
				DirectiveCommentaire dir = new DirectiveCommentaire(lf.contenu);
				fichierDescription.add(dir);
			} else {
				//System.out.println("Bloc:" + lf.entete);
				if (lf.entete.equals(ValeursDirectives.FICHIER)) {
					//fileOut.rotationFichier(lf.contenu);
					if(fichierDescription==null){
						fichierDescription = new FichierDescription(true, lf.contenu);
					} else {
						finale.add(fichierDescription);
						fichierDescription = new FichierDescription(true, lf.contenu);
					}
					fichierDescription.add(new DirectiveFichier(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.DEB_ENTETE)) {
					fichierDescription.add(GenerateurDirective.genereEntete(fileIn));
				} else if (lf.entete.equals(ValeursDirectives.REPLACEVALEUR)) {
					fichierDescription.add(new DirectiveRemplacementVariable(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.SUPPVALEUR)) {
					fichierDescription.add(new DirectiveSuppressionVariable(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.REPLACEPARAM)) {
					fichierDescription.add(new DirectiveRemplacementParam(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.SUPPPARAM)) {
					fichierDescription.add(new DirectiveSuppressionParam(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.PARAM_MOTEUR)) {
					fichierDescription.add(new DirectiveParamMoteur(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.COMMENTAIRE)) {
					fichierDescription.add(new DirectiveCommentaire(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.INSTRUCTION_SQL)) {
					fichierDescription.add(new DirectiveInstructionSql(lf.contenu));
				} else if (lf.entete.equals(ValeursDirectives.DEBUTDECLARATIONINSERT)) {
					fichierDescription.add(GenerateurDirective.genereInsert(fileIn));
				} else if (lf.entete.equals(ValeursDirectives.DEBUTUPRC)) {
					fichierDescription.add(GenerateurDirective.genereUprc(fileIn));
				} else if (lf.entete.equals(ValeursDirectives.DEBUTFONCTIONNALITE)) {
					fichierDescription.add(GenerateurDirective.genereFonctionnalite(fileIn));
				} else if (lf.entete.equals(ValeursDirectives.DEBUTHABILITATION)) {
					fichierDescription.add(GenerateurDirective.genereHabilitations(fileIn));
				} else if(lf.entete.equals(ValeursDirectives.DEBUTHABILITATION)){
					fichierDescription.add(GenerateurDirective.genereHabilitations(fileIn));
				} else if(lf.entete.equals(ValeursDirectives.DEBUTSEGMENT)){
					fichierDescription.add(GenerateurDirective.genereSegment(fileIn));
				} else if(lf.entete.equals(ValeursDirectives.DEBUTTBL)){
					fichierDescription.add(GenerateurDirective.genereTbl(fileIn));
				} else if(lf.entete.equals(ValeursDirectives.DEBUTPPE)){
					System.out.println("Directive PPE");
					fichierDescription.add(GenerateurDirective.generePpe(fileIn));
				}else if (lf.entete.equals(ValeursDirectives.COMM_TECH)){
					//
				} else {
					new GenereOrdersException("Directive "+lf.entete+" inconnue!!!");
				}
			}
			i++;
		}
		
		if(fichierDescription!=null){
			finale.add(fichierDescription);
		}
		System.out.println("Analyse terminée");
		return finale;
	}
	
}
