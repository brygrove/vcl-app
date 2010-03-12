package com.jreportquery.events;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JList;

import org.openuri.reportconfig.ReportType;

import com.jreportquery.listeners.KeyMouseAdapter;
import com.jreportquery.models.ReportListModel;
import com.jreportquery.models.ReportOptionsExecutionModel;

public class ReportListSelectionEvent extends KeyMouseAdapter {
	protected ReportListModel reportListModel = null;
	
	public ReportListSelectionEvent(ReportListModel reportListModel ){
		this.reportListModel = reportListModel;
	}
	
	public void selectionChanged(JList list) {
		int index = list.getSelectedIndex();
			
		System.out.println( index );
		
		if (index == -1 ){
			return;
		}
		
		String reportName = ((String) reportListModel.getReportNamesList().get( index )).toLowerCase().trim();
		
		System.out.println(reportName);
		
		ReportType report = null;
		
		for ( ReportType rpt : reportListModel.getReports().getReportArray() ){
			if ( rpt.getName().toLowerCase().trim().equals(reportName) ){
				report = rpt;
				break;
			}
		}
		
		
		if (report != null ){
			reportListModel.getReportOptionsModel().setReport(report);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		selectionChanged( (JList) ke.getSource() );
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		//selectionChanged( (JList) me.getSource() );
	}

	@Override
	public void mousePressed(MouseEvent me) {
		selectionChanged( (JList) me.getSource() );
	}
}
