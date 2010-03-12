package com.jreportquery.views;

import javax.swing.Action;
import javax.swing.JButton;

import info.clearthought.layout.TableLayout;

import com.jreportquery.models.ReportOptionsExecutionModel;
import com.jreportquery.views.base.AbstractPanelView;

public class ReportCmdPanelView extends AbstractPanelView {
	
	protected JButton butExec = null;
	
	public ReportCmdPanelView(ReportOptionsExecutionModel reportOptionsExecModel){
		super(reportOptionsExecModel);
		reportOptionsExecModel.setReportCommandView( this );
	}
	
	public ReportOptionsExecutionModel getReportOptionsModel() {
		return (ReportOptionsExecutionModel)getModel();
	}

	@Override
	public void initEvents() {
		Action runReportAction = getReportOptionsModel().getRunReportAction();
		
		this.butExec.addActionListener(runReportAction);
		
	}

	@Override
	public void initLayout() {
	
		setBounds (0, 0, 300, 50);

		// Create a TableLayout for the frame
		double border = 2;
		double size[][] =
		{{border, 145,145, border},  // Columns
			{border, 20,20, border}}; // Rows

		setLayout (new TableLayout(size));
	}

	@Override
	public void initComponents() {
	
	
		butExec = new JButton( "Run Report" );
		
		add( butExec , "1,1" );
	}


}
