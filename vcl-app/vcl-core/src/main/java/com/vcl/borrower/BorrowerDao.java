package com.vcl.borrower;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vcl.dao.JpaDao;
import com.vcl.util.jpa.HqlParamBuilder;

@Repository
public class BorrowerDao extends JpaDao<Borrower, String> {

	private static final String AND = "AND ";
	private static final String OR = "OR ";
	
	private static final String FIND_BORROW_IDS = "SELECT b.borrowID FROM Borrower b ";

	private static final String FIND_BY_BORROWER_SEARCH = "SELECT b FROM Borrower b " + "WHERE '1' = '1' ";

	private static final String FILTER_BY_NAME_KEYWORD = "( b.firstName LIKE :nameKeyword OR b.lastName LIKE :nameKeyword ) ";

	private static final String FILTER_BY_BORROWID_KEYWORD = "b.borrowID LIKE :borrowIdKeyword ";

	private static final String FILTER_BY_PHONE_KEYWORD = "( b.phoneNo LIKE :phoneKeyword OR b.cell LIKE :phoneKeyword) ";

	private static final String FILTER_BY_KEYWORD = 
		FILTER_BY_BORROWID_KEYWORD + OR + 
		FILTER_BY_NAME_KEYWORD + OR + 
		FILTER_BY_PHONE_KEYWORD;
	
	private static final String BORROW_ID_KEYWORD = "borrowIdKeyword";
	private static final String NAME_KEYWORD = "nameKeyword";
	private static final String PHONE_KEYWORD = "phoneKeyword";
	
	@SuppressWarnings("unchecked")
	public List<String> findAllIds() {
		return createQuery(FIND_BORROW_IDS)
			.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> findIds(int firstResult, int maxResult) {
		return createQueryPaged(FIND_BORROW_IDS, firstResult, maxResult)
			.getResultList();
	}

	public List<Borrower> searchBorrower(BorrowerSearchArg search) {
		String hql = FIND_BY_BORROWER_SEARCH;
		HqlParamBuilder paramBuilder = new HqlParamBuilder();

		if (search.getKeyword() != null) {
			hql = hql.concat(AND).concat(FILTER_BY_KEYWORD);
			paramBuilder.addParam(BORROW_ID_KEYWORD, search.getKeyword());
			paramBuilder.addParam(NAME_KEYWORD, search.getKeyword());
			paramBuilder.addParam(PHONE_KEYWORD, search.getKeyword());
		}
		else {
			if (search.getBorrowIdKeyword() != null) {
				hql = hql.concat(AND).concat(FILTER_BY_BORROWID_KEYWORD);
				paramBuilder.addParam(BORROW_ID_KEYWORD, search.getBorrowIdKeyword());
			}
			
			if (search.getNameKeyword() != null) {
				hql = hql.concat(AND).concat(FILTER_BY_NAME_KEYWORD);
				paramBuilder.addParam(NAME_KEYWORD, search.getNameKeyword());
			}
			
			if (search.getPhoneNumKeyword() != null) {
				hql = hql.concat(AND).concat(FILTER_BY_PHONE_KEYWORD);
				paramBuilder.addParam(PHONE_KEYWORD, search.getPhoneNumKeyword());
			}
		}

		return findByHqlQueryPaged(hql, 
				paramBuilder.getParams(), 
				search.getFirstResult(), 
				search.getMaxResult());
	}

}
