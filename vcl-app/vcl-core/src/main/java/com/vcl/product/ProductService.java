package com.vcl.product;

import java.util.List;

public interface ProductService {

	Product persist(Product p);

	Product merge(Product p);

	void remove(Product p);

	Product findByIndexNo(String indexNo);

	Product findByIndexNo(String indexNo, List<ProductRelationshipFetchType> fetchTypes);

	List<String> findAllIndexNos();

	List<String> findIndexNos(int firstResult, int maxResult);

	List<Product> findAllPaged(int firstResult, int maxResult);

	List<Product> findByCategoryNum(String catNo, int firstResult, int maxResult);

	List<Product> searchProducts(ProductSearchArg prodSearch);
}