package edu.jhu.sample.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.sample.worker.ServletProcessorBean;
import edu.jhu.sample.results.ResultUserBean;
import edu.jhu.sample.results.ResultExpenseBean;
import edu.jhu.sample.util.CommandsDB;

/**
 * Servlet implementation class SqlGateway
 */
@WebServlet("/SqlGateway")
public class SqlGateway extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection = null;
	String url = "/index.jsp";
	String errorText = "";

	static String dbURL = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9234377";
	static String username = "sql9234377";
	static String password = "cGaUbHFeLP";

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded successfully.");
			connection = DriverManager.getConnection(dbURL, username, password);
			System.out.println("Connection Successful.");

		} catch (ClassNotFoundException e) {
			url = "/index.jsp";
			errorText = "Error loading the database driver: " + e.getMessage();
		} catch (SQLException e) {
			url = "/index.jsp";
			errorText = "Error executing the SQL statement: <br>" + e.getMessage();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext sc = getServletContext();
		ServletProcessorBean servlet = new ServletProcessorBean();
		
		ResultUserBean user = new ResultUserBean();
		List<ResultExpenseBean> expenses = new ArrayList<>();
		HttpSession session = request.getSession();
		errorText = "";

		// get current action
		String action = request.getParameter("action");

		if (session.isNew()) {
			url = "/index.jsp";
		} else {

			try {
				// Create new user
				if (action.equals("CreateUser")) {
					url = "/signup.jsp";
					user = servlet.processUser(request);
					String password1 = request.getParameter("password");
					String password2 = request.getParameter("password-repeat");
					if (!password1.isEmpty() && password1.equals(password2)) {
						Class.forName("com.mysql.jdbc.Driver");
						connection = DriverManager.getConnection(dbURL, username, password);
						try {
							if (!CommandsDB.emailExists(connection, user.getEmail())) {
									CommandsDB.insertUser(connection, user);
									// sendEmailConfirmation(request, user);
									url = "/home.jsp";
							} else {
								errorText = "User already exists. Try with different email.";
								user.setPassword("");
							}
						} catch(Exception e) {
							errorText = "Something went wrong! Unable to create user.";
						}
					} else {
						errorText = "Passwords did not match, try again";
						user.setPassword("");
					}
				}
				
				// Login a user
				else if (action.equals("LoginUser")) {
					url = "/login.jsp";
					user = servlet.processUser(request);
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(dbURL, username, password);
					try {
						user = CommandsDB.selectUser(connection, user);
						if (!user.getEmail().isEmpty() && !user.getName().isEmpty()) {
							expenses = CommandsDB.retrieveExpenses(connection, user.getEmail());
							url = "/home.jsp";
						} else {
							errorText = "Invalid Email or Password.";
							user.setPassword("");
						}
					} catch(Exception e) {
						errorText = "Something went wrong! Unable to login user.";
					}
				}
				
				// Add an expense
				else if (action.equals("AddExpense")) {
					url = "/add.jsp";
	        		user = (ResultUserBean) session.getAttribute("user");
					ResultExpenseBean expense = servlet.processExpense(request);
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(dbURL, username, password);
					try {
						CommandsDB.insertExpense(connection, user.getEmail(), expense);
						expenses = CommandsDB.retrieveExpenses(connection, user.getEmail());
						url = "/home.jsp";
					} catch(Exception e) {
						errorText = "Something went wrong! Unable to add expense.";
					}
				}
				
				// Remove expense
				else if (action.equals("RemoveExpense")) {
					url = "/home.jsp";
					user = (ResultUserBean) session.getAttribute("user");
	        		Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(dbURL, username, password);
					try {
						Integer expenseID = Integer.parseInt(request.getParameter("expense_to_remove"));
						CommandsDB.deleteExpense(connection, expenseID);
						expenses = CommandsDB.retrieveExpenses(connection, user.getEmail());
					} catch(Exception e) {
						errorText = "Something went wrong! Unable to remove expense.";
					}
				}
				
				// Edit expense page
				else if (action.equals("EditPage")) {
					url = "/home.jsp";
					user = (ResultUserBean) session.getAttribute("user");
	        		Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(dbURL, username, password);
					try {
						Integer expenseID = Integer.parseInt(request.getParameter("expense_to_edit"));
						ResultExpenseBean expense = CommandsDB.selectExpense(connection, expenseID);
		        		session.setAttribute("expense", expense);
		        		request.setAttribute("expense", expense);
		        		url = "/edit.jsp";
					} catch(Exception e) {
						errorText = "Something went wrong! Unable to load expense to edit.";
					}
				}
				
				// Make edit
				else if (action.equals("EditExpense")) {
					url = "/edit.jsp";
					user = (ResultUserBean) session.getAttribute("user");
					ResultExpenseBean expense = servlet.processExpense(request);
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(dbURL, username, password);
					try {
						Integer expenseID = ((ResultExpenseBean) session.getAttribute("expense")).getId();
						CommandsDB.editExpense(connection, expenseID, expense);
						expenses = CommandsDB.retrieveExpenses(connection, user.getEmail());
		        		url = "/home.jsp";
					} catch(Exception e) {
						errorText = "Something went wrong! Unable to load expense to edit.";
					}
				}
				
				// Home (Expenses) Page
				else if (action.equals("Home")) {
					url = "/home.jsp";
					user = (ResultUserBean) session.getAttribute("user");
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection(dbURL, username, password);
					try {
						expenses = CommandsDB.retrieveExpenses(connection, user.getEmail());
					} catch(Exception e) {
						errorText = "Something went wrong! Unable to load expense list.";
					}
				}
				
				// Logout the user
				else if (action.equals("Logout")) {
					if (!session.isNew()) {
						session.setAttribute("errorText", null);
						session.setAttribute("user", null);
						session.setAttribute("expenses", null);
						session.setAttribute("expense", null);
					}
					url = "/index.jsp";
				}
				
				
			}
			// ----------------- Error Catching -----------------
			catch (ClassNotFoundException e) {
				errorText = "Error loading the database driver: <br>" + e.getMessage();
			} catch (SQLException e) {
				errorText = "Error executing the SQL statement: <br>" + e.getMessage();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					errorText = "Error closing database connection";
				}
			}
		}

		session.setAttribute("errorText", errorText);
		session.setAttribute("user", user);
		request.setAttribute("user", user);
		request.setAttribute("expenses", expenses);
	
		request.getRequestDispatcher(url).forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}