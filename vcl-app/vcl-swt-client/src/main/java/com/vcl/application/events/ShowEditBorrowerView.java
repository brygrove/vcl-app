package com.vcl.application.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.vcl.application.ApplicationResources;
import com.vcl.application.borrower.BorrowerController;
import com.vcl.application.borrower.BorrowerView;

public class ShowEditBorrowerView extends SelectionAdapter {

	private static final long serialVersionUID = -473405811908397640L;
	
	protected ApplicationResources appResources;
	
	public ShowEditBorrowerView(ApplicationResources appResources) {
		this.appResources = appResources; 
	}
	
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		
		BorrowerView view = new BorrowerView();
		BorrowerController controller = new BorrowerController();
		view.setController(controller);
		appResources.addViewPartTab("Borrower", view);
		
	}
}
