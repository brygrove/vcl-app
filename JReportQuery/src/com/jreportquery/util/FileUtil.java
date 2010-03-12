package com.jreportquery.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileUtil {

	public static void saveTextToFile(String xmlResultFileName, StringBuffer sb) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter( new FileWriter( xmlResultFileName ));
			bw.write(sb.toString());
		}
		catch(Exception ex)
		{
			System.err.println("error saving text to file " + xmlResultFileName + " Exception: " + ex.getMessage() );
		}
		finally {
			if ( bw != null ){
				try {
					bw.close();
				}catch(Exception ex){
					System.err.println("error closing buffer while saving to file " + xmlResultFileName + " Exception: " + ex.getMessage() );
				}
			}
		}
		
	}

}
