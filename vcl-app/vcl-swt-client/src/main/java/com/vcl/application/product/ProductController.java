package com.vcl.application.product;

import com.vcl.application.action.entity.CreateEntityAction;
import com.vcl.application.action.entity.DeleteEntityAction;
import com.vcl.application.action.entity.EntityNewAction;
import com.vcl.application.action.entity.SaveEntityAction;
import com.vcl.application.action.entity.UpdateEntityAction;
import com.vcl.application.mvc.BaseController;
import com.vcl.application.mvc.ModelOwner;
import com.vcl.client.VclClient;
import com.vcl.product.Product;
import com.vcl.product.ProductService;

public class ProductController extends BaseController<ProductView, Product> {
	
	private static final String INDEX_NO = "indexNo";
	
	private ProductService productService = VclClient.getServiceLocator().getProductService();
	
	public ProductController() {
	}
	
	public CreateProductAction createCreateProductAction() {
		return new CreateProductAction(getModelOwner());
	}

	public UpdateProductAction createUpdateProductAction() {
		return new UpdateProductAction(getModelOwner());
	}

	public EntityNewAction<Product> createNewProductAction() {
		return new EntityNewAction<Product>(getModelOwner()); 
	}
	
	public DeleteProductAction createDeleteProductAction() {
		return new DeleteProductAction(getModelOwner());
	}
	
	public SaveProductAction createSaveProductAction() {
		return new SaveProductAction(getModelOwner());
	}
	
	class CreateProductAction extends CreateEntityAction<Product> {
		
		public CreateProductAction(ModelOwner<Product> modelOwner) {
			super(INDEX_NO, modelOwner);
		}

		@Override
		public boolean doesEntityExist() {
			return productService.findByIndexNo((String)getIdentifier()) != null;
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getIndexNo();
		}

		@Override
		protected Product doCreate() {
			return productService.persist(getEntity());
		}
	}
	
	class DeleteProductAction extends DeleteEntityAction<Product> {

		public DeleteProductAction(ModelOwner<Product> entityModelOwner) {
			super(INDEX_NO, entityModelOwner);
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getIndexNo();
		}

		@Override
		public boolean doesEntityExist() {
			return productService.findByIndexNo((String)getIdentifier()) != null;
		}

		@Override
		protected void doDelete() {
			productService.remove(getModel());
		}
	}
	
	class UpdateProductAction extends UpdateEntityAction<Product> {

		public UpdateProductAction(ModelOwner<Product> modelOwner) {
			super(INDEX_NO, modelOwner);
		}

		@Override
		public boolean doesEntityExist() {
			return productService.findByIndexNo((String)getIdentifier()) != null;
		}

		@Override
		public Object getIdentifier() {
			return getEntity().getIndexNo();
		}
		
		@Override
		protected Product doUpdate() {
			return productService.merge(getEntity());
		}
	}
	
	class SaveProductAction extends SaveEntityAction<Product> {

		public SaveProductAction(ModelOwner<Product> modelOwner) {
			super(INDEX_NO, modelOwner);
		}

		@Override
		protected Product doSave() {
			return productService.merge(getEntity());
		}
	}

}
