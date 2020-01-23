package com.test;

import com.genere.orders.objets.Table;
import com.genere.orders.tools.TableTools;

public class GenererTable {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		//TableTools tool = new TableTools();
		Table table = TableTools.INSTANCE.getTableDefinition("PARAV");
		System.out.println("nom:"+table.getNom());
		System.out.println("codsoc:"+ table.getChamps().get(1).getNom());
	}

}
