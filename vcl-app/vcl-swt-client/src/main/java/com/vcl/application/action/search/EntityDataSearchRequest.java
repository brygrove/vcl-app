package com.vcl.application.action.search;

import com.vcl.application.control.SearchModel;
import com.vcl.application.model.table.TableDataModelBinding;

public class EntityDataSearchRequest {

	private SearchModel searchModel;
	@SuppressWarnings("rawtypes")
	private EntityDataSearchProvider entityDataSearchProvider;
	private TableDataModelBinding tableModelDataBinding;
	
	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	@SuppressWarnings("rawtypes")
	public EntityDataSearchProvider getEntityDataSearchProvider() {
		return entityDataSearchProvider;
	}

	@SuppressWarnings("rawtypes")
	public void setEntityDataSearchProvider(EntityDataSearchProvider entityDataSearchProvider) {
		this.entityDataSearchProvider = entityDataSearchProvider;
	}

	public TableDataModelBinding getTableModelDataBinding() {
		return tableModelDataBinding;
	}

	public void setTableModelDataBinding(TableDataModelBinding tableModelDataBinding) {
		this.tableModelDataBinding = tableModelDataBinding;
	}
	
	public static EntityDataSearchRequest createFromConfig(SearchEntityConfig conf) {
		EntityDataSearchRequest request = new EntityDataSearchRequest();
		request.setEntityDataSearchProvider(conf.getEntityDataSearchProvider());
		request.setSearchModel(conf.getSearchModel());
		request.setTableModelDataBinding(conf.getTableModelDataBinding());
		return request;
	}
	
	@SuppressWarnings("rawtypes")
	public static EntityDataSearchRequest create(
			SearchModel searchModel, 
			TableDataModelBinding tableModelDataBinding,
			EntityDataSearchProvider entityDataSearchProvider) {
		
		EntityDataSearchRequest request = new EntityDataSearchRequest();
		request.setSearchModel(searchModel);
		request.setEntityDataSearchProvider(entityDataSearchProvider);
		request.setTableModelDataBinding(tableModelDataBinding);
		return request;
	}
}
