package com.vcl.product.inventory;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.IntegrationTxnRollbackTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class InventoryServiceITest extends IntegrationTxnRollbackTestContext {
	
	@Inject private InventoryService invService;
	
	@Inject private InventoryServiceSteps invSteps;
	
	@Test
	public void testCrud() {
		
		invSteps.createInventory("TEST1");
		invSteps.createInventory("TEST2");
		invSteps.createInventory("TEST3");

		Assert.assertTrue(invService.findAllPaged(0, 20).size() == 3);
		
		Inventory i = invService.findByIndexNo("TEST1");
		invService.remove(i);
		
		Assert.assertTrue(invService.findAllPaged(0, 20).size() == 2);
		
	}
	
}
