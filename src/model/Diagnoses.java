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
public class Diagnoses {
	
	private String diagnosisID;
	private String name;
	private String description;
	private Date date;
	
	
	/**
	 * @param diagnosisID
	 * @param name
	 * @param description
	 * @param instrucions
	 */
	public Diagnoses(String diagnosisID, String name, String description, Instruction[] instrucions) {
		this.diagnosisID = diagnosisID;
		this.name = name;
		this.description = description;
	}


	public Diagnoses() {
	}


	/**
	 * @return the diagnosisID
	 */
	public String getDiagnosisID() {
		return diagnosisID;
	}


	/**
	 * @param diagnosisID the diagnosisID to set
	 */
	public void setDiagnosisID(String diagnosisID) {
		this.diagnosisID = diagnosisID;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
