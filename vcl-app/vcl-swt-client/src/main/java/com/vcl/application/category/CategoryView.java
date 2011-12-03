package com.vcl.application.category;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import com.vcl.application.action.search.borrower.BorrowerDataSearchProvider;
import com.vcl.application.action.search.borrower.BorrowerSearchFactory;
import com.vcl.application.control.DataSearchControl;
import com.vcl.application.mvc.View;
import com.vcl.application.validation.toolkit.BindingValidationToolkit;
import com.vcl.borrower.Borrower;
import com.vcl.product.category.Category;

public class CategoryView extends ViewPart implements View<Category> {
	private DataBindingContext m_bindingContext;
	private BindingValidationToolkit bindingValidationToolkit;
	public static final String ID = "com.vcl.application.borrower.BorrowerView"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Group grpBorrower;
	private Label lblBorrowerId;
	private Label validationErrorLabel;
	private Text txtBorrowerID;
	
	
	private WritableValue categoryValue = new WritableValue(); 
	
	private Button btnCreate;
	private Button btnNew;
	
	private CategoryController controller;
	private DataSearchControl dataSearchControl;
	private Button btnUpdate;
	private Button btnDelete;
	
	public CategoryView() {
		setPartName("Category");
		setModel(new Category());
	}

	@Override
	public Category getModel() {
		return (Category) categoryValue.getValue();
	}

	@Override
	public void setModel(Category model) {
		categoryValue.setValue(model);
	}
	
	@Override
	public Class<Category> getModelClass() {
		return Category.class;
	}
	
	public void setController(CategoryController controller) {
		this.controller = controller;
		controller.setView(this);
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = this.toolkit.createComposite(parent, SWT.NONE);
		this.toolkit.paintBordersFor(container);
		//TODO 
		this.grpBorrower = new Group(container, SWT.NONE);
		this.grpBorrower.setText("Category");
		this.grpBorrower.setBounds(10, 10, 574, 296);
		toolkit.adapt(this.grpBorrower);
		toolkit.paintBordersFor(this.grpBorrower);
		
		this.lblBorrowerId = new Label(this.grpBorrower, SWT.NONE);
		this.lblBorrowerId.setText("Category :");
		this.lblBorrowerId.setBounds(24, 81, 60, 13);
		toolkit.adapt(this.lblBorrowerId, true, true);
		
		this.validationErrorLabel = new Label(this.grpBorrower, SWT.WRAP);
		this.validationErrorLabel.setText("Validation Status:");
		this.validationErrorLabel.setBounds(150, 133, 263, 78);
		toolkit.adapt(this.validationErrorLabel, true, true);
		
		this.txtBorrowerID = toolkit.createText(this.grpBorrower, "New Text", SWT.NONE);
		this.txtBorrowerID.setText("");
		this.txtBorrowerID.setBounds(90, 78, 373, 19);
		
		this.btnCreate = toolkit.createButton(this.grpBorrower, "Create", SWT.NONE);
		this.btnCreate.setBounds(200, 235, 68, 23);
		
		this.btnNew = toolkit.createButton(this.grpBorrower, "New", SWT.NONE);
		this.btnNew.setBounds(126, 235, 68, 23);
		
		this.dataSearchControl = new DataSearchControl(this.grpBorrower, SWT.NONE);
		this.dataSearchControl.setBounds(55, 23, 358, 27);
		toolkit.adapt(this.dataSearchControl);
		toolkit.paintBordersFor(this.dataSearchControl);
		this.dataSearchControl.setModelOwner(this);
		//TODO 
		this.dataSearchControl.setEntityDataSearchProvider(new BorrowerDataSearchProvider());
		this.dataSearchControl.setTableDataModelDataBinding(BorrowerSearchFactory.createTableModelBinding());
		
		this.btnUpdate = toolkit.createButton(this.grpBorrower, "Update", SWT.NONE);
		this.btnUpdate.setBounds(274, 235, 68, 23);
		
		this.btnDelete = toolkit.createButton(this.grpBorrower, "Delete", SWT.NONE);
		this.btnDelete.setBounds(348, 235, 68, 23);
		this.grpBorrower.setTabList(new Control[]{this.txtBorrowerID});

		createActions();
		m_bindingContext = initDataBindings();
		//initializeToolBar();
		//initializeMenu();
	}

	public void dispose() {
		this.toolkit.dispose();
		super.dispose();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		btnCreate.addSelectionListener(controller.createCreateCategoryAction());
		btnUpdate.addSelectionListener(controller.createUpdateCategoryAction());
		btnDelete.addSelectionListener(controller.createDeleteCategoryAction());
		btnNew.addSelectionListener(controller.createNewCategoryAction());
		
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue txtBorrowerIDObserveTextObserveWidget = SWTObservables.observeText(txtBorrowerID, SWT.Modify);
		IObservableValue borrowerValueBorrowIDObserveDetailValue = PojoObservables.observeDetailValue(categoryValue, "catNo", String.class);
		bindingContext.bindValue(txtBorrowerIDObserveTextObserveWidget, borrowerValueBorrowIDObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
