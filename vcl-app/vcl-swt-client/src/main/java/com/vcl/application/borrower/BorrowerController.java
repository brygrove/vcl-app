package com.vcl.application.borrower;

import com.vcl.application.action.entity.CreateEntityAction;
import com.vcl.application.action.entity.DeleteEntityAction;
import com.vcl.application.action.entity.EntityNewAction;
import com.vcl.application.action.entity.SaveEntityAction;
import com.vcl.application.action.entity.UpdateEntityAction;
import com.vcl.application.mvc.BaseController;
import com.vcl.application.mvc.ModelOwner;
import com.vcl.borrower.Borrower;
import com.vcl.borrower.BorrowerService;
import com.vcl.client.VclClient;

public class BorrowerController extends BaseController<BorrowerView, Borrower> {
	
	private static final String BORROW_ID = "borrowID";
	
	private BorrowerService borrowerService = VclClient.getServiceLocator().getBorrowerService();
	
	public BorrowerController() {
	}
	
	public CreateBorrowerAction createCreateBorrowerAction() {
		return new CreateBorrowerAction(getModelOwner());
	}

	public UpdateBorrowerAction createUpdateBorrowerAction() {
		return new UpdateBorrowerAction(getModelOwner());
	}

	public EntityNewAction<Borrower> createNewBorrowerAction() {
		return new EntityNewAction<Borrower>(getModelOwner()); 
	}
	
	public DeleteBorrowerAction createDeleteBorrowerAction() {
		return new DeleteBorrowerAction(getModelOwner());
	}
	
	public SaveBorrowerAction createSaveBorrowerAction() {
		return new SaveBorrowerAction(getModelOwner());
	}
	
	class CreateBorrowerAction extends CreateEntityAction<Borrower> {
		
		public CreateBorrowerAction(ModelOwner<Borrower> modelOwner) {
			super(BORROW_ID, modelOwner);
		}

		@Override
		public boolean doesEntityExist() {
			return borrowerService.findById((String)getIdentifier()) != null;
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getBorrowID();
		}

		@Override
		protected Borrower doCreate() {
			return borrowerService.persist(getEntity());
		}
	}
	
	class DeleteBorrowerAction extends DeleteEntityAction<Borrower> {

		public DeleteBorrowerAction(ModelOwner<Borrower> entityModelOwner) {
			super(BORROW_ID, entityModelOwner);
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getBorrowID();
		}

		@Override
		public boolean doesEntityExist() {
			return borrowerService.findById((String)getIdentifier()) != null;
		}

		@Override
		protected void doDelete() {
			borrowerService.remove(getModel());
		}
	}
	
	class UpdateBorrowerAction extends UpdateEntityAction<Borrower> {

		public UpdateBorrowerAction(ModelOwner<Borrower> modelOwner) {
			super(BORROW_ID, modelOwner);
		}

		@Override
		public boolean doesEntityExist() {
			return borrowerService.findById((String)getIdentifier()) != null;
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getBorrowID();
		}
		
		@Override
		protected Borrower doUpdate() {
			return borrowerService.merge(getEntity());
		}
	}
	
	class SaveBorrowerAction extends SaveEntityAction<Borrower> {

		public SaveBorrowerAction(ModelOwner<Borrower> modelOwner) {
			super(BORROW_ID, modelOwner);
		}

		@Override
		protected Borrower doSave() {
			return borrowerService.merge(getEntity());
		}
	}

}
