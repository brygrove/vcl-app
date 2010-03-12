package com.jreportquery.models;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import org.apache.log4j.Logger;
import org.openuri.reportconfig.KeywordType;
import org.openuri.reportconfig.ParamType;
import org.openuri.reportconfig.ReportType;

import com.jreportquery.data.DataManager;
import com.jreportquery.data.meta.RSColumn;
import com.jreportquery.dialogs.ReportViewerDialog;
import com.jreportquery.models.base.AbstractModel;
import com.jreportquery.views.ReportCmdPanelView;
import com.jreportquery.views.ReportOptionsView;


public class ReportOptionsExecutionModel extends AbstractModel {
	
	public Logger logger = Logger.getLogger(ReportOptionsExecutionModel.class.getName());
	
	protected List<JLabel> reportOptionLabels = new ArrayList<JLabel>();
	
	protected List<JComponent> reportOptionComponents = new ArrayList();
	
	protected ReportType report = null; 
	
	protected Properties fieldProperties = new Properties();
	
	protected ReportOptionsView reportOptionsView = null;
	
	protected ReportCmdPanelView reportCmdPanelView = null; 
	
	protected AbstractAction runReportAction = null; 
	
	public ReportOptionsExecutionModel(){
		super();
		initEvents();
		
		try {
			fieldProperties.load( new FileInputStream("config\\fields.properties"));
		}catch(Exception ex){
			logger.error("failed to load field properties. Full field names will not be used.");
		}
	}
	
	
	public ReportType getReport() {
		return report;
	}

	public void setReport(ReportType report) {
		this.report = report;
		
		//JOptionPane.showMessageDialog(null, "selecting report : " + report.getName() );
		
		if (this.report.getParams() != null ) {
			fillReportOptions();
		}
			
	}
	
	public void fillReportOptions() {
		if (reportOptionsView != null ){
			reportOptionsView.initParamComponents(this.report.getParams().getParamArray());
		}
	}

	public void clearReportOptions(){
		reportOptionComponents.clear();
		reportOptionLabels.clear();
	}
	

	public void addReportOption(JLabel label, JComponent comp){
		reportOptionLabels.add( label );
		reportOptionComponents.add( comp );
	}

	public Collection<JComponent> getReportOptionComponents() {
		return reportOptionComponents;
	}

	@Override
	public void initEvents() {
		
		this.runReportAction = new AbstractAction("Run Report"){
			public void actionPerformed(ActionEvent e) {
				ReportOptionsExecutionModel.this.runReport();
			}
		};
		
	}
	
	protected void runReport() {
		
		logger.info("run report...");
		
		if ( this.report != null  ){
			
			String sql = report.getQuery();
			
			ArrayList params = new ArrayList();
			
			
			ParamType[] rptParams =  report.getParams().getParamArray();
			
			for (int i = 0; i < rptParams.length; i++){
				
				ParamType param = rptParams[i];
				
				JComponent comp = reportOptionComponents.get(i);
				JLabel label = reportOptionLabels.get(i);
				
				Object value = null; 
				
				if ( comp instanceof JTextComponent ) {
					String text = ((JTextComponent)comp).getText();
					String post = ""; 
					String pre = "";  
					
					KeywordType keyword = param.getKeyword();
					if (keyword != null ){
						post = keyword.getPostfix();
						pre = keyword.getPrefix();
						if ( post == null ){
							post = "";
						}
						if ( pre == null ){
							pre = "";
						}
					}
					
					text = post +  text + pre;
					value = text;
				}
				
				
				params.add( value  );
			}
			
			List<RSColumn> metaInfo = new ArrayList();
			
			Vector queryResults = (Vector) DataManager.getInst().executeSqlStatAsList(Vector.class, sql, params, metaInfo);
			
			System.out.println(metaInfo);
			
			Vector columnNames = new Vector();
			for (RSColumn col : metaInfo){
				String fieldName = fieldProperties.getProperty(col.getColumnName().toLowerCase());
				if (fieldName == null ){
					fieldName = col.getColumnName();
				}
				columnNames.add( fieldName );
				
			}
			
			JDialog dialog = ReportViewerDialog.createResultsDialog(queryResults, columnNames);
			dialog.setVisible(true);
		}
		
	}
	

	public AbstractAction getRunReportAction() {
		return this.runReportAction;
	}


	public Collection getReportOptionLabels() {
		return reportOptionLabels;
	}


	public ReportOptionsView getReportOptionsView() {
		return reportOptionsView;
	}


	public void setReportOptionsView(ReportOptionsView reportOptionsView) {
		this.reportOptionsView = reportOptionsView;
	}


	public void setReportCommandView(ReportCmdPanelView reportCmdPanelView) {
		this.reportCmdPanelView = reportCmdPanelView;
	}
	
}
