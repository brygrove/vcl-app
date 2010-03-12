package com.jreportquery.views;

import info.clearthought.layout.TableLayout;

import javax.swing.JList;
import javax.swing.JScrollPane;

import org.apache.log4j.Logger;

import com.jreportquery.models.ReportListModel;
import com.jreportquery.views.base.AbstractPanelView;


public class ReportListView extends AbstractPanelView {
	
	private JList reportList = null;
	
	public static Logger logger = Logger.getLogger(ReportListView.class.getName());
	
	public ReportListView(ReportListModel reportListModel) {
		super(reportListModel);
	}
	

	public ReportListModel getReportListModel() {
		return (ReportListModel)getModel();
	}
	
	@Override
	public void initLayout() {
		setBounds (100, 100, 300, 450);

		// Create a TableLayout for the frame
		double border = 2;
		double size[][] =
		{{border, 300, border},  // Columns
			{border, 445, border}}; // Rows

		setLayout (new TableLayout(size));
	}
	


	@Override
	public void initComponents() {
		reportList = new JList();
		JScrollPane scroll = new JScrollPane(reportList);
		reportList.setListData( getReportListModel().getReportNamesList() );
		
		add( scroll, "1,1");
	}
	

	@Override
	public void initEvents() {
		logger.info("init events report list view... ");
		reportList.addMouseListener( getReportListModel().getReportListSelectionEvent()   );
		reportList.addKeyListener( getReportListModel().getReportListSelectionEvent()  );
		
	}


}
