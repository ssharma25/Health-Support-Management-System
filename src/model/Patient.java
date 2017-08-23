/**
 * 
 */
package model;

import java.sql.Date;

/**
 * @author Wade Moore
 * @date   Oct 9, 2016
 *
 */
public class Patient extends User {
	
	private boolean isSick;
	private Diagnoses[] diagnoses;
	private String gender;
	private String address;
	private String phoneNum;
	
	
	public Patient() {
	}
	
	
	/**
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param dob
	 * @param isSick
	 * @param diagnoses
	 */
	public Patient(String username, String password, String name, Date dob, boolean isSick,
			Diagnoses[] diagnosis, String gender, String address, String phoneNum) {
		super(username, password, name, dob);
		this.isSick = isSick;
		this.diagnoses = diagnosis;
		this.gender = gender;
		this.address = address;
		this.phoneNum = phoneNum;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the isSick
	 */
	public boolean isSick() {
		return isSick;
	}


	/**
	 * @param isSick the isSick to set
	 */
	public void setSick(boolean isSick) {
		this.isSick = isSick;
	}


	/**
	 * @return the diagnoses
	 */
	public Diagnoses[] getDiagnosis() {
		return diagnoses;
	}


	/**
	 * @param diagnoses the diagnoses to set
	 */
	public void setDiagnosis(Diagnoses[] diagnosis) {
		this.diagnoses = diagnosis;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

}
