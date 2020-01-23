package com.genere.orders.parser.v2.directives;

import java.io.IOException;

import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class DirectiveCommentaire extends AbstractDirective {

	private String commentaire;
	
	public DirectiveCommentaire(String commentaire){
		this.commentaire = commentaire;
	}
	
	@Override
	public boolean execute() throws IOException {
		FichierEcritureSingleton.INSTANCE.ecrire(commentaire);
		return true;
	}

}
