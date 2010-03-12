package com.jreportquery.reports.datasource;

import java.util.ArrayList;
import java.util.List;

import com.jreportquery.util.FileUtil;

/**
 *  report design model containing the columns and the data to transform 
 *  into different formats. 
 **/
public class ReportDataSource {

	List<ReportHeaderColumn> columns = new ArrayList<ReportHeaderColumn>();
	
	List<Object[]> rows = new ArrayList<Object[]>();
	
	public List<Object[]> getRows() {
		return rows;
	}

	public void setRows(List<Object[]> rows) {
		this.rows = rows;
	}
	
	public void addRow(Object[] o){
		rows.add(o);
	}
	
	public void addColumn(ReportHeaderColumn c){
		columns.add(c);
	}
	
	public List<ReportHeaderColumn> getColumns() {
		return columns;
	}

	public void setColumns(List<ReportHeaderColumn> columns) {
		this.columns = columns;
	}
	
	public StringBuffer toXmlBuffer() {
		
		StringBuffer sb = new StringBuffer(); 
		
		sb.append("<?xml version='1.0' ?>\n");
		sb.append("<dataset>\n");
			
		// header section
		sb.append("\t<header>\n");
		for ( ReportHeaderColumn h : getColumns() ){
			sb.append("\t\t<col align=\"" + h.getAlign() + "\" ><![CDATA[" + h.getName() + "]]></col>\n");
		}
		sb.append("\t</header>\n");
		
		// row section
		for ( Object[] row : getRows() ){
			
			sb.append("\t<row>\n");
			
			for ( Object col : row ){
				col = col == null ? "" : col;
				sb.append("\t\t<col><![CDATA[" + col.toString() + "]]></col>\n");
			}
			
			sb.append("\t</row>\n");
		}
		
		sb.append("</dataset>\n");
		
		return sb;
	}

	
}
