package com.vcl.product;

import com.vcl.product.category.Category;

public class ProductBuilder {

	private String indexNo;
	private String title;
	private String subTitle;
	private String author;
	private String isbn;
	private Category category;

	public String getIndexNo() {
		return indexNo;
	}

	public ProductBuilder setIndexNo(String indexNo) {
		this.indexNo = indexNo;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public ProductBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public ProductBuilder setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public ProductBuilder setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getIsbn() {
		return isbn;
	}

	public ProductBuilder setIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public ProductBuilder setCategory(Category category) {
		this.category = category;
		return this;
	}

	public Product build() {
		Product p = new Product();
		p.setIndexNo(indexNo);
		p.setTitle(title);
		p.setSubTitle(subTitle);
		p.setCategory(category);
		p.setAuthor(author);
		p.setIsbn(isbn);
		return p;
	}

}
