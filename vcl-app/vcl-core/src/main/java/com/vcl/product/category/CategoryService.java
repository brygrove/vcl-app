package com.vcl.product.category;

import java.util.List;

public interface CategoryService {

	Category persist(Category c);

	/**
	 * Renames the category to a new category name and changes references to the
	 * old category for all products to the new category name.
	 * 
	 * @return the number of products updated due to the renaming of the
	 *         category from an existing category to and new category.
	 */
	int renameCategory(String catNoFrom, String catNoTo);

	void remove(Category c);

	Category findByCatNo(String catNo);

	List<String> findAllIds();

	List<String> findIds(int firstResult, int maxResult);

	List<Category> findAllPaged(int firstResult, int maxResult);

	List<Category> searchCategory(CategorySearchArg search);

}