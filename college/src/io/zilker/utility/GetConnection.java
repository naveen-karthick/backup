package io.zilker.utility;

import java.sql.*;

public class GetConnection {
	Connection con = null;

	public void closeConnection(Connection con, ResultSet result, PreparedStatement statement) {
		try {
			con.close();
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createconnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_logbook", "root", "ztech@123");

		} catch (Exception e) {
			System.out.println("error");
		}

	}

	public Connection getConnection() {
		return con;
	}
}
