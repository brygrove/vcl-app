package com.jreportquery.dialogs;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

import com.jreportquery.mvc.PanelView;

public class ReportDataView extends PanelView {

	protected JTable table = null; 
	protected AbstractTableModel tableModel = null;
	protected JPanel reportViewOptionsPanel = new JPanel();
	protected JButton printReportHtmlButton = null;
	protected AbstractAction printReportHtmlAction = null; 
	
	protected Vector queryResults = new Vector();
	
	public Vector getQueryResults() {
		return queryResults;
	}

	public Vector getColumnNames() {
		return columnNames;
	}

	protected Vector columnNames = new Vector();
	
	
	public AbstractTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(AbstractTableModel tableModel) {
		this.tableModel = tableModel;
	}


	@Override
	protected void initComponents() {
		if (tableModel == null ){
			tableModel = new DefaultTableModel();
		}
		
		table = new JXTable(tableModel);
		
		printReportHtmlButton = new JButton("print report");
		reportViewOptionsPanel.add( printReportHtmlButton ); 
		add( new JScrollPane(table ));
		add( reportViewOptionsPanel , BorderLayout.SOUTH );
	}

	public void setTableModelData( Vector queryResults, Vector columnNames ){
		
		this.queryResults = queryResults;
		this.columnNames = columnNames;
		
		tableModel = new DefaultTableModel(queryResults,columnNames );
		table.setModel(tableModel);
	}
	
	
	@Override
	protected void initEvents() {
		
		printReportHtmlButton.setAction( printReportHtmlAction );
		
	}

	@Override
	protected void initLayout() {
		setLayout(new BorderLayout());
		
	}

	public void setPrintReportHtmlAction(AbstractAction printReportHtmlAction) {
		this.printReportHtmlAction = printReportHtmlAction;
	}
	
}
