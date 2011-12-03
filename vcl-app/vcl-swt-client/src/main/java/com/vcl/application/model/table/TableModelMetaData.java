package com.vcl.application.model.table;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableModelMetaData {

	private Map<String, TableMetaColumn> tableMetaColumns = new LinkedHashMap<String, TableMetaColumn>();

	public TableModelMetaData setTableMetaColumns(TableMetaColumn... columns) {
		for (TableMetaColumn col : columns) {
			tableMetaColumns.put(col.getColName(), col);
		}
		return this;
	}
	
	public Collection<TableMetaColumn> getTableMetaColumns() {
		return tableMetaColumns.values();
	}
	
	public TableMetaColumn findTableMetaColByColName(String colName) {
		return tableMetaColumns.get(colName);
	}

	public TableModelMetaData addTableMetaColumn(String colName, String bindingPropertyName, int width) {
		tableMetaColumns.put(colName, new TableMetaColumn(colName, bindingPropertyName, width));
		return this;
	}
	
	public TableModelMetaData addTableMetaColumn(String colName, String bindingPropertyName) {
		tableMetaColumns.put(colName, new TableMetaColumn(colName, bindingPropertyName));
		return this;
	}
	
	public String[] getDataListBindingProperties() {
		String[] bindProperties = new String[tableMetaColumns.size()];
		
		int i = 0;
		for(TableMetaColumn col : tableMetaColumns.values()) {
			bindProperties[i] = col.getBindingPropertyName();
			i++;
		}
		
		return bindProperties;
	}

	public int getTableMetaColumnsSize() {
		return tableMetaColumns.size();
	}
}
