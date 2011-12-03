package com.vcl.application.action.entity;

import org.eclipse.swt.events.SelectionEvent;
import org.springframework.dao.DataAccessException;

import com.vcl.application.mvc.ModelOwner;
import com.vcl.util.MessageBoxUtil;

public abstract class EntityPersistenceAction<T> extends EntityAction<T> implements EntityAware {

	protected abstract T doPersistenceAction();
	
	private ModelOwner<T> entityModelOwner;
	
	private String verb = "persisting";
	
	public EntityPersistenceAction(String verb, ModelOwner<T> entityModelOwner) {
		this.verb = verb;
		this.entityModelOwner = entityModelOwner;
	}

	public void setEntity(T entity) {
		entityModelOwner.setModel(entity);
	}
	
	@Override
	public T getEntity() {
		return entityModelOwner.getModel();
	}

	@Override
	public Class<T> getEntityClass() {
		return entityModelOwner.getModelClass();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		T entity = getEntity();
		try {
			entity = doPersistenceAction();
			setEntity(entity);
		} 
		catch (DataAccessException dae) {
			MessageBoxUtil.showError("Error " + verb + " " + getDataType() + " : " + getEntity()
					+ ". Data Access Error: " + dae.getMessage());
			dae.printStackTrace();
		} catch (Exception ex) {
			MessageBoxUtil.showError("Error " + verb + " " + getDataType() + " : " + getEntity() + ". Error: "
					+ ex.getMessage());
			ex.printStackTrace();
		}

	}

	@Override
	public Object getIdentifier() {
		return null;
	}
	
	@Override
	public boolean doesEntityExist() {
		return false;
	}
}
