package edu.jhu.sample.data;

import java.sql.*;

import edu.jhu.sample.results.ResultExpenseBean;
import edu.jhu.sample.results.ResultUserBean;


public class UserDB {
	
	public static String validateAccess(ResultUserBean user) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        //String query = "Call `expensity`.`validate_user`(?,?)";   //For Local Only
        String query = "Call `validate_user`(?,?)";					
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            
            //Initialize
            String usrName = "Unauthorized User";
            
            while (rs.next()) {
            	usrName = rs.getString(1);         
            	return usrName;             	
            }
            
            return usrName;
            
        } catch (SQLException e) {
            System.out.println(e);
            return e.getMessage();
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
	}
		
	public static int createUser(ResultUserBean user) {
		
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        //ResultSet rs = null;
        
        String query = "insert into expensity_user(username,email,password,usertypeid) "
        		+ "VALUES (?,?,?,1)"; 
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            int recordsAdded = ps.executeUpdate();
		    
		    return recordsAdded;  
		    
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            //DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
	}
	
    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT email FROM expensity_user "
                + "WHERE email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    /******** 
     --- SAMPLE INSERT ROW ---
    insert into expensity.userexpenses (userid, expensedescription, amount, date_created)
    values 
    (2,'Gas', '50.43', '2018-08-02'); 
	**/
    
    public static int addExpense(String email, ResultExpenseBean expense) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query 
		= "insert into expensity_userexpenses (userid, expensedescription, amount, date_created, comments) "
		+ "select UserId, '?','?','?','?'" 
		+ "from expensity_user "
		+ "where Email = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, expense.getDescription());
            ps.setString(2, expense.getAmount());
            ps.setString(3, expense.getDate());
            ps.setString(4, expense.getNote());
            ps.setString(5, email);
            int count_ps = ps.executeUpdate();
            
            return count_ps;
            
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
		    /*try {
			// ---- QUERY THE USER TABLE ----
		    ps = connection.prepareStatement(userInsertSQL);
		    ps.setString(1, user.getName());          
		    ps.setString(2, user.getEmail());
		    ps.setString(3, user.getEmail());
		    int count_ps = ps.executeUpdate();
		    
		  
		    int count = count_ps;
		    return count;           
		} catch (SQLException e) {
		    System.out.println(e);
		    return 0;
		}
		
		finally {
		    DBUtil.closePreparedStatement(ps);
		    pool.freeConnection(connection);
		}	
		return userAccessLevel;
		}
		*/
		
    	// NEED TO FIX THIS METHOD
	    public static boolean editExpense(String email) {
	        ConnectionPool pool = ConnectionPool.getInstance();
	        Connection connection = pool.getConnection();
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	
	        String query = "SELECT Email FROM Users "
	                + "WHERE Email = ?";
	        try {
	            ps = connection.prepareStatement(query);
	            ps.setString(1, email);
	            rs = ps.executeQuery();
	            return rs.next();
	        } catch (SQLException e) {
	            System.out.println(e);
	            return false;
	        } finally {
	            DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
	        }
	    }
    
    	// NEED TO FIX THIS METHOD
		public static int UpdateExpense(ResultUserBean user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		
		String userInsertSQL
		        = "INSERT INTO user(Name, Email, EmploymentStatus) "
		        + "VALUES (?, ?, ?)";
		
		String regInsertSQL 
				= "insert into registration (userid, hotelAccomodation, ParkingPermit, Totalcost) "
				+ "select UserId, ?,?,?" 
				+ "from user "
				+ "where Email = ?";
		
		String coursesInsertSQL 
				= "insert into usercourses (userid, coursename) "
				+ "select UserId, ?"
				+ "from user "
				+ "where Email = ?";                
		
		try {
			// ---- INSERT RECORD INTO USER TABLE ----
		    ps = connection.prepareStatement(userInsertSQL);
		    ps.setString(1, user.getName());          
		    ps.setString(2, user.getEmail());
		    ps.setString(3, user.getEmail());
		    int count_ps = ps.executeUpdate();
		    
		  
		    int count = count_ps;
		    return count;           
		} catch (SQLException e) {
		    System.out.println(e);
		    return 0;
		}
		
		finally {
		    DBUtil.closePreparedStatement(ps);
		    DBUtil.closePreparedStatement(ps1);
		    DBUtil.closePreparedStatement(ps2);
		    pool.freeConnection(connection);
		}
		}    
    
}