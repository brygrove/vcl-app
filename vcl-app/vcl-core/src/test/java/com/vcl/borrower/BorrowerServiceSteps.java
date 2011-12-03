package com.vcl.borrower;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

@Component
public class BorrowerServiceSteps {

	@Inject private BorrowerService borrowerService;
	
	public Borrower createBorrower(String borrowId, String firstName, String lastName) {
		Borrower b = new Borrower();
		b.setBorrowID(borrowId);
		b.setFirstName(firstName);
		b.setLastName(lastName);
		b.setPhoneNo("");
		return borrowerService.persist(b);
	}
}
