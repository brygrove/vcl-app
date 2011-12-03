package com.vcl.application.borrower;

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

public class BorrowerView extends ViewPart implements View<Borrower> {
	private DataBindingContext m_bindingContext;
	private BindingValidationToolkit bindingValidationToolkit;
	public static final String ID = "com.vcl.application.borrower.BorrowerView"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Group grpBorrower;
	private Label lblFirstName;
	private Label lblBorrowerId;
	private Label lblLastName;
	private Label lblPhoneNo;
	private Label lblCellNo;
	private Label lblAddress;
	private Label lblEmail;
	private Label validationErrorLabel;
	private Text txtAddress;
	private Text txtEmail;
	private Text txtCell;
	private Text txtPhone;
	private Text txtLastName;
	private Text txtFirstName;
	private Text txtBorrowerID;
	
	
	private WritableValue borrowerValue = new WritableValue(); 
	
	private Button btnCreate;
	private Button btnNew;
	
	private BorrowerController controller;
	private DataSearchControl dataSearchControl;
	private Button btnUpdate;
	private Button btnDelete;
	
	public BorrowerView() {
		setPartName("Borrower");
		setModel(new Borrower());
	}

	@Override
	public Borrower getModel() {
		return (Borrower) borrowerValue.getValue();
	}

	@Override
	public void setModel(Borrower model) {
		borrowerValue.setValue(model);
	}
	
	@Override
	public Class<Borrower> getModelClass() {
		return Borrower.class;
	}
	
