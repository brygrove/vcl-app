package com.vcl.product;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDao dao;

	@Override
	public Product persist(Product p) {
		return dao.persist(p);
	}

	@Override
	public Product merge(Product p) {
		return dao.merge(p);
	}

	@Override
	public void remove(Product p) {
		dao.removeById(p.getIndexNo());
	}

	@Override
	public Product findByIndexNo(String indexNo) {
		return dao.findById(indexNo);
	}

	@Override
	public Product findByIndexNo(String indexNo, List<ProductRelationshipFetchType> fetchTypes) {
		Product p = findByIndexNo(indexNo);
		for (ProductRelationshipFetchType fetchType : fetchTypes) {
			loadProductRelationship(p, fetchType);
		}
		return p;
	}

	private void loadProductRelationship(Product p, ProductRelationshipFetchType fetchType) {
		switch (fetchType) {

		case INVENTORY:
			p.getInventory();
			break;

		case LOST_DAMAGED_STOLEN:
			p.getLostdamagedstolen();
			break;

		case CHECK_IN_OUT:
			p.getCheckinouts().size();
			break;
		}
	}

	@Override
	public List<String> findAllIndexNos() {
		return dao.findAllIndexNos();
	}

	@Override
	public List<String> findIndexNos(int firstResult, int maxResult) {
		return dao.findIndexNos(firstResult, maxResult);
	}

	@Override
	public List<Product> findAllPaged(int firstResult, int maxResult) {
		return dao.findAllPaged(firstResult, maxResult);
	}

	@Override
	public List<Product> findByCategoryNum(String catNo, int firstResult, int maxResult) {
		return dao.findByCategoryNum(catNo, firstResult, maxResult);
	}

	@Override
	public List<Product> searchProducts(ProductSearchArg prodSearch) {
		return dao.searchProducts(prodSearch);
	}

}
