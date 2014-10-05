package com.merjapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC {
	
	private Connection connection;
	
    static {
        loadDriver();
    }
	
    public SQLiteJDBC() {}
    
    public SQLiteJDBC(String databaseName) throws SQLException {
    	this();
    	connect(databaseName);
    }
    
    /**
	 * Checks whether the MySQL JDBC Driver is installed
	 */
	private static boolean loadDriver() {
		try {
			Class.forName("org.sqlite.JDBC");
			return true;
		} catch (java.lang.ClassNotFoundException e) {
			return false;
		}
	}

	public void connect(String databaseName) throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method executes an update statement
	 * 
	 * @param con
	 *            database connection
	 * @param sqlStatement
	 *            SQL DDL or DML statement to execute
	 */
	public void executeUpdate(String sqlStatement) throws SQLException {
		try {
			Statement s = connection.createStatement();
//			s.setQueryTimeout();
			s.execute(sqlStatement);
			s.close();
		} catch (SQLException e) {}
	}
	
	   /**
     * This method executes a select statement and displays the result
     * 
     * @param con
     *            database connection
     * @param sqlStatement
     *            SQL SELECT statement to execute
     */
    public ResultSet executeQuery(String sqlStatement) throws Exception {
    	Statement s = connection.createStatement();
//    	s.setQueryTimeout();
    	return s.executeQuery(sqlStatement);
    }
    
    public PreparedStatement prepareStatement(String query) throws SQLException {
    	return connection.prepareStatement(query);
    }
    
}
