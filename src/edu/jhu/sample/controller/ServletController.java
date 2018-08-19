package edu.jhu.sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.sample.data.UserDB;
import edu.jhu.sample.results.ResultExpenseBean;
import edu.jhu.sample.results.ResultUserBean;
import edu.jhu.sample.util.MailUtilGmail;
import edu.jhu.sample.worker.ServletUserBean;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUserBean servlet = new ServletUserBean();

		String url = "/index.jsp";
		ServletContext sc = getServletContext();

		ResultUserBean user = new ResultUserBean();
		ResultExpenseBean expense = new ResultExpenseBean();
		HttpSession session = request.getSession();
		String errorText = "";

		// get current action
		String action = request.getParameter("action");

		// Retrieve action and set URL to appropriate page
		if (action.equals("Signup")) {
			user = (ResultUserBean) session.getAttribute("user");
			url = "/signup.jsp";
		} else if (action.equals("Login")) {
			url = "/login.jsp";
		} else if (action.equals("CreateUser")) {
			user = servlet.processRequest(request);
			String password1 = request.getParameter("password");
			String password2 = request.getParameter("password-repeat");
			if (!password1.isEmpty() && password1.equals(password2)) {
				boolean userExists = UserDB.emailExists(user.getEmail());
				if (!userExists) {
					int userAddedCheck = UserDB.createUser(user);
					if (userAddedCheck == 1) {
						sendEmailConfirmation(request, user);
						url = "/home.jsp";
					} else {
						errorText = "Something went wrong. Try again";
						url = "/signup.jsp";
					}
				} else {
					errorText = "User already exists. Try with different email.";
					user.setPassword("");
					url = "/signup.jsp";
				}
			} else {
				errorText = "Passwords did not match, try again";
				user.setPassword("");
				url = "/signup.jsp";
			}
			
		} else if (action.equals("LoginUser")) {
			// TODO: Make DB retrieval call here, with user email and p/w
			user = servlet.processRequest(request);
			if (user.getPassword().isEmpty()) {
				errorText = "Invalid User Name or Password";
				user.setPassword("");
				url = "/login.jsp";
			} else {
				url = "/home.jsp";
			}
			
		} else if (action.equals("Home")) {
			if (session.isNew()) {
        		url = "/index.jsp";
        	} else {
        		url = "/home.jsp";
        	}
			
		} else if (action.equals("AddExpense")) {
			url = "/add.jsp";
		} else if (action.equals("AddExpense!")) {
			if (session.isNew()) {
        		url = "/index.jsp";
        	} else {
        		user = (ResultUserBean) session.getAttribute("user");
				expense = servlet.processDetails(request);
				UserDB.addExpense(user.getEmail(), expense);
				url = "/home.jsp";
        	}
			
			// Navigate to Edit.jsp with given expense
		} else if (action.equals("EditPage")) {
			if (session.isNew()) {
        		url = "/index.jsp";
        	} else {
        		// TODO
        		//ResultExpenseBean expense_to_edit = request.getParameter("expense_to_edit");
        		//session.setAttribute("expense", expense_to_edit);
        		url = "/edit.jsp";
        	}
			
			// Edit Expense thru DB, and redirect back to Home.jsp
		} else if (action.equals("EditExpense")) {
			if (session.isNew()) {
        		url = "/index.jsp";
        	} else {
        		user = (ResultUserBean) session.getAttribute("user");
        		// TODO: Make DB edit call here, with user email and expense
        		//expense = servlet.processDetails(request);
        		//UserDB.editExpense(user.getEmail(), expense);
        		url = "/home.jsp";
        	}
			
		} else if (action.equals("DeleteExpense")) {
			if (session.isNew()) {
        		url = "/index.jsp";
        	} else {
				user = (ResultUserBean) session.getAttribute("user");
	    		// TODO: Make DB delete call here, with user email and expense id(?)
				//ResultExpenseBean expense_to_remove = request.getParameter("expense_to_remove");
				//UserDB.deleteExpense(user.getEmail(), expense_to_remove);
				url = "/home.jsp";
        	}
			
		} // Clear the Session and Logout the user
		else if (action.equals("Logout")) {
			session.setAttribute("user", null);
			url = "/index.jsp";
		}
		// Just Testing

		session.setAttribute("errorText", errorText);
		session.setAttribute("user", user);
		request.setAttribute("user", user);

		sc.getRequestDispatcher(url).forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void sendEmailConfirmation(HttpServletRequest request, ResultUserBean user) {
		// get parameters from the request
		String email = user.getEmail();
		String name = user.getName();

		// send email to user
		String to = email;
		String from = "jd4164136@gmail.com";
		String subject = "Welcome to our email list";
		String body = "Dear " + name + ",\n\n"
				+ "Thanks for joining our email list. We'll make sure to send "
				+ "you announcements about new products and promotions.\n"
				+ "Have a great day and thanks again!\n\n" + "John Doe\n" + "Expensity";
		boolean isBodyHTML = false;

		try {
			// MailUtilLocal.sendMail(to, from, subject, body, isBodyHTML);
			MailUtilGmail.sendMail(to, from, subject, body, isBodyHTML);
		} catch (MessagingException e) {
			String errorMessage = "ERROR: Unable to send email. " + "Check Tomcat logs for details.<br>"
					+ "NOTE: You may need to configure your system " + "as described in chapter 14.<br>"
					+ "ERROR MESSAGE: " + e.getMessage();
			request.setAttribute("errorMessage", errorMessage);
			this.log("Unable to send email. \n" + "Here is the email you tried to send: \n"
					+ "=====================================\n" + "TO: " + email + "\n" + "FROM: "
					+ from + "\n" + "SUBJECT: " + subject + "\n" + "\n" + body + "\n\n");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
