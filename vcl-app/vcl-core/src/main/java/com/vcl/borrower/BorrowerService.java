package com.vcl.borrower;

import java.util.List;

public interface BorrowerService {

	Borrower persist(Borrower b);
	
	Borrower merge(Borrower b);
	
	void remove(Borrower b);

	Borrower findById(String borrowId);

	List<String> findAllIds();

	List<String> findIds(int firstResult, int maxResult);

	List<Borrower> findAllPaged(int firstResult, int maxResult);
	
	List<Borrower> searchBorrower(BorrowerSearchArg search);
}