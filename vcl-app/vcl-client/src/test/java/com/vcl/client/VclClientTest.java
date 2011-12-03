package com.vcl.client;

import org.junit.Test;

import com.vcl.borrower.BorrowerService;
import com.vcl.product.ProductService;
import com.vcl.product.category.CategoryService;
import com.vcl.product.inventory.InventoryService;
import com.vcl.product.lostdamagedstolen.LostDamagedStolenService;

public class VclClientTest {
	
	@Test
	public void testFindAllProductsPaged() {
		ProductService service = VclClient.getServiceLocator().getProductService();
		service.findAllPaged(0, 50);
	}
	
	@Test
	public void testFindAllCategoriesPaged() {
		CategoryService service = VclClient.getServiceLocator().getCategoryService();
		service.findAllPaged(0, 50);
	}
	
	@Test
	public void testFindAllBorrowersPaged() {
		BorrowerService service = VclClient.getServiceLocator().getBorrowerService();
		service.findAllPaged(0, 50);
	}
	
	@Test
	public void testFindAllInventoryPaged() {
		InventoryService service = VclClient.getServiceLocator().getInventoryService();
		service.findAllPaged(0, 50);
	}
	
	@Test
	public void testFindAllLostDamagedStolenPaged() {
		LostDamagedStolenService service = VclClient.getServiceLocator().getLostDamagedStolenService();
		service.findAllPaged(0, 50);
	}
	
}
