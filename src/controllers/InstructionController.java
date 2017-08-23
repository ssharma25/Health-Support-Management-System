package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.DataBean;
import model.DataFactory;
import model.Instruction;

public class InstructionController implements DataBean<Instruction> 
{
	private Connection conn;
	
	public InstructionController() throws ClassNotFoundException, SQLException 
	{
		conn = DataFactory.getConnection();
	}


	public boolean add(Instruction i) 
	{
		int rows = 0;
		try 
		{
			PreparedStatement patient = conn.prepareStatement("INSERT INTO INSTRUCTION " +
				"(instructionID,description, isMandatory) VALUES " +
				"(?,?, ?)");
			patient.setInt(1, getID());
			patient.setString(2, i.getDescription());
			patient.setBoolean(3, i.isMandatory());
	
		} catch (SQLException e) {
			return false;
		}
		return rows > 0;
	}
	
	@Override
	public boolean update(Instruction updateObj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Instruction> getByUser(Instruction listObj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getID() {
		int id = -1;
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(instructionID) as id FROM INSTRUCTION");
			ResultSet rs = ps.executeQuery();
			rs.next();
			id = rs.getInt("id") + 1; //Add one so it is the next id
		} catch (SQLException e) {
			return id;
		}
		return id;
	}


	@Override
	public boolean delete(Instruction object) {
		// TODO Auto-generated method stub
		return false;
	}
		
	}