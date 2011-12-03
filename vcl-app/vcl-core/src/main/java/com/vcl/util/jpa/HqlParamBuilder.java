package com.vcl.util.jpa;

import java.util.ArrayList;
import java.util.List;

public class HqlParamBuilder {
	
	private List<HqlParam> hqlParams = new ArrayList<HqlParam>();

	public void addParam(HqlParam hqlParam) {
		hqlParams.add(hqlParam);
	}

	public void addParam(String paramName, Object paramValue) {
		addParam(HqlParam.param(paramName, paramValue));
	}

	public void setParams(List<HqlParam> hqlParams){
		this.hqlParams = hqlParams;
	}
	
	public List<HqlParam> getParams() {
		return hqlParams;
	}
}
