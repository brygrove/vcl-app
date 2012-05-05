package com.vcl.application.action.search.product;

import java.util.ArrayList;
import java.util.List;

import com.vcl.application.action.search.EntityDataSearchProvider;
import com.vcl.application.action.search.EntityDataSearchRequest;
import com.vcl.application.control.SearchModel;
import com.vcl.application.product.ProductModel;
import com.vcl.client.VclClient;
import com.vcl.product.Product;
import com.vcl.product.ProductSearchArg;
import com.vcl.product.ProductService;

public class ProductDataSearchProvider implements EntityDataSearchProvider<ProductModel> {

	private ProductService productService = VclClient.getServiceLocator().getProductService();
	
	@Override
	public List<ProductModel> searchEntity(EntityDataSearchRequest request) {
		
		SearchModel searchModel = request.getSearchModel();
		String keyword = searchModel.getKeywordText();
		String keywordSearchStr = keyword;
		if (keywordSearchStr == null) {
			keywordSearchStr = "%";
		}
		else {
			keywordSearchStr = "%" + keywordSearchStr + "%";
		}
		
		ProductSearchArg search = new ProductSearchArg();
		search.setKeyword(keywordSearchStr);
		search.setFirstResult(searchModel.getFirstResult());
		search.setMaxResult(searchModel.getMaxResults());
		
		return createModelList(productService.searchProducts(search));
	}
	
	private List<ProductModel> createModelList(List<Product> products) {
		List<ProductModel> models = new ArrayList<ProductModel>();
		
		for (Product p : products) {
			models.add(new ProductModel(p));
		}
		
		return models;
	}

}
