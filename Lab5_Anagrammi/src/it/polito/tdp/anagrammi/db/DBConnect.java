package it.polito.tdp.anagrammi.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root&password=root";
	private static Connection conn = null;

	public static Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			try {
				conn = DriverManager.getConnection(jdbcURL);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return conn;

	}

}
