package com.genere.orders.tools;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.genere.orders.config.WSConfigurer;
import com.genere.orders.objets.ListeTables;
import com.genere.orders.objets.Table;

public class TableTools {

	// private Map<String, Table> listeTable;
	private boolean persistanceEnable = false;

	private ListeTables listeTables;
	String ficherMcd = WSConfigurer.INSTANCE.getProperty("mcd_version");

	public static TableTools INSTANCE = new TableTools();
	
	
	private TableTools() {
		// this.listeTable = new HashMap<String, Table>();
		// this.listeTables = new ListeTables();

		String rer = WSConfigurer.INSTANCE.getProperty("persistance");
		if (rer != null && rer.equals("true")) {
			this.persistanceEnable = true;

			try {
				FileInputStream fichier = new FileInputStream(ficherMcd);
				ObjectInputStream ois = new ObjectInputStream(fichier);
				this.listeTables = (ListeTables) ois.readObject();
				ois.close();
				System.out.println("Chargement fichier persistance OK");
			} catch (java.io.IOException e) {
				System.out.println("Exception au chargement def:"
						+ e.getMessage());
				this.listeTables = new ListeTables();
			} catch (ClassNotFoundException e) {
				System.out.println("Exception au chargement def:"
						+ e.getMessage());
				this.listeTables = new ListeTables();
			}
		} else {
			this.listeTables = new ListeTables();
		}
	}

	/**
	 * permet d'accéder à la définition d'une table
	 * @param nomTable
	 * @return définition d'une table
	 * @throws Exception
	 */
	public Table getTableDefinition(String nomTable) throws Exception {
		return this.listeTables.getTableDefinition(nomTable);
	}

	/**
	 * méthode de destruction de la classe. Ecriture du fichier MCD
	 */
	public void finalize() {
		
		if (this.persistanceEnable) {
			try {
				System.out.println("Ecriture fichier MCD");
				FileOutputStream fichier = new FileOutputStream(ficherMcd);
				ObjectOutputStream oos = new ObjectOutputStream(fichier);
				oos.writeObject(this.listeTables);
				oos.flush();
				oos.close();
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}
	}
}
