package com.vcl.application.action.search;

import com.vcl.application.control.SearchModel;

import com.vcl.application.model.table.TableDataModelBinding;

@SuppressWarnings("rawtypes")
public class SearchEntityConfig {

	private SearchModel searchModel;
	private EntityDataSearchProvider entityDataSearchProvider;
	private TableDataModelBinding tableModelDataBinding;

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public EntityDataSearchProvider getEntityDataSearchProvider() {
		return entityDataSearchProvider;
	}

	public void setEntityDataSearchProvider(EntityDataSearchProvider entityDataSearchProvider) {
		this.entityDataSearchProvider = entityDataSearchProvider;
	}

	public TableDataModelBinding getTableModelDataBinding() {
		return tableModelDataBinding;
	}

	public void setTableModelDataBinding(TableDataModelBinding tableModelDataBinding) {
		this.tableModelDataBinding = tableModelDataBinding;
	}

}
