package com.vcl.product;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.IntegrationTxnRollbackTestContext;
import com.vcl.product.category.Category;
import com.vcl.product.category.CategoryServiceSteps;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductServiceITest extends IntegrationTxnRollbackTestContext {
	
	@Inject private ProductService prodService;
	@Inject private ProductServiceSteps prodSteps;
	@Inject private CategoryServiceSteps categorySteps;

	@Test
	public void testCrud() {
		
		Category category = categorySteps.createCategory("BIBLE");
		
		prodSteps.createProduct("BOOK 1", category);
		prodSteps.createProduct("BOOK 2", category);
		prodSteps.createProduct("BOOK 3", category);

		Assert.assertTrue(prodService.findAllPaged(0, 20).size() == 3);
		Assert.assertTrue(prodService.findIndexNos(0, 20).size() == 3);
		Assert.assertTrue(prodService.findAllIndexNos().size() == 3);
		
		Product p = prodService.findByIndexNo("BOOK 1");
		p.setTitle("Sample Book");
		prodService.merge(p);
		
		Assert.assertTrue(prodService.findAllPaged(0, 20).size() == 3);
		
		prodService.remove(p);
		
		Assert.assertTrue(prodService.findAllPaged(0, 20).size() == 2);
	}

}
