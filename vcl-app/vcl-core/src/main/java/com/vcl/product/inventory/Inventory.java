package com.vcl.product.inventory;

import java.io.Serializable;
import javax.persistence.*;

import com.vcl.product.Product;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@Table(name="inventory")
public class Inventory implements Serializable {

	private static final long serialVersionUID = -3795295292220204182L;

	@Id
	private String indexNo;

	//bi-directional one-to-one association to Product
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="indexNo")
	private Product product;

    public Inventory() {
    }

	public String getIndexNo() {
		return this.indexNo;
	}

	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}