	public void setController(BorrowerController controller) {
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
		
		this.grpBorrower = new Group(container, SWT.NONE);
		this.grpBorrower.setText("Borrower");
		this.grpBorrower.setBounds(10, 10, 574, 449);
		toolkit.adapt(this.grpBorrower);
		toolkit.paintBordersFor(this.grpBorrower);
		
		this.lblFirstName = new Label(this.grpBorrower, SWT.NONE);
		this.lblFirstName.setBounds(55, 106, 89, 13);
		toolkit.adapt(this.lblFirstName, true, true);
		this.lblFirstName.setText("First Name :");
		
		this.lblBorrowerId = new Label(this.grpBorrower, SWT.NONE);
		this.lblBorrowerId.setText("Borrower ID :");
		this.lblBorrowerId.setBounds(55, 81, 89, 13);
		toolkit.adapt(this.lblBorrowerId, true, true);
		
		this.lblLastName = new Label(this.grpBorrower, SWT.NONE);
		this.lblLastName.setText("Last Name :");
		this.lblLastName.setBounds(55, 133, 89, 13);
		toolkit.adapt(this.lblLastName, true, true);
		
		this.validationErrorLabel = new Label(this.grpBorrower, SWT.WRAP);
		this.validationErrorLabel.setText("Validation Status:");
		this.validationErrorLabel.setBounds(150, 301, 263, 78);
		toolkit.adapt(this.validationErrorLabel, true, true);
		
		this.lblPhoneNo = new Label(this.grpBorrower, SWT.NONE);
		this.lblPhoneNo.setText("Phone No:");
		this.lblPhoneNo.setBounds(55, 161, 89, 13);
		toolkit.adapt(this.lblPhoneNo, true, true);
		
		this.lblCellNo = new Label(this.grpBorrower, SWT.NONE);
		this.lblCellNo.setText("Cell No:");
		this.lblCellNo.setBounds(55, 185, 89, 13);
		toolkit.adapt(this.lblCellNo, true, true);
		
		this.lblAddress = new Label(this.grpBorrower, SWT.NONE);
		this.lblAddress.setText("Address:");
		this.lblAddress.setBounds(55, 235, 89, 13);
		toolkit.adapt(this.lblAddress, true, true);
		
		this.lblEmail = new Label(this.grpBorrower, SWT.NONE);
		this.lblEmail.setText("Email:");
		this.lblEmail.setBounds(55, 210, 89, 13);
		toolkit.adapt(this.lblEmail, true, true);
		
		this.txtAddress = toolkit.createText(this.grpBorrower, "New Text", SWT.WRAP | SWT.MULTI);
		this.txtAddress.setText("");
		this.txtAddress.setBounds(150, 232, 263, 53);
		
		this.txtEmail = toolkit.createText(this.grpBorrower, "New Text", SWT.NONE);
		this.txtEmail.setText("");
		this.txtEmail.setBounds(150, 207, 263, 19);
		
		this.txtCell = toolkit.createText(this.grpBorrower, "New Text", SWT.NONE);
		this.txtCell.setText("");
		this.txtCell.setBounds(150, 182, 149, 19);
		
		this.txtPhone = toolkit.createText(this.grpBorrower, "New Text", SWT.NONE);
		this.txtPhone.setText("");
		this.txtPhone.setBounds(150, 158, 149, 19);
		
		this.txtLastName = toolkit.createText(this.grpBorrower, "New Text", SWT.NONE);
		this.txtLastName.setText("");
		this.txtLastName.setBounds(150, 130, 263, 19);
		
		this.txtFirstName = toolkit.createText(this.grpBorrower, "New Text", SWT.NONE);
		this.txtFirstName.setText("");
		this.txtFirstName.setBounds(150, 103, 263, 19);
		
		this.txtBorrowerID = toolkit.createText(this.grpBorrower, "New Text", SWT.NONE);
		this.txtBorrowerID.setText("");
		this.txtBorrowerID.setBounds(150, 78, 203, 19);
		
		this.btnCreate = toolkit.createButton(this.grpBorrower, "Create", SWT.NONE);
		this.btnCreate.setBounds(197, 402, 68, 23);
		
		this.btnNew = toolkit.createButton(this.grpBorrower, "New", SWT.NONE);
		this.btnNew.setBounds(123, 402, 68, 23);
		
		this.dataSearchControl = new DataSearchControl(this.grpBorrower, SWT.NONE);
		this.dataSearchControl.setBounds(55, 23, 358, 27);
		toolkit.adapt(this.dataSearchControl);
		toolkit.paintBordersFor(this.dataSearchControl);
		this.dataSearchControl.setModelOwner(this);
		this.dataSearchControl.setEntityDataSearchProvider(new BorrowerDataSearchProvider());
		this.dataSearchControl.setTableDataModelDataBinding(BorrowerSearchFactory.createTableModelBinding());
		
		this.btnUpdate = toolkit.createButton(this.grpBorrower, "Update", SWT.NONE);
		this.btnUpdate.setBounds(271, 402, 68, 23);
		
		this.btnDelete = toolkit.createButton(this.grpBorrower, "Delete", SWT.NONE);
		this.btnDelete.setBounds(345, 402, 68, 23);
		
		this.grpBorrower.setTabList(new Control[]{this.txtBorrowerID, this.txtFirstName, this.txtLastName, this.txtPhone, this.txtCell, this.txtEmail, this.txtAddress});

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
		btnCreate.addSelectionListener(controller.createCreateBorrowerAction());
		btnUpdate.addSelectionListener(controller.createUpdateBorrowerAction());
		btnDelete.addSelectionListener(controller.createDeleteBorrowerAction());
		btnNew.addSelectionListener(controller.createNewBorrowerAction());
		
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
		
		bindingValidationToolkit = new BindingValidationToolkit(bindingContext);
		
		//
		IObservableValue txtBorrowerIDObserveTextObserveWidget = SWTObservables.observeText(txtBorrowerID, SWT.Modify);
		IObservableValue borrowerValueBorrowIDObserveDetailValue = PojoObservables.observeDetailValue(borrowerValue, "borrowID", String.class);
		bindingContext.bindValue(txtBorrowerIDObserveTextObserveWidget, borrowerValueBorrowIDObserveDetailValue, null, null);
		//
		IObservableValue txtFirstNameObserveTextObserveWidget = SWTObservables.observeText(txtFirstName, SWT.Modify);
		IObservableValue borrowerValueFirstNameObserveDetailValue = PojoObservables.observeDetailValue(borrowerValue, "firstName", String.class);
		bindingContext.bindValue(txtFirstNameObserveTextObserveWidget, borrowerValueFirstNameObserveDetailValue, bindingValidationToolkit.createRequiredStringValidation(txtFirstName, "First Name is required"), null);
		
		//
		IObservableValue txtLastNameObserveTextObserveWidget = SWTObservables.observeText(txtLastName, SWT.Modify);
		IObservableValue borrowerValueLastNameObserveDetailValue = PojoObservables.observeDetailValue(borrowerValue, "lastName", String.class);
		bindingContext.bindValue(txtLastNameObserveTextObserveWidget, borrowerValueLastNameObserveDetailValue, bindingValidationToolkit.createRequiredStringValidation(txtLastName, "Last Name is required"), null);
		
		//
		IObservableValue txtPhoneObserveTextObserveWidget = SWTObservables.observeText(txtPhone, SWT.Modify);
		IObservableValue borrowerValuePhoneNoObserveDetailValue = PojoObservables.observeDetailValue(borrowerValue, "phoneNo", String.class);
		bindingContext.bindValue(txtPhoneObserveTextObserveWidget, borrowerValuePhoneNoObserveDetailValue, null, null);
		//
		IObservableValue txtCellObserveTextObserveWidget = SWTObservables.observeText(txtCell, SWT.Modify);
		IObservableValue borrowerValueCellObserveDetailValue = PojoObservables.observeDetailValue(borrowerValue, "cell", String.class);
		bindingContext.bindValue(txtCellObserveTextObserveWidget, borrowerValueCellObserveDetailValue, null, null);
		//
		IObservableValue txtEmailObserveTextObserveWidget = SWTObservables.observeText(txtEmail, SWT.Modify);
		IObservableValue borrowerValueEmailObserveDetailValue = PojoObservables.observeDetailValue(borrowerValue, "email", String.class);
		bindingContext.bindValue(txtEmailObserveTextObserveWidget, borrowerValueEmailObserveDetailValue, null, null);
		//
		IObservableValue txtAddressObserveTextObserveWidget = SWTObservables.observeText(txtAddress, SWT.Modify);
		IObservableValue borrowerValueAddressObserveDetailValue = PojoObservables.observeDetailValue(borrowerValue, "address", String.class);
		bindingContext.bindValue(txtAddressObserveTextObserveWidget, borrowerValueAddressObserveDetailValue, null, null);
		
		bindingContext.bindValue(SWTObservables.observeText(validationErrorLabel),
				bindingValidationToolkit.createAggregateValidationStatus(), null, null);
		
		//
		return bindingContext;
	}

}
