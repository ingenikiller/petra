package com.genere.orders.log;

//import java.util.logging.Logger;

//import org.apache.log4j.PropertyConfigurator;

//import com.genere.orders.config.WSConfigurer;

public class WSLogger {
	public static final WSLogger INSTANCE = new WSLogger();
	
	//private static final String CONFIG_FILE_PATH = "log4j.properties";
	
	//public final Logger LOGGER = Logger.getLogger(WSLogger.class.getName());
	
	private WSLogger() {
		//PropertyConfigurator.configure(WSConfigurer.getFileURLNextToMe(this, CONFIG_FILE_PATH));
	}
}
