package com.genere.orders.config;



public class WSConfigurer extends Configurer {
	public static final WSConfigurer INSTANCE = new WSConfigurer();

	private static final long serialVersionUID = -8261572884574761863L;

	//private static final String CONFIG_FILE_PATH = "com/genere/orders/config/config.properties";
	private static final String CONFIG_FILE_PATH = "./conf/config.properties";
	//private static final String CONFIG_FILE_PATH = System.getProperty("user.dir")+"\\conf\\config.properties";
	//private static final String CONFIG_FILE_PATH = "config.properties";

	//private static final String GENERIX_SERVLETCONTROL_URL = "generix_servletControl_URL";
	
	private WSConfigurer() {
		super(CONFIG_FILE_PATH);
		//System.out.println("System "+System.getProperty("user.dir") );
	}

//	public String getGenerixServletControlURL() {
//		return getProperty(GENERIX_SERVLETCONTROL_URL);
//	}
}