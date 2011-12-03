package com.vcl.application.validation;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.fieldassist.ControlDecoration;

public class StringRequiredValidator implements IValidator {
 
    private final String errorText;
    private final ControlDecoration controlDecoration;
 
    public StringRequiredValidator(String errorText,
        ControlDecoration controlDecoration) {
        super();
        this.errorText = errorText;
        this.controlDecoration = controlDecoration;
    }
 
    public IStatus validate(Object value) {
        if (value instanceof String) {
            String text = (String) value;
            if (text.trim().length() == 0) {
                controlDecoration.show();
                return ValidationStatus
                        .error(errorText);
            }
        }
        controlDecoration.hide();
        return Status.OK_STATUS;
    }
}