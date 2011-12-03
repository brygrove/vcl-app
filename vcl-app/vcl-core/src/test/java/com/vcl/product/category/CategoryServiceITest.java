package com.vcl.product.category;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.IntegrationTxnRollbackTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryServiceITest extends IntegrationTxnRollbackTestContext {
	
	@Inject private CategoryService catService;
	
	@Inject private CategoryServiceSteps catSteps;
	
	@Test
	public void testCrud() {
		
		catSteps.createCategory("TEST1");
		catSteps.createCategory("TEST2");
		catSteps.createCategory("TEST3");

		Assert.assertEquals(3, catService.findAllPaged(0, 20).size());
		Assert.assertEquals(3, catService.findIds(0, 20).size());
		Assert.assertEquals(3, catService.findAllIds().size());
		
		CategorySearchArg search = new CategorySearchArg();
		search.setKeyword("TEST%");
		
		Assert.assertEquals(3, catService.searchCategory(search).size());
		
		Category c = catService.findByCatNo("TEST1");
		catService.remove(c);
		
		Assert.assertTrue(catService.findAllPaged(0, 20).size() == 2);
		
	}
	
}
