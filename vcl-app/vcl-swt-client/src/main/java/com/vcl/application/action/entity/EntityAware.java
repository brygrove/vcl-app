package com.vcl.application.action.entity;

public interface EntityAware {
	
	String getDataType();
	
	Object getIdentifier();
	
	boolean doesEntityExist();
	
	String getIdName();
}
