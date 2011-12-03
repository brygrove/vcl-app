package com.vcl.application.validation.toolkit;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Control;

public class BindingValidationContext {

	private DataBindingContext dataBindingContext;

	private Map<Control, ControlDecoration> decoratorMap;

	public BindingValidationContext() {
		decoratorMap = new HashMap<Control, ControlDecoration>();
	}

	public DataBindingContext getDataBindingContext() {
		return dataBindingContext;
	}

	public void setDataBindingContext(DataBindingContext dataBindingContext) {
		this.dataBindingContext = dataBindingContext;
	}

	public Map<Control, ControlDecoration> getDecoratorMap() {
		return decoratorMap;
	}

	public void setDecoratorMap(Map<Control, ControlDecoration> decoratorMap) {
		this.decoratorMap = decoratorMap;
	}

}
