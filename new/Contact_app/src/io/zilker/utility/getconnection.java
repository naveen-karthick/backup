package io.zilker.utility;


import java.sql.*;

public class getconnection {
	Connection con=null;
	
	
	public void createconnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_app","root","ztech@123");
			
		}
	catch(Exception e)
		{
			System.out.println("error");
		}
		
	}
	public Connection con()
	{
		return con;
	}
}

