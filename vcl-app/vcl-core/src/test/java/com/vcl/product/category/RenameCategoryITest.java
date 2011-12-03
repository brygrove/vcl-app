package com.vcl.product.category;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.IntegrationTestContext;
import com.vcl.product.ProductServiceSteps;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RenameCategoryITest extends IntegrationTestContext {
	
	@Inject private CategoryService catService;
	@Inject private ProductServiceSteps prodSteps;
	@Inject private CategoryServiceSteps catSteps;
	
	@Test
	public void testCrud() {
		
		Category category = catSteps.createCategory("TEST1");
		
		prodSteps.createProduct("BOOK 1", category);
		prodSteps.createProduct("BOOK 2", category);
		prodSteps.createProduct("BOOK 3", category);
		
		Assert.assertTrue(catService.findAllPaged(0, 20).size() == 1);
		
		int totalProdsChangedToNewCategory = catService.renameCategory("TEST1", "TEST2");
		
		Assert.assertTrue(totalProdsChangedToNewCategory == 3);
		
		catSteps.assertProductCountByCategory("TEST1", 0);
		catSteps.assertProductCountByCategory("TEST2", 3);
		
		Assert.assertTrue(catService.findAllPaged(0, 20).size() == 1);
		
	}
	
}
