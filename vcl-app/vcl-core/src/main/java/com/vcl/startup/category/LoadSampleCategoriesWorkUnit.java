package com.vcl.startup.category;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.vcl.product.category.Category;
import com.vcl.product.category.CategoryService;
import com.vcl.startup.WebBootstrapWorkUnit;

@Component
public class LoadSampleCategoriesWorkUnit extends WebBootstrapWorkUnit {
	
	private String[][] categories = {
		new String[] {"Theological Studies"},
		new String[] {"Fiction"},
	};
	
	@Inject
	private CategoryService catService;

	@Override
	public void executeWork() {
		for(String[] catRow : categories) {
			createCategory(catRow);
		}
	}
	
	private Category createCategory(String[] catRow) {
		Category c = new Category();
		c.setCatNo(catRow[0]);
		
		return catService.persist(c);
	}

}
