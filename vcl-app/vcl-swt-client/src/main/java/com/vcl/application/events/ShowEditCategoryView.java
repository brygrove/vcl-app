package com.vcl.application.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.vcl.application.ApplicationResources;
import com.vcl.application.category.CategoryController;
import com.vcl.application.category.CategoryView;

public class ShowEditCategoryView extends SelectionAdapter {

	private static final long serialVersionUID = -473405811908397640L;
	
	protected ApplicationResources appResources;
	
	public ShowEditCategoryView(ApplicationResources appResources) {
		this.appResources = appResources; 
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		CategoryView view = new CategoryView();
		CategoryController controller = new CategoryController();
		view.setController(controller);
		appResources.addViewPartTab("Category", view);
	}
}
