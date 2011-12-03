package com.vcl.product.inventory;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Inject
	private InventoryDao dao;

	@Override
	public Inventory persist(Inventory i) {
		return dao.persist(i);
	}

	@Override
	public void remove(Inventory i) {
		dao.removeById(i.getIndexNo());
	}

	@Override
	public Inventory findByIndexNo(String indexNo) {
		return dao.findById(indexNo);
	}

	@Override
	public List<Inventory> findAllPaged(int firstResult, int maxResult) {
		return dao.findAllPaged(firstResult, maxResult);
	}

}
