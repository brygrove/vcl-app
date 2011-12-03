package com.vcl.application.mvc;

public interface ViewOwner<V> {

	public void setView(V view); 
	
	public V getView();
}
