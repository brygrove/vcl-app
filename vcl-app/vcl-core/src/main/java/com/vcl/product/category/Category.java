package com.vcl.product.category;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="category")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1123236453432L;

	@Id
	private String catNo;

    public Category() {
    }

	public Category(String catNo) {
		this.catNo = catNo;
	}

	public String getCatNo() {
		return this.catNo;
	}

	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}

}