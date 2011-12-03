package com.vcl.product.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vcl.dao.JpaDao;

@Repository
public class CategoryDao extends JpaDao<Category, String> {
	
	private static final String FIND_CAT_NOS = 
		"SELECT c.catNo FROM Category c ";
	
	@SuppressWarnings("unchecked")
	public List<String> findAllIds() {
		return createQuery(FIND_CAT_NOS)
			.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findIds(int firstResult, int maxResult) {
		return createQueryPaged(FIND_CAT_NOS, firstResult, maxResult)
			.getResultList();
	}
	
	
}
