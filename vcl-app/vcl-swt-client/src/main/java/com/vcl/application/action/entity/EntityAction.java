package com.vcl.application.action.entity;

import org.eclipse.swt.events.SelectionAdapter;

import com.vcl.application.exception.CreateNewObjectException;

public abstract class EntityAction<T> extends SelectionAdapter {
	
	public abstract T getEntity(); 
		
	public String getDataType() {
		return getEntityClass().getSimpleName();
	}

	public abstract Class<T> getEntityClass(); 
	
	protected T createNewEntity() {
		try {
			return getEntityClass().newInstance();
		}catch(Exception ex){
			throw new CreateNewObjectException(getEntityClass(), ex);
		}
	}

}
