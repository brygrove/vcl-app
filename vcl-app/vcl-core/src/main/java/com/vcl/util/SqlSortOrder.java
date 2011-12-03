package com.vcl.util;

public enum SqlSortOrder {

	ASCENDING("ASC"),
	DESCENDING("DESC");
	
	private String value;
	
	private SqlSortOrder(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
