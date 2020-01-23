package com.genere.orders.parser.v2.directives;

import java.io.IOException;

import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class DirectiveFichier extends AbstractDirective {

	private String nomFichier;
	
	
	public DirectiveFichier(String nomFichier){
		this.nomFichier = nomFichier;
	}
	
	@Override
	public boolean execute() throws IOException {
		FichierEcritureSingleton.INSTANCE.rotationFichier(nomFichier);
		return true;
	}

}
