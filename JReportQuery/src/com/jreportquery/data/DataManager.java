package com.jreportquery.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.openuri.reportconfig.DatasourceType;

import com.jreportquery.data.meta.RSColumn;

import java.sql.ResultSetMetaData;

public class DataManager {
	
	protected DataSource dataSource = null; 
	protected Logger logger = Logger.getLogger(DataManager.class.getName());
	protected static DataManager instance = null;
	
	private DataManager(){
		
	}
	
	public static DataManager getInst(){
		if (instance == null) {
			instance = new  DataManager();
		}
		return instance;
	}
	
	
	public List executeSqlStatAsList( Class listType, String sql, ArrayList params, List metaInfo ){
		
		List results = null;
		try {
			results = (List) listType.newInstance();
		}catch(Exception ex){
			return null;
		}
		
		if (dataSource == null ){
			throw new RuntimeException("cannot use a null data source ");
		}

        //
        // Now, we can use JDBC DataSource as we normally would.
        //
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
        	logger.info("executing sql statement as list");
        	logger.info("sql: " + sql);
        	logger.info("params: " + params.toString() );
           
        	conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            
            for (int i = 0; i < params.size(); i++){
            	Object o = params.get(i);
            	stmt.setObject(i+1, o);
            }
            
            rset = stmt.executeQuery();
            
         
            int numcols = rset.getMetaData().getColumnCount();
            
            if ( metaInfo != null ){
            	
            	ResultSetMetaData rsmd = rset.getMetaData();
            	for(int i=1;i<=numcols;i++) {
            		String colname = rsmd.getColumnName(i);
            		String collabel = rsmd.getColumnLabel(i);
            		String coltype = rsmd.getColumnTypeName(i);
            		
            		metaInfo.add( new RSColumn( colname,collabel, i, coltype));
            	}
            	
            	
            }
            
            while(rset.next()) {
            	List row = null; 
            	try {
            		row = (List) listType.newInstance();
        		}catch(Exception ex){
        			return null;
        		}
            	
            	for(int i=1;i<=numcols;i++) {
                	Object o = rset.getObject(i);
                	row.add(o);
                }
                
                results.add(row);
            }
        } 
        catch(SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try { rset.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { conn.close(); } catch(Exception e) { }
        }
		
		return results;
	}
	
	
	public void executeSqlStat2Stdout(String sql, ArrayList params ){
		
		if (dataSource == null ){
			throw new RuntimeException("cannot use a null data source ");
		}

        //
        // Now, we can use JDBC DataSource as we normally would.
        //
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
        	logger.info("executing sql statement 2 stdout");
        	logger.info("sql: " + sql);
        	logger.info("params: " + params.toString() );
           
        	conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            
            for (int i = 0; i < params.size(); i++){
            	Object o = params.get(i);
            	stmt.setObject(i+1, o);
            }
            
            rset = stmt.executeQuery();
            int numcols = rset.getMetaData().getColumnCount();
            
            logger.info("Results Meta Info:");
            
            ResultSetMetaData rsmd = rset.getMetaData();
        	for(int i=1;i<=numcols;i++) {
        		String colname = rsmd.getColumnName(i);
        		String collabel = rsmd.getColumnLabel(i);
        		String coltype = rsmd.getColumnTypeName(i);
        		logger.info("column: " + colname + " " + collabel +  " " + coltype);
        	}
            
            logger.info("Results:");
            logger.info("-------------------------------------");
            
            
         
            while(rset.next()) {
                for(int i=1;i<=numcols;i++) {
                    logger.info("\t" + rset.getString(i));
                }
                System.out.println("");
            }
        } 
        catch(SQLException e) {
            e.printStackTrace();
        } 
        finally {
            try { rset.close(); } catch(Exception e) { }
            try { stmt.close(); } catch(Exception e) { }
            try { conn.close(); } catch(Exception e) { }
        }
        
	}
	
	public void cleanup(){
		
		if (dataSource == null ){
			System.err.println("no data source to cleanup");
			return;
		}
		
		BasicDataSource bds = (BasicDataSource) dataSource;
        try {
        	bds.close();
        }catch(Exception ex){
        	ex.printStackTrace();
        }
	}

	public void setupDataSource(DatasourceType dataSourceConfig){

		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(dataSourceConfig.getDriver() );
		ds.setUsername(dataSourceConfig.getUser());
		ds.setPassword(dataSourceConfig.getPass());
		ds.setUrl(dataSourceConfig.getJdbc());
		
		dataSource = ds;

	}
	
	
}
