package com.jreportquery.reports;

import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import com.jreportquery.reports.datasource.ReportDataSource;
import com.jreportquery.reports.elements.ReportResult;

public class XsltTransformReport extends Report {

	protected String xslPath = null;

	protected String transformPathName = "report.html";

	public String getTransformPathName() {
		return transformPathName;
	}

	public void setTransformPathName(String transformPathName) {
		this.transformPathName = transformPathName;
	}

	public String getXslPath() {
		return xslPath;
	}

	public void setXslPath(String xslPath) {
		this.xslPath = xslPath;
	}


	public String getXmlPath() {
		return xmlPath;
	}


	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}


	protected String xmlPath = null;


	@Override
	protected ReportResult transformReportResult(ReportDataSource rs) {
		ReportResult rptRes = new ReportResult();

		if ( getXmlPath() == null || getXslPath() == null ){
			System.err.println("missing an xml or xslt document");
			return rptRes;
		}

		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer =
				tFactory.newTransformer
				(new javax.xml.transform.stream.StreamSource
						(getXslPath()));

			transformer.transform
			(new javax.xml.transform.stream.StreamSource
					(getXmlPath()),
					new javax.xml.transform.stream.StreamResult
					( new FileOutputStream(getTransformPathName())));
			
			rptRes.setResult(getTransformPathName());
			rptRes.setResultFileName(getTransformPathName());
			
			
		}
		catch (Exception e) {
			e.printStackTrace( );
		}




		return rptRes;
	}



}
