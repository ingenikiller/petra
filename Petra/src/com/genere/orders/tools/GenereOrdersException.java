package com.genere.orders.tools;

/**
 * Surcharge de la classe Exception 
 * @author oljean
 *
 */
public class GenereOrdersException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructeur
	 * @param message
	 */
	public GenereOrdersException(String message){
		super(message);
	}
	
}
