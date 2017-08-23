package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DataBean;
import model.DataFactory;
import model.Patient;
import model.Supporter;

public class SupporterController implements DataBean<Supporter> {

	private Connection conn;
	
	public SupporterController() throws ClassNotFoundException, SQLException {
		conn = DataFactory.getConnection();
	}
	
	@Override
	public boolean add(Supporter s) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO SUPPORTSPATIENT " +
					"(pUsername, sUsername, authorizationDate) VALUES (?,?,?)");
			ps.setString(1, s.getPatient());
			ps.setString(2, s.getUsername());
			ps.setDate(3, s.getAuthorizationDate());
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
		return rows > 0;
	}

	@Override
	public boolean update(Supporter s) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE SUPPORTSPATIENT SET " +
					"type=? , authorizationDate=? WHERE pUsername=? AND sUsername=?");
			ps.setString(3, s.getPatient());
			ps.setString(4, s.getUsername());
			if (s.isPrimary()) {
				ps.setString(1, "Y");
			} else {
				ps.setString(1, "N");
			}

			
			ps.setDate(2, s.getAuthorizationDate()); 
			
			
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
		return rows > 0;
	}


	@Override
	public boolean delete(Supporter s) {
		int rows = 0;
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM SUPPORTSPATIENT " +
										"WHERE pUsername=? AND sUsername=?");
			ps.setString(1, s.getPatient());
			ps.setString(2, s.getUsername());
			
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
		return rows > 0;

		
	}

	@Override
	public List<Supporter> getByUser(Supporter s) {
		List<Supporter> list = new ArrayList<Supporter>();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM SUPPORTSPATIENT " +
									"WHERE pUsername=?");
			ps.setString(1, s.getPatient());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Supporter ns = new Supporter();
				ns.setAuthorizationDate(rs.getDate("authorizationDate"));
				ns.setUsername(rs.getString("sUsername"));
				
				list.add(ns);
			}
		} catch (SQLException e) {}
		
		return list;
	}
	
	public List<Patient> getAllPatients(Supporter s) {
		List<Patient> list = new ArrayList<Patient>();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM SUPPORTSPATIENT " +
									"WHERE sUsername=?");
			ps.setString(1, s.getUsername());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Patient ns = new Patient();
				ns.setUsername(rs.getString("pUsername"));
				
				list.add(ns);
			}
		} catch (SQLException e) {}
		
		return list;
	}

}
