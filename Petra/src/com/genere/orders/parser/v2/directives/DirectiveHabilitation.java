package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.Habilitations;

public class DirectiveHabilitation extends AbstractDirective {

	private String profilMetier;
	private String chapitre;
	private String fct;
	private String mode;
	private String uti;
	
	public DirectiveHabilitation(String profilMetier, String chapitre, String fct, String mode, String uti){
		this.profilMetier = profilMetier;
		this.chapitre = chapitre;
		this.fct = fct;
		this.mode = mode;
		this.uti = uti;
	}
	
	@Override
	public boolean execute() throws Exception {
		Habilitations hab = new Habilitations(profilMetier, chapitre, fct, mode, uti);
		return hab.execute();
	}

}
