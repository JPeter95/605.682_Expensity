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
 * Servlet implementation class SslController
 */
@WebServlet("/SslController")
public class SslController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SslController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUserBean servlet = new ServletUserBean();
		
		String url = "/index.jsp";
        ServletContext sc = getServletContext();
        String requestUrl = request.getRequestURL().toString();

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
        
        session.setAttribute("errorText", errorText);
        session.setAttribute("user", user);
        request.setAttribute("user", user);
        
        if (url.equals("/signup.jsp") || url.equals("/login.jsp")) {
        	requestUrl = requestUrl.replace("http:", "https:");
        	requestUrl = requestUrl.replace("8080", "8443");
        	requestUrl = requestUrl.replace("/SslController", url);
        	response.sendRedirect(requestUrl);
        } else {
			sc.getRequestDispatcher(url).forward(request, response);
			response.getWriter().append("Served at: ").append(request.getContextPath());
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
