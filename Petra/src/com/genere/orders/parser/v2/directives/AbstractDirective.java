package com.genere.orders.parser.v2.directives;

public abstract class AbstractDirective {

	protected String commentaire;
	
	public abstract boolean execute() throws Exception;
	
}
