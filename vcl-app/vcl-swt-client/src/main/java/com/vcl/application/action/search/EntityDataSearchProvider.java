package com.vcl.application.action.search;

import java.util.List;


public interface EntityDataSearchProvider<T> {
	
	public List<T> searchEntity(EntityDataSearchRequest request);
	
}
