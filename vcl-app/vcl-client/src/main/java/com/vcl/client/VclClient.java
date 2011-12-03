package com.vcl.client;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class VclClient {
	
	private static final String VCL_SERVICE_LOCATOR = "vclServiceLocator";
	private static String VCL_CLIENT_CONFIG_PATH = "vcl-client-config.xml";
	private static String VCL_CLIENT_BEANS_PATH = "vcl-client-beans.xml";
	
	private ApplicationContext clientContext;
	
	private VclServiceLocator serviceLocator; 
	
	private static final String CLIENT_PROPS_PATH_SYS_PROP = "CLIENT_PROPS_PATH";
	private static final String CLIENT_PROPS_FILENAME = "client.properties";
	private static final String FILE_CLIENT_PROPS_PATH = "file:" + CLIENT_PROPS_FILENAME;
	private static final String CLASSPATH_CLIENT_PROPS_PATH = "classpath:" + CLIENT_PROPS_FILENAME;
	
	private static VclClient instance = new VclClient();
	
	private VclClient() {
		initClientContextAndServiceLocator();
	}
	
	private void initClientContextAndServiceLocator() {
		useClientPropertiesAsFileElseClasspathIfFileNotExist();
		clientContext = new ClassPathXmlApplicationContext(new String[] { VCL_CLIENT_CONFIG_PATH, VCL_CLIENT_BEANS_PATH} );
		serviceLocator = (VclServiceLocator) clientContext.getBean(VCL_SERVICE_LOCATOR);
	}

	private void useClientPropertiesAsFileElseClasspathIfFileNotExist() {
		String clientPropsPath = FILE_CLIENT_PROPS_PATH;
		
		if (!new File(CLIENT_PROPS_FILENAME).exists()) {
			clientPropsPath = CLASSPATH_CLIENT_PROPS_PATH;
		}
		
		System.setProperty(CLIENT_PROPS_PATH_SYS_PROP, clientPropsPath);
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
