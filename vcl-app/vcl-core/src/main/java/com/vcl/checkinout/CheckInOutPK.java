package com.vcl.checkinout;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the checkinout database table.
 * 
 */
@Embeddable
public class CheckInOutPK implements Serializable {

	private static final long serialVersionUID = 408350058837224811L;

	private String borrowID;

	private String indexNo;

    @Temporal( TemporalType.DATE)
	private java.util.Date checkedOutDate;

    public CheckInOutPK() {
    }
	public String getBorrowID() {
		return this.borrowID;
	}
	public void setBorrowID(String borrowID) {
		this.borrowID = borrowID;
	}
	public String getIndexNo() {
		return this.indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public java.util.Date getCheckedOutDate() {
		return this.checkedOutDate;
	}
	public void setCheckedOutDate(java.util.Date checkedOutDate) {
		this.checkedOutDate = checkedOutDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CheckInOutPK)) {
			return false;
		}
		CheckInOutPK castOther = (CheckInOutPK)other;
		return 
			this.borrowID.equals(castOther.borrowID)
			&& this.indexNo.equals(castOther.indexNo)
			&& this.checkedOutDate.equals(castOther.checkedOutDate);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.borrowID.hashCode();
		hash = hash * prime + this.indexNo.hashCode();
		hash = hash * prime + this.checkedOutDate.hashCode();
		
		return hash;
    }
}