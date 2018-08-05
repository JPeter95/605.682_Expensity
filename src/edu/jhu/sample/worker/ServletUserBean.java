package edu.jhu.sample.worker;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import edu.jhu.sample.results.ResultUserBean;

public class ServletUserBean {

	public ResultUserBean processRequest(HttpServletRequest request) throws RemoteException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		return new ResultUserBean(name, email, password);
	}
	
	
	
}
