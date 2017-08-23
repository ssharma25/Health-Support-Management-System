/**
 * 
 */
package model;

/**
 * @author Wade Moore
 * @date   Oct 15, 2016
 *
 */
public class Instruction {
	
	private String instructionID;
	private String description;
	private boolean mandatory;
	
	
	/**
	 * @param instructionID
	 * @param description
	 * @param mandatory
	 */
	public Instruction(String instructionID, String description, boolean mandatory) {
		this.instructionID = instructionID;
		this.description = description;
		this.mandatory = mandatory;
	}


	/**
	 * @return the instructionID
	 */
	public String getInstructionID() {
		return instructionID;
	}


	/**
	 * @param instructionID the instructionID to set
	 */
	public void setInstructionID(String instructionID) {
		this.instructionID = instructionID;
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
	 * @return the mandatory
	 */
	public boolean isMandatory() {
		return mandatory;
	}


	/**
	 * @param mandatory the mandatory to set
	 */
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	

}
