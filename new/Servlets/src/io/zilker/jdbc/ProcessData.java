package io.zilker.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import io.zilker.bean.PersonDetails;
import io.zilker.utility.GetConnection;

public class ProcessData {
	Connection con=null;
	GetConnection connection;
	
	public boolean updateDetails(HttpServletRequest request) {
		connection=new GetConnection();
		connection.createconnection();
		con=connection.getConnection();
		PreparedStatement statement=null;
		PersonDetails person=new PersonDetails();
		person.setFname(request.getParameter("fname"));
		person.setLname(request.getParameter("lname"));
		person.setAge(request.getParameter("age"));
		person.setCity(request.getParameter("city"));
		person.setGender(request.getParameter("gender"));
		Validation validate=new Validation();
		if(!validate.checkDate(request.getParameter("birthdate"), person)) {
			return false;
		}
		person.setBirthDate( request.getParameter("birthdate"));
		person.setStreet( request.getParameter("street"));
		if(!validate.checkSalary(request.getParameter("salary"),person)) {
			return false;
		}
		try {
			statement=con.prepareStatement("INSERT INTO `servlet`.`form_details` (`fname`, `age`, `last_name`, `street`, `city`, `sex`, `birth_date`, `salary`) VALUES (?,?,?,?,?,?,?,?) ");
			statement.setString(1,person.getFname());
			statement.setString(2,person.getAge());
			statement.setString(3,person.getLname());
			statement.setString(4,person.getStreet());
			statement.setString(5,person.getCity());
			statement.setString(6,person.getGender());
			statement.setString(7,person.getBirthDate());
			statement.setString(8,Integer.toString(person.getSalary()));
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		  finally {
			  try {
				  if(con!=null) {
					  con.close();  
				  }
				  
				
				if(statement!=null) {
					statement.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  }
		
		return true;
	}
}
