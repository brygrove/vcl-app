<?xml version="1.0" encoding="UTF-16" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html"/>
<xsl:template match="/">
<html>
	 <head>
	 <title>JReport Query Viewer</title>
	 <LINK REL="StyleSheet" HREF="report.css" TYPE="text/css" MEDIA="screen" />	 </head>
	 <body>
		 <table padding='0' align='left'> 
			<tr>
			<xsl:for-each select="dataset/header/col"> 
			   <th align="left" class="tdCol"> 
			      <xsl:value-of select="." /> 
			   </th> 
			</xsl:for-each>
			</tr>
			<xsl:for-each select="dataset/row">
			<tr> 
				<xsl:for-each select="col">
				<td> <xsl:value-of select="." /> </td>	
				</xsl:for-each>
			</tr> 
			</xsl:for-each> 
		 </table>	 
    </body> 
</html> 
</xsl:template> 
</xsl:stylesheet> 
