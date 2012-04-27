package com.vcl.product;

import java.io.Serializable;

public class ProductSearchArg implements Serializable {
	
	private static final long serialVersionUID = 2964708267991175876L;

	private String keyword;
	
	private int firstResult;

	private int maxResult = 20;
	
	public ProductSearchArg() {
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
		return "ProductSearchArg [keyword=" + keyword + ", firstResult=" + firstResult + ", maxResult=" + maxResult
				+ "]";
	}

}