package execute;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.DataFactory;

public class Reset {

	public static void main(String[] args) {

		Connection conn = null;
		try {
			conn = DataFactory.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
		try{
			stmt.executeUpdate("DROP TABLE PATIENT");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE SICKPATIENT");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE SUPPORTSPATIENT");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE PATIENTINSTRUCTION");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE INSTRUCTION");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE ALERT");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE HEALTHINDICATOR");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE HEALTHOBSERVATION");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE DIAGNOSES");
		}catch(SQLException e){System.out.println(e.getMessage());}
		
		try{
			stmt.executeUpdate("DROP TABLE USERS");
		}catch(SQLException e){System.out.println(e.getMessage());}
	
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
