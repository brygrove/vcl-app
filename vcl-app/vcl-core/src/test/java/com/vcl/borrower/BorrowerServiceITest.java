package com.vcl.borrower;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.IntegrationTxnRollbackTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BorrowerServiceITest extends IntegrationTxnRollbackTestContext {
	
	@Inject private BorrowerService borrowerService;
	
	@Inject private BorrowerServiceSteps borrowerSteps;
	
	@Test
	public void testCrud() {
		
		borrowerSteps.createBorrower("TEST1", "Bob", "Jacobs");
		borrowerSteps.createBorrower("TEST2", "Rick", "Jacobs");
		borrowerSteps.createBorrower("TEST3", "Bill", "Jacobs");

		Assert.assertTrue(borrowerService.findAllPaged(0, 20).size() == 3);
		Assert.assertTrue(borrowerService.findIds(0, 20).size() == 3);
		Assert.assertTrue(borrowerService.findAllIds().size() == 3);
		
		BorrowerSearchArg search = new BorrowerSearchArg();
		search.setNameKeyword("Bob%");
		search.setBorrowIdKeyword("TEST1%");
		search.setPhoneNumKeyword("%");
		Assert.assertEquals(1, borrowerService.searchBorrower(search).size());
		
		search = new BorrowerSearchArg();
		search.setNameKeyword("Jac%");
		Assert.assertEquals(3, borrowerService.searchBorrower(search).size());
		
		search = new BorrowerSearchArg();
		search.setKeyword("Jac%");
		Assert.assertEquals(3, borrowerService.searchBorrower(search).size());
		
		search = new BorrowerSearchArg();
		search.setKeyword("TEST%");
		Assert.assertEquals(3, borrowerService.searchBorrower(search).size());
		
		
		Borrower b = borrowerService.findById("TEST1");
		borrowerService.remove(b);
		
		Assert.assertTrue(borrowerService.findAllPaged(0, 20).size() == 2);
		
	}
	
}
