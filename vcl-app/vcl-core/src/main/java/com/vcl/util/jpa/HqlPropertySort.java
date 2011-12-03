package com.vcl.util.jpa;

import com.vcl.util.*;

public class HqlPropertySort {
	
	private String propertyName;
	
	private SqlSortOrder sqlSortOrder;
	
	public HqlPropertySort(String propertyName, SqlSortOrder sqlSortOrder) {
		this.propertyName = propertyName;
		this.sqlSortOrder = sqlSortOrder;
	}
	
	public HqlPropertySort(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public SqlSortOrder getSqlSortOrder() {
		return sqlSortOrder;
	}

	public void setSqlSortOrder(SqlSortOrder sqlSortOrder) {
		this.sqlSortOrder = sqlSortOrder;
	}
	
	public static HqlPropertySort createPropertySort( String propertyName, SqlSortOrder sqlSortOrder){
		return new HqlPropertySort(propertyName, sqlSortOrder);
	}

	public boolean isSortOrderDefined() {
		return sqlSortOrder != null;
	}
	
	public String toOrderByPropertyString( ){
		return propertyName + " " + sqlSortOrder;
	}
	
}
