package com.vcl.application;

import java.util.Properties;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.ViewPart;

import com.vcl.application.events.ApplicationEvents;
import com.vcl.application.events.EventName;

public interface ApplicationResources {
	
	public Shell getWindowHandle();
	
	public Properties getApplicationSettings(); 
	
	public ApplicationEvents<EventName> getGlobalApplicationEvents();
	
	public void addViewPartTab(String tabName, ViewPart viewPart);
	
}
