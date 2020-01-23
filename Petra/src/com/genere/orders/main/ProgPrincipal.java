package com.genere.orders.main;

import java.io.IOException;
import java.sql.SQLException;

import com.genere.orders.parser.AbstractParser;
import com.genere.orders.parser.Traitement;
import com.genere.orders.tools.GenereOrdersException;

public class ProgPrincipal {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws Exception{
		if(args.length==0){
			System.out.println("Pas de fichier en entrée!!!!");
		}else {
			System.out.println("Lancement PeTRA "+ Traitement.version);
			/*String versionParser = WSConfigurer.INSTANCE.getProperty("versionParser");
			System.out.println("version:"+versionParser);*/
			AbstractParser parser = null;
			
			for(int i=0;i<args.length;i++){
			
				parser = new Traitement(args[i]);
				
				try{
					parser.execute();
				}
				catch(GenereOrdersException e){
					System.out.println("ERREUR: "+e.getMessage());
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
