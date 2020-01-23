package com.genere.orders.config;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Properties;

//import com.sopra.tools.log.WSLogger;

public class Configurer extends Properties {

	private static final long serialVersionUID = 7426572199917777001L;

	protected Configurer(String configFileName) {
		super();
		
		try {
			//URL url = getFileURLNextToMe(this, configFileName);
			URL url = this.getClass().getClassLoader().getResource(configFileName);
			//System.out.println("Path:"+url.toString());
			if(url==null){
				System.out.println("fichier "+configFileName+" absent");
			}
			load(url.openStream());
		} catch (IOException e) {
			
			e.printStackTrace();
			// ServerMain.LOGGER.info("Could not read config file, using defaults settings", e);
		}
	}

	public String getProperty(String key, Object... arguments) {
		// on gere les int "a la main" car sinon, le formatage par defaut rajouet un " " (espace) au millier : 2 333 au
		// lien de 2333
		for (int i = 0; i < arguments.length; i++) {
			Object obj = arguments[i];
			if (obj instanceof Integer) {
				arguments[i] = Integer.toString(((Integer) obj).intValue());
			} else if (obj instanceof String ) {
				try {
					arguments[i] = URLEncoder.encode((String) obj, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// on ne transmet pas l'erreur car l'encodage UTF-8 est assure d'etre supporte,
					// on ne peut donc logiquement jamais arriver ici !
					//WSLogger.INSTANCE.LOGGER.info("Problem while encoding in UTF-8 the parameter of the URL", e);
				}
			}
		}
		return MessageFormat.format(getProperty(key), arguments);
	}

	public static URL getFileURLNextToMe(Object me, String fileName) {
		return me.getClass().getClassLoader().getResource(me.getClass().getPackage().getName().replaceAll("\\.", "/")
				+ "/" + fileName);
	}
	
	public String getPath(String properties){
		return this.getProperty(properties).replaceAll("\\", "\\\\");
	}
	
	
}