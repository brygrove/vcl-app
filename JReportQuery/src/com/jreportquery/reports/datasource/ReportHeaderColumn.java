package com.jreportquery.reports.datasource;

public class ReportHeaderColumn {
	
	public static final String DEFAULT_ALIGN = "left";
	
	private String name = "";
	private String align = DEFAULT_ALIGN;
	
	
	public ReportHeaderColumn(){
		
	}
	
	public ReportHeaderColumn(String name ) {
		this(name, DEFAULT_ALIGN);
	}
	
	public ReportHeaderColumn(String name, String align) {
		this.name = name;
		this.align = align;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlign() {
		return align;
	}
	public void setAlign(String align) {
		this.align = align;
	}
	

}
