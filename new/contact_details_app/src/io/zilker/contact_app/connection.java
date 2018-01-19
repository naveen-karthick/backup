package io.zilker.contact_app;

import java.sql.*;

public class connection {

	
	
	public Connection con()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_app","root","ztech@123");
			
		}
	catch(Exception e)
		{
		
		}
		return con;
		
	}
}
