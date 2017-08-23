package model;

import java.sql.Date;

public abstract class User {

	private String username;
	private String password;
	private String name;
	private Date dob;

	
	public User() {
		
	}

	/**
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param dob
	 */
	public User(String username, String password, String name, Date dob) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.dob = dob;
	}
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}


	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
