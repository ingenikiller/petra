package com.genere.orders.parser.v2.directives;

import com.genere.orders.tools.UprcTools;

public class DirectiveUtPrc extends AbstractDirective {

	private String nomUprc;
	private String compteur;
	private String calendrier;
	private String periode;
	private String heure;
	private String elementActif;
	
	public DirectiveUtPrc(String nomUprc, String compteur, String commentaire, String calendrier, String periode, String heure, String elementActif){
		this.nomUprc = nomUprc;
		this.compteur = compteur;
		this.commentaire = commentaire;
		this.calendrier = calendrier;
		this.periode = periode;
		this.heure = heure;
		this.elementActif = elementActif;
		
	}
	
	
	
	@Override
	public boolean execute() throws Exception {
		UprcTools tool = new UprcTools(this.nomUprc, this.compteur, this.commentaire, this.calendrier, this.periode, this.heure, this.elementActif);
		tool.execute();
		return true;
	}

}
