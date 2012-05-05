package com.vcl.application.model.dialog;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.vcl.application.action.search.EntityDataSearchProvider;
import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.control.SearchModel;
import com.vcl.application.control.table.UITableBuilder;
import com.vcl.application.model.ModelObject;
import com.vcl.application.model.table.TableDataModelBinding;

public class EntityTableDialog extends Dialog {
	private DataBindingContext m_bindingContext;

	public static final int TABLE_WIDTH = 600;
	public static final int TABLE_HEIGHT = 360;
	public static final int DLG_WIDTH = 620;
	public static final int DLG_HEIGHT = 480;
	
	protected Object result;
	protected Shell shell;
	private Text txtSearch;
	private Label lblSearch;
	
	private UITableBuilder builder = null; 
	private EntityDataSearchRequest searchRequest;
	private SearchModel searchModel;
	private Table table;
	private TableViewer tableViewer;
	private Button btnSearch;
	
	private Object selectedEntity = null;
	private Button btnOk;
	private Button btnCancel;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EntityTableDialog(Shell parent, int style) {
		super(parent, style);
		setText("Entity Search");
		searchModel = new SearchModel();
	}
	
	public SearchModel getSearchModel() {
		return searchModel;
	}

	public EntityDataSearchRequest getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(EntityDataSearchRequest searchRequest) {
		this.searchRequest = searchRequest;
		this.searchModel = searchRequest.getSearchModel();
	}

	private TableDataModelBinding getTableDataModelBinding() {
		return searchRequest.getTableModelDataBinding();
	}

	private EntityDataSearchProvider<?> getEntityDataSearchProvider() {
		return searchRequest.getEntityDataSearchProvider();
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		initialize();
		refreshData();
		this.shell.open();
		this.shell.layout();
		Display display = getParent().getDisplay();
		while (!this.shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return this.result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void initialize() {
		this.shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		this.shell.setSize(DLG_WIDTH, DLG_HEIGHT);
		this.shell.setText(getText());
		
		this.txtSearch = new Text(this.shell, SWT.BORDER);
		this.txtSearch.setBounds(111, 7, 271, 19);
		
		this.lblSearch = new Label(this.shell, SWT.NONE);
		this.lblSearch.setBounds(10, 10, 103, 19);
		this.lblSearch.setText("Search");
		
		builder = new UITableBuilder();
		builder.setTableDataModelBinding(getTableDataModelBinding());
		builder.setParent(shell);
		
		this.btnSearch = new Button(this.shell, SWT.NONE);
		this.btnSearch.setBounds(386, 5, 78, 23);
		this.btnSearch.setText("Search");
		
		this.btnOk = new Button(this.shell, SWT.NONE);
		this.btnOk.setBounds(147, 415, 116, 23);
		this.btnOk.setText("OK");
		
		this.btnCancel = new Button(this.shell, SWT.NONE);
		this.btnCancel.setBounds(348, 415, 116, 23);
		this.btnCancel.setText("Cancel");
		this.table = builder.build();
		this.tableViewer = builder.getTableViewer();
		this.table.setBounds(10, 45, TABLE_WIDTH, TABLE_HEIGHT);
	
		m_bindingContext = initDataBindings();
		
		initEvents();
	}
	
	private void initEvents() {
		this.btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshData();
			}
		});
		
		this.txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// enter key
				if (e.keyCode == 13) {
					refreshData();
				}
			}
		});
		
		this.tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent e) {
				ISelection sel = e.getSelection();
				if (sel instanceof StructuredSelection) {
					Object o = ((StructuredSelection)sel).getFirstElement();
					ModelObject model = (ModelObject) o;
					if (model != null) {
						selectedEntity = model.getData();
					}
				}
			}
		});
		
		this.btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = Boolean.FALSE;
				shell.dispose();
			}
		});
		
		this.btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doEntitySelectAndDisposeDialog();
			}
		});
		
		this.tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent arg0) {
				doEntitySelectAndDisposeDialog();
			}
		});
		
		this.table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// enter key
				if (e.keyCode == 13) {
					doEntitySelectAndDisposeDialog();
				}
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	private void doEntitySelectAndDisposeDialog() {
		if (selectedEntity != null && tableViewer.getSelection() != null) {
			searchModel.getModelOwner().setModel(selectedEntity);
			result = Boolean.TRUE;
			shell.dispose();
		}
	}

	public void refreshData() {
		List<?> dataList = getEntityDataSearchProvider().searchEntity(searchRequest);
		getTableDataModelBinding().setDataList(dataList);
		builder.bind();
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue txtSearchObserveTextObserveWidget = SWTObservables.observeText(txtSearch, SWT.Modify);
		IObservableValue searchModelKeywordTextObserveValue = PojoObservables.observeValue(searchModel, "keywordText");
		bindingContext.bindValue(txtSearchObserveTextObserveWidget, searchModelKeywordTextObserveValue, null, null);
		//
		return bindingContext;
	}
}
