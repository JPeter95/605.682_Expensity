package edu.jhu.sample.worker;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import edu.jhu.sample.results.ResultExpenseBean;
import edu.jhu.sample.results.ResultUserBean;

public class ServletUserBean {

	public ResultUserBean processRequest(HttpServletRequest request) throws RemoteException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		return new ResultUserBean(name, email, password);
	}
	
	public ResultExpenseBean processDetails(HttpServletRequest request) throws RemoteException {
		String description = request.getParameter("description");
		String amount = request.getParameter("amount");
		String date = request.getParameter("date");
		String note = request.getParameter("note");

		return new ResultExpenseBean(description, amount, date, note);
	}
	
}
