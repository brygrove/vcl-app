package com.vcl.application.mvc;

public interface Controller<V, M> extends ModelProvider<M>, ViewOwner<V> {
	
	public V getModelOwner();
	
}
