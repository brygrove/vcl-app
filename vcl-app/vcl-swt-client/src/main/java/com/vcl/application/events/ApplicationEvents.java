package com.vcl.application.events;

import java.util.EventListener;

public interface ApplicationEvents<ENUM_EVENT_NAME> {
	
	public void addEvent(ENUM_EVENT_NAME name, EventListener event); 
	
	public <T extends EventListener> T getEvent(ENUM_EVENT_NAME name, Class<T> eventType);
	
}
	