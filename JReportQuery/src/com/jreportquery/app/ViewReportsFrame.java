package com.jreportquery.app;

import info.clearthought.layout.TableLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openuri.reportconfig.ReportConfigurationDocument;
import org.openuri.reportconfig.ReportConfigurationType;
import org.openuri.reportconfig.ReportsType;

import com.jreportquery.data.DataManager;
import com.jreportquery.models.ReportListModel;
import com.jreportquery.models.ReportOptionsExecutionModel;
import com.jreportquery.views.ReportCmdPanelView;
import com.jreportquery.views.ReportListView;
import com.jreportquery.views.ReportOptionsView;

public class ViewReportsFrame extends JFrame {

	public static ReportConfigurationDocument rptConfDoc = null;
	
	public String versionStr = "1.0.0";
	
	public static Logger logger = null; 
	
	
	public static String reportConfigPath = "config\\report-config.xml";
	
	// reports element 
	protected ReportsType reports = null;
	
	private ReportOptionsView reportOptionsView = null;
	private ReportListView reportListView = null;
	private ReportCmdPanelView reportCmdPanelView = null;
	
	private ReportListModel reportListModel = new ReportListModel();
	private ReportOptionsExecutionModel reportOptionsModel = new ReportOptionsExecutionModel();
	
	
	public ViewReportsFrame(){

		reportListModel.setReportOptionsModel(reportOptionsModel);
		
		reportListView = new ReportListView( reportListModel );
		reportOptionsView = new ReportOptionsView( reportOptionsModel );
		reportCmdPanelView = new ReportCmdPanelView( reportOptionsModel );
		
		initFrame();
	}

	public void initEvents() {
		addWindowListener
		(new WindowAdapter()
		{
			public void windowClosing (WindowEvent e)
			{
				DataManager.getInst().cleanup();
				System.exit (0);
			}
		}
		);
	}

	public void initFrame() {
		
		setTitle("Report Runner v" + versionStr );
		
		initEvents();

		initLayout();

		initComponents( );
	}

	public void initLayout() {

		setSize(800, 600);

		getContentPane().setBounds (100, 100, 400, 300);

		// Create a TableLayout for the frame
		double border = 10;
		double size[][] =
		{{border, 300, 400 , border},  // Columns
			{border, 500, border}}; // Rows

		getContentPane().setLayout (new TableLayout(size));

	}

	public void initComponents(){

		JPanel leftPane = new JPanel();
		leftPane.setSize(300, 500);
		leftPane.setBounds(1,1 , 300, 500);
		double border = 5;
		double size[][] =
		{{300},  // Columns
		{450,50}}; // Rows
		leftPane.setLayout( new TableLayout(size));
		leftPane.add( reportListView, "0, 0" );
		leftPane.add( reportCmdPanelView, "0, 1" );
		
		getContentPane().add( leftPane, "1, 1");
		getContentPane().add( reportOptionsView, "2, 1");
		
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BasicConfigurator.configure();
		logger = Logger.getLogger(VclReportRunnerApp.class.getName());

		File file = null;
		rptConfDoc = null;

		try {
			logger.info("loading report config document " + reportConfigPath);
			
			file = new File(reportConfigPath);
			rptConfDoc = ReportConfigurationDocument.Factory.parse( file );
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		if ( rptConfDoc != null ){
			logger.info("initializing data source...");
			
			ReportConfigurationType rptConf = rptConfDoc.getReportConfiguration();

			DataManager.getInst().setupDataSource(rptConf.getDatasource() );
			
			logger.info("displaying gui...");
			
			ViewReportsFrame reportOptionsFrame = new ViewReportsFrame();
			reportOptionsFrame.setReports( rptConf.getReports()  );
			reportOptionsFrame.setVisible(true);
		}
		else 
		{
			logger.error("failed to load xml file " + reportConfigPath );
		}
	}
	
	public void setReports(ReportsType reports){
		this.reports = reports;
		this.reportListModel.setReports( reports );
	}

	public ReportsType getReports() {
		return reports;
	}



}
