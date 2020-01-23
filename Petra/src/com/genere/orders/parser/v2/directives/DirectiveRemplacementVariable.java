package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.VariableRequeteRemplacement;

public class DirectiveRemplacementVariable extends AbstractDirective {

	private String couple;
	
	public DirectiveRemplacementVariable(String couple){
		this.couple = couple;
	}
	
	@Override
	public boolean execute() throws Exception {
		VariableRequeteRemplacement.INSTANCE.addCouple(this.couple);
		return true;
	}

}
