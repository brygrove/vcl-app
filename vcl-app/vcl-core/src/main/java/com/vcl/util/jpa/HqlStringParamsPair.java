package com.vcl.util.jpa;

import java.util.List;

public class HqlStringParamsPair {
	private StringBuffer hqlStringBuff = new StringBuffer();
	
	private HqlParamBuilder hqlParamBuilder = new HqlParamBuilder(); 
	
	public void addParam(HqlParam param) {
		hqlParamBuilder.addParam(param);
	}
	
	public void addParam(String paramName, Object paramValue) {
		hqlParamBuilder.addParam(paramName, paramValue );
	}
	
	public void appendHqlStartingWhereCondition() {
		hqlStringBuff.append(" '1'='1' ");
	}
	
	public void appendHql(String hqlSubString) {
		hqlStringBuff.append(hqlSubString);
	}
	
	public void appendAndOperatorAndHql(String hqlSubString) {
		hqlStringBuff.append( " and " + hqlSubString);
	}
	
	public void appendOrOperatorAndHql(String hqlSubString) {
		hqlStringBuff.append( " or " + hqlSubString);
	}

	public List<HqlParam> getParams() {
		return hqlParamBuilder.getParams();
	}

	public void setParams(List<HqlParam> params) {
		this.hqlParamBuilder.setParams( params );
	}

	public String getHqlString() {
		return hqlStringBuff.toString();
	}
	
	public String getWhereHqlString(){
		return " where " + getHqlString() + " ";
	}

	public void appendAndOperatorAndHqlStringParams(HqlStringParamsPair hqlStringParamsPair) {
		getParams().addAll(hqlStringParamsPair.getParams());
		appendAndOperatorAndHql(hqlStringParamsPair.getHqlString());
	}

	public void appendHqlStringParams(HqlStringParamsPair hqlStringParamsPair) {
		getParams().addAll(hqlStringParamsPair.getParams());
		appendHql(hqlStringParamsPair.getHqlString());
	}

	public static HqlStringParamsPair createHqlAndParamPair(String hql, String paramName, Object paramValue) {

		HqlStringParamsPair hqlStrParams = new HqlStringParamsPair();
		hqlStrParams.addParam(paramName, paramValue);
		hqlStrParams.appendHql(hql);

		return hqlStrParams;
	}
}
