package com.vcl.application.product;

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
import com.vcl.application.action.search.product.ProductDataSearchProvider;
import com.vcl.application.action.search.product.ProductSearchFactory;
import com.vcl.application.control.DataSearchControl;
import com.vcl.application.mvc.View;
import com.vcl.application.validation.toolkit.BindingValidationToolkit;
import com.vcl.borrower.Borrower;
import com.vcl.product.Product;

public class ProductView extends ViewPart implements View<Product> {
	private DataBindingContext m_bindingContext;
	private BindingValidationToolkit bindingValidationToolkit;
	public static final String ID = "com.vcl.application.product.ProductView"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Group grpMain;
	private Label lblIndexNo;
	private Label validationErrorLabel;
	private Text txtIndexNo;
	
	
	private WritableValue productValue = new WritableValue(); 
	
	private Button btnCreate;
	private Button btnNew;
	
	private ProductController controller;
	private DataSearchControl dataSearchControl;
	private Button btnUpdate;
	private Button btnDelete;
	private Label lblTitle;
	private Text txtTitle;
	private Label lblSubTitle;
	private Text txtSubTitle;
	
	public ProductView() {
		setPartName("Product");
		setModel(new Product());
	}

	@Override
	public Product getModel() {
		return (Product) productValue.getValue();
	}

	@Override
	public void setModel(Product model) {
		productValue.setValue(model);
	}
	
	@Override
	public Class<Product> getModelClass() {
		return Product.class;
	}
	
	public void setController(ProductController controller) {
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
		
		this.grpMain = new Group(container, SWT.NONE);
		this.grpMain.setText("Product");
		this.grpMain.setBounds(10, 10, 574, 449);
		toolkit.adapt(this.grpMain);
		toolkit.paintBordersFor(this.grpMain);
		
		this.lblIndexNo = new Label(this.grpMain, SWT.NONE);
		this.lblIndexNo.setText("Index No :");
		this.lblIndexNo.setBounds(55, 59, 89, 13);
		toolkit.adapt(this.lblIndexNo, true, true);
		
		this.validationErrorLabel = new Label(this.grpMain, SWT.WRAP);
		this.validationErrorLabel.setText("Validation Status:");
		this.validationErrorLabel.setBounds(150, 301, 263, 78);
		toolkit.adapt(this.validationErrorLabel, true, true);
		
		this.txtIndexNo = toolkit.createText(this.grpMain, "New Text", SWT.NONE);
		this.txtIndexNo.setText("");
		this.txtIndexNo.setBounds(150, 56, 203, 19);
		
		this.btnCreate = toolkit.createButton(this.grpMain, "Create", SWT.NONE);
		this.btnCreate.setBounds(197, 402, 68, 23);
		
		this.btnNew = toolkit.createButton(this.grpMain, "New", SWT.NONE);
		this.btnNew.setBounds(123, 402, 68, 23);
		
		this.dataSearchControl = new DataSearchControl(this.grpMain, SWT.NONE);
		this.dataSearchControl.setBounds(55, 23, 358, 27);
		toolkit.adapt(this.dataSearchControl);
		toolkit.paintBordersFor(this.dataSearchControl);
		this.dataSearchControl.setModelOwner(this);
		this.dataSearchControl.setEntityDataSearchProvider(new ProductDataSearchProvider());
		this.dataSearchControl.setTableDataModelDataBinding(ProductSearchFactory.createTableModelBinding());
		
		this.btnUpdate = toolkit.createButton(this.grpMain, "Update", SWT.NONE);
		this.btnUpdate.setBounds(271, 402, 68, 23);
		
		this.btnDelete = toolkit.createButton(this.grpMain, "Delete", SWT.NONE);
		this.btnDelete.setBounds(345, 402, 68, 23);
		
		this.lblTitle = new Label(this.grpMain, SWT.NONE);
		this.lblTitle.setText("Title :");
		this.lblTitle.setBounds(55, 81, 89, 13);
		toolkit.adapt(this.lblTitle, true, true);
		
		this.txtTitle = new Text(this.grpMain, SWT.BORDER);
		this.txtTitle.setBounds(150, 78, 329, 19);
		toolkit.adapt(this.txtTitle, true, true);
		
		this.lblSubTitle = new Label(this.grpMain, SWT.NONE);
		this.lblSubTitle.setText("Sub Title :");
		this.lblSubTitle.setBounds(55, 106, 89, 13);
		toolkit.adapt(this.lblSubTitle, true, true);
		
		this.txtSubTitle = new Text(this.grpMain, SWT.BORDER);
		this.txtSubTitle.setBounds(150, 103, 329, 19);
		toolkit.adapt(this.txtSubTitle, true, true);
		this.grpMain.setTabList(new Control[]{this.txtIndexNo});

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
		btnCreate.addSelectionListener(controller.createCreateProductAction());
		btnUpdate.addSelectionListener(controller.createUpdateProductAction());
		btnDelete.addSelectionListener(controller.createDeleteProductAction());
		btnNew.addSelectionListener(controller.createNewProductAction());
		
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
		IObservableValue txtIDObserveTextObserveWidget = SWTObservables.observeText(txtIndexNo, SWT.Modify);
		IObservableValue produtValueIDObserveDetailValue = PojoObservables.observeDetailValue(productValue, "indexNo", String.class);
		bindingContext.bindValue(txtIDObserveTextObserveWidget, produtValueIDObserveDetailValue, null, null);
		//
		IObservableValue txtTitleObserveTextObserveWidget_1 = SWTObservables.observeText(txtTitle, SWT.Modify);
		IObservableValue productValueTitleObserveDetailValue = PojoObservables.observeDetailValue(productValue, "title", String.class);
		bindingContext.bindValue(txtTitleObserveTextObserveWidget_1, productValueTitleObserveDetailValue, null, null);
		//
		IObservableValue txtSubTitleObserveTextObserveWidget = SWTObservables.observeText(txtSubTitle, SWT.Modify);
		IObservableValue productValueSubTitleObserveDetailValue = PojoObservables.observeDetailValue(productValue, "subTitle", String.class);
		bindingContext.bindValue(txtSubTitleObserveTextObserveWidget, productValueSubTitleObserveDetailValue, null, null);
		//
		return bindingContext;
	}
}
