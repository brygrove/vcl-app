package com.vcl.application.action.entity;

import org.eclipse.swt.events.SelectionEvent;

import com.vcl.application.mvc.ModelOwner;
import com.vcl.util.MessageBoxUtil;

public class EntityNewAction<T> extends EntityAction<T> {
	
	private ModelOwner<T> modelOwner;
	
	public EntityNewAction(ModelOwner<T> modelOwner) {
		this.modelOwner = modelOwner;
	}
	
	@Override
	public Class<T> getEntityClass() {
		return modelOwner.getModelClass();
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		createNewEntityTrapErrors();
	}
	
	protected void createNewEntityTrapErrors() {
		try {
			T entity = createNewEntity();
			modelOwner.setModel(entity);
		} catch (Exception ex) {
			MessageBoxUtil.showError("Error while creating new " + getDataType() + ". Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	@Override
	public T getEntity() {
		return modelOwner.getModel();
	}

}
