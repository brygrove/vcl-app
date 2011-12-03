package com.vcl.product.lostdamagedstolen;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.IntegrationTxnRollbackTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LostDamagedStolenServiceITest extends IntegrationTxnRollbackTestContext {
	
	@Inject private LostDamagedStolenService ldsService;
	
	@Inject private LostDamagedStolenServiceSteps invSteps;
	
	@Test
	public void testCrud() {
		
		invSteps.createLostDamagedStolen("TEST1", "Lost");
		invSteps.createLostDamagedStolen("TEST2", "Damaged");
		invSteps.createLostDamagedStolen("TEST3", "Stolen");

		Assert.assertTrue(ldsService.findAllPaged(0, 20).size() == 3);
		
		LostDamagedStolen l = ldsService.findByIndexNo("TEST1");
		ldsService.remove(l);
		
		Assert.assertTrue(ldsService.findAllPaged(0, 20).size() == 2);
		
	}
	
}
