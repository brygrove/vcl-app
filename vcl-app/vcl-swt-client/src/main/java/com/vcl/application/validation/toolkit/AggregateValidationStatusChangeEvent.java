package com.vcl.application.validation.toolkit;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.ISWTObservable;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.widgets.Control;

public class AggregateValidationStatusChangeEvent implements IChangeListener {

	private BindingValidationContext bindingValidationContext;

	public AggregateValidationStatusChangeEvent(BindingValidationContext bindingValidationContext) {
		this.bindingValidationContext = bindingValidationContext;
	}

	@Override
	public void handleChange(ChangeEvent arg0) {

		for (Object o : bindingValidationContext.getDataBindingContext().getBindings()) {
			
			Binding binding = (Binding) o;
			IStatus status = (IStatus) binding.getValidationStatus().getValue();
			Control control = null;
			
			if (binding.getTarget() instanceof ISWTObservable) {
				ISWTObservable swtObservable = (ISWTObservable) binding.getTarget();
				control = (Control) swtObservable.getWidget();
			}
			
			ControlDecoration decoration = bindingValidationContext.getDecoratorMap().get(control);

			if (decoration != null) {
				if (status.isOK()) {
					decoration.hide();
				} else {
					decoration.setDescriptionText(status.getMessage());
					decoration.show();
				}
			}
		}
	}

}
