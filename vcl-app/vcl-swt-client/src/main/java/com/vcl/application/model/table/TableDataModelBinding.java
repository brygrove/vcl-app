package com.vcl.application.model.table;

import java.util.ArrayList;
import java.util.List;

public class TableDataModelBinding {

	private Class<?> dataModelClass;
	
	private TableModelMetaData tableModelMetaData;
	
	@SuppressWarnings("rawtypes")
	private List dataList = null;


	public TableDataModelBinding(Class<?> dataModelClass) {
		this.dataModelClass = dataModelClass;
	}
	
	public Class<?> getDataModelClass() {
		return dataModelClass;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getDataList() {
		if (dataList == null) {
			this.dataList = new ArrayList<T>();
		}
		return (List<T>) dataList;
	}

	public <T> TableDataModelBinding setDataList(List<T> dataList) {
		this.dataList = dataList;
		return this;
	}

	public TableModelMetaData getTableModelMetaData() {
		return tableModelMetaData;
	}

	public TableDataModelBinding setTableModelMetaData(TableModelMetaData tableModelMetaData) {
		this.tableModelMetaData = tableModelMetaData;
		return this;
	}
	
}
