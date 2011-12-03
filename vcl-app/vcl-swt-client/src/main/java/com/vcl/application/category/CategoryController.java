package com.vcl.application.category;

import com.vcl.application.action.entity.CreateEntityAction;
import com.vcl.application.action.entity.DeleteEntityAction;
import com.vcl.application.action.entity.EntityNewAction;
import com.vcl.application.action.entity.SaveEntityAction;
import com.vcl.application.action.entity.UpdateEntityAction;
import com.vcl.application.mvc.BaseController;
import com.vcl.application.mvc.ModelOwner;
import com.vcl.client.VclClient;
import com.vcl.product.category.Category;
import com.vcl.product.category.CategoryService;
import com.vcl.util.MessageBoxUtil;

public class CategoryController extends BaseController<CategoryView, Category> {
	
	private static final String CATEGORY_NO = "catNo";
	
	private CategoryService categoryService = VclClient.getServiceLocator().getCategoryService();
	
	public CategoryController() {
	}
	
	public CreateCategoryAction createCreateCategoryAction() {
		return new CreateCategoryAction(getModelOwner());
	}

	public RenameCategoryAction createRenameCategoryAction() {
		return new RenameCategoryAction(getModelOwner());
	}

	public EntityNewAction<Category> createNewCategoryAction() {
		return new EntityNewAction<Category>(getModelOwner()); 
	}
	
	public DeleteCategoryAction createDeleteCategoryAction() {
		return new DeleteCategoryAction(getModelOwner());
	}
	
	public SaveCategoryAction createSaveCategoryAction() {
		return new SaveCategoryAction(getModelOwner());
	}
	
	class CreateCategoryAction extends CreateEntityAction<Category> {
		
		public CreateCategoryAction(ModelOwner<Category> modelOwner) {
			super(CATEGORY_NO, modelOwner);
		}

		@Override
		public boolean doesEntityExist() {
			return categoryService.findByCatNo((String)getIdentifier()) != null;
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getCatNo();
		}

		@Override
		protected Category doCreate() {
			return categoryService.persist(getEntity());
		}
	}
	
	class DeleteCategoryAction extends DeleteEntityAction<Category> {

		public DeleteCategoryAction(ModelOwner<Category> entityModelOwner) {
			super(CATEGORY_NO, entityModelOwner);
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getCatNo();
		}

		@Override
		public boolean doesEntityExist() {
			return categoryService.findByCatNo((String)getIdentifier()) != null;
		}

		@Override
		protected void doDelete() {
			categoryService.remove(getModel());
		}
	}
	
	class RenameCategoryAction extends UpdateEntityAction<Category> {
		
		private String catChangeTo;
		
		public RenameCategoryAction(ModelOwner<Category> modelOwner) {
			super(CATEGORY_NO, modelOwner);
		}

		@Override
		public boolean doesEntityExist() {
			return categoryService.findByCatNo((String)getIdentifier()) != null;
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getCatNo();
		}
		
		@Override
		protected Category doUpdate() {
			Category category = getEntity();
			
			catChangeTo = MessageBoxUtil.showInputBox("Category To Change To");
			try {
				categoryService.renameCategory(getEntity().getCatNo(), catChangeTo);
				category = categoryService.findByCatNo(catChangeTo);
			}
			catch(Exception ex) {
				throw new RuntimeException("Failed to rename category from " 
						+ getEntity().getCatNo() + " to " + catChangeTo, ex);
			}
			
			return category;
		}
	}
	
	class SaveCategoryAction extends SaveEntityAction<Category> {

		public SaveCategoryAction(ModelOwner<Category> modelOwner) {
			super(CATEGORY_NO, modelOwner);
		}

		@Override
		protected Category doSave() {
			return categoryService.persist(getEntity());
		}
	}

}
