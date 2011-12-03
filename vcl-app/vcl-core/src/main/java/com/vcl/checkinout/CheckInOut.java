package com.vcl.checkinout;

import java.io.Serializable;
import javax.persistence.*;

import com.vcl.product.Product;

import java.util.Date;


/**
 * The persistent class for the checkinout database table.
 * 
 */
@Entity
@Table(name="checkinout")
public class CheckInOut implements Serializable {

	private static final long serialVersionUID = -3181460708595139084L;

	@EmbeddedId
	private CheckInOutPK id;

	@Temporal(TemporalType.DATE)
	private Date checkedInDate;

	@Temporal(TemporalType.DATE)
	private Date dueDate;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="indexNo", insertable=false, updatable=false)
	private Product product;

    public CheckInOut() {
    }

	public CheckInOutPK getId() {
		return this.id;
	}

	public void setId(CheckInOutPK id) {
		this.id = id;
	}
	
	public Date getCheckedInDate() {
		return this.checkedInDate;
	}

	public void setCheckedInDate(Date checkedInDate) {
		this.checkedInDate = checkedInDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}