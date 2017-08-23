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
public class Alert {
	
	private String alertID;
	private String patientUsername;
	private int observationID;
	private int indicatorID;
	private Date dateIssued;
	private String description;
	
	/**
	 * @param alertID
	 * @param patientUsername
	 * @param dateIssued
	 * @param description
	 */
	public Alert(String alertID, String patientUsername, Date dateIssued, String description) {
		this.alertID = alertID;
		this.patientUsername = patientUsername;
		this.dateIssued = dateIssued;
		this.description = description;
	}

	/**
	 * @return the alertID
	 */
	public String getAlertID() {
		return alertID;
	}

	/**
	 * @param alertID the alertID to set
	 */
	public void setAlertID(String alertID) {
		this.alertID = alertID;
	}

	/**
	 * @return the patientUsername
	 */
	public String getPatientUsername() {
		return patientUsername;
	}

	/**
	 * @param patientUsername the patientUsername to set
	 */
	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}

	/**
	 * @return the dateIssued
	 */
	public Date getDateIssued() {
		return dateIssued;
	}

	/**
	 * @param dateIssued the dateIssued to set
	 */
	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
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
	 * @return the observationID
	 */
	public int getObservationID() {
		return observationID;
	}

	/**
	 * @param observationID the observationID to set
	 */
	public void setObservationID(int observationID) {
		this.observationID = observationID;
	}

	/**
	 * @return the indicatorID
	 */
	public int getIndicatorID() {
		return indicatorID;
	}

	/**
	 * @param indicatorID the indicatorID to set
	 */
	public void setIndicatorID(int indicatorID) {
		this.indicatorID = indicatorID;
	}
	
	

}
