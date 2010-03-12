package com.jreportquery.models.base;

public abstract class AbstractModel {
	
	public AbstractModel(){
		
		initModel();
	}
	
	public final void initModel(){
		
		initEvents();
		
	}
	
	public abstract void initEvents();
	
}
