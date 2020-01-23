package com.genere.orders.parser.v2.directives;

import com.genere.orders.objets.Requete;

public class DirectiveInsert extends AbstractDirective {

	private String table;
	private String clause;
	
	public DirectiveInsert(String table, String clause){
		this.table = table;
		this.clause = clause;
	}
	
	@Override
	public boolean execute() throws Exception {
		Requete req = new Requete();
		req.setTable(this.table);
		req.setClause(this.clause);
		System.out.println("Recherche table:" + req.getTable());

		req.execute();
		
		return true;
	}

}
