package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.VariableRequeteRemplacement;

public class DirectiveSuppressionVariable extends AbstractDirective {

	private String cle;
	
	public DirectiveSuppressionVariable(String cle){
		this.cle = cle;
	}
	
	@Override
	public boolean execute() throws Exception {
		VariableRequeteRemplacement.INSTANCE.suppCouple(cle);
		return true;
	}

}
