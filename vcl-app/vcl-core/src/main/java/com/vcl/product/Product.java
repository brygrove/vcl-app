package com.vcl.product;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vcl.checkinout.CheckInOut;
import com.vcl.product.category.Category;
import com.vcl.product.inventory.Inventory;
import com.vcl.product.lostdamagedstolen.LostDamagedStolen;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
public class Product implements Serializable {

	private static final long serialVersionUID = -4172307401263501753L;

	@Id
	private String indexNo;

    @Lob()
	private String author = "";

    @ManyToOne
	private Category category;

	@Column(name="prod_condition")
	private String condition;

	private String cost = "";

    @Temporal( TemporalType.DATE)
	private Date datePurchased;

	private String donation = "";

	private String fiction = "";

	@Column(name="ISBN")
	private String isbn;

	private String itemType = "";

	private String noPages = "";

    @Lob()
	private String notes = "";

	private String publisher;

	private String series = "";

	private String subTitle = "";

	private String title = "";

	private String type = "";

	//bi-directional many-to-one association to CheckInOut
	@OneToMany(mappedBy="product")
	private Set<CheckInOut> checkinouts;

	//bi-directional one-to-one association to Inventory
	@OneToOne(mappedBy="product", fetch=FetchType.LAZY)
	private Inventory inventory;

	//bi-directional one-to-one association to LostDamagedStolen
	@OneToOne(mappedBy="product", fetch=FetchType.LAZY)
	private LostDamagedStolen lostdamagedstolen;

    public Product() {
    }
    
	public String getIndexNo() {
		return this.indexNo;
	}

	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Date getDatePurchased() {
		return this.datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public String getDonation() {
		return this.donation;
	}

	public void setDonation(String donation) {
		this.donation = donation;
	}

	public String getFiction() {
		return this.fiction;
	}

	public void setFiction(String fiction) {
		this.fiction = fiction;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getItemType() {
		return this.itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getNoPages() {
		return this.noPages;
	}

	public void setNoPages(String noPages) {
		this.noPages = noPages;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSeries() {
		return this.series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getSubTitle() {
		return this.subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<CheckInOut> getCheckinouts() {
		if (checkinouts == null) {
			return new HashSet<CheckInOut>();
		}
		return this.checkinouts;
	}

	public void setCheckinouts(Set<CheckInOut> checkinouts) {
		this.checkinouts = checkinouts;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public LostDamagedStolen getLostdamagedstolen() {
		return this.lostdamagedstolen;
	}

	public void setLostdamagedstolen(LostDamagedStolen lostdamagedstolen) {
		this.lostdamagedstolen = lostdamagedstolen;
	}
	
}