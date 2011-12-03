package com.vcl.application.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

public class ExitApplicationEvent extends SelectionAdapter {

	private static final long serialVersionUID = -4734058829908397640L;
	
	protected Shell shell;
	
	public ExitApplicationEvent(Shell shell) {
		this.shell = shell; 
	}
	
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		shell.dispose();
	}
	
}
