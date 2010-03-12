package com.jreportquery.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import com.jreportquery.reports.XsltTransformReport;
import com.jreportquery.reports.datasource.ReportDataSource;
import com.jreportquery.reports.datasource.ReportHeaderColumn;
import com.jreportquery.reports.elements.ReportResult;
import com.jreportquery.util.FileUtil;
import com.jreportquery.viewer.DocumentViewer;


public class ReportViewerDialog extends JDialog {

	protected ReportDataView rptDataView = new ReportDataView(); 
	
	
	protected AbstractAction printReportHtmlAction = new AbstractAction("Print As HTML"){
		@Override
		public void actionPerformed(ActionEvent e) {
			doPrintReportHtml();
		}
		
	};
	
	public ReportViewerDialog(){
		initLayout();
		initViews();
	}
	
	
	public void initViews()
	{
		rptDataView.setPrintReportHtmlAction( printReportHtmlAction );
		rptDataView.initView();
		
		getContentPane().add( rptDataView);
	}
	protected void initLayout() {
		setModal(false);
		setSize(700, 480);
		getContentPane().setLayout(new BorderLayout());
	}

	public void setData( Vector queryResults, Vector columnNames ){
		
		rptDataView.setTableModelData(queryResults, columnNames);
	}
	
	public ReportDataSource getDataSourceFromTableModel(){
		ReportDataSource ds = new ReportDataSource();
		
		Vector<Vector> results = rptDataView.getQueryResults();
		
		Vector<String> columns = rptDataView.getColumnNames();
		
		for ( String colname : columns ){
			ds.addColumn( new ReportHeaderColumn(colname));
		}
		
		for ( Vector row : results ){
			ds.addRow( row.toArray() );
		}
		
		return ds;
	}
	
	public void doPrintReportHtml(){
		String xmlFileName = "dataset.xml";
		String xsltFileName = "reportbuilder.xsl";
		
		ReportDataSource dataSource = getDataSourceFromTableModel();
		FileUtil.saveTextToFile(xmlFileName, dataSource.toXmlBuffer());
		
		XsltTransformReport report = new XsltTransformReport();
		report.setDataSource( dataSource );
		report.setXmlPath(xmlFileName);
		report.setXslPath(xsltFileName);
		
		ReportResult rptResult = report.buildReportResult();
		
		DocumentViewer.viewDocument( rptResult.getResultFileName() );
	}
	
	public static ReportViewerDialog createResultsDialog( Vector queryResults, Vector columnNames ){
		
		ReportViewerDialog dialog = new ReportViewerDialog();
		dialog.setData(queryResults, columnNames);
		
		return dialog;
		
	}
	
}
