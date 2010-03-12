package com.jreportquery.reports.elements;

public  class ReportResult {
	Object result = null;
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getResultFileName() {
		return resultFileName;
	}
	public void setResultFileName(String resultFileName) {
		this.resultFileName = resultFileName;
	}
	String resultFileName = null; 
}
