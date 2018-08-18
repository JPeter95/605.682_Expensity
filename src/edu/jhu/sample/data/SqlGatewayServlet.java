package edu.jhu.sample.data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.sample.results.ResultUserBean;
import edu.jhu.sample.data.UserDB;
import edu.jhu.sample.data.SQLUtil;

/**
 * Servlet implementation class SqlGatewayServlet
 */
@WebServlet("/SqlGatewayServlet")
public class SqlGatewayServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
		
		String url = "";
		String userInfoSQLResult = "";
        String coursesSQLResult = "";
        String regSQLResult = "";
        
        HttpSession session = request.getSession();
        ResultUserBean user = (ResultUserBean) session.getAttribute("user");
        
       	
	        try {
	        	/*
	        	// get a connection        	
	        	Class.forName("com.mysql.jdbc.Driver"); 
	        	String dbURL = "jdbc:mysql://localhost:3306/jhu";
	            String username = "root";
	            String password = "root"; 
	                	
				Connection connection = DriverManager.getConnection(
	                    dbURL, username, password);
	            */
					            
	            Class.forName("com.mysql.jdbc.Driver");
	            String dbURL = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9248779";
	            String username = "sql9248779";
	            String password = "iMIIePUq6P";
	            Connection connection = DriverManager.getConnection(
	                    dbURL, username, password);			
				
				
				// create a statement
	            Statement statement = connection.createStatement();
				
				// get current action
		        String action = request.getParameter("action");
		        
		        //System.out.println("before" + action);		       
				if (action.equals("showData")) {	  			
					ResultSet getDemoData
                    = statement.executeQuery("Call sp_showExpenses('"+ user.getEmail() +"')");
	                regSQLResult = SQLUtil.getHtmlTable(getDemoData);		                
	                getDemoData.close();
	                
				}		
	        } catch (ClassNotFoundException e) {
	        	coursesSQLResult = "<p>Error loading the databse driver: <br>"
	                    + e.getMessage() + "</p>";
	        } catch (SQLException e) {
	        	coursesSQLResult = "<p>Error executing the SQL statement: <br>"
	                    + e.getMessage() + "</p>";
	        }
        //}
        
        /*session.setAttribute("userInfoSQLResult", userInfoSQLResult);
        session.setAttribute("coursesSQLResult", coursesSQLResult);*/       
        session.setAttribute("regSQLResult", regSQLResult);
        
        
        
        url = "/home.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}