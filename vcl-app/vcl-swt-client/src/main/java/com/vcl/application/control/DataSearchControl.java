package com.vcl.application.control;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.vcl.application.action.search.DialogConfig;
import com.vcl.application.action.search.EntityDataSearchProvider;
import com.vcl.application.action.search.SearchEntityAction;
import com.vcl.application.action.search.SearchEntityConfig;
import com.vcl.application.model.table.TableDataModelBinding;
import com.vcl.application.mvc.ModelOwner;

public class DataSearchControl extends Composite {
	
	@SuppressWarnings("unused")
	private DataBindingContext m_bindingContext;

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text txtSearch;
	private Button btnSearch;
	
	private static final String KEYWORD_TEXT_PROPERTY = "keywordText";
	
	private SearchModel searchModel;
	private SearchEntityConfig searchEntityConfig;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public DataSearchControl(Composite parent, int style) {
		super(parent, SWT.BORDER);
		
		searchModel = new SearchModel();
		
		searchEntityConfig = new SearchEntityConfig();
		searchEntityConfig.setSearchModel(searchModel);
		
		initialize();
	}

	@SuppressWarnings("rawtypes")
	public void setModelOwner(ModelOwner modelOwner) {
		searchModel.setModelOwner(modelOwner);
	}
	
	@SuppressWarnings("rawtypes")
	public void setEntityDataSearchProvider(EntityDataSearchProvider eds) {
		searchEntityConfig.setEntityDataSearchProvider(eds);
	}
	
	public void setTableDataModelDataBinding(TableDataModelBinding tableDataModelBinding) {
		searchEntityConfig.setTableModelDataBinding(tableDataModelBinding);
	}
	
	public void setDialogConfig(DialogConfig dlgcfg) {
		searchEntityConfig.setDialogConfig(dlgcfg);
	}
	
	public DialogConfig getDialogConfig() {
		return searchEntityConfig.getDialogConfig();
	}

	private void initialize() {
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolkit.dispose();
			}
		});
		this.toolkit.adapt(this);
		this.toolkit.paintBordersFor(this);
		setLayout(new FormLayout());
		
		this.txtSearch = toolkit.createText(this, "New Text", SWT.NONE);
		FormData fd_txtSearch = new FormData();
		fd_txtSearch.left = new FormAttachment(0, 2);
		fd_txtSearch.top = new FormAttachment(0, 2);
		this.txtSearch.setLayoutData(fd_txtSearch);
		this.txtSearch.setText("");
		
		this.btnSearch = toolkit.createButton(this, "Search", SWT.NONE);
		fd_txtSearch.right = new FormAttachment(this.btnSearch, -6);
		FormData fd_btnSearch = new FormData();
		fd_btnSearch.left = new FormAttachment(100, -73);
		fd_btnSearch.top = new FormAttachment(0);
		fd_btnSearch.right = new FormAttachment(100);
		this.btnSearch.setLayoutData(fd_btnSearch);
		
		createActions();
		m_bindingContext = initDataBindings();
	}

	private void createActions() {
		this.btnSearch.addSelectionListener(new SearchEntityAction(searchEntityConfig));
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue txtSearchObserveTextObserveWidget = SWTObservables.observeText(txtSearch, SWT.Modify);
		IObservableValue searchModelKeywordTextObserveValue = PojoObservables.observeValue(searchModel, KEYWORD_TEXT_PROPERTY);
		bindingContext.bindValue(txtSearchObserveTextObserveWidget, searchModelKeywordTextObserveValue, null, null);
		//
		return bindingContext;
	}
}
