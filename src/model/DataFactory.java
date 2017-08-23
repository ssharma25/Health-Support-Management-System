package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataFactory {
	
	private static final String jdbcURL 
	= "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
    private static final String user = "wpmoore2";
    private static final String passwd = "200058226";
    static Connection conn;
    
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	if(conn == null){ 
		conn = DriverManager.getConnection(jdbcURL, user, passwd);
    	}
		return conn;
    }

}
