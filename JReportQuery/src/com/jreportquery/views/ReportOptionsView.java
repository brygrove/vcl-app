package com.jreportquery.views;

import java.awt.BorderLayout;

import info.clearthought.layout.TableLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.openuri.reportconfig.ParamType;

import com.jreportquery.config.ReportOptionComponentConfig;
import com.jreportquery.models.ReportOptionsExecutionModel;
import com.jreportquery.views.base.AbstractPanelView;

public class ReportOptionsView extends AbstractPanelView {
	
	public Logger logger =  Logger.getLogger(ReportOptionsView.class.getName());
	
	public ReportOptionsView(ReportOptionsExecutionModel reportOptionsModel){
		super(reportOptionsModel);
		reportOptionsModel.setReportOptionsView( this );
	}
	
	public ReportOptionsExecutionModel getReportOptionsModel() {
		return (ReportOptionsExecutionModel)getModel();
	}
	
	public void initLayout() {
		 setBounds (100, 100, 200, 200);
		 setSize(300, 300);
		 setBorder(BorderFactory.createBevelBorder(1));
	     // Create a TableLayout for the frame
	        double border = 10;
	        double size[][] =
	            {{border, 160, 200 , border},  // Columns
	        		{border, 20, 20 ,20 , 20, 20,  20,  20,  20,  20, border}}; // Rows

	     setLayout (new TableLayout(size));
	     //setLayout (new BorderLayout());
	}
	
	public void initComponents() {
		
	}

	@Override
	public void initEvents() {
		// TODO Auto-generated method stub
		
	}
	

	public void initParamComponents(ParamType[] params){
		
		if (params == null ){
			return; 
		}
		
		getReportOptionsModel().clearReportOptions();
		
		removeAll();
		
		for (int i = 0; i < params.length; i++){
			ParamType param = params[i];
			String paramName = param.getName();
			
			JLabel label = new JLabel();
			label.setText( param.getLabel() );
			
			add(label, "1, " + (i+1)); // Top
		 	
			Class c = ReportOptionComponentConfig.getInst().getComponentClassByReportOptionType( param.getType() );
			
			if ( c == null ){
				JOptionPane.showMessageDialog(null, "Report option " + param.getName() + " - " + param.getLabel() + " does not have a valid type " + param.getType() );
				continue;
			}
			
			JComponent comp = null;
			
			try {
				comp = (JComponent)c.newInstance();
			}catch (Exception ex){
				logger.error("could not create a jcomponent from class " + c.getName() );
				continue;
			}
			
		 	comp.setName(paramName);
		 	
		 	add(comp, "2, " + (i+1)); // Top
		
		 	
		 	getReportOptionsModel().addReportOption(label,comp);
		 
		}
		
		invalidate();
		validateTree();
		repaint();
		
		for (JComponent c :  getReportOptionsModel().getReportOptionComponents() ){
			System.out.println(c);
		}
	}


}
