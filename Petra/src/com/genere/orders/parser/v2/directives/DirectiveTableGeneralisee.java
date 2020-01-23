package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.TableGeneraliseeTools;

public class DirectiveTableGeneralisee extends AbstractDirective {

	private String tbl;
	private String mode;
	private String type;
	private String traduction;
	
	public DirectiveTableGeneralisee(String tbl, String mode, String type, String commentaire, String traduction){
		this.tbl = tbl;
		this.commentaire = commentaire;
		this.mode = mode;
		this.type = type;
		this.traduction = traduction;
	}
	
	
	@Override
	public boolean execute() throws Exception {
		TableGeneraliseeTools tb = new TableGeneraliseeTools(tbl, mode, type, commentaire, traduction);
		tb.execute();
		return true;
	}

}
