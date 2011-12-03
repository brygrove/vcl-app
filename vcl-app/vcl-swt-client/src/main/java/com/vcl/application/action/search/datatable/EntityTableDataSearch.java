package com.vcl.application.action.search.datatable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import com.vcl.application.VclManagerApplication;
import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.control.SearchModel;
import com.vcl.application.model.dialog.EntityTableDialog;

public class EntityTableDataSearch {

	private EntityTableDialog entityTableDialog;
	
	private static final String SEARCH = " Search";
	
	private static final String PLEASE_SELECT_A = "Select a ";
	
	private static final String END_OF_SELECTION_INSTRUCTIONS = 
		" below and double click or click the Ok button.";
	
	private Shell dialogParent;

	public Shell getDialogParentOrUseDefault() {
		if (dialogParent == null) {
			dialogParent = VclManagerApplication.getInstance().getWindowHandle();
		}
		return dialogParent;
	}

	public void setDialogParent(Shell dialogParent) {
		this.dialogParent = dialogParent;
	}

	public void searchEntity(EntityDataSearchRequest searchRequest) {
		String title = getTitle(searchRequest.getSearchModel());
		String message = getMessage(searchRequest.getSearchModel());
		
		entityTableDialog = new EntityTableDialog(getDialogParentOrUseDefault(), SWT.DIALOG_TRIM);
		entityTableDialog.setText(title);
		entityTableDialog.setSearchRequest(searchRequest);
		Object resultResult = entityTableDialog.open();
		
	}
	
	private String getMessage(SearchModel searchModel) {
		return PLEASE_SELECT_A
			.concat(getModelClassName(searchModel))
			.concat(END_OF_SELECTION_INSTRUCTIONS);
	}

	private String getModelClassName(SearchModel searchModel) {
		return searchModel.getModelOwner().getModelClass().getSimpleName();
	}
	
	private String getTitle(SearchModel searchModel) {
		return new StringBuilder()
			.append(getModelClassName(searchModel))
			.append(SEARCH)	
			.toString();
	}
	
}
