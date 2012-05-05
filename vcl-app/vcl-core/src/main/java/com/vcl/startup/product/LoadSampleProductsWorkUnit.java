package com.vcl.startup.product;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.vcl.product.Product;
import com.vcl.product.ProductService;
import com.vcl.startup.WebBootstrapWorkUnit;

@Component
public class LoadSampleProductsWorkUnit extends WebBootstrapWorkUnit {
	
	private String[][] products = {
		new String[] {"A01234", "Jesus Great I AM", "Second Edition","Martin Bardont","11111-12345-33333-4444"},
		new String[] {"A01357", "Jesus Son of Man", "Fourth Edition","Bardento Marios","11111-22222-12345-4444"},
		new String[] {"A02468", "Jesus King of Kings", "First Edition","Maria Calipso","11111-22222-33333-12345"},
		new String[] {"A00110", "Jesus Lord of Lords", "First Edition","Grace Barusto","12345-22222-33333-4444"},
	};
	
	@Inject
	private ProductService productService;

	@Override
	public void executeWork() {
		for(String[] row : products) {
			createProduct(row);
		}
	}
	
	private Product createProduct(String[] row) {
		Product p = new Product();
		p.setIndexNo(row[0]);
		p.setTitle(row[1]);
		p.setSubTitle(row[2]);
		p.setAuthor(row[3]);
		p.setIsbn(row[4]);
		
		return productService.persist(p);
	}

}
