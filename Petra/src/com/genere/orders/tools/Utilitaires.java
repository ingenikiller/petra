package com.genere.orders.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Utilitaires {

	/**
	 * Date from gnx.
	 *
	 * @param date the date
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static String dateDuJour() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy à H:mm");
		return format.format(date);
	}
	
}
