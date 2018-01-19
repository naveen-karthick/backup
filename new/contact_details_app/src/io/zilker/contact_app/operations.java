package io.zilker.contact_app;
import java.sql.*;
public class operations {

	public void search(String f_name,String l_name,Connection con)
	{
		application ap=new application();
		
		try {
			ap.search_contact(f_name,l_name,con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
