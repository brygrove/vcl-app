package com.vcl.client;

import javax.inject.Inject;

import com.vcl.borrower.BorrowerService;
import com.vcl.product.ProductService;
import com.vcl.product.category.CategoryService;
import com.vcl.product.inventory.InventoryService;
import com.vcl.product.lostdamagedstolen.LostDamagedStolenService;

public class VclServiceLocator {
	
	@Inject private ProductService productService; 
	@Inject private BorrowerService borrowerService; 
	@Inject private CategoryService categoryService; 
	@Inject private InventoryService inventoryService; 
	@Inject private LostDamagedStolenService lostDamagedStolenService;
	
	public ProductService getProductService() {
		return productService;
	}

	public BorrowerService getBorrowerService() {
		return borrowerService;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public InventoryService getInventoryService() {
		return inventoryService;
	}

	public LostDamagedStolenService getLostDamagedStolenService() {
		return lostDamagedStolenService;
	}
	
}
