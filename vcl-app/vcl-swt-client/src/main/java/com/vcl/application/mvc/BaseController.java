package com.vcl.application.mvc;


public abstract class BaseController<V, M> implements Controller<V, M> {
	
	protected V view;
	
	public V getView() {
		return view;
	}

	public void setView(V view) {
		this.view = view;
	}
	
	@Override
	public V getModelOwner() {
		return getView();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public M getModel() {
		if (view instanceof View) {
			return (M) ((View)view).getModel();
		}
		return null;
	}
}
