package com.vcl.application.action.search.category;

import java.util.ArrayList;
import java.util.List;

import com.vcl.application.action.search.EntityDataSearchProvider;
import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.category.CategoryModel;
import com.vcl.application.control.SearchModel;
import com.vcl.client.VclClient;
import com.vcl.product.category.Category;
import com.vcl.product.category.CategorySearchArg;
import com.vcl.product.category.CategoryService;

public class CategoryDataSearchProvider implements EntityDataSearchProvider<CategoryModel> {

	private CategoryService categoryService = VclClient.getServiceLocator().getCategoryService();
	
	@Override
	public List<CategoryModel> searchEntity(EntityDataSearchRequest request) {
		
		SearchModel searchModel = request.getSearchModel();
		String keyword = searchModel.getKeywordText();
		String keywordSearchStr = keyword;
		if (keywordSearchStr == null) {
			keywordSearchStr = "%";
		}
		else {
			keywordSearchStr = "%" + keywordSearchStr + "%";
		}
		
		CategorySearchArg search = new CategorySearchArg();
		search.setKeyword(keywordSearchStr);
		search.setFirstResult(searchModel.getFirstResult());
		search.setMaxResult(searchModel.getMaxResults());
		
		return createModelList(categoryService.searchCategory(search));
	}
	
	private List<CategoryModel> createModelList(List<Category> entities) {
		List<CategoryModel> models = new ArrayList<CategoryModel>();
		
		for (Category e : entities) {
			models.add(new CategoryModel(e));
		}
		
		return models;
	}

}
