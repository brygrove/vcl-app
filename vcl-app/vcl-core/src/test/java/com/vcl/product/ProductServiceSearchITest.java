package com.vcl.product;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.vcl.IntegrationTxnRollbackTestContext;
import com.vcl.product.category.Category;
import com.vcl.product.category.CategoryServiceSteps;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ProductServiceSearchITest extends IntegrationTxnRollbackTestContext {
	
	@Inject private ProductService prodService;
	@Inject private ProductServiceSteps prodSteps;
	@Inject private CategoryServiceSteps categorySteps;

	@Before
	public void setup() {
		Category cat1 = categorySteps.createCategory("CAT1");
		Category cat2 = categorySteps.createCategory("CAT2");
		Category cat3 = categorySteps.createCategory("CAT3");
		
		prodSteps.createProduct(new ProductBuilder()
			.setIndexNo("A01234")
			.setCategory(cat1)
			.setTitle("Jesus Great I AM")
			.setSubTitle("Second Edition")
			.setAuthor("Martin Bardont")
			.setIsbn("11111-12345-33333-4444")
			.build());
		
		prodSteps.createProduct(new ProductBuilder()
			.setIndexNo("A01357")
			.setCategory(cat1)
			.setTitle("Jesus Son of Man")
			.setSubTitle("Fourth Edition")
			.setAuthor("Bardento Marios")
			.setIsbn("11111-22222-12345-4444")
			.build());
		
		
		prodSteps.createProduct(new ProductBuilder()
			.setIndexNo("A02468")
			.setCategory(cat2)
			.setTitle("Jesus King of Kings")
			.setSubTitle("First Edition")
			.setAuthor("Maria Calipso")
			.setIsbn("11111-22222-33333-12345")
			.build());
		
		prodSteps.createProduct(new ProductBuilder()
			.setIndexNo("A00110")
			.setCategory(cat3)
			.setTitle("Jesus Lord of Lords")
			.setSubTitle("First Edition")
			.setAuthor("Grace Barusto")
			.setIsbn("12345-22222-33333-4444")
			.build());
	}
	
	
	@Test
	public void testSearchProducts() {
		
		assertKeywordFindsProductCount(
				createSearch("Jesus King of Kings"), 1);
		
		assertKeywordFindsProductCount(
				createSearch("%Jesus%"), 4);
		
		assertKeywordFindsProductCount(
				createSearch("Second Edition"), 1);
		
		assertKeywordFindsProductCount(
				createSearch("%Edition%"), 4);
		
		assertKeywordFindsProductCount(
				createSearch("A01234"), 1);
		
		assertKeywordFindsProductCount(
				createSearch("%0%"), 4);
		
		assertKeywordFindsProductCount(
				createSearch("%%"), 4);
		
		assertKeywordFindsProductCount(
				createSearch("Martin Bardont"), 1);
		
		assertKeywordFindsProductCount(
				createSearch("%Mar%"), 3);
		
		assertKeywordFindsProductCount(
				createSearch("%Bar%"), 3);
		
		assertKeywordFindsProductCount(
				createSearch("%Maria%"), 1);
		
		assertKeywordFindsProductCount(
				createSearch("%12345%"), 4);
		
		assertKeywordFindsProductCount(
				createSearch("%-4444%"), 3);
		
		assertKeywordFindsProductCount(
				createSearch("12345-22222-33333-4444"), 1);
		
		assertKeywordFindsProductCount(
				createPagedSearch("%Jesus%", 0, 1), 1);
		
		assertKeywordFindsProductCount(
				createPagedSearch("%Jesus%", 0, 2), 2);
		
	}
	
	private ProductSearchArg createSearch(String keyword) {
		ProductSearchArg search = new ProductSearchArg();
		search.setKeyword(keyword);
		return search;
	}
	
	private ProductSearchArg createPagedSearch(String keyword, int firstResult, int maxResults) {
		ProductSearchArg search = new ProductSearchArg();
		search.setKeyword(keyword);
		search.setFirstResult(firstResult);
		search.setMaxResult(maxResults);
		return search;
	}
	
	private void assertKeywordFindsProductCount(ProductSearchArg prodSearch, int count) {
		Assert.assertEquals(count, prodService.searchProducts(prodSearch).size());
	}

}
