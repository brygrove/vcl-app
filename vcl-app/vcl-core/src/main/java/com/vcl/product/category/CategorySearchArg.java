package com.vcl.product.category;

import java.io.Serializable;

public class CategorySearchArg implements Serializable {

	private static final long serialVersionUID = -1826929761132968055L;

	private String keyword;

	private int firstResult;

	private int maxResult = 20;

	public CategorySearchArg() {
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	@Override
	public String toString() {
		return "CategorySearchArg [keyword=" + keyword + ", firstResult=" + firstResult + ", maxResult=" + maxResult
				+ "]";
	}

}
