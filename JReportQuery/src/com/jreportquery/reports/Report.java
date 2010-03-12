package com.jreportquery.reports;

import com.jreportquery.reports.datasource.ReportDataSource;
import com.jreportquery.reports.datasource.ReportHeaderColumn;
import com.jreportquery.reports.elements.ReportResult;
import com.jreportquery.util.FileUtil;

public abstract class  Report {


	protected ReportDataSource datasource = null;

	public void setDataSource(ReportDataSource dataSource2) {
		datasource = dataSource2;
	}
	
	public ReportResult buildReportResult(){
		return transformReportResult(datasource);
	}
	
	protected abstract ReportResult transformReportResult( ReportDataSource rs );
	

}
