package com.vcl.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class VclClient {
	
	private static final String VCL_SERVICE_LOCATOR = "vclServiceLocator";
	private static String VCL_CLIENT_CONFIG_PATH = "vcl-client-config.xml";
	private static String VCL_CLIENT_BEANS_PATH = "vcl-client-beans.xml";
	
	private ApplicationContext clientContext;
	
	private VclServiceLocator serviceLocator; 
	
	private static VclClient instance = new VclClient();
	
	private VclClient() {
		initClientContextAndServiceLocator();
	}
	
	private void initClientContextAndServiceLocator() {
		clientContext = new ClassPathXmlApplicationContext(new String[] { VCL_CLIENT_CONFIG_PATH, VCL_CLIENT_BEANS_PATH} );
		serviceLocator = (VclServiceLocator) clientContext.getBean(VCL_SERVICE_LOCATOR);
	}

	public static VclClient getInstance() {
		if (instance == null) {
			instance = new VclClient();
		}
		return instance;
	}
	
	public static VclServiceLocator getServiceLocator() {
		return getInstance().serviceLocator;
	}
		
}
