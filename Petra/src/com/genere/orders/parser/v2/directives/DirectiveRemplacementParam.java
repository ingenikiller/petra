package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.ParamRequeteReplacement;

public class DirectiveRemplacementParam extends AbstractDirective {

	private String couple;
	
	public DirectiveRemplacementParam(String couple){
		this.couple = couple;
	}
	
	@Override
	public boolean execute() throws Exception {
		ParamRequeteReplacement.INSTANCE.addCouple(this.couple,
				false);
		return true;
	}

}
