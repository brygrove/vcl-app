package com.vcl.product.lostdamagedstolen;

import java.io.Serializable;
import javax.persistence.*;

import com.vcl.product.Product;


/**
 * The persistent class for the lostdamagedstolen database table.
 * 
 */
@Entity
@Table(name="lostdamagedstolen")
public class LostDamagedStolen implements Serializable {

	private static final long serialVersionUID = 3228010988475877894L;

	@Id
	private String indexNo;

    @Lob()
	private String description;

	//bi-directional one-to-one association to Product
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="indexNo")
	private Product product;

    public LostDamagedStolen() {
    }

	public String getIndexNo() {
		return this.indexNo;
	}

	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}