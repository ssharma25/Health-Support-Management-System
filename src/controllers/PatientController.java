package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Alert;
import model.DataBean;
import model.DataFactory;
import model.Diagnoses;
import model.HealthObservation;
import model.Instruction;
import model.Patient;

public class PatientController implements DataBean<Patient> {

	private Connection conn;
	
	public PatientController() throws ClassNotFoundException, SQLException {
		conn = DataFactory.getConnection();
	}
	
	
	public boolean add(Patient p) {
		int urows = 0;
		int prows = 0;
		try {
			PreparedStatement user = conn.prepareStatement("INSERT INTO USERS " +
							"(username, password) VALUES (?, ?)");
			user.setString(1,  p.getUsername());
			user.setString(2, p.getPassword());
			urows = user.executeUpdate();
			
			PreparedStatement patient = conn.prepareStatement("INSERT INTO PATIENT " +
						"(username, name, gender, dob, address, phoneNum) VALUES " +
						"(?, ?, ?, ?, ?, ?)");
			patient.setString(1, p.getUsername());
			patient.setString(2, p.getName());
			patient.setString(3, p.getGender());
			patient.setDate(4, p.getDob());
			patient.setString(5, p.getAddress());
			patient.setString(6, p.getPhoneNum());
			
			prows = patient.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}
		
		
		return (prows > 0) && (urows > 0);
	}

	@Override
	public boolean update(Patient p) {
		int urows = 0;
		int prows = 0;
		try {
			PreparedStatement patient = conn.prepareStatement("UPDATE PATIENT " +
					"SET name=?, gender=?, dob=?, address=?, phoneNum=? " +
						"WHERE username=?");
			patient.setString(6, p.getUsername());
			patient.setString(1, p.getName());
			patient.setString(2, p.getGender());
			patient.setDate(3, p.getDob());
			patient.setString(4, p.getAddress());
			patient.setString(5, p.getPhoneNum());
			
			prows = patient.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}
		
		
		return (prows > 0) && (urows > 0);
	}
	
	@Override
	public boolean delete(Patient p){
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM PATIENT WHERE username=?");
			ps.setString(1, p.getUsername());
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return rows > 0;
	}

	@Override
	public List<Patient> getByUser(Patient p) {
		List<Patient> list  = new ArrayList<Patient>();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM PATIENT WHERE username=?");
			ps.setString(1, p.getUsername());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Patient np = new Patient();
				np.setUsername(rs.getString("username"));
				np.setDob(rs.getDate("dob"));
				np.setAddress(rs.getString("address"));
				np.setGender(rs.getString("gender"));
				np.setName(rs.getString("name"));
				np.setPhoneNum(rs.getString("phoneNum"));
				
				list.add(np);
			}
			
		} catch (SQLException e) {}
		
		return list;
	}
	
	/** Manage Patient Diagnoses **/
	public boolean addDiagnoses(int id, String user, Date date) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO SICKPATIENT " +
					"(diagnosesID, username, diagnosesDate) VALUES (?, ?, ?)");
			ps.setInt(1, id);
			ps.setString(2, user);
			ps.setDate(3, date);
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return rows > 0;
	}
	
	public boolean removeDiagnoses(int id, String user) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM SICKPATIENT " +
					"WHERE diagnosesID=? AND username=?");
			ps.setInt(1, id);
			ps.setString(2, user);
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return rows > 0;
	}
	
	public List<Diagnoses> getPatientDiagnoses(String user) {
		List<Diagnoses> list = new ArrayList<Diagnoses>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT D.name, S.diagnosesDate " + 
					"FROM Diagnoses D, SICKPATIENT S WHERE D.diagnosesID = S.diagnosesID AND S.username=?");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Diagnoses d = new Diagnoses();
				d.setDate(rs.getDate("diagnosesDate"));
				d.setName(rs.getString("name"));
				list.add(d);
			}
			ps.setString(1, user);
		} catch (SQLException e) {}
		
		
		return list;
	}
	
	/** Manage Patient health observations **/
	public boolean addHealthObs(HealthObservation ho) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO HEALTHOBSERVATION " +
					"(observationID, name, observationTime, recordingTime, value) VALUES " +
					"(?, ?, ?, ?, ?)");
			ps.setInt(1, getObservationID());
			ps.setString(2, ho.getName());
			ps.setDate(3, ho.getObservationTime());
			ps.setDate(4, ho.getRecordingTime());
			ps.setDouble(5, ho.getValue());
			
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}	
		return rows > 0;
	}
	
	public boolean removeHealthObs(int id) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM HEALTHOBSERVATION " +
					"WHERE observationID=?");
			ps.setInt(1, id);
			
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}	
		return rows > 0;
	}
	
	/** Manage Patient alerts **/
	public boolean addAlert(Alert a) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ALERT "
					+ "(alertID, username, healthIndicator, healthObservation, issueDate, message) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1, getAlertID());
			ps.setString(2, a.getPatientUsername());
			ps.setInt(3, a.getIndicatorID());
			ps.setInt(4, a.getObservationID());
			ps.setDate(5, a.getDateIssued());
			ps.setString(6, a.getDescription());
			
			rows = ps.executeUpdate();
					
		} catch (SQLException e) {
			return false;
		}	
		return rows > 0;
	}
	
	public boolean removeAlert(int id) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM ALERT "
					+ "WHERE alertID=?");
			ps.setInt(1, id);
			rows = ps.executeUpdate();
					
		} catch (SQLException e) {
			return false;
		}	
		return rows > 0;
	}

	/** STUB I WILL IMPLEMENT SOON **/
	public boolean addInstruction(Instruction i) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO)");
		} catch (SQLException e) {
			return false;
		}
		
		
		return rows > 0;
	}

	
	
	public int getObservationID() {
		int id = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(observationID) as id FROM HEALTHOBSERVATION");
			ResultSet rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id") + 1; //Add one so it is the next id
		} catch (SQLException e) {
			return id;
		}
		return id;
	}
	
	public int getAlertID() {
		int id = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(alertID) as id FROM ALERT");
			ResultSet rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id") + 1; //Add one so it is the next id
		} catch (SQLException e) {
			return id;
		}
		return id;
	}
	
	public int getDiagnosesID() {
		int id = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(diagnosesID) as id FROM DIAGNOSES");
			ResultSet rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id") + 1; //Add one so it is the next id
		} catch (SQLException e) {
			return id;
		}
		return id;
	}
	

	
	
	
	/** RUN THIS PROGRAM TO TEST YOU CONNECTION 
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DataFactory.getConnection();
			
			System.out.println("Success");
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't find class: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Couldn't connect: " + e.getMessage());
		}
		
	} **/
	
	
	
}
