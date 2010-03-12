package com.jreportquery.data.meta;

/** result set column */ 
public class RSColumn {

	private String columnName = "";
	private String columnLabel = "";
	private int index = 0;
	private String type = "";
	
	

	public RSColumn(String columnName, String columnLabel, int index,
			String type) {
		super();
		this.columnName = columnName;
		this.columnLabel = columnLabel;
		this.index = index;
		this.type = type;
	}


	public String getColumnName() {
		return columnName;
	}


	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String toString(){
		return getColumnName();
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}



	
}
