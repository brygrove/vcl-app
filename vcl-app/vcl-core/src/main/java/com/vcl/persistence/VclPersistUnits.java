package com.vcl.persistence;

public enum VclPersistUnits {
	
	VCL_CORE("vcl-core");
	
	private String persistUnitName;
	
	private VclPersistUnits(String unitName){
		this.persistUnitName = unitName;
	}

	public String getPersistUnitName() {
		return persistUnitName;
	}
	
}
