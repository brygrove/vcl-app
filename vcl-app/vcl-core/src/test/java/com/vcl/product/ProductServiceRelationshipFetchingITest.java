package com.vcl.product;

import static com.vcl.product.ProductRelationshipFetchType.CHECK_IN_OUT;
import static com.vcl.product.ProductRelationshipFetchType.INVENTORY;
import static com.vcl.product.ProductRelationshipFetchType.LOST_DAMAGED_STOLEN;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vcl.IntegrationTestContext;
import com.vcl.product.category.Category;
import com.vcl.product.category.CategoryServiceSteps;
import com.vcl.product.inventory.Inventory;
import com.vcl.product.inventory.InventoryServiceSteps;
import com.vcl.product.lostdamagedstolen.LostDamagedStolen;
import com.vcl.product.lostdamagedstolen.LostDamagedStolenServiceSteps;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceRelationshipFetchingITest extends IntegrationTestContext {
	
	@Inject private ProductService prodService;
	@Inject private ProductServiceSteps prodSteps;
	@Inject private InventoryServiceSteps inventorySteps;
	@Inject private CategoryServiceSteps categorySteps;
	@Inject private LostDamagedStolenServiceSteps ldsSteps;
	
	@Test
	public void testCreateAndLoadProductRelationships() {
		
		String indexNo = "BOOK 1";
		
		Category category = categorySteps.createCategory("BIBLE");
		prodSteps.createProduct(indexNo, category);
		
		Product p = prodService.findByIndexNo(indexNo);
		Inventory i = inventorySteps.createInventory(indexNo);
		p.setInventory(i);

		LostDamagedStolen lds = ldsSteps.createLostDamagedStolen(indexNo, "Damaged can still be rented out though");
		p.setLostdamagedstolen(lds);

		prodService.merge(p);
		
		Assert.assertTrue(prodService.findAllPaged(0, 20).size() == 1);
		
		p = prodSteps.findByIndexNo(indexNo, INVENTORY, LOST_DAMAGED_STOLEN, CHECK_IN_OUT);
		Assert.assertNotNull(p.getInventory());
		Assert.assertNotNull(p.getLostdamagedstolen());
		
		inventorySteps.remove(i);
		ldsSteps.remove(lds);
		
		p = prodSteps.findByIndexNo(indexNo, INVENTORY, LOST_DAMAGED_STOLEN, CHECK_IN_OUT);
		Assert.assertNull(p.getInventory());
		Assert.assertNull(p.getLostdamagedstolen());
		
		Assert.assertTrue(prodService.findAllPaged(0, 20).size() == 1);
		
	}
}
