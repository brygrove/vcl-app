package com.vcl.application.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.vcl.application.ApplicationResources;
import com.vcl.util.MessageBoxUtil;

public class ShowBorrowerSearchView extends SelectionAdapter {

	private static final long serialVersionUID = -473405811908397640L;
	
	protected ApplicationResources appResources;
	
	public ShowBorrowerSearchView(ApplicationResources appResources) {
		this.appResources = appResources; 
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		MessageBoxUtil.showInformation("This feature is not implemented.");
	}
}
