/**
 * 
 */
package model;

import java.sql.Date;

/**
 * @author Wade Moore
 * @date   Oct 15, 2016
 *
 */
public class Supporter extends User {
	
	private Date authorizationDate;
	private String patient;
	private boolean isPrimary;
	
	public Supporter() {
		
	}

	/**
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param dob
	 */
	public Supporter(String username, String password, String name, Date dob) {
		super(username, password, name, dob);
	}

	/**
	 * @return the authorizationDate
	 */
	public Date getAuthorizationDate() {
		return authorizationDate;
	}

	/**
	 * @param authorizationDate the authorizationDate to set
	 */
	public void setAuthorizationDate(Date authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	/**
	 * @return the patients
	 */
	public String getPatient() {
		return patient;
	}

	/**
	 * @param patients the patients to set
	 */
	public void setPatient(String patients) {
		this.patient = patients;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

}
