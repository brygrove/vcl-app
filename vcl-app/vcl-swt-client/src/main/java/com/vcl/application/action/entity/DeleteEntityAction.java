package com.vcl.application.action.entity;

import com.vcl.application.mvc.ModelOwner;
import com.vcl.application.validation.IdRequiredValidator;
import com.vcl.util.MessageBoxUtil;


public abstract class DeleteEntityAction<T> extends EntityPersistenceAction<T>  {
	
	private String idName;
	
	public DeleteEntityAction(String idName, ModelOwner<T> entityModelOwner) {
		super("deleting", entityModelOwner);
		this.idName = idName;
	}
	
	@Override
	protected T doPersistenceAction() {
		T entity = getEntity();
		
		if (!IdRequiredValidator.validateAndShowWarningMessage(this)) {
			return entity;
		}
		
		if (doesEntityExist()) {
			entity = deleteEntityAndCreateNewEntity();
		}
		else {
			MessageBoxUtil.showWarning("The " + getDataType() + " cannot be deleted because it does not exist with " + idName + " [" + getIdentifier() + "]");
		}
		
		return entity;
	}
	
	private T deleteEntityAndCreateNewEntity() {
		doDelete();
		return createNewEntity();
	}
	
	@Override
	public String getIdName() {
		return idName;
	}
	
	public abstract Object getIdentifier();
	
	public abstract boolean doesEntityExist();
	
	protected abstract void doDelete();

}
