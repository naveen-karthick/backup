package io.zilker.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import io.zilker.bean.ReportDetails;
import io.zilker.bean.StaffDetails;
import io.zilker.utility.Constants;
import io.zilker.utility.GetConnection;

public class FetchDetails {
	Connection con = null;
	GetConnection connection;

	public Map<Integer, ReportDetails> fetchHodReport(ReportDetails report) {

		connection.createconnection();
		con = connection.getConnection();

		Map<Integer, ReportDetails> reports = new HashMap<Integer, ReportDetails>();
		PreparedStatement statement=null;
		ResultSet hodReport=null;
		

		try {
			 statement = con.prepareStatement(Constants.FETCHHODREPORT);
			for (int dept = 1; dept <= 6; dept++) {
				statement.setInt(1, dept);
				statement.setString(2, report.getDateOn());
				 hodReport = statement.executeQuery();
				if (hodReport.next()) {
					ReportDetails sampleReport = new ReportDetails();
					sampleReport.setDepartmentName(hodReport.getString(1));
					sampleReport.setStatus(hodReport.getInt(2));
					sampleReport.setUpdatedOn(hodReport.getString(3));
					reports.put(dept, sampleReport);
				} else {
					reports.put(dept, null);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection(con,hodReport,statement);
		}

		return reports;
	}

	public StaffDetails getStaffDetails(int staffId) {
		StaffDetails staff = new StaffDetails();
		staff.setStaffId(staffId);
		connection = new GetConnection();
		connection.createconnection();
		con = connection.getConnection();
		PreparedStatement statement=null;
		ResultSet result=null;
		
		try {
			 statement = con.prepareStatement(Constants.STAFF);

			statement.setInt(1, staffId);
			 result = statement.executeQuery();
			result.next();
			staff.setDeptId(result.getInt(3));
			staff.setStaffName(result.getString(1));
			staff.setDesigId(result.getInt(2));
			staff.setDepartment(result.getString(4));
			staff.setDesignation(result.getString(5));
			return staff;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection(con,result,statement);
		}
		return staff;
	}

	public Map<Integer, ReportDetails> fetchReport(ReportDetails report) {
		Map<Integer, ReportDetails> reports = new HashMap<Integer, ReportDetails>();
		connection.createconnection();
		con = connection.getConnection();
		PreparedStatement statement=null;
		ResultSet reportDetails=null;
		
		try {

			for (int hour = 1; hour <= 7; hour++) {
				 statement = con.prepareStatement(Constants.FETCHREPORT);
				statement.setString(1, report.getDateOn());
				statement.setInt(2, hour);
				statement.setInt(3, report.getYearOfDepartment());
				statement.setInt(4, report.getDepartment());
				 reportDetails = statement.executeQuery();
				if (reportDetails.next()) {
					ReportDetails sampleReport = new ReportDetails();
					sampleReport.setStaffId(reportDetails.getInt(1));
					sampleReport.setStaffName(reportDetails.getString(2));
					sampleReport.setWorkDone(reportDetails.getString(3));
					sampleReport.setUpdatedOn(reportDetails.getString(4));
					reports.put(hour, sampleReport);
				} else {
					reports.put(hour, null);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection.closeConnection(con,reportDetails,statement);
		}

		return reports;
	}

}
