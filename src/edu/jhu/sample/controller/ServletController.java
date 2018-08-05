package edu.jhu.sample.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.jhu.sample.results.ResultUserBean;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUserBean servlet = new ServletUserBean();
		
		String url = "/index.jsp";
        ServletContext sc = getServletContext();

        ResultUserBean user = new ResultUserBean();
        HttpSession session = request.getSession();
        String errorText = "";
        
        // get current action
        String action = request.getParameter("action");
        
        // perform action and set URL to appropriate page
        if (action.equals("Signup")) {
        	user = (ResultUserBean) session.getAttribute("user");
        	url = "/signup.jsp";
        } 
        else if (action.equals("Login")) {
        	user = (ResultUserBean) session.getAttribute("user");
            url = "/login.jsp";
        } 
        else if (action.equals("CreateUser")) {
        	user = servlet.processRequest(request);
        	String password1 = request.getParameter("password");
        	String password2 = request.getParameter("password-repeat");
        	if (!password1.isEmpty() && password1.equals(password2)) {
        		// TODO: Make DB entry call here, with user name, email, and p/w
        		url = "/home.jsp";
        	} else {
        		errorText = "Passwords did not match, try again";
        		user.setPassword("");
        		url = "/signup.jsp";
        	}
        }
        else if (action.equals("LoginUser")) {
        	// TODO: Make DB retrieval call here, with user email and p/w
        	user = servlet.processRequest(request);
        	if (user.getPassword().isEmpty()) {
        		errorText = "Invalid User Name or Password";
        		user.setPassword("");
        		url = "/login.jsp";
        	} else {
        		url = "/home.jsp";
        	}
        }
        else if (action.equals("Home")) {
        	url = "/home.jsp";
        }
        
        session.setAttribute("errorText", errorText);
        session.setAttribute("user", user);
        request.setAttribute("user", user);
        
		sc.getRequestDispatcher(url).forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
