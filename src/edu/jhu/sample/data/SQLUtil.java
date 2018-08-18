package edu.jhu.sample.data;

import java.sql.*;
//import email.SqlGatewayServlet;

public class SQLUtil {
	
	public static String getUserInfoTable(ResultSet results)
            throws SQLException {
        
		StringBuilder htmlTable = new StringBuilder();
               
        if(results.next()) {
        htmlTable.append("<p>Hello, <b>");
        htmlTable.append(results.getString(1));
        htmlTable.append("!</b></p>");
        
        htmlTable.append("<p>You are registered as a <b>");
        htmlTable.append(results.getString(2));
        htmlTable.append("</b>.</p>");
        
        htmlTable.append("<p>Your email will be sent to: <b>");
        htmlTable.append(results.getString(3));
        htmlTable.append("</b>.</p>");
        }     
        
        return htmlTable.toString();	
   }
	
    public static String getHtmlTable(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();        
        /*        
 		int rowCount = 0;
        while(results.next()) {
        	rowCount++;
        } 
        
        if (rowCount <= 0) {
        	htmlTable.append("<p> No Expenses Found. Click 'Add' to continue.</p>");
        	return htmlTable.toString();
        }
        else {
        */      
        htmlTable.append("<table>");

        // add header row
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th class=\"col_header1\">");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>");

        // add all other rows
        while (results.next()) {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>");
                htmlTable.append(results.getString(i));
                htmlTable.append("</td>");
            }
            htmlTable.append("</tr>");
        }

        htmlTable.append("</table>");
        return htmlTable.toString();
        //}
    }
    
	public static String getRegInfoTable(ResultSet results)
            throws SQLException {
		
        StringBuilder htmlTable = new StringBuilder();

        htmlTable.append("<table>");
	           
        while (results.next()) {
        htmlTable.append("<tr>");
        htmlTable.append("<td>Description</td>");
        htmlTable.append("<td>");
        htmlTable.append(results.getString(2));
        htmlTable.append("</td>");
        
        htmlTable.append("</tr>");
        }     
        
        htmlTable.append("</table>");        
        return htmlTable.toString();	
   }
}