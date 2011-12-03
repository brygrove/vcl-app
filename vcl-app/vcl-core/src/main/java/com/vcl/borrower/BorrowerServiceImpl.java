package com.vcl.borrower;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BorrowerServiceImpl implements BorrowerService {
	
	@Inject
	private BorrowerDao dao;
	
	@Override
	public Borrower persist(Borrower b) {
		return dao.persist(b);
	}
	
	@Override
	public void remove(Borrower b) {
		dao.removeById(b.getBorrowID());
	}
	
	@Override
	public Borrower findById(String borrowId) {
		return dao.findById(borrowId);
	}
	
	@Override
	public List<String> findAllIds() {
		return dao.findAllIds();
	}
	
	@Override
	public List<String> findIds(int firstResult, int maxResult) {
		return dao.findIds(firstResult, maxResult);
	}
	
	@Override
	public List<Borrower> findAllPaged(int firstResult, int maxResult) {
		return dao.findAllPaged(firstResult, maxResult);
	}

	@Override
	public Borrower merge(Borrower b) {
		return dao.merge(b);
	}

	@Override
	public List<Borrower> searchBorrower(BorrowerSearchArg search) {
		return dao.searchBorrower(search);
	}
	
}
