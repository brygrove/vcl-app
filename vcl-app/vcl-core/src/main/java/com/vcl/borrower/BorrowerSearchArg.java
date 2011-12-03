package com.vcl.borrower;

import java.io.Serializable;

public class BorrowerSearchArg implements Serializable {
	
	private static final long serialVersionUID = -1826929761132968055L;

	private String borrowIdKeyword;

	private String nameKeyword;

	private String phoneNumKeyword;
	
	private String keyword;

	private int firstResult;

	private int maxResult = 20;
	
	public BorrowerSearchArg() {
	}

	public String getBorrowIdKeyword() {
		return borrowIdKeyword;
	}

	public void setBorrowIdKeyword(String borrowIdKeyword) {
		this.borrowIdKeyword = borrowIdKeyword;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getNameKeyword() {
		return nameKeyword;
	}

	public void setNameKeyword(String nameKeyword) {
		this.nameKeyword = nameKeyword;
	}

	public String getPhoneNumKeyword() {
		return phoneNumKeyword;
	}

	public void setPhoneNumKeyword(String phoneNumKeyword) {
		this.phoneNumKeyword = phoneNumKeyword;
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
		return "BorrowerSearchArg [borrowIdKeyword=" + borrowIdKeyword + ", nameKeyword=" + nameKeyword
				+ ", phoneNumKeyword=" + phoneNumKeyword + ", firstResult=" + firstResult + ", maxResult=" + maxResult
				+ "]";
	}

}
