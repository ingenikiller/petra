package com.genere.orders.parser;

import java.sql.Statement;

import com.genere.orders.bdd.ConnexionBDD;
import com.genere.orders.parser.v2.analyse.AnalyseFinale;
import com.genere.orders.parser.v2.analyse.AnalyserFichier;
import com.genere.orders.tools.TableTools;

public class Traitement extends AbstractParser{

	//version PeTRA
	public static final String version="1.3.5";
	
	public String fichierEntree;
	
	/**
	 * constructeur par défaut
	 * @param fichierEntree chemin du fichier à analyser
	 */
	public Traitement(String fichierEntree){
		this.fichierEntree=fichierEntree;
	}
	
	/**
	 * méthode exécutant le traitement
	 */
	public void execute() throws Exception{
		
		System.out.println("Traitement fichier:"+fichierEntree);
		AnalyserFichier analyser = new AnalyserFichier(fichierEntree);
		AnalyseFinale analyse = analyser.execute();
		
		//execute Oracle
		Statement stt = ConnexionBDD.getInstance().createStatement();
		stt.execute("ALTER SESSION SET NLS_TERRITORY='FRANCE'");
		System.out.println("Alter session OK");
		
		//traitement
		analyse.execute();
		
		TableTools.INSTANCE.finalize();
		System.out.println("Fin traitement");
	}
	
}
