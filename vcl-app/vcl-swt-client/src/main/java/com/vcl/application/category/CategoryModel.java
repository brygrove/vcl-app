package com.vcl.application.category;

import com.vcl.application.model.AbstractModelObject;
import com.vcl.product.category.Category;

public class CategoryModel extends AbstractModelObject {

	private Category category = null;

	public CategoryModel(Category category) {
		this.category = category;
	}

	public String getCatNo() {
		return category.getCatNo();
	}

	public void setCatNo(String catNo) {
		category.setCatNo(catNo);
	}

	public String toString() {
		return category.toString();
	}

	@Override
	public Object getData() {
		return category;
	}
}
