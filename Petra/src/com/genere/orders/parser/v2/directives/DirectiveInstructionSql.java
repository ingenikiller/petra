package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.fichiers.FichierEcritureSingleton;

public class DirectiveInstructionSql extends AbstractDirective {

	private String instruction;
	
	public DirectiveInstructionSql(String instruction){
		this.instruction = instruction;
	}
	
	@Override
	public boolean execute() throws Exception {
		FichierEcritureSingleton.INSTANCE.ecrire(this.instruction);
		return true;
	}

}
