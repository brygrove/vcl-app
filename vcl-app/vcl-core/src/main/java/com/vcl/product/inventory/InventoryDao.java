package com.vcl.product.inventory;

import org.springframework.stereotype.Repository;

import com.vcl.dao.JpaDao;

@Repository
public class InventoryDao extends JpaDao<Inventory, String> {
	
}
