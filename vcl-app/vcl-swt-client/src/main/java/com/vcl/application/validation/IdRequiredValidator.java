package com.vcl.application.validation;

import com.vcl.application.action.entity.EntityAware;
import com.vcl.util.MessageBoxUtil;

public class IdRequiredValidator {
	
	public static boolean validate(EntityAware entityAware) {
		Object id = entityAware.getIdentifier(); 
		
		if (id == null) {
			return false;
		}
		
		if (id instanceof String) {
			return id.toString().trim().length() != 0; 
		}
		
		return true;
	}
	
	public static boolean validateAndShowWarningMessage(EntityAware entityAware) {
		boolean isValid = validate(entityAware);
		
		if (!isValid) {
			MessageBoxUtil.showWarning("The " + entityAware.getDataType() + "'s " + entityAware.getIdName() + " is required");
		}
		
		return isValid;
	}
	
}
