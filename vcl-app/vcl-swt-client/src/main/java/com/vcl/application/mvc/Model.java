package com.vcl.application.mvc;

public interface Model<T> {
	
	public void setValue(T value);
	
	public T getValue();
	
}
