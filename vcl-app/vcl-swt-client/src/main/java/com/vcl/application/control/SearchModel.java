package com.vcl.application.control;

import com.vcl.application.mvc.ModelOwner;

public class SearchModel {
	
	private String keywordText;
	
	private int pageNo;
	private int firstResult = 0;
	private int maxResults = 20;
	
	@SuppressWarnings("rawtypes")
	private ModelOwner modelOwner;
	
	public String getKeywordText() {
		return keywordText;
	}

	public void setKeywordText(String keywordText) {
		this.keywordText = keywordText;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	@SuppressWarnings("rawtypes")
	public ModelOwner getModelOwner() {
		if (modelOwner == null) {
			throw new RuntimeException("BUG: Forgot to set the Model Owner");
		}
		return modelOwner;
	}

	@SuppressWarnings("rawtypes")
	public void setModelOwner(ModelOwner modelOwner) {
		this.modelOwner = modelOwner;
	}

}
