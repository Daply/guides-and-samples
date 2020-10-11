package com.flowersshop.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
	
	private static String hostName = "localhost";
	private static String dbName = "flowersshop";
	private static String userName = "root";
	private static String password = "root";
	
	public static Connection getMySQLConnection()
	         throws ClassNotFoundException, SQLException {
	    return getMySQLConnection(hostName, dbName, userName, password);
	}
	  
	private static Connection getMySQLConnection(String hostName, String dbName,
	        String userName, String password) throws SQLException,
	        ClassNotFoundException {
	    
	    Class.forName("com.mysql.jdbc.Driver");
	    String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	    Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	    return conn;
	}
	
}
