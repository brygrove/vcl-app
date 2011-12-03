package com.vcl.application.model.table;


public class TableMetaColumn {
	
	private static final int DEFAULT_WIDTH = 100;
	
	private String colName;
	private String bindingPropertyName;
	private int width;
	
	public TableMetaColumn(String colName, String bindingPropertyName) {
		this.colName = colName;
		this.bindingPropertyName = bindingPropertyName;
	}

	public TableMetaColumn(String colName, String bindingPropertyName, int width) {
		this.colName = colName;
		this.bindingPropertyName = bindingPropertyName;
		this.width = width;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getBindingPropertyName() {
		return bindingPropertyName;
	}

	public void setBindingPropertyName(String bindingPropertyName) {
		this.bindingPropertyName = bindingPropertyName;
	}

	public int getWidth() {
		return width;
	}
	
	public int getWidthOrDefault() {
		return getWidth() == 0 ? DEFAULT_WIDTH : getWidth();
	}
}
