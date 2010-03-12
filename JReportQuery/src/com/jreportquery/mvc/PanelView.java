package com.jreportquery.mvc;

import javax.swing.JPanel;

public abstract class PanelView extends JPanel {
	protected abstract void initLayout();
	protected abstract void initComponents();
	protected abstract void initEvents();
	
	public void initView(){
		initLayout();
		initComponents();
		initEvents();
	}
	
}
