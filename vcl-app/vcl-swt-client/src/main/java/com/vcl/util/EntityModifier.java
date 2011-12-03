package com.vcl.util;

public interface EntityModifier<T> {
	
	public Class<T> getEntityClass();
	
	public T getEntity();
}
