package com.vcl.application.action.entity;

import com.vcl.application.mvc.ModelOwner;
import com.vcl.application.validation.IdRequiredValidator;
import com.vcl.util.MessageBoxUtil;


public abstract class CreateEntityAction<T> extends EntityPersistenceAction<T> {
	
	private String idName;
	
	public CreateEntityAction(String idName, ModelOwner<T> entityModelOwner) {
		super("creating", entityModelOwner);
		this.idName = idName;
	}
	
	@Override
	protected T doPersistenceAction() {
		T entity = getEntity();
		
		if (!IdRequiredValidator.validateAndShowWarningMessage(this)) {
			return entity;
		}
		
		if (doesEntityExist()) {
			MessageBoxUtil.showWarning("A " + getDataType() + " already exists with " + idName + " [" + getIdentifier() + "]");
		}
		else {
			entity = doCreate();
		}
		
		return entity;
	}
	
	@Override
	public String getIdName() {
		return idName;
	}
	
	public abstract Object getIdentifier();
	
	public abstract boolean doesEntityExist();
	
	protected abstract T doCreate();
	
}
