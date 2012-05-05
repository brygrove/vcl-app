package com.vcl.application.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.vcl.application.ApplicationResources;
import com.vcl.application.product.ProductController;
import com.vcl.application.product.ProductView;

public class ShowEditProductView extends SelectionAdapter {

	private static final long serialVersionUID = -473405811908397640L;
	
	protected ApplicationResources appResources;
	
	public ShowEditProductView(ApplicationResources appResources) {
		this.appResources = appResources; 
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		ProductView view = new ProductView();
		ProductController controller = new ProductController();
		view.setController(controller);
		appResources.addViewPartTab("Product", view);
	}
}
