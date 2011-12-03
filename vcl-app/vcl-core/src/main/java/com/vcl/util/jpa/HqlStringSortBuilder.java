package com.vcl.util.jpa;

import java.util.ArrayList;
import java.util.List;

import com.vcl.util.SqlSortOrder;
import com.vcl.util.Utils;

public abstract class HqlStringSortBuilder<T> {
	
	private List<HqlPropertySort> propertiesToSort = new ArrayList<HqlPropertySort>();

	public void addSortByProperty(HqlPropertySort propSort) {
		propertiesToSort.add(propSort);
	}
	
	public void addSortByProperty(String propertyName, SqlSortOrder sortOrder) {
		propertiesToSort.add( new HqlPropertySort(propertyName, sortOrder) );
	}
	
	public void addSortByProperty(String propertyName ) {
		propertiesToSort.add( new HqlPropertySort(propertyName, null) );
	}
	
	protected String buildOrderByHql( ){
		StringBuffer sb = new StringBuffer();
		
		if ( Utils.isEmpty( propertiesToSort )){
			return "";
		}
		
		sb.append(" order by ");
		for ( int i = 0; i < propertiesToSort.size(); i++ ){
			HqlPropertySort propSort = propertiesToSort.get( i );
			
			sb.append(propSort.getPropertyName() );
			
			if ( propSort.isSortOrderDefined() ){
				sb.append(" " + propSort.getSqlSortOrder().getValue() );
			}
			
			if ( i < ( propertiesToSort.size() - 1 ) ){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	public abstract String getOrderByHql( T sortConfig );

}
