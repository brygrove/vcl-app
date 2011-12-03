package com.vcl.startup.borrower;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.vcl.borrower.Borrower;
import com.vcl.borrower.BorrowerService;
import com.vcl.startup.WebBootstrapWorkUnit;

@Component
public class LoadSampleBorrowersWorkUnit extends WebBootstrapWorkUnit {
	
	private String[][] borrowers = {
		new String[] {"001", "Bob", "Jones"},
		new String[] {"002", "Rick", "Jones"},
		new String[] {"003", "John", "Smith"},
		new String[] {"004", "Jacob", "Spence"},
	};
	
	@Inject
	private BorrowerService borrowerService;

	@Override
	public void executeWork() {
		for(String[] borrowerRow : borrowers) {
			createBorrower(borrowerRow);
		}
	}
	
	private Borrower createBorrower(String[] borrower) {
		Borrower b = new Borrower();
		b.setBorrowID(borrower[0]);
		b.setFirstName(borrower[1]);
		b.setLastName(borrower[2]);
		
		return borrowerService.persist(b);
	}

}
