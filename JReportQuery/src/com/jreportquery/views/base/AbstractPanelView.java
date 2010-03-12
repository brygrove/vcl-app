package com.jreportquery.views.base;

import javax.swing.JPanel;

import com.jreportquery.models.base.AbstractModel;

public abstract class AbstractPanelView extends JPanel implements IView {
	
	protected AbstractModel model = null; 
	
	public AbstractPanelView(AbstractModel mdl) {
		setModel(mdl);
		initView();
	}
	
	public final void initView() {
		
		initLayout();
		initComponents();
		initEvents();
	}
	
	public abstract void initEvents();
	
	public abstract void initComponents();
	
	public abstract void initLayout();
	
	public final AbstractModel getModel(){
		return model;
	}

	public final void setModel(AbstractModel m) {
		this.model = m;
	}
}
