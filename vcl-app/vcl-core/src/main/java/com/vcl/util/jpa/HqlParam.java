package com.vcl.util.jpa;

import static com.vcl.util.Utils.*;

import javax.persistence.Query;

/**
 * used to build a list of hql parameters
 **/
public class HqlParam {
	private String paramName;
	private Object paramValue;

	public HqlParam(String paramName, Object paramValue) {
		this.paramName = paramName;
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}

	public static HqlParam param(String name, Object value) {
		return new HqlParam(name, value);
	}

	@Override
	public String toString() {
		return "HqlParam [paramName=" + paramName + ", paramValue=" + paramValue + "]";
	}

	public void setParameterForQuery(Query query) {
		query.setParameter(paramName, paramValue);
	}
	
	public static HqlParam createWildcardIgnoreCaseParam(String paramName, String keyword){
		String keywordSearch = isEmpty(keyword) ? "" : keyword.toUpperCase().trim();
		return new HqlParam(paramName, "%" + keywordSearch + "%");
	}
	
	public static HqlParam createWildcardParam(String paramName, String keyword){
		String keywordSearch = isEmpty(keyword) ? "" : keyword.trim();
		return new HqlParam(paramName, "%" + keywordSearch + "%");
	}
}	
