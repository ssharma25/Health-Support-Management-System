/**
 * 
 */
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DataFactory;
import model.Diagnoses;

/**
 * @author Wade Moore
 * @date   Oct 23, 2016
 *
 */
public class DiagnosesController {
	
	private Connection conn;
	
	public DiagnosesController() throws ClassNotFoundException, SQLException {
		conn = DataFactory.getConnection();
	}


	public boolean add(Diagnoses d) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO DIAGNOSES " +
							"(diagnosesID, name) VALUES (?, ?)");
			ps.setInt(1, getID());
			ps.setString(2, d.getName());
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}
		return rows > 0;
	}


	public boolean update(Diagnoses d) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE DIAGNOSES SET " +
							"diagnosesID=? WHERE name=?");
			ps.setString(1, d.getDiagnosisID());
			ps.setString(2, d.getName());
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			return false;
		}
		return rows > 0;
	}

	public List<Diagnoses> getByUser(Diagnoses d) {
		List<Diagnoses> list = new ArrayList<Diagnoses>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM DIAGNOSES");
			ps.setString(1, d.getDiagnosisID());
			ps.setString(2, d.getName());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Diagnoses nd = new Diagnoses();
				nd.setDiagnosisID(String.valueOf(rs.getInt("diagnosesID")));
				nd.setName(rs.getString("name"));
				
				list.add(nd);
			}
			
		} catch (SQLException e) {}
		
		return list;
	}


	public boolean delete(String name) {
		int rows = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM DIAGNOSES WHERE name=?");
			ps.setString(1, name);
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return rows > 0;
	}

	public int getID() {
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
	
}
