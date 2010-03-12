package com.jreportquery.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openuri.reportconfig.DatasourceType;
import org.openuri.reportconfig.KeywordType;
import org.openuri.reportconfig.ParamType;
import org.openuri.reportconfig.ParamsType;
import org.openuri.reportconfig.ReportConfigurationDocument;
import org.openuri.reportconfig.ReportConfigurationType;
import org.openuri.reportconfig.ReportType;

import com.jreportquery.data.DataManager;


public class VclReportRunnerApp {

	public static Logger logger = null; 
	public static  BufferedReader br = new BufferedReader( new InputStreamReader(System.in));

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		logger = Logger.getLogger(VclReportRunnerApp.class.getName());

		
		File file = null;
		ReportConfigurationDocument rptConfDoc = null;

		try {
			file = new File("config\\report-config.xml");
			rptConfDoc = ReportConfigurationDocument.Factory.parse( file );
		}catch(Exception ex){
			ex.printStackTrace();
		}

		if ( rptConfDoc != null ){
			ReportConfigurationType rptConf = rptConfDoc.getReportConfiguration();

			DatasourceType dataSourceConfig = rptConf.getDatasource();

			DataManager.getInst().setupDataSource(dataSourceConfig);
			
			ArrayList params = null; 
			String sql = "";
			
			
			ReportType report = rptConf.getReports().getReportArray()[0];
			
			sql = report.getQuery(); 
			params = new ArrayList();
			ParamsType rptParams = report.getParams();
			
			logger.info("Running Report " + report.getName() );
			
			
			for (int i = 0; i < rptParams.getParamArray().length; i++) {
				ParamType param = rptParams.getParamArray(i);
				logger.info("Enter param: " + " name: " +  param.getName() + " label: " + param.getLabel());
				String userInput = "";
				try {
					userInput = br.readLine();
				}catch(Exception ex){
					logger.error("failed to enter param correctly.");
				}
				
				KeywordType keyword = param.getKeyword();
				if (keyword != null )
				{
					userInput =   keyword.getPrefix() + userInput +  keyword.getPostfix();
				}
				
				params.add(userInput);
			}
			
			DataManager.getInst().executeSqlStat2Stdout( sql, params );
		
			DataManager.getInst().cleanup();
		}

	}
	
	


}
