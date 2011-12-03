package com.vcl.application.mvc;

public interface ModelOwner<M> {
	
	public Class<M> getModelClass();
	
	public M getModel();
	
	public void setModel(M model);
	
}
