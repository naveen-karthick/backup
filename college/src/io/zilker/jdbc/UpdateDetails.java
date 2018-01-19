package io.zilker.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import io.zilker.bean.ReportDetails;
import io.zilker.utility.Constants;
import io.zilker.utility.GetConnection;

public class UpdateDetails {
	Connection con = null;
	GetConnection connection;
	Logger log;

	public boolean updateHodReport(ReportDetails report) {
		
		PreparedStatement statement=null;
		
		
		
		try {
			connection = new GetConnection();
			connection.createconnection();
			con = connection.getConnection();
		 statement = con.prepareStatement(Constants.HODREPORT);
			statement.setString(1, report.getDateOn());
			statement.setInt(2, report.getDepartment());
			statement.setInt(3, report.getStaffId());
			statement.setInt(4, report.getStatus());
			statement.setString(5, report.getUpdatedOn());
			statement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			connection.closeConnection(con,null,statement);
		}

	}

	public boolean updateReport(ReportDetails report) {
		
		
		PreparedStatement statement=null;
		
		
		try {
			connection = new GetConnection();
			connection.createconnection();
			con = connection.getConnection();
			 statement = con.prepareStatement(Constants.SUBMITREPORT);
			statement.setString(1, report.getDateOn());
			statement.setInt(2, report.getHourOfClass());
			statement.setInt(3, report.getStaffId());
			statement.setString(4, report.getWorkDone());
			statement.setString(5, report.getUpdatedOn());
			statement.setInt(6, report.getYearOfDepartment());
			statement.setInt(7, report.getDepartment());
			statement.executeUpdate();

			return true;

		} catch (MySQLIntegrityConstraintViolationException e) {
			log = Logger.getLogger(UpdateDetails.class.getName());
			log.warning("Duplicate report Entry please verify the details you have entered");
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warning("Data format doesn't match as specified please try again");
			e.printStackTrace();

			return false;
		} finally {
			
			connection.closeConnection(con,null,statement);
		}

	}
}
