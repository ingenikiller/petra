package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.ParamMoteur;

public class DirectiveParamMoteur extends AbstractDirective {

private String couple;
	
	public DirectiveParamMoteur(String couple){
		this.couple = couple;
	}
	
	@Override
	public boolean execute() throws Exception {
		ParamMoteur.INSTANCE.addCouple(couple);
		return true;
	}

}
