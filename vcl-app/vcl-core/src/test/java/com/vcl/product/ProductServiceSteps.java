package com.vcl.product;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.vcl.product.category.Category;

@Component
public class ProductServiceSteps {
	
	@Inject
	private ProductService prodService;
	
	public Product createProduct(String indexNo, Category category) {
		Product p = new Product();
		p.setIndexNo(indexNo);
		p.setCategory(category);
		return prodService.persist(p);
	}
	
	public Product createProduct(Product prod) {
		return prodService.persist(prod);
	}
	
	public Product findByIndexNo(String indexNo, ProductRelationshipFetchType... fetchTypes) {
		return prodService.findByIndexNo(indexNo, Arrays.asList(fetchTypes));
	}
	
	public List<Product> findByCategory(String catNo) {
		return prodService.findByCategoryNum(catNo, 0, Integer.MAX_VALUE);
	}
	
}
