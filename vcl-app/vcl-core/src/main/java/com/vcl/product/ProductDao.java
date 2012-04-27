package com.vcl.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vcl.dao.JpaDao;
import com.vcl.product.category.Category;
import com.vcl.util.jpa.HqlParamBuilder;

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
	
	private static final String AND = "AND ";
	private static final String OR = "OR ";
	
	private static final String FIND_BY_PRODUCT_SEARCH = 
		"SELECT P FROM Product p JOIN p.category c " + 
		"WHERE '1' = '1'  ";
	
	private static final String FILTER_BY_INDEXNO_KEYWORD = "( p.indexNo LIKE :indexNoKeyword ) ";
	private static final String FILTER_BY_TITLE_KEYWORD = "( p.title LIKE :titleKeyword OR  p.subTitle LIKE :titleKeyword ) ";
	private static final String FILTER_BY_AUTHOR_KEYWORD = "( p.author LIKE :authorKeyword ) ";
	private static final String FILTER_BY_ISBN_KEYWORD = "( p.isbn LIKE :isbnKeyword ) ";
	private static final String FILTER_BY_CATNO_KEYWORD = "( c.catNo LIKE :catNoKeyword ) ";
		
	private static final String FILTER_BY_KEYWORD = 
		FILTER_BY_INDEXNO_KEYWORD + OR + 
		FILTER_BY_TITLE_KEYWORD + OR + 
		FILTER_BY_AUTHOR_KEYWORD + OR +
		FILTER_BY_ISBN_KEYWORD + OR + 
		FILTER_BY_CATNO_KEYWORD;
	
	private static final String INDEXNO_KEYWORD = "indexNoKeyword";
	private static final String TITLE_KEYWORD = "titleKeyword";
	private static final String AUTHOR_KEYWORD = "authorKeyword";
	private static final String ISBN_KEYWORD = "isbnKeyword";
	private static final String CATNO_KEYWORD = "catNoKeyword";
	
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
	
	public List<Product> searchProducts(ProductSearchArg search) {
		String hql = FIND_BY_PRODUCT_SEARCH;
		HqlParamBuilder paramBuilder = new HqlParamBuilder();

		if (search.getKeyword() != null) {
			hql = hql.concat(AND).concat(FILTER_BY_KEYWORD);
			paramBuilder.addParam(INDEXNO_KEYWORD, search.getKeyword());
			paramBuilder.addParam(TITLE_KEYWORD, search.getKeyword());
			paramBuilder.addParam(AUTHOR_KEYWORD, search.getKeyword());
			paramBuilder.addParam(ISBN_KEYWORD, search.getKeyword());
			paramBuilder.addParam(CATNO_KEYWORD, search.getKeyword());
		}
		else {
			//TODO add specific multi keyword compound search. 
		}

		return findByHqlQueryPaged(hql, 
				paramBuilder.getParams(), 
				search.getFirstResult(), 
				search.getMaxResult());
	}
	
	
}
