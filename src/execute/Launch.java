package execute;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import model.DataFactory;

public class Launch {

	public static void main(String[] args) throws IOException {
		

		Connection conn = null;
		try {
			conn = DataFactory.getConnection();
			Statement stmt = conn.createStatement();
			
			//Test if the tables have already been created
			stmt.executeQuery("SELECT * FROM USERS");
		} catch (SQLException e) {
			
			//If not create them
			try {
				createTables(conn);
			} catch (Exception e1) {
				e.printStackTrace();
				System.out.println(" " + e.getMessage());
				JOptionPane.showMessageDialog(null, "Couldn't create tables", "SQL ERROR", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Couldn't find oracle drivers", "SQL ERROR", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	private static void createTables(Connection conn) throws SQLException {
		
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("CREATE TABLE USERS " +
				"(username VARCHAR(50), password VARCHAR(50), PRIMARY KEY (username))");
		
		stmt.executeUpdate("CREATE TABLE PATIENT " + 
				"(username VARCHAR(50), name VARCHAR(100), gender VARCHAR(10), dob DATE," +
				"address VARCHAR(100), phoneNum VARCHAR(12), PRIMARY KEY (username)," +
				"FOREIGN KEY (username) REFERENCES USERS (username))");
		
		stmt.executeUpdate("CREATE TABLE DIAGNOSES " +
				"(diagnosesID INTEGER, name VARCHAR(100), PRIMARY KEY (diagnosesID), " +
				"CONSTRAINT UK_name UNIQUE (name))");
		
		stmt.executeUpdate("CREATE TABLE SICKPATIENT " +
				"(diagnosesID INTEGER, username VARCHAR(50), diagnosesDate DATE, " +
				"PRIMARY KEY (diagnosesID, username), FOREIGN KEY (diagnosesID) REFERENCES DIAGNOSES (diagnosesID), " +
				"FOREIGN KEY (username) REFERENCES USERS (username))");
		
		stmt.executeUpdate("CREATE TABLE SUPPORTSPATIENT " +
				"(pUsername VARCHAR(50), sUsername VARCHAR(50), authorizationDate DATE, type VARCHAR(10), " +
				"PRIMARY KEY (pUsername, sUsername), FOREIGN KEY (pUsername) REFERENCES USERS (username), " +
				"FOREIGN KEY (sUsername) REFERENCES USERS (username))");
		
		stmt.executeUpdate("CREATE TABLE INSTRUCTION " +
				"(instructionID INTEGER, description VARCHAR(4000), isMandatory VARCHAR(5), " +
				"PRIMARY KEY (instructionID))");
		
		stmt.executeUpdate("CREATE TABLE PATIENTINSTRUCTION " +
				"(username VARCHAR(50), instructionID INTEGER, PRIMARY KEY (username, instructionID), " +
				"FOREIGN KEY (username) REFERENCES USERS (username), " +
				"FOREIGN KEY (instructionID) REFERENCES INSTRUCTION (instructionID))");
		
		stmt.executeUpdate("CREATE TABLE HEALTHINDICATOR " +
				"(indicatorID INTEGER, diagnosesID INTEGER, name VARCHAR(100), lowerLimit FLOAT, " +
				"upperLimit FLOAT, visitFrequency INTEGER, description VARCHAR(1000), PRIMARY KEY (indicatorID), " +
				"CONSTRAINT UK_healthIndicator UNIQUE (diagnosesID, name), " +
				"FOREIGN KEY (diagnosesID) REFERENCES DIAGNOSES (diagnosesID))");
		
		stmt.executeUpdate("CREATE TABLE HEALTHOBSERVATION " +
				"(observationID INTEGER, username VARCHAR(50), name VARCHAR(100), observationTime DATE, " +
				"recordingTime DATE, healthValue NUMBER(6,2), PRIMARY KEY (observationID), " +
				"FOREIGN KEY (username) REFERENCES USERS (username))");
		
		stmt.executeUpdate("CREATE TABLE ALERT " +
				"(alertID INTEGER, username VARCHAR(50), healthIndicator INTEGER, healthObservation INTEGER, " +
				"issueDate DATE, message VARCHAR(4000), PRIMARY KEY (alertID), " +
				"FOREIGN KEY (healthIndicator) REFERENCES HEALTHINDICATOR (indicatorID), " +
				"FOREIGN KEY (healthObservation) REFERENCES HEALTHOBSERVATION (observationID), " +
				"FOREIGN KEY (username) REFERENCES USERS (username))");
		
		conn.close();
		
		
	}

}
