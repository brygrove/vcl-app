package com.vcl.application.validation.toolkit;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

import com.vcl.application.validation.StringRequiredValidator;


public class BindingValidationToolkit {

	private BindingValidationContext context = new BindingValidationContext();

    public BindingValidationToolkit(DataBindingContext dataBindingContext) {
    	setDataBindingContext(dataBindingContext);
    }
    
	public void setDataBindingContext(DataBindingContext dataBindingContext) {
		context.setDataBindingContext(dataBindingContext);
	}

	public UpdateValueStrategy createRequiredStringValidation(Control control, String validationMsg) {
		
		ControlDecoration controlDecoration = new ControlDecoration(control, SWT.LEFT | SWT.TOP);
		controlDecoration.setDescriptionText(validationMsg);
		
		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(
				FieldDecorationRegistry.DEC_ERROR);
		
		controlDecoration.setImage(fieldDecoration.getImage());
		
		registerControlDecoration(control, controlDecoration);
		
		return new UpdateValueStrategy()
			.setAfterConvertValidator(
					new StringRequiredValidator(validationMsg, controlDecoration));
	}
	
	private void registerControlDecoration(Control control, ControlDecoration controlDecoration) {
		context.getDecoratorMap().put(control, controlDecoration);
	}

	public AggregateValidationStatus createAggregateValidationStatus() {

		AggregateValidationStatus aggregateValidationStatus = new AggregateValidationStatus(context
				.getDataBindingContext().getBindings(), AggregateValidationStatus.MAX_SEVERITY);

		aggregateValidationStatus.addChangeListener(new AggregateValidationStatusChangeEvent(context));

		return aggregateValidationStatus;
	}
	
}
