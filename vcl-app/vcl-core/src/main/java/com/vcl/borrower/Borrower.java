package com.vcl.borrower;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the borrower database table.
 * 
 */
@Entity
@Table(name="borrower")
public class Borrower implements Serializable {

	private static final long serialVersionUID = 4370365182768014519L;

	@Id
	private String borrowID;

	private String address;

	private String cell;

	private String email;

	private String firstName;

	private String lastName;

	private String phoneNo;

    public Borrower() {
    }

	public String getBorrowID() {
		return this.borrowID;
	}

	public void setBorrowID(String borrowID) {
		this.borrowID = borrowID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}