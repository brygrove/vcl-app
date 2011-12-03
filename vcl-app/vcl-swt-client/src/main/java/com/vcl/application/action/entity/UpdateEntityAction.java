package com.vcl.application.action.entity;

import com.vcl.application.mvc.ModelOwner;
import com.vcl.application.validation.IdRequiredValidator;
import com.vcl.util.MessageBoxUtil;


public abstract class UpdateEntityAction<T> extends EntityPersistenceAction<T> {
	
	private String idName;
	
	public UpdateEntityAction(String idName, ModelOwner<T> entityModelOwner) {
		super("updating", entityModelOwner);
		this.idName = idName;
	}
	
	@Override
	protected T doPersistenceAction() {
		T entity = getEntity();
		
		if (!IdRequiredValidator.validateAndShowWarningMessage(this)) {
			return entity;
		}
		
		if (!doesEntityExist()) {
			MessageBoxUtil.showWarning("A " + getDataType() + " does not exist for " + idName + " [" + getIdentifier() + "]");
		}
		else {
			entity = doUpdate();
		}
		
		return entity;
	}
	
	@Override
	public String getIdName() {
		return idName;
	}
	
	public abstract Object getIdentifier();
	
	public abstract boolean doesEntityExist();
	
	protected abstract T doUpdate();

}
