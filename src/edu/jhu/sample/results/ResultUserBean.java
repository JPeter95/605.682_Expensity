package edu.jhu.sample.results;

import java.io.Serializable;

public class ResultUserBean implements Serializable {

	String name;
	String email;
	String password;

	public ResultUserBean() {
		this.name = "";
		this.email = "";
		this.password = "";
	}

	public ResultUserBean(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
