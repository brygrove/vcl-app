package com.vcl.application.action.entity;

import com.vcl.application.mvc.ModelOwner;
import com.vcl.application.validation.IdRequiredValidator;


public abstract class SaveEntityAction<T> extends EntityPersistenceAction<T> {

	protected abstract T doSave();
	
	private String idName;
	
	public SaveEntityAction(String idName, ModelOwner<T> entityModelOwner) {
		super("saving", entityModelOwner);
		this.idName = idName;
	}
	
	@Override
	protected T doPersistenceAction() {
		
		if (!IdRequiredValidator.validateAndShowWarningMessage(this)) {
			return getEntity();
		}
		
		return doSave();
	}
	
	@Override
	public String getIdName() {
		return idName;
	}
	
}
