package com.jreportquery.config;

import java.util.HashMap;

import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JTextField;

public class ReportOptionComponentConfig {
	
	private HashMap reportOptionComponentTypes = new HashMap();
	private static ReportOptionComponentConfig instance  = null; 
	
	public ReportOptionComponentConfig(){
		initReportOptionComponentTypes();
	}
	
	public static ReportOptionComponentConfig getInst() {
		if (instance == null ){
			instance = new ReportOptionComponentConfig();
		}
		return instance;
	}
	
	public void initReportOptionComponentTypes(){
		reportOptionComponentTypes.put("text", JTextField.class );
		reportOptionComponentTypes.put("list", JList.class );
		reportOptionComponentTypes.put("number", JFormattedTextField.class );
		
	}
	
	public Class getComponentClassByReportOptionType(String type ){
		Class compClass = (Class) reportOptionComponentTypes.get(type);
		return compClass;
	}
	
}
