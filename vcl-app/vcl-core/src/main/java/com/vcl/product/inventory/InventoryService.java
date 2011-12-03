package com.vcl.product.inventory;

import java.util.List;

public interface InventoryService {

	Inventory persist(Inventory i);

	void remove(Inventory i);

	Inventory findByIndexNo(String indexNo);

	List<Inventory> findAllPaged(int firstResult, int maxResult);

}