package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.PpeTools;

public class DirectivePPE extends AbstractDirective {

	private String codePpe;
	private String fonction;
	
	
	public DirectivePPE(String codePpe, String fonction, String commentaire){
		this.codePpe = codePpe;
		this.fonction = fonction;
		this.commentaire = commentaire;
	}
	
	@Override
	public boolean execute() throws Exception {
		PpeTools tool = new PpeTools(codePpe, fonction, commentaire);
		tool.execute();
		return false;
	}

}
