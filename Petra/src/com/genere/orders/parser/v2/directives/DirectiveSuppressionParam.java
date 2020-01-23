package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.ParamRequeteReplacement;

public class DirectiveSuppressionParam extends AbstractDirective {

	private String cle;
	
	public DirectiveSuppressionParam(String cle){
		this.cle = cle;
	}
	
	@Override
	public boolean execute() throws Exception {
		ParamRequeteReplacement.INSTANCE.suppCouple(cle);
		return true;
	}

}
