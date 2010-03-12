package com.jreportquery.models;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.openuri.reportconfig.ReportType;
import org.openuri.reportconfig.ReportsType;

import com.jreportquery.events.ReportListSelectionEvent;
import com.jreportquery.listeners.KeyMouseAdapter;
import com.jreportquery.models.base.AbstractModel;

public class ReportListModel extends AbstractModel {
	
	public static Logger log = Logger.getLogger(ReportListModel.class);
	
	private ReportsType reports = null;
	
	private Vector reportNamesList = new Vector();
	
	private ReportOptionsExecutionModel reportOptionsModel = null; 
	
	private ReportListSelectionEvent reportListSelectionEvent = null;
	
	public ReportListModel() {
		
		initEvents();
	}
	
	@Override
	public void initEvents() {
		log.info("ReportListModel.initEvents()");
		this.reportListSelectionEvent = new ReportListSelectionEvent( this );
	
	}
	

	public ReportsType getReports() {
		return reports;
	}

	public void setReports(ReportsType reports) {
		this.reports = reports;
		
		reportNamesList.clear();
		
		for (ReportType report : reports.getReportArray()){
			reportNamesList.add( report.getName() );
		}
		
	}

	public Vector getReportNamesList() {
		return reportNamesList;
	}

	public ReportOptionsExecutionModel getReportOptionsModel() {
		return reportOptionsModel;
	}

	public void setReportOptionsModel(ReportOptionsExecutionModel reportOptionsModel) {
		this.reportOptionsModel = reportOptionsModel;
	}

	public ReportListSelectionEvent getReportListSelectionEvent() {
		return reportListSelectionEvent;
	}




	
}
