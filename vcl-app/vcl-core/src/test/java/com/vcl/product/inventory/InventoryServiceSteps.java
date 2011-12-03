package com.vcl.product.inventory;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class InventoryServiceSteps {

	@Inject private InventoryService invService;
	
	public Inventory createInventory(String indexNo) {
		Inventory i = new Inventory();
		i.setIndexNo(indexNo);
		return invService.persist(i);
	}
	
	public void remove(Inventory i) {
		invService.remove(i);
	}
}
