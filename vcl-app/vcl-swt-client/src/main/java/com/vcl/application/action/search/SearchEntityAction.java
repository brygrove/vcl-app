package com.vcl.application.action.search;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.vcl.application.action.search.datatable.EntityTableDataSearch;
import com.vcl.borrower.Borrower;
import com.vcl.util.MessageBoxUtil;

@SuppressWarnings("rawtypes")
public class SearchEntityAction extends SelectionAdapter {
	
	private SearchEntityConfig searchEntityConfig;
	
	public SearchEntityAction(SearchEntityConfig searchEntityConfig) {
		this.searchEntityConfig = searchEntityConfig;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		doSearchAction();
	}
	
	@SuppressWarnings("unused")
	private EntityDataSearchProvider getSearchProvider() {
		return searchEntityConfig.getEntityDataSearchProvider();
	}
	
	public void doSearchAction() {
		EntityDataSearchRequest searchRequest; 
		try {
			searchRequest = EntityDataSearchRequest.createFromConfig(searchEntityConfig);
			EntityTableDataSearch search = new EntityTableDataSearch();
			search.searchEntity(searchRequest);
		}
		catch(Exception ex) {
			MessageBoxUtil.showError("Error while searching for data. Error: " + ex);
			ex.printStackTrace();
		}
	}
	
	public Object findEntity() {
		Borrower borrower = new Borrower();
		borrower.setBorrowID("Found");
		return borrower;
	}
}
