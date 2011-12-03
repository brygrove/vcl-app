package com.vcl.product.category;

import javax.inject.Inject;

import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.vcl.product.ProductServiceSteps;

@Component
public class CategoryServiceSteps {

	@Inject private CategoryService catService;
	@Inject private ProductServiceSteps prodSteps; 
	
	public Category createCategory(String catNo) {
		Category c = new Category();
		c.setCatNo(catNo);
		return catService.persist(c);
	}
	
	public void assertProductCountByCategory(String catNo, int expectedCount) {
		Assert.assertEquals(expectedCount, prodSteps.findByCategory(catNo).size());
	}
}
