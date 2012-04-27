package com.vcl.application.product;

import java.util.Date;
import java.util.Set;

import com.vcl.application.model.AbstractModelObject;
import com.vcl.checkinout.CheckInOut;
import com.vcl.product.Product;
import com.vcl.product.category.Category;
import com.vcl.product.inventory.Inventory;
import com.vcl.product.lostdamagedstolen.LostDamagedStolen;

public class ProductModel extends AbstractModelObject {

	private Product product = null;

	public ProductModel(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getIndexNo() {
		return product.getIndexNo();
	}

	public void setIndexNo(String indexNo) {
		product.setIndexNo(indexNo);
	}

	public String getAuthor() {
		return product.getAuthor();
	}

	public void setAuthor(String author) {
		product.setAuthor(author);
	}

	public Category getCategory() {
		return product.getCategory();
	}

	public void setCategory(Category category) {
		product.setCategory(category);
	}

	public String getCondition() {
		return product.getCondition();
	}

	public void setCondition(String condition) {
		product.setCondition(condition);
	}

	public String getCost() {
		return product.getCost();
	}

	public void setCost(String cost) {
		product.setCost(cost);
	}

	public Date getDatePurchased() {
		return product.getDatePurchased();
	}

	public void setDatePurchased(Date datePurchased) {
		product.setDatePurchased(datePurchased);
	}

	public String getDonation() {
		return product.getDonation();
	}

	public void setDonation(String donation) {
		product.setDonation(donation);
	}

	public String getFiction() {
		return product.getFiction();
	}

	public void setFiction(String fiction) {
		product.setFiction(fiction);
	}

	public String getIsbn() {
		return product.getIsbn();
	}

	public void setIsbn(String isbn) {
		product.setIsbn(isbn);
	}

	public String getItemType() {
		return product.getItemType();
	}

	public void setItemType(String itemType) {
		product.setItemType(itemType);
	}

	public String getNoPages() {
		return product.getNoPages();
	}

	public void setNoPages(String noPages) {
		product.setNoPages(noPages);
	}

	public String getNotes() {
		return product.getNotes();
	}

	public void setNotes(String notes) {
		product.setNotes(notes);
	}

	public String getPublisher() {
		return product.getPublisher();
	}

	public boolean equals(Object obj) {
		return product.equals(obj);
	}

	public void setPublisher(String publisher) {
		product.setPublisher(publisher);
	}

	public String getSeries() {
		return product.getSeries();
	}

	public void setSeries(String series) {
		product.setSeries(series);
	}

	public String getSubTitle() {
		return product.getSubTitle();
	}

	public void setSubTitle(String subTitle) {
		product.setSubTitle(subTitle);
	}

	public String getTitle() {
		return product.getTitle();
	}

	public void setTitle(String title) {
		product.setTitle(title);
	}

	public String getType() {
		return product.getType();
	}

	public void setType(String type) {
		product.setType(type);
	}

	public Set<CheckInOut> getCheckinouts() {
		return product.getCheckinouts();
	}

	public void setCheckinouts(Set<CheckInOut> checkinouts) {
		product.setCheckinouts(checkinouts);
	}

	public Inventory getInventory() {
		return product.getInventory();
	}

	public void setInventory(Inventory inventory) {
		product.setInventory(inventory);
	}

	public LostDamagedStolen getLostdamagedstolen() {
		return product.getLostdamagedstolen();
	}

	public void setLostdamagedstolen(LostDamagedStolen lostdamagedstolen) {
		product.setLostdamagedstolen(lostdamagedstolen);
	}

	public String toString() {
		return product.toString();
	}

	@Override
	public Object getData() {
		return getProduct();
	}
}
