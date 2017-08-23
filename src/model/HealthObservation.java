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
public class HealthObservation {
	
	private Date observationTime;
	private Date recordingTime;
	private String patientUsername;
	private String name;
	private double value;
	
	public HealthObservation() {
	}

	/**
	 * @return the observationTime
	 */
	public Date getObservationTime() {
		return observationTime;
	}

	/**
	 * @param observationTime the observationTime to set
	 */
	public void setObservationTime(Date observationTime) {
		this.observationTime = observationTime;
	}

	/**
	 * @return the recordingTime
	 */
	public Date getRecordingTime() {
		return recordingTime;
	}

	/**
	 * @param recordingTime the recordingTime to set
	 */
	public void setRecordingTime(Date recordingTime) {
		this.recordingTime = recordingTime;
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
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	
}
