package com.vcl.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vcl.dao.JpaDao;
import com.vcl.product.category.Category;

@Repository
public class ProductDao extends JpaDao<Product, String> {
	
	private static final String FIND_INDEX_NOS = 
		"SELECT p.indexNo FROM Product p ";
	
	private static final String CHANGE_CATEGORY = 
		"UPDATE Product SET " + 
			"category = :categoryTo " + 
		"WHERE category = :categoryFrom ";
	
	private static final String FIND_BY_CATEGORY = 
		"SELECT p FROM Product p " + 
		"WHERE p.category.catNo = :catNo ";
	
	private static final String CATEGORY_FROM = "categoryFrom";
	
	private static final String CATEGORY_TO = "categoryTo";

	private static final String CAT_NO = "catNo";
	
	
	@SuppressWarnings("unchecked")
	public List<String> findAllIndexNos() {
		return createQuery(FIND_INDEX_NOS)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findIndexNos(int firstResult, int maxResult) {
		return createQueryPaged(FIND_INDEX_NOS, firstResult, maxResult)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryNum(String catNo, int firstResult, int maxResult) {
		return createQueryPaged(FIND_BY_CATEGORY, firstResult, maxResult)
			.setParameter(CAT_NO, catNo)
			.getResultList();
	}

	public int changeCategory(Category catFrom, Category catTo) {
		return createQuery(CHANGE_CATEGORY)
			.setParameter(CATEGORY_FROM, catFrom)
			.setParameter(CATEGORY_TO, catTo)
			.executeUpdate();
	}
	
	
}
