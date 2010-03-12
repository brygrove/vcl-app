package com.jreportquery.reports.test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdesktop.jdic.desktop.Desktop;
import org.jdesktop.jdic.desktop.DesktopException;

import com.jreportquery.reports.Report;
import com.jreportquery.reports.XsltTransformReport;
import com.jreportquery.reports.datasource.ReportDataSource;
import com.jreportquery.reports.datasource.ReportHeaderColumn;
import com.jreportquery.reports.elements.ReportResult;
import com.jreportquery.util.FileUtil;
import com.jreportquery.viewer.DocumentViewer;
import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;

public class TestReports {

	public static void main(String[] args) {
		String xmlFileName = "dataset.xml";
		String xsltFileName = "reportbuilder.xsl";
		
		ReportDataSource dataSource = BuildDataSource();
		FileUtil.saveTextToFile(xmlFileName, dataSource.toXmlBuffer());
		
		XsltTransformReport report = new XsltTransformReport();
		report.setDataSource( dataSource );
		report.setXmlPath(xmlFileName);
		report.setXslPath(xsltFileName);
		
		ReportResult rptResult = report.buildReportResult();
		
		DocumentViewer.viewDocument( rptResult.getResultFileName() );
		
	}

	public static ReportDataSource BuildDataSource() {
		ReportDataSource dataSource = new ReportDataSource(); 
		
		dataSource.addColumn( new ReportHeaderColumn("Item #"));
		dataSource.addColumn( new ReportHeaderColumn("Description"));
		dataSource.addColumn( new ReportHeaderColumn("Category"));
		
		dataSource.addRow( new Object[]{"123","Coke","Softdrinks"});
		dataSource.addRow( new Object[]{"124","Pepsi","Softdrinks"});
		dataSource.addRow( new Object[]{"125","Dr Pepper","Softdrinks"});
		dataSource.addRow( new Object[]{"126","A&W Root Beer","Softdrinks"});
		dataSource.addRow( new Object[]{"127","Dad's Cookies","Snacks"});
		dataSource.addRow( new Object[]{"128","Lays Chips","Snacks"});
		
		return dataSource;
	}
	
}
