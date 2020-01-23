package com.genere.orders.objets;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.genere.orders.bdd.ConnexionBDD;
import com.genere.orders.tools.GenereOrdersException;

public class ListeTables implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Table> listeTable;
	
	/**
	 * constructeur par défaut
	 */
	public ListeTables(){
		this.listeTable = new HashMap<String, Table>();
	}
	
	/**
	 * obtenir la définition d'une table
	 * @param nomTable
	 * @return objet Table
	 * @throws Exception 
	 */
	public Table getTableDefinition(String nomTable) throws Exception{
		Table table = this.listeTable.get(nomTable);
		if(table != null){
			System.out.println("Table pré chargée en persistance");
			return table;
		} else {
			try {
				System.out.println("Table non chargée en persistance");
				table = genereDefinition(nomTable);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(table==null){
			throw new GenereOrdersException("Table "+nomTable+" inexistante sur le schéma");
		}
		return table;
	}
	
	/**
	 * Requetage via Metadata
	 * @param nomTable
	 * @return
	 * @throws SQLException
	 */
	private Table genereDefinition(String nomTable) throws SQLException{
		ResultSet resultat = ConnexionBDD.getInstance().getTableDefinition(nomTable.substring(nomTable.indexOf(".") + 1));
		
		Table table = new Table(nomTable);
		while(resultat.next()){
			table.addChamp(resultat.getObject("COLUMN_NAME").toString(), resultat.getObject("TYPE_NAME").toString());
		}
		listeTable.put(nomTable, table);
		return table;
	}	
}
