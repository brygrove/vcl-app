package com.vcl.application.borrower;

import com.vcl.application.model.AbstractModelObject;
import com.vcl.borrower.Borrower;

public class BorrowerModel extends AbstractModelObject {

	private Borrower borrower = null;
	
	public BorrowerModel(Borrower borrower) {
		this.borrower = borrower; 
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public String getBorrowID() {
		return borrower.getBorrowID();
	}

	public void setBorrowID(String borrowID) {
		borrower.setBorrowID(borrowID);
	}

	public String getAddress() {
		return borrower.getAddress();
	}

	public void setAddress(String address) {
		borrower.setAddress(address);
	}

	public String getCell() {
		return borrower.getCell();
	}

	public void setCell(String cell) {
		borrower.setCell(cell);
	}

	public String getEmail() {
		return borrower.getEmail();
	}

	public void setEmail(String email) {
		borrower.setEmail(email);
	}

	public String getFirstName() {
		return borrower.getFirstName();
	}

	public void setFirstName(String firstName) {
		borrower.setFirstName(firstName);
	}

	public String getLastName() {
		return borrower.getLastName();
	}

	public void setLastName(String lastName) {
		borrower.setLastName(lastName);
	}

	public String getPhoneNo() {
		return borrower.getPhoneNo();
	}

	public void setPhoneNo(String phoneNo) {
		borrower.setPhoneNo(phoneNo);
	}

	public String toString() {
		return borrower.toString();
	}

	@Override
	public Object getData() {
		return getBorrower();
	}
}
