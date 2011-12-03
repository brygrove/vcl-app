package com.vcl.application.events;

import java.util.EventListener;
import java.util.LinkedHashMap;
import java.util.Map;

import com.vcl.application.ApplicationResources;

public class GlobalApplicationEvents implements ApplicationEvents<EventName> {

	private ApplicationResources appResources;

	private Map<EventName, EventListener> events = new LinkedHashMap<EventName, EventListener>();

	public GlobalApplicationEvents(ApplicationResources appResources) {
		this.appResources = appResources;
		registerEvents();
	}

	private final void registerEvents() {
		addEvent(EventName.EXIT_APPLICATION, new ExitApplicationEvent(appResources.getWindowHandle()));
		addEvent(EventName.SHOW_EDIT_BORROWER_VIEW, new ShowEditBorrowerView(appResources));
		addEvent(EventName.SHOW_EDIT_CATEGORY_VIEW, new ShowEditCategoryView(appResources));
		addEvent(EventName.SHOW_EDIT_PRODUCT_VIEW, new ShowEditProductView(appResources));
		addEvent(EventName.SHOW_EDIT_USER_VIEW, new ShowEditUserView(appResources));
		addEvent(EventName.SHOW_CHECK_IN_OUT_PRODUCT_VIEW, new ShowCheckInOutProductView(appResources));
		addEvent(EventName.SHOW_PRODUCT_SEARCH_VIEW, new ShowProductSearchView(appResources));
		addEvent(EventName.SHOW_BORROWER_SEARCH_VIEW, new ShowBorrowerSearchView(appResources));
		addEvent(EventName.SHOW_REPORTS_VIEW, new ShowReportsView(appResources));
		
	}

	public ExitApplicationEvent getExitAppEvent() {
		return getEvent(EventName.EXIT_APPLICATION, ExitApplicationEvent.class);
	}
	
	public ShowEditBorrowerView getShowEditBorrowerView() {
		return getEvent(EventName.SHOW_EDIT_BORROWER_VIEW, ShowEditBorrowerView.class);
	}
	
	public ShowEditCategoryView getShowEditCategoryView() {
		return getEvent(EventName.SHOW_EDIT_CATEGORY_VIEW, ShowEditCategoryView.class);
	}
	
	public ShowEditProductView getShowEditProductView() {
		return getEvent(EventName.SHOW_EDIT_PRODUCT_VIEW, ShowEditProductView.class);
	}
	

	@Override
	public void addEvent(EventName name, EventListener event) {
		events.put(name, event);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EventListener> T getEvent(EventName name, Class<T> eventType) {
		T eventListener = (T) events.get(name);
		if (eventListener == null) {
			throw new RuntimeException("No global application event is registered for name [" + name + "]");
		}
		return eventListener;
	}

}
