package com.genere.orders.parser.v2.directives;

import java.io.IOException;

import com.genere.orders.config.WSConfigurer;
import com.genere.orders.parser.Traitement;
import com.genere.orders.tools.Utilitaires;
import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class DirectiveEntete extends AbstractDirective {

	private String sujet;
	private String titre;

	public DirectiveEntete(String sujet, String titre) {
		this.sujet = sujet;
		this.titre = titre;
	}

	@Override
	public boolean execute() throws IOException {
		FichierEcritureSingleton fileOut = FichierEcritureSingleton.INSTANCE;
		fileOut.ecrire("-- ------------------------------------------------------------------------------------------------");
		fileOut.ecrire("-- \t" + titre + ": " + sujet);
		fileOut.ecrire("--");
		fileOut.ecrire("--");
		fileOut.ecrire("-- Création le: " + Utilitaires.dateDuJour());
		fileOut.ecrire("-- Auteur: "
				+ WSConfigurer.INSTANCE.getProperty("utilisateur"));
		fileOut.ecrire("-- ");
		fileOut.ecrire("-- Version PeTRA:" + Traitement.version);
		fileOut.ecrire("-- ------------------------------------------------------------------------------------------------");
		return true;
	}

}
