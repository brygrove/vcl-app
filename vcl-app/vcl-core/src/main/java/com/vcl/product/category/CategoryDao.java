package com.vcl.product.category;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vcl.dao.JpaDao;
import com.vcl.util.jpa.HqlParamBuilder;

@Repository
public class CategoryDao extends JpaDao<Category, String> {
	
	private static final String AND = "AND ";
	//private static final String OR = "OR ";
	
	private static final String FIND_BY_CATEGORY_SEARCH = "SELECT c FROM Category c " + "WHERE '1' = '1' ";

	private static final String FILTER_BY_CATNO_KEYWORD = "( c.catNo LIKE :catNoKeyword ) ";

	private static final String FILTER_BY_KEYWORD = 
		FILTER_BY_CATNO_KEYWORD;
	
	private static final String CATNO_KEYWORD = "catNoKeyword";
	
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

	public List<Category> searchCategory(CategorySearchArg search) {
		String hql = FIND_BY_CATEGORY_SEARCH;
		HqlParamBuilder paramBuilder = new HqlParamBuilder();

		if (search.getKeyword() != null) {
			hql = hql.concat(AND).concat(FILTER_BY_KEYWORD);
			paramBuilder.addParam(CATNO_KEYWORD, search.getKeyword());
		}

		return findByHqlQueryPaged(hql, 
				paramBuilder.getParams(), 
				search.getFirstResult(), 
				search.getMaxResult());
	}
	
	
}
