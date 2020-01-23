package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.SegmentTools;

public class DirectiveSegment extends AbstractDirective {

	private String segment;
	
	/**
	 * constructeur par défaut
	 * @param segment
	 * @param commentaire
	 */
	public DirectiveSegment(String segment, String commentaire){
		this.segment = segment;
		this.commentaire = commentaire;
	}
	
	@Override
	public boolean execute() throws Exception {
		SegmentTools st = new SegmentTools(this.segment, this.commentaire);
		st.execute();
		return true;
	}

}
