package com.jreportquery.viewer;

import java.io.File;

import org.jdesktop.jdic.desktop.Desktop;

public class DocumentViewer {
	
	public static void viewDocument( String path ){
		
		File f = new File( path );
		
		try {
			Desktop.browse( f.toURI().toURL() );		
		
		}catch(Exception ex){
			System.err.println("failed to browse document " + path );
			ex.printStackTrace();
		}
	}
}